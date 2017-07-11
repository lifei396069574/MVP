package com.mvp.presenter;

import com.mvp.base.BasePresenter;
import com.mvp.model.main.MainModel;
import com.mvp.model.main.MainModelImpel;
import com.mvp.model.main.OnMainListener;
import com.mvp.view.iview.MainView;

/**
 * 作者：李飞 on 2017/6/22 14:52
 * 类的用途：
 */

public class MainPresenter extends BasePresenter<MainView> implements OnMainListener{

    private MainView mMainView;
    private MainModel mMainModel;

    public MainPresenter(MainView mMainView) {
        this.mMainView = mMainView;
        mMainModel= new MainModelImpel();
    }


    public void xx(String str){
       // mMainModel.xx();
    }


    @Override
    public void onSuccess(String str) {
        //   mMainView.onSucceed(message);
    }

    @Override
    public void onFailure(String s) {
        mMainView.onFail(s);
    }
}
