package kanfang.qf.com.kanfang.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/11/21.
 */
public class SharedUtils {

    /**
     * 共享参数文件 名
     */
    static final String SHARED_NAME = "kanfang";

    /**
     * 第一次启动保存的key
     */
    static final String FIRST_RUN = "isFirstRun";


    /**
     * 判断是否是第一次启动
     *
     * @param context
     * @return
     */
    public static boolean isFirstRun(Context context) {
        SharedPreferences shared = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return shared.getBoolean(FIRST_RUN, true);
    }

    /**
     * 进入主界面 时，调用
     *
     * @param context
     */
    public static void saveFirstRun(Context context) {
        SharedPreferences shared = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        shared.edit().putBoolean(FIRST_RUN, false).commit();
    }
}
