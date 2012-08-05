package dsq.thedroid.db;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public interface DbAccessWrite {
    long create(SQLiteDatabase db, String tableName, ContentValues values);

    boolean update(SQLiteDatabase db, String tableName, long id, ContentValues values);
    boolean updateAll(SQLiteDatabase db, String tableName, ContentValues values);

    // FIX 23/01/12 Maybe consider edging stuff.
    boolean delete(SQLiteDatabase db, String tableName, String column, String value);
    boolean deleteById(SQLiteDatabase db, String tableName, long id);
}
