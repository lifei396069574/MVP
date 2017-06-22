package com.mvp.view.avtivity;

import android.os.Message;

import com.mvp.R;
import com.mvp.base.BaseActivity;
import com.mvp.presenter.MainPresenter;
import com.mvp.view.iview.MainView;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView{

    @Override
    public void onSucceed(Message message) {

    }

    @Override
    public void onFail(String str) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void createPresenter() {
        mPresenter=new MainPresenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initDatas() {

    }
}
