// TimeDao.java
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TimeDao implements ICRUDDao<Time> {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public TimeDao(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    private void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    private void close() {
        dbHelper.close();
    }

    @Override
    public void insert(Time time) throws SQLException {
        open();
        ContentValues values = new ContentValues();
        values.put("codigo", time.getCodigo());
        values.put("nome", time.getNome());
        values.put("cidade", time.getCidade());
        db.insert("Time", null, values);
        close();
    }

    @Override
    public void update(Time time) throws SQLException {
        open();
        ContentValues values = new ContentValues();
        values.put("nome", time.getNome());
        values.put("cidade", time.getCidade());
        db.update("Time", values, "codigo = ?", new String[]{String.valueOf(time.getCodigo())});
        close();
    }

    @Override
    public void delete(Time time) throws SQLException {
        open();
        db.delete("Time", "codigo = ?", new String[]{String.valueOf(time.getCodigo())});
        close();
    }

    @Override
    public Time findOne(int id) throws SQLException {
        open();
        Cursor cursor = db.query("Time", null, "codigo = ?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            Time time = new Time();
            time.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
            time.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            time.setCidade(cursor.getString(cursor.getColumnIndex("cidade")));
            cursor.close();
            close();
            return time;
        }
        close();
        return null;
    }

    @Override
    public List<Time> findAll() throws SQLException {
        open();
        List<Time> times = new ArrayList<>();
        Cursor cursor = db.query("Time", null, null, null, null, null, "nome");
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Time time = new Time();
                time.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
                time.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                time.setCidade(cursor.getString(cursor.getColumnIndex("cidade")));
                times.add(time);
            } while (cursor.moveToNext());
            cursor.close();
        }
        close();
        return times;
    }
}
