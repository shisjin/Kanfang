package kanfang.qf.com.kanfang.utils;

import android.util.Log;

/**
 * 发布时，可关掉Log
 */
public class L {

    /**
     * 统一管理Log
     */
    private static final boolean DEBUG = true;

    static final String TAG = "ytmfdwTAG";

    public static void d(String msg) {
        if (DEBUG) {
            Log.d(TAG, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (DEBUG) {
            Log.d(tag, msg);
        }
    }
}
