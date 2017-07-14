package com.mvp.model.utils;

import android.content.Context;
import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 作者：李飞 on 2017/7/14:36
 * 类的用途：
 */

public final class Utils {

    private static Context context;
    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }
    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        Utils.context = context.getApplicationContext();
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

    // 判断手机号
    public static boolean isMobileNO(String mobiles) {
    /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
        String telRegex = "[1][358]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
    }

    //  判断邮箱 是否正确
    public static boolean isEmailAddress(String url) {
        String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(url);
        return matcher.matches();
    }

}
