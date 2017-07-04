package com.mvp.model.http;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.mvp.app.Constant;
import com.mvp.base.BaseBean;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：李飞 on 2017/6/21 21:33
 * 类的用途：
 */

public class Api {

    private static Gson addGson() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();//使用 gson coverter，统一日期请求格式
        return gson;
    }

    static class RetrofitInstance {
        private static ApiService apiserver = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(Api.addGson()))
                .client(new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor(
                        new HttpLoggingInterceptor.Logger() {
                            @Override
                            public void log(String message) {
                                if (TextUtils.isEmpty(message)) return;
                                String s = message.substring(0, 1);
                                //如果收到想响应是json才打印
                                if ("{".equals(s) || "[".equals(s)) {
                                    Log.d("RetrofitInstance", "收到响应: " + message);
                                }
                            }
                        }).setLevel(HttpLoggingInterceptor.Level.BASIC)).build())
                .build()
                .create(ApiService.class);
    }


    //得到Server对象
    public static ApiService getServer() {
        return RetrofitInstance.apiserver;
    }

    public static <T> void loadDataFromNet(Observable<BaseBean<T>> observable, HttpObserver<T> httpObserver) {
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<BaseBean<T>, T>() {
                    @Override
                    public T apply(BaseBean<T> tBaseBean) throws Exception {
                        Log.d("Api", "tBaseBean-----------" + tBaseBean.toString());

                        return tBaseBean.getData();
                    }
                })
                .subscribe(httpObserver);
    }
}
