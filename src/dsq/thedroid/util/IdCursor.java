package dsq.thedroid.util;

import android.app.Activity;
import dsq.thedroid.db.DbAdapter;

public interface IdCursor {
    void go(DbAdapter adapter, Activity activity, long id, CursorOperation operation);
}
