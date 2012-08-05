package dsq.thedroid.ui;

import android.app.ListActivity;
import android.database.Cursor;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import dsq.thedroid.db.DbAdapter;

public class DefaultLists implements Lists {
    public void refresh(final ListActivity activity, final Cursor cursor, final ListView view, final ComponentIndex rowUi, final ListMapping mapping, final SimpleCursorAdapter.ViewBinder binder) {
        activity.startManagingCursor(cursor);
        final int[] dest = ids(mapping, mapping.dest());
        SimpleCursorAdapter c = new SimpleCursorAdapter(activity, rowUi.value, cursor, mapping.source(), dest);
        c.setViewBinder(binder);
        view.setAdapter(c);
        view.setOnItemClickListener(new ListView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapterView.showContextMenuForChild(view);
            }
        });
    }

    private int[] ids(final ListMapping mapping, final ComponentIndex[] destUis) {
        final int[] destUiIds = new int [mapping.dest().length];
        for (int i = 0; i < destUis.length; i++) {
            ComponentIndex destUi = destUis[i];
            destUiIds[i] = destUi.value;
        }
        return destUiIds;
    }

    public void refreshById(final ListActivity activity, final DbAdapter adapter, final int id, final ListView view, final ComponentIndex rowUi, final ListMapping mapping, final SimpleCursorAdapter.ViewBinder binder) {
        Cursor cursor = adapter.fetchById(id);
        refresh(activity, cursor, view, rowUi, mapping, binder);
    }

    public void refreshAll(final ListActivity activity, final DbAdapter adapter, final ListView view, final ComponentIndex rowUi, final ListMapping mapping, final SimpleCursorAdapter.ViewBinder binder) {
        final Cursor cursor = adapter.fetchAll();
        refresh(activity, cursor, view, rowUi, mapping, binder);
    }
}
