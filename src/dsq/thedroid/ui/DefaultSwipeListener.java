package dsq.thedroid.ui;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

// FIX 18/02/12 Inspired by the approach at: http://www.41post.com/2382/programming/android-touchscreen-swipe-movement-detection
public class DefaultSwipeListener implements SwipeListener {

    private final int MAGIC_NUMBER_BASED_ON_RESEARCH = 16;
    
    private final Swipe swipe;

    public DefaultSwipeListener(Swipe swipe) {
        this.swipe = swipe;
    }

    public boolean onTouch(View view, MotionEvent event) {
        Log.v("ERSATZ", "onTouch" + event.getX() + ", " + event.getY());
        return safe(event);
    }

    // FIX 18/02/12 As explained by the inspiration, this is to prevent flooding of events.
    private boolean safe(MotionEvent event) {
        synchronized (event) {
            try {
                event.wait(MAGIC_NUMBER_BASED_ON_RESEARCH);
                return unsafe(event);
            } catch (InterruptedException exc) {
                return true;
            }
        }
    }

    private boolean unsafe(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_UP) {
            swipe.release(event.getX(), event.getY());
        } else if (action == MotionEvent.ACTION_DOWN) {
            swipe.press(event.getX(), event.getY());
        }
        return true;
    }
}
