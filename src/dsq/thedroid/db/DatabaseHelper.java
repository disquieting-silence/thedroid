package dsq.thedroid.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final SQLiteDatabase.CursorFactory LOG_CURSOR_FACTORY = new SQLiteDatabase.CursorFactory() {
        public Cursor newCursor(SQLiteDatabase db, SQLiteCursorDriver masterQuery, String editTable, SQLiteQuery query) {

            Log.v("thedroid.sql", query.toString());
            return new SQLiteCursor(db, masterQuery, editTable, query);
        }
    };
    
    private DbHelper helper = new DefaultDbHelper();
    private final Table[] tables;

    public DatabaseHelper(Context context, Db settings) {
        super(context, settings.name(), LOG_CURSOR_FACTORY, settings.version());
        this.tables = settings.tables();
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        helper.onCreate(sqLiteDatabase, tables);
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        helper.onUpgrade(sqLiteDatabase, tables, oldVersion, newVersion);
    }

}
