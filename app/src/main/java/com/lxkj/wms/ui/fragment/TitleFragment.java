package com.lxkj.wms.ui.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import com.lxkj.wms.AppConsts;
import com.lxkj.wms.GlobalBeans;
import com.lxkj.wms.biz.EventCenter;
import com.lxkj.wms.http.OkHttpHelper;
import com.lxkj.wms.serialportapi.SoftDecodingAPI;
import com.lxkj.wms.ui.activity.NaviActivity;
import com.lxkj.wms.utils.KeyboardUtil;
import com.lxkj.wms.utils.ScreenUtil;


public abstract class TitleFragment extends Fragment implements EventCenter.EventListener {


    protected final GlobalBeans beans;
    protected NaviActivity act;
    protected int screenWidth;//屏幕宽度

    protected View rootView;
    public OkHttpHelper mOkHttpHelper;
    public Context mContext;
    public String userId,cityId,lat,lng,userPhone;
    public EventCenter eventCenter;
    public SoftDecodingAPI api;
    public ClipboardManager clipboardManager;
    public boolean isOpen = false;//是否已经跳转  避免多次跳转
    public TitleFragment() {
        beans = GlobalBeans.getSelf();
        screenWidth = ScreenUtil.getScreenWidth(getContext());
        mOkHttpHelper = OkHttpHelper.getInstance();
        eventCenter = beans.getEventCenter();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            // 获取系统剪贴板
            clipboardManager=(ClipboardManager)act.getSystemService(Context.CLIPBOARD_SERVICE);
            mContext = act;
            eventCenter.registEvent(this, EventCenter.EventType.EVT_LOGIN);
            eventCenter.registEvent(this, EventCenter.EventType.EVT_LOGOUT);
            act.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }catch (Exception e){

        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (null == act) {
            act = (NaviActivity) activity;
        }
        mContext = act;
        userPhone = AppConsts.account;
        userId = AppConsts.userId;
    }

    @Override
    public void onDetach() {
        act = null;
        super.onDetach();
    }

    public void setActivity(NaviActivity act) {
        this.act = act;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("onActivityResult","onActivityResult");
    }

    public int getTitleId() {
        return 0;
    }

    public String getTitleName() {
        return null;
    }

    public boolean hideLeftArrow() {
        return false;
    }

    protected boolean isAlive() {
        return null != act && !this.isDetached() && !act.isFinishing();
    }

    protected void hideKeyboard() {
        KeyboardUtil.hideKeyboard(act);
    }

    ProgressDialog dialog;

    protected void showProgressDialog(final String title, final String msg) {
        if (null == dialog) {
            dialog = ProgressDialog.show(act, title, msg);
        } else {
            dialog.setTitle(title);
            dialog.setMessage(msg);
            dialog.show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        eventCenter.unregistEvent(this, EventCenter.EventType.EVT_LOGIN);
        eventCenter.unregistEvent(this, EventCenter.EventType.EVT_LOGOUT);
    }

    @Override
    public void onEvent(EventCenter.HcbEvent e) {
        switch (e.type){
            case EVT_LOGIN:
                userId = AppConsts.userId;
                break;
            case EVT_LOGOUT:
                act.finish();
                userId = "";
                break;
        }
    }
}
