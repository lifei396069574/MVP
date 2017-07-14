package com.mvp.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mvp.R;
import com.mvp.app.MyApplication;

import org.greenrobot.eventbus.EventBus;

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

        MyApplication.getInstance().addActivity(this);//添加当前Activity
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
        MyApplication.getInstance().removeActivity(this);
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
}
