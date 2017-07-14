package com.mvp.base;

import android.content.Context;

/**
 * 作者：李飞 on 2017/6/21 20:15
 * 类的用途：
 */

public interface BaseView {

    Context context();

    void onSucceed(Object object);

    void onFail(String str);


}
