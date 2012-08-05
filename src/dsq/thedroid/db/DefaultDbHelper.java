package dsq.thedroid.db;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DefaultDbHelper implements DbHelper {
    
    public void onCreate(final SQLiteDatabase db, final Table[] tables) {
        for (Table table : tables) {
            db.execSQL(table.create());
        }

        for (Table table : tables) {
            final String[] inserts = table.load();
            for (String insert : inserts) {
                db.execSQL(insert);
            }
        }
    }

    public void onUpgrade(final SQLiteDatabase db, final Table[] tables, final int oldVersion, final int newVersion) {
        Log.v("thedroid::DefaultDbHelper.onUpgrade", "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        drop(db, tables);
        onCreate(db, tables);
    }

    private void drop(final SQLiteDatabase db, Table[] tables) {
        for (Table table : tables) {
            db.execSQL(table.drop());
        }
    }
}
