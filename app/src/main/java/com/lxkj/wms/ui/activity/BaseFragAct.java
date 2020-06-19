package com.lxkj.wms.ui.activity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.gyf.barlibrary.ImmersionBar;
import com.lxkj.wms.GlobalBeans;
import com.lxkj.wms.R;
import com.lxkj.wms.biz.ActivityWatcher;
import com.lxkj.wms.biz.EventCenter;
import com.lxkj.wms.utils.AppUtil;
import com.lxkj.wms.utils.ScreenUtil;


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
        int screenWidth = ScreenUtil.getScreenWidth(this);
        Log.e("dpWidth",AppUtil.px2dip(this,screenWidth)+"");
        Log.e("dpHeight",AppUtil.px2dip(this,ScreenUtil.getScreenHeight(this))+"");
        if (AppUtil.px2dip(this,screenWidth) > 500)
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        else
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }


    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarColor(R.color.white);
        mImmersionBar.statusBarDarkFont(true, 0.2f);
        mImmersionBar.init();
    }
    public boolean isHideKeybord = true;
    /**
     * 点击空白区域隐藏键盘.
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent me) {
        if (isHideKeybord){
            if (me.getAction() == MotionEvent.ACTION_DOWN) {  //把操作放在用户点击的时候
                View v = getCurrentFocus();      //得到当前页面的焦点,ps:有输入框的页面焦点一般会被输入框占据
                if (isShouldHideKeyboard(v, me)) { //判断用户点击的是否是输入框以外的区域
                    hideKeyboard(v.getWindowToken());   //收起键盘
                }
            }
        }
        return super.dispatchTouchEvent(me);
    }
    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     *
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {  //判断得到的焦点控件是否包含EditText
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],    //得到输入框在屏幕中上下左右的位置
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击位置如果是EditText的区域，忽略它，不收起键盘。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略
        return false;
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     * @param token
     */
    private void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
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
