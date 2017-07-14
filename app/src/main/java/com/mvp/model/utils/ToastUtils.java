package com.mvp.model.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 作者：李飞 on 2017/6/24 11:46
 * 类的用途：
 */

public class ToastUtils {
    private static Context context;
    private ToastUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }
    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        ToastUtils.context = context.getApplicationContext();
    }
    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext(){
        if (context != null){
            return context;
        }
        throw new NullPointerException("u should init first");
    }

    public static void toast(String str) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }
}
