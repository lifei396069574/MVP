package com.mvp.base;

import android.os.Message;

/**
 * 作者：李飞 on 2017/6/21 20:15
 * 类的用途：
 */

public interface BaseView {

    void onSucceed(Message message);

    void onFail(String str);
}
