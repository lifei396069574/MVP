package com.mvp.model.http;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 *
 */

public abstract class HttpObserver<T> implements Observer<T> {

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
        onFiled(e.toString());
    }

    public abstract void onSuccess(T t);
    public abstract void onFiled(String t);

}
