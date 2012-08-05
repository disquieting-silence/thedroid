package dsq.thedroid.ui;

import android.app.Activity;
import android.view.Menu;

public interface Menus {
    boolean options(Activity activity, Menu menu, int id);
    void context(Activity activity, Menu menu, int id);
}
