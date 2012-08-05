package dsq.thedroid.util;

import android.content.Intent;
import android.os.Bundle;

import java.io.Serializable;

public interface StateExtractor {
    Serializable extract(String tag, Bundle bundle, Intent intent);
    Serializable strict(String tag, Bundle bundle, Intent intent);
}
