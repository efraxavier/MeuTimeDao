// TimeController.java
import android.content.Context;
import java.sql.SQLException;
import java.util.List;

public class TimeController {
    private TimeDao timeDao;

    public TimeController(Context context) {
        timeDao = new TimeDao(context);
    }

    public void addTime(Time time) throws SQLException {
        timeDao.insert(time);
    }

    public void updateTime(Time time) throws SQLException {
        timeDao.update(time);
    }

    public void deleteTime(Time time) throws SQLException {
        timeDao.delete(time);
    }

    public Time getTime(int id) throws SQLException {
        return timeDao.findOne(id);
    }

    public List<Time> getAllTimes() throws SQLException {
        return timeDao.findAll();
    }
}
