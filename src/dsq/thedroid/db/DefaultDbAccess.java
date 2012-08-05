package dsq.thedroid.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DefaultDbAccess implements DbAccess {

    private DbAccessRead reader = new DefaultDbAccessRead();
    private DbAccessWrite writer = new DefaultDbAccessWrite();

    public long create(SQLiteDatabase db, String tableName, ContentValues values) {
        return writer.create(db, tableName, values);
    }

    public Cursor fetchById(SQLiteDatabase db, String tableName, String[] columns, long id) {
        return reader.fetchById(db, tableName, columns, id);
    }

    public Cursor fetchAll(SQLiteDatabase db, String tableName, String[] columns) {
        return reader.fetchAll(db, tableName, columns);
    }

    public boolean contains(SQLiteDatabase db, String tableName, String[] columns, String whereClause, String[] whereValues) {
        return reader.contains(db, tableName, columns, whereClause, whereValues);
    }

    public Cursor fetch(SQLiteDatabase db, String tableName, String[] columns, String whereClause, String[] whereArgs) {
        return reader.fetch(db, tableName, columns, whereClause, whereArgs);
    }

    public Cursor query(SQLiteDatabase db, String sql, String[] bindings) {
        return reader.query(db, sql, bindings);
    }

    public boolean updateAll(SQLiteDatabase db, String tableName, ContentValues values) {
        return writer.updateAll(db, tableName, values);
    }

    public boolean update(SQLiteDatabase db, String tableName, long id, ContentValues values) {
        return writer.update(db, tableName, id, values);
    }

    public boolean delete(SQLiteDatabase db, String tableName, String column, String value) {
        return writer.delete(db, tableName, column, value);
    }

    // FIX 23/01/12 Maybe consider edging stuff.
    public boolean deleteById(SQLiteDatabase db, String tableName, long id) {
        return writer.deleteById(db, tableName, id);
    }
}
