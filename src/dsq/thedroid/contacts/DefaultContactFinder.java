package dsq.thedroid.contacts;

import android.content.ContentResolver;
import android.database.Cursor;
import dsq.thedroid.db.DbUtils;

import static android.provider.ContactsContract.CommonDataKinds.Phone;
import static android.provider.ContactsContract.Contacts.*;

public class DefaultContactFinder implements ContactFinder {
    public BasicContact find(Cursor cursor, ContentResolver resolver) throws NoPhoneNumberException {
        String id = DbUtils.getColumn(cursor, _ID);
        String name = DbUtils.getColumn(cursor, DISPLAY_NAME);
        Cursor phoneCursor = resolver.query(Phone.CONTENT_URI, null, Phone._ID + " = ?", new String[]{id}, null);
        if (phoneCursor.moveToFirst()) {
            String phoneNumber = DbUtils.getColumn(phoneCursor, Phone.NUMBER);
            return new BasicContact(name, phoneNumber);
        }
        throw new NoPhoneNumberException(name);
    }

    // FIX 24/01/12 Duplication with above. See how best to extract the commonality.
    public BasicContact findFromContact(Cursor cursor, ContentResolver resolver) throws NoPhoneNumberException {
        String id = DbUtils.getColumn(cursor, _ID);
        String name = DbUtils.getColumn(cursor, DISPLAY_NAME);
        String hasPhoneValue = DbUtils.getColumn(cursor, HAS_PHONE_NUMBER);
        if (Integer.parseInt(hasPhoneValue) > 0) {
            Cursor phoneCursor = resolver.query(Phone.CONTENT_URI, null, Phone.CONTACT_ID + " = ?", new String[]{id}, null);
            if (phoneCursor.moveToFirst()) {
                String phoneNumber = DbUtils.getColumn(phoneCursor, Phone.NUMBER);
                return new BasicContact(name, phoneNumber);
            }
        }
        throw new NoPhoneNumberException(name);
    }

}
