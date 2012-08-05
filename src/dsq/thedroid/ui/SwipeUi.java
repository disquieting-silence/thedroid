package dsq.thedroid.ui;

import android.app.Activity;

public interface SwipeUi {
    void setup(int id, Activity src, Class<? extends Activity> dest, int direction);
}
