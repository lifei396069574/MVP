package com.mvp.presenter;

import com.mvp.base.BaseBean;
import com.mvp.base.BasePresenter;
import com.mvp.model.http.Api;
import com.mvp.model.http.HttpObserver;
import com.mvp.view.iview.MainView;

/**
 * 作者：李飞 on 2017/6/22 14:52
 * 类的用途：
 */

public class MainPresenter extends BasePresenter<MainView> {

    public void xx(){

        Api.loadDataFromNet(Api.getServer().getData(), new HttpObserver<BaseBean>() {
            @Override
            public void onSuccess(BaseBean objectBaseBean) {
                    mView.onSucceed(objectBaseBean);
            }

            @Override
            public void onFiled(String t) {
                    mView.onFail(t);
            }
        });
    }

}
