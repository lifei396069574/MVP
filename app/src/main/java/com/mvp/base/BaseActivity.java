package com.mvp.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.mvp.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：李飞 on 2017/6/21 20:36
 * 类的用途：
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {
    protected FragmentManager mFragmentManager;
    protected Activity mContext;//上下文
    protected T mPresenter;
    private Toolbar mToolBar;
    private String mTitle;
    private Fragment mFragment;
    private Unbinder mUnBinder;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //acticity跳转动画——右进左出
        overridePendingTransition(R.anim.left_in, R.anim.left_out);
        super.onCreate(savedInstanceState);
        //展示页面
        setContentView(getLayoutId());
        //初始化共有内容
        init();
        //初始化数据
        initDatas();
    }

    private void init() {
        mContext = this;
        //ButterKnife绑定
        mUnBinder = ButterKnife.bind(this);
        //建立presenter
        createPresenter();

        if (mPresenter!=null){
            mPresenter.attachView(this);
        }

    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        Presenter解除绑定
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
        //ButterKnift解除绑定
        mUnBinder.unbind();
        // eventbus  解绑
        EventBus.getDefault().unregister(this);
        //删除当前Activity
    }

    protected void setToolBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    protected abstract int getLayoutId();

    protected abstract void createPresenter();

    protected abstract void initDatas();

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


    public FragmentTransaction fragmentReplace(int layoutID, Fragment fragment){
        return mFragmentManager.beginTransaction().replace(layoutID,fragment);
    }

    public void showFragment(Fragment fragment ,int layoutID) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (mFragment != null) {
            fragmentTransaction.hide(mFragment);
        }
        if (!fragment.isAdded()) {
            fragmentTransaction.add(layoutID, fragment);
        }
        fragmentTransaction.show(fragment);
        fragmentTransaction.commit();
        mFragment = fragment;

    }

    /**
     * 隐藏软键盘
     *
     * @param v
     */
    public void hideInputMethod(final EditText v) {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(),InputMethodManager.HIDE_IMPLICIT_ONLY);

    }

    /**
     * 显示软键盘
     *
     * @param v
     */
    public void showInputMethod(final EditText v) {

        v.requestFocus();
        InputMethodManager imm = (InputMethodManager)mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(v,InputMethodManager.SHOW_IMPLICIT);
    }
}
