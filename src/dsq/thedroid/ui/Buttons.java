package dsq.thedroid.ui;

import android.app.Activity;
import android.view.View;

public interface Buttons {
    void cancel(int id, Activity activity);
    void confirm(int id, Activity activity, View.OnClickListener action);
}
