package dsq.thedroid.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public interface DbAccessRead {
    Cursor query(SQLiteDatabase db, String sql, String[] bindings);
    Cursor fetch(SQLiteDatabase db, String tableName, String[] columns, String whereClause, String[] whereArgs);
    Cursor fetchById(SQLiteDatabase db, String tableName, String[] columns, long id);
    Cursor fetchAll(SQLiteDatabase db, String tableName, String[] columns);
    boolean contains(SQLiteDatabase db, String tableName, String[] columns, String whereClause, String[] whereValues);
}
