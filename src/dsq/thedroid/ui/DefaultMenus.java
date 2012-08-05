package dsq.thedroid.ui;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;

public class DefaultMenus implements Menus {

    public boolean options(Activity activity, Menu menu, int id) {
        MenuInflater inflater = activity.getMenuInflater();
        inflater.inflate(id, menu);
        return true;
    }

    public void context(Activity activity, Menu menu, int id) {
        options(activity, menu, id);
    }
}
