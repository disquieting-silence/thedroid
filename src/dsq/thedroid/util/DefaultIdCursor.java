package dsq.thedroid.util;

import android.app.Activity;
import android.database.Cursor;
import dsq.thedroid.db.DbAdapter;


public class DefaultIdCursor implements IdCursor {
    public void go(DbAdapter adapter, Activity activity, long id, CursorOperation operation) {
        Cursor cursor = adapter.fetchById(id);
        activity.startManagingCursor(cursor);
        operation.go(id, cursor);
        cursor.close();
    }
}
