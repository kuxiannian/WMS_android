package com.lxkj.wms.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;

import com.gyf.barlibrary.ImmersionBar;
import com.lxkj.wms.AppConsts;
import com.lxkj.wms.GlobalBeans;
import com.lxkj.wms.R;
import com.lxkj.wms.biz.ActivitySwitcher;
import com.lxkj.wms.ui.fragment.login.LoginFra;
import com.lxkj.wms.utils.SharePrefUtil;
import com.lxkj.wms.utils.ToastUtil;
import com.zhy.m.permission.MPermissions;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends BaseFragAct {

    private final static int SECOND = 1000;
    private int downCount = 1;

    private GlobalBeans beans;
    private Handler uiHandler;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        context = this;
        GlobalBeans.initForMainUI(getApplication());
        beans = GlobalBeans.getSelf();
        uiHandler = beans.getHandler();


        uiHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                new Timer().scheduleAtFixedRate(timerTask, 0, SECOND);
            }
        }, 400);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.transparentStatusBar();
        mImmersionBar.statusBarDarkFont(false, 0.2f);
        mImmersionBar.init();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    private final TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            if (downCount > 0) {
                downCount--;
            } else {
                enterApp();
                this.cancel();
            }
        }
    };


    private void enterApp() {
        uiHandler.post(new Runnable() {
            @Override
            public void run() {
                Log.e("uid", SharePrefUtil.getString(context, AppConsts.UID, ""));
                ActivitySwitcher.startFragment(WelcomeActivity.this, LoginFra.class);
                finish();
            }
        });
    }


    private long backPressTime = 0;

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mLocationClient.stop();
    }

    @Override
    public void onBackPressed() {
        final long uptimeMillis = SystemClock.uptimeMillis();
        if (uptimeMillis - backPressTime > 2 * SECOND) {
            backPressTime = uptimeMillis;
            ToastUtil.show(getString(R.string.press_again_to_leave));
        } else {
            onExit();
        }
    }

    private void onExit() {
        timerTask.cancel();
        finish();
        if (null != beans) {
            beans.onTerminate();
        }
    }


}