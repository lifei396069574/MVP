package com.mvp.model.http;

import com.mvp.base.BaseBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * 作者：李飞 on 2017/6/21 21:36
 * 类的用途：
 */

public interface ApiService {

    @GET()
    Observable<BaseBean<String>> getData(@Url String url);

    @FormUrlEncoded
    @POST()
    Observable<BaseBean>  postData(@Url String url,@FieldMap Map<String, String> map);


}
