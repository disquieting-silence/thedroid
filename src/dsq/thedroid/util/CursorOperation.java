package dsq.thedroid.util;

import android.database.Cursor;

public interface CursorOperation {
    void go(long id, Cursor cursor);
}
