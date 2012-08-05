package dsq.thedroid.ui;

import android.app.Activity;
import android.content.Intent;

public class DefaultSwipe implements Swipe {

    public static final int SWIPE_LEFT = 1;
    public static final int SWIPE_RIGHT = 2;

    private final Activity src;
    private final Class<? extends Activity> dest;
    private final int direction;

    private float x;
    private float y;

    public DefaultSwipe(Activity src, Class<? extends Activity> dest, int direction) {
        this.src = src;
        this.dest = dest;
        this.direction = direction;
    }

    public void press(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void release(float x, float y) {
        if (direction == SWIPE_LEFT && x + 10 < this.x) launch();
        else if (direction == SWIPE_RIGHT && x > this.x + 10) launch();
    }

    private void launch() {
        Intent intent = new Intent(src, dest);
        intent.putExtras(src.getIntent());
        src.startActivity(intent);
    }
}
