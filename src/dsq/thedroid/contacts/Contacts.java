package dsq.thedroid.contacts;

import android.app.Activity;
import android.content.Intent;

public interface Contacts {
    void browse(Activity activity, int reqCode);
    
    BasicContact process(Activity activity, Intent data) throws NoPhoneNumberException;
}
