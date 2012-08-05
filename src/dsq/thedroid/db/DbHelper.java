package dsq.thedroid.db;

import android.database.sqlite.SQLiteDatabase;

public interface DbHelper {
    void onCreate(SQLiteDatabase sqLiteDatabase, Table[] tables);
    void onUpgrade(SQLiteDatabase sqLiteDatabase, Table[] tables, int oldVersion, int newVersion);
}
