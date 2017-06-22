package com.mvp.model;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonSyntaxException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 *
 */

public abstract class HttpObserver<T> implements Observer<T> {

    private Context mContext;

    public HttpObserver(Context context) {
        this.mContext = context;
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSubscribe(Disposable d) {


    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        if ((e instanceof UnknownHostException)) {
            Toast.makeText(mContext, "网络异常", Toast.LENGTH_SHORT).show();
        } else if (e instanceof JsonSyntaxException) {
            Toast.makeText(mContext, "数据异常", Toast.LENGTH_SHORT).show();
        } else if (e instanceof SocketTimeoutException) {
            Toast.makeText(mContext, "连接超时", Toast.LENGTH_SHORT).show();
        } else if (e instanceof ConnectException) {
            Toast.makeText(mContext, "连接服务器失败", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, "未知错误", Toast.LENGTH_SHORT).show();
        }
        Log.d("TAG", "e:" + e);
    }

    public abstract void onSuccess(T t);

}
