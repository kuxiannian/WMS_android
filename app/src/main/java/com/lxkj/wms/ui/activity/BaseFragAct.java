package com.lxkj.wms.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gyf.barlibrary.ImmersionBar;
import com.lxkj.wms.GlobalBeans;
import com.lxkj.wms.R;
import com.lxkj.wms.biz.ActivityWatcher;
import com.lxkj.wms.biz.EventCenter;


public class BaseFragAct extends AppCompatActivity {
    public GlobalBeans beans;
    public EventCenter eventCenter;
    protected ImmersionBar mImmersionBar;
    @Override
    protected void onCreate(Bundle arg0) {
        if (null == GlobalBeans.getSelf()) {
            GlobalBeans.initForMainUI(getApplication());
        }
        beans = GlobalBeans.getSelf();
        eventCenter = beans.getEventCenter();
        super.onCreate(arg0);
        initImmersionBar();
    }


    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarColor(R.color.white);
        mImmersionBar.statusBarDarkFont(true,0.2f);
        mImmersionBar.init();
    }



    @Override
    protected void onResume() {
        super.onResume();
        ActivityWatcher.setCurAct(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ActivityWatcher.setCurAct(null);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
