package dsq.thedroid.contacts;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

public class DefaultContacts implements Contacts {
    
    private final ContactFinder finder = new DefaultContactFinder();

    public void browse(Activity activity, int reqCode) {
        Intent pickContacts = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
        activity.startActivityForResult(pickContacts, reqCode);
    }

    public BasicContact process(Activity activity, Intent data) throws NoPhoneNumberException {
        BasicContact contact = null;
        Uri contactData = data.getData();        
        Cursor cursor = activity.managedQuery(contactData, null, null, null, null);
        if (cursor.moveToFirst()) {
            contact = finder.find(cursor, activity.getContentResolver());
        }
        cursor.close();
        if (contact == null) throw new IllegalStateException("No phone number to process.");
        return contact;
    }
}
