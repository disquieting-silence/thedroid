package dsq.thedroid.util;

import android.content.Intent;
import android.os.Bundle;

import java.io.Serializable;


public class DefaultStateExtractor implements StateExtractor {
    public Serializable extract(String tag, Bundle bundle, Intent intent) {
        Serializable r = (bundle == null) ? null : bundle.getSerializable(tag);
        if (r == null) {
            Bundle extras = intent.getExtras();
            r = extras != null ? extras.getSerializable(tag) : null;
        }
        return r;
    }

    public Serializable strict(String tag, Bundle bundle, Intent intent) {
        Serializable r = extract(tag, bundle, intent);
        if (r == null) throw new IllegalStateException(tag + " is null. This should never happen.");
        return r;
    }
}
