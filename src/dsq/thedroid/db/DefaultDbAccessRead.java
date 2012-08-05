package dsq.thedroid.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DefaultDbAccessRead implements DbAccessRead {

    private static final String NO_SELECTION = null;
    private static final String[] NO_SELECTION_ARGS = null;
    private static final String NO_GROUP_BY = null;
    private static final String NO_HAVING = null;
    private static final String NO_ORDER_BY = null;
    private static final String NO_LIMIT = null;

    public Cursor query(SQLiteDatabase db, String sql, String[] bindings) {
        return db.rawQuery(sql, bindings);
    }

    public Cursor fetch(SQLiteDatabase db, String tableName, String[] columns, String whereClause, String[] whereArgs) {
        return db.query(true, tableName, columns, whereClause, whereArgs, NO_GROUP_BY, NO_HAVING, NO_ORDER_BY, NO_LIMIT);
    }

    public Cursor fetchById(SQLiteDatabase db, String tableName, String[] columns, long id) {
        Cursor cursor = fetch(db, tableName, columns, "_id = ?", new String[] { "" + id });
        if (cursor != null) cursor.moveToFirst();
        return cursor;
    }

    public Cursor fetchAll(SQLiteDatabase db, String tableName, String[] columns) {
        return db.query(tableName, columns,
            NO_SELECTION, NO_SELECTION_ARGS, NO_GROUP_BY, NO_HAVING, NO_ORDER_BY, NO_LIMIT);
    }

    public boolean contains(SQLiteDatabase db, String tableName, String[] columns, String whereClause, String[] whereValues) {
        Cursor cursor = fetch(db, tableName, columns, whereClause, whereValues);
        boolean success = cursor.moveToFirst();
        cursor.close();
        return success;
    }
}
