package com.mvp.base;

import android.content.Context;
import android.os.Message;

import com.mvp.app.MyApplication;

/**
 * 作者：李飞 on 2017/6/21 20:21
 * 类的用途：
 */

public class BasePresenter<T extends BaseView> {

    protected Context mContext;
    protected T mView;
    protected Message mMessage;

    public T getView() {
        return mView;
    }

    public void attachView(T t) {
        this.mView = t;
        mMessage = new Message();
    }

    public void detachView() {
        if (mView != null) {
            mView = null;
        }
        if (mMessage != null) {
            mMessage = null;
        }
    }

    public Context context(){
        if (mView!=null){
            return mView.context();
        }
       return MyApplication.getInstance();
    }
}
