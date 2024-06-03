// JogadorDao.java
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JogadorDao implements ICRUDDao<Jogador> {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public JogadorDao(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    private void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    private void close() {
        dbHelper.close();
    }

    @Override
    public void insert(Jogador jogador) throws SQLException {
        open();
        ContentValues values = new ContentValues();
        values.put("nome", jogador.getNome());
        values.put("data_nasc", jogador.getDataNasc().toString());
        values.put("altura", jogador.getAltura());
        values.put("peso", jogador.getPeso());
        values.put("TimeCodigo", jogador.getTime().getCodigo());
        db.insert("Jogador", null, values);
        close();
    }

    @Override
    public void update(Jogador jogador) throws SQLException {
        open();
        ContentValues values = new ContentValues();
        values.put("nome", jogador.getNome());
        values.put("data_nasc", jogador.getDataNasc().toString());
        values.put("altura", jogador.getAltura());
        values.put("peso", jogador.getPeso());
        values.put("TimeCodigo", jogador.getTime().getCodigo());
        db.update("Jogador", values, "id = ?", new String[]{String.valueOf(jogador.getId())});
        close();
    }

    @Override
    public void delete(Jogador jogador) throws SQLException {
        open();
        db.delete("Jogador", "id = ?", new String[]{String.valueOf(jogador.getId())});
        close();
    }

    @Override
    public Jogador findOne(int id) throws SQLException {
        open();
        Cursor cursor = db.query("Jogador", null, "id = ?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            Jogador jogador = new Jogador();
            jogador.setId(cursor.getInt(cursor.getColumnIndex("id")));
            jogador.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            jogador.setDataNasc(LocalDate.parse(cursor.getString(cursor.getColumnIndex("data_nasc"))));
            jogador.setAltura(cursor.getFloat(cursor.getColumnIndex("altura")));
            jogador.setPeso(cursor.getFloat(cursor.getColumnIndex("peso")));

            int timeCodigo = cursor.getInt(cursor.getColumnIndex("TimeCodigo"));
            TimeDao timeDao = new TimeDao(dbHelper.getContext());
            Time time = timeDao.findOne(timeCodigo);
            jogador.setTime(time);

            cursor.close();
            close();
            return jogador;
        }
        close();
        return null;
    }

    @Override
    public List<Jogador> findAll() throws SQLException {
        open();
        List<Jogador> jogadores = new ArrayList<>();
        Cursor cursor = db.query("Jogador", null, null, null, null, null, "nome");
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Jogador jogador = new Jogador();
                jogador.setId(cursor.getInt(cursor.getColumnIndex("id")));
                jogador.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                jogador.setDataNasc(LocalDate.parse(cursor.getString(cursor.getColumnIndex("data_nasc"))));
                jogador.setAltura(cursor.getFloat(cursor.getColumnIndex("altura")));
                jogador.setPeso(cursor.getFloat(cursor.getColumnIndex("peso")));

                int timeCodigo = cursor.getInt(cursor.getColumnIndex("TimeCodigo"));
                TimeDao timeDao = new TimeDao(dbHelper.getContext());
                Time time = timeDao.findOne(timeCodigo);
                jogador.setTime(time);

                jogadores.add(jogador);
            } while (cursor.moveToNext());
            cursor.close();
        }
        close();
        return jogadores;
    }
}
