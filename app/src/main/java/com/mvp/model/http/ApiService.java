package com.mvp.model.http;

import com.mvp.base.BaseBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * 作者：李飞 on 2017/6/21 21:36
 * 类的用途：
 */

public interface ApiService {

    @GET("home/news/data/newspage?nid=9876549087207987625&n_type=0&p_from=1&dtype=-1")
    Observable<BaseBean> getData();

    @FormUrlEncoded
    @POST("")
    Observable<BaseBean>  postData(@FieldMap Map<String, String> map);


}
