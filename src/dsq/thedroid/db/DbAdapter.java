package dsq.thedroid.db;

import android.database.Cursor;
import android.database.SQLException;

public interface DbAdapter {
    boolean deleteById(long id);
    Cursor fetchAll();
    Cursor fetchById(long id) throws SQLException;
}
