package dsq.thedroid.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public interface DbLifecycle {
    SQLiteDatabase open(Context context, Db settings) throws SQLException;
    void close();
}
