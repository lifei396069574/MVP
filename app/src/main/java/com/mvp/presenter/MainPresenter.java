package com.mvp.presenter;

import android.content.Context;

import com.mvp.base.BasePresenter;
import com.mvp.view.iview.MainView;

/**
 * 作者：李飞 on 2017/6/22 14:52
 * 类的用途：
 */

public class MainPresenter extends BasePresenter<MainView>{

    private Context mContext;

    public MainPresenter(Context mContext) {
        this.mContext = mContext;
    }

    // 请求

//    public void postAddNet(Map<String, Object> map) {
//        Api.loadDataFromNet(Api.getServer().postAdd(map), new HttpObserver<AddBean>(mContext) {
//            @Override
//            public void onSuccess(AddBean addBean) {
//                mMessage.what=0;
//                mMessage.obj = addBean;
//                mView.onSucceed(mMessage);
//            }
//        });
//    }
}
