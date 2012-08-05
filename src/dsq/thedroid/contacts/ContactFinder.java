package dsq.thedroid.contacts;

import android.content.ContentResolver;
import android.database.Cursor;

public interface ContactFinder {
    BasicContact find(Cursor cursor, ContentResolver resolver) throws NoPhoneNumberException;
}
