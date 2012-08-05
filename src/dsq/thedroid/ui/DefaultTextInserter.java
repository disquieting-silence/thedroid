package dsq.thedroid.ui;

import android.view.View;
import android.widget.EditText;

public class DefaultTextInserter implements TextInserter {
    public void tryInsert(View view, String text) {
        if (view instanceof EditText) {
            EditText edit = (EditText)view;
            int pos = edit.getSelectionStart();
            if (pos > -1) {
                edit.getText().insert(edit.getSelectionStart(), text);
            }
        }
    }
}
