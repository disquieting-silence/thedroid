package dsq.thedroid.db;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class DefaultDbAccessWrite implements DbAccessWrite {

    private static final String NO_ID = null;

    // FIX 26/01/12 This NO_ID parameter is not doing what I think it is doing. It has nothing to do with ID.
    public long create(SQLiteDatabase db, String tableName, ContentValues values) {
        return db.insert(tableName, NO_ID, values);
    }

    public boolean update(SQLiteDatabase db, String tableName, long id, ContentValues values) {
        return db.update(tableName, values, "_id = ?", new String[]{"" + id}) > 0;
    }

    public boolean updateAll(SQLiteDatabase db, String tableName, ContentValues values) {
        return db.update(tableName, values, "", new String[0]) > 0;
    }

    public boolean delete(SQLiteDatabase db, String tableName, String column, String value) {
        return db.delete(tableName, column + " = ?", new String[] { value }) > 0;
    }

    public boolean deleteById(SQLiteDatabase db, String tableName, long id) {
        return db.delete(tableName, "_id = ?", new String[]{"" + id}) > 0;
    }
}
