package dsq.thedroid.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DefaultDbLifecycle implements DbLifecycle {
    private DatabaseHelper helper;

    public SQLiteDatabase open(Context context, Db settings) throws SQLException {
        helper = new DatabaseHelper(context, settings);
        return helper.getWritableDatabase();
    }

    public void close() {
        helper.close();
    }
}
