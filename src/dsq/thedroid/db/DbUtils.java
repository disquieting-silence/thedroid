package dsq.thedroid.db;

import android.database.Cursor;

public class DbUtils {
    public static String getColumn(Cursor cursor, String column) {
        int index = cursor.getColumnIndexOrThrow(column);
        return cursor.getString(index);
    }
}
