package com.mvp.view.avtivity;

import android.content.Context;
import android.widget.Button;

import com.mvp.R;
import com.mvp.base.BaseActivity;
import com.mvp.model.utils.LogUtils;
import com.mvp.model.utils.ToastUtils;
import com.mvp.presenter.MainPresenter;
import com.mvp.view.iview.MainView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView {

    private boolean isExit;

    @BindView(R.id.button)
    Button mButton;

    @Override
    public void onSucceed(Object object) {
    }

    @Override
    public void onFail(String str) {
        LogUtils.a(str);
        ToastUtils.showToast(mContext,str);
    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void createPresenter() {
        mPresenter = new MainPresenter();
    }


    @Override
    protected void initDatas() {
    }

    @OnClick(R.id.button)
    public void onClick() {
        mPresenter.xx();
    }

    @Override
    public void onBackPressed() {

        if(!isExit){
            ToastUtils.showToast(mContext,R.string.press_again_to_exit);
            isExit = true;
            EventBus.getDefault().post(isExit);
        }else{
            super.onBackPressed();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        isExit = false;
    }

}
