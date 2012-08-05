package dsq.thedroid.ui;

import android.app.Activity;
import android.view.View;
import android.widget.Button;

public class DefaultButtons implements Buttons {

    public void cancel(final int id, final Activity activity) {
        Button cancelButton = (Button) activity.findViewById(id);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                activity.setResult(Activity.RESULT_CANCELED);
                activity.finish();
            }
        });
    }

    public void confirm(int id, final Activity activity, final View.OnClickListener action) {
        Button confirmButton = (Button) activity.findViewById(id);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                action.onClick(view);
                activity.finish();
            }
        });
    }
}
