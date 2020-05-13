package com.lxkj.wms.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gyf.barlibrary.ImmersionBar;
import com.lxkj.wms.AppConsts;
import com.lxkj.wms.GlobalBeans;
import com.lxkj.wms.biz.EventCenter;
import com.lxkj.wms.http.OkHttpHelper;
import com.lxkj.wms.utils.ScreenUtil;

import butterknife.ButterKnife;

public abstract class CachableFrg extends Fragment implements EventCenter.EventListener {

    protected GlobalBeans beans;
    protected EventCenter eventCenter;
    protected View rootView;
    protected int screenWidth;//屏幕宽度
    public OkHttpHelper mOkHttpHelper;
    public String userId,cityId,lat,lng,city;
    public ImmersionBar mImmersionBar;
    protected abstract int rootLayout();

    protected abstract void initView();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beans = GlobalBeans.getSelf();
        eventCenter = beans.getEventCenter();
        eventCenter.registEvent(this, EventCenter.EventType.EVT_LOGIN);
        eventCenter.registEvent(this, EventCenter.EventType.EVT_LOGOUT);
        mOkHttpHelper = OkHttpHelper.getInstance();
        userId = AppConsts.userId;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(rootLayout(), container, false);
            ButterKnife.bind(this, rootView);
            initView();
        } else {
            //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
        }
        screenWidth = ScreenUtil.getScreenWidth(getContext());

        return rootView;
    }

    ProgressDialog dialog;

    protected void showProgressDialog(final int title, final int msg) {
        showProgressDialog(getString(title), getString(msg));
    }

    protected void showProgressDialog(final String title, final String msg) {
        if (null == dialog) {
            dialog = ProgressDialog.show(getActivity(), title, msg);
        } else {
            dialog.setTitle(title);
            dialog.setMessage(msg);
            dialog.show();
        }
    }

    protected void dismissDialog() {
        try {
            dialog.dismiss();
        } catch (Exception e) {
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        eventCenter.unregistEvent(this, EventCenter.EventType.EVT_LOGIN);
    }

    @Override
    public void onEvent(EventCenter.HcbEvent e) {
        switch (e.type){
            case EVT_LOGIN:
                break;
            case EVT_LOGOUT:
                break;
        }
    }
}
