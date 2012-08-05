package dsq.thedroid.ui;

import android.app.Activity;
import android.view.View;

public class DefaultSwipeUi implements SwipeUi {
    
    public void setup(int id, Activity src, Class<? extends Activity> dest, int direction) {
        Swipe swipe = new DefaultSwipe(src, dest, direction);
        View view = src.findViewById(id);
        SwipeListener listener = new DefaultSwipeListener(swipe);
        view.setOnTouchListener(listener);
    }
}
