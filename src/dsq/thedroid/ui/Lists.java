package dsq.thedroid.ui;

import android.app.ListActivity;
import android.database.Cursor;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import dsq.thedroid.db.DbAdapter;

public interface Lists {
    /**
     * Refresh a list view with the information stored in a cursor
     * @param activity the current list activity
     * @param cursor the cursor with the information
     * @param view the ListView object to render into
     * @param rowUi the id (in the xml file) of the row UI component
     * @param mapping the mapping between cursor fields and ui ids for each row
     * @param binder the ui renderer
     */
    void refresh(ListActivity activity, Cursor cursor, ListView view, ComponentIndex rowUi, ListMapping mapping, SimpleCursorAdapter.ViewBinder binder);
    void refreshById(ListActivity activity, DbAdapter adapter, int id, ListView view, ComponentIndex rowUi, ListMapping mapping, SimpleCursorAdapter.ViewBinder binder);
    void refreshAll(ListActivity activity, DbAdapter adapter, ListView view, ComponentIndex rowUi, ListMapping mapping, SimpleCursorAdapter.ViewBinder binder);
}