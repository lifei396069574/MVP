package com.mvp.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：李飞 on 2017/7/13 19:37
 * 类的用途：
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView{

    protected T mPresenter;
    protected View mView;
    protected Activity mActivity;
    protected Context mContext;
    private Unbinder mUnBinder;

    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(),null);
        return mView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mUnBinder = ButterKnife.bind(this,view);

        createPresenter();

        if (mPresenter!=null){
            mPresenter.attachView(this);
        }

        initData();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mPresenter != null){
            mPresenter.detachView();
            mPresenter=null;
        }
        mUnBinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    public static void registerEvent(Object obj){
        EventBus.getDefault().register(obj);
    }

    public static void sendEvent(Object obj){
        EventBus.getDefault().postSticky(obj);
    }

    protected void startActivity(Class<?> cls){
        startActivity(new Intent(mContext,cls));
    }

    @Subscribe(threadMode = ThreadMode.MAIN , sticky = true)
    public void onEventMainThread(Object obj){

    }


    protected abstract void createPresenter();

    protected abstract int getLayoutId();

    protected abstract void initData();
}
