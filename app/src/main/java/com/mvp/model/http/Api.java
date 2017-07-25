package com.mvp.model.http;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.mvp.app.Constant;
import com.mvp.app.MyApplication;
import com.mvp.base.BaseBean;
import com.mvp.model.utils.NetUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

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

    private static OkHttpClient initOkHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        /**
         * 日志拦截器
         */
        Interceptor loggingIntercept = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                long t1 = System.nanoTime();
                Log.e("TAG",String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()));

                Response response = chain.proceed(request);
                long t2 = System.nanoTime();
                Log.e("TAG",String.format("Received response for %s in %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6d, response.headers()));

                return response;
            }
        };
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = null;
                if(!NetUtils.isConnected()){
                    Request request = chain.request()
                            .newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                    response = chain.proceed(request);
                    return response;
                }else{
                    int maxAge = 0;
                    response = chain.proceed(chain.request())
                            .newBuilder()
                            .removeHeader("pragma")
                            .header("Cache-Control","max-age=" + maxAge)
                            .build();
                    return response;
                }
            }
        };
        /**
         * 缓存路径
         */
        File httpCacheDirectory = new File(MyApplication.getInstance().getExternalFilesDir("test").getPath());
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(httpCacheDirectory, cacheSize);
        builder.addInterceptor(loggingIntercept);
        builder.addInterceptor(cacheInterceptor);
        builder.cache(cache);
        //设置超时
        builder.connectTimeout(5, TimeUnit.SECONDS);
        builder.readTimeout(20,TimeUnit.SECONDS);
        builder.writeTimeout(20,TimeUnit.SECONDS);
        //错误重连

        return  builder.build();
    }

    private static class RetrofitInstance {
        private static final ApiService apiserver = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())    //请求String类
                .addConverterFactory(GsonConverterFactory.create(Api.addGson()))   // bean   gson 解析
                .client(Api.initOkHttp())
                .build()
                .create(ApiService.class);
    }


    //得到Server对象
    public static final ApiService getServer() {
        return RetrofitInstance.apiserver;
    }

    public static <T> void loadDataFromNet(Observable<BaseBean<T>> observable, HttpObserver<BaseBean<T>> httpObserver) {
                 observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(httpObserver);
    }
}
