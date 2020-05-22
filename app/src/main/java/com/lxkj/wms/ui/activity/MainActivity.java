package com.lxkj.wms.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.lxkj.wms.AppConsts;
import com.lxkj.wms.GlobalBeans;
import com.lxkj.wms.R;
import com.lxkj.wms.adapter.HomeAdapter;
import com.lxkj.wms.bean.HomeItemBean;
import com.lxkj.wms.bean.PrmCodeListBean;
import com.lxkj.wms.biz.ActivitySwitcher;
import com.lxkj.wms.biz.EventCenter;
import com.lxkj.wms.http.BaseCallback;
import com.lxkj.wms.http.OkHttpHelper;
import com.lxkj.wms.http.Url;
import com.lxkj.wms.ui.fragment.ckjh.CkjhFra;
import com.lxkj.wms.ui.fragment.hwdj.AddHwdjFra;
import com.lxkj.wms.ui.fragment.kccx.KccxFra;
import com.lxkj.wms.ui.fragment.putoff.PutOffBillFra;
import com.lxkj.wms.ui.fragment.puton.PutOnBillFra;
import com.lxkj.wms.ui.fragment.rksh.RkshFra;
import com.lxkj.wms.ui.fragment.stockcheck.StockCheckFra;
import com.lxkj.wms.utils.ListUtil;
import com.lxkj.wms.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends BaseFragAct implements EventCenter.EventListener, View.OnClickListener {

    @BindView(R.id.gv)
    GridView gv;
    List<HomeItemBean> items;
    HomeAdapter adapter;
    Context context;
    @BindView(R.id.tvLogOut)
    TextView tvLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (GlobalBeans.getSelf() == null) {
            GlobalBeans.initForMainUI(getApplication());
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        ButterKnife.bind(this);
        context = this;
        eventCenter.registEvent(this, EventCenter.EventType.EVT_LOGIN);
        eventCenter.registEvent(this, EventCenter.EventType.EVT_LOGOUT);
        items = new ArrayList<>();

        adapter = new HomeAdapter(this, items);
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (items.get(i).icon) {
                    case R.mipmap.ic_hwdj:
                        ActivitySwitcher.startFragment(MainActivity.this, AddHwdjFra.class);
                        break;
                    case R.mipmap.ic_rksh:
                        ActivitySwitcher.startFragment(MainActivity.this, RkshFra.class);
                        break;
                    case R.mipmap.ic_ckjh:
                        ActivitySwitcher.startFragment(MainActivity.this, CkjhFra.class);
                        break;
                    case R.mipmap.ic_kccx:
                        ActivitySwitcher.startFragment(MainActivity.this, KccxFra.class);
                        break;
                    case R.mipmap.ic_kcpd:
                        ActivitySwitcher.startFragment(MainActivity.this, StockCheckFra.class);
                        break;
                    case R.mipmap.ic_sjzy:
                        ActivitySwitcher.startFragment(MainActivity.this, PutOnBillFra.class);
                        break;
                    case R.mipmap.ic_xjzy:
                        ActivitySwitcher.startFragment(MainActivity.this, PutOffBillFra.class);
                        break;
                }
            }
        });
        getPrmCodeList();
        tvLogOut.setOnClickListener(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 获取权限编码列表
     */
    private void getPrmCodeList() {
        Map<String, String> params = new HashMap<>();
        params.put("all", "true");
        OkHttpHelper.getInstance().post_json(this, Url.getPrmCodeList, params, new BaseCallback<PrmCodeListBean>() {

            @Override
            public void onFailure(Request request, Exception e) {
            }

            @Override
            public void onSuccess(Response response, PrmCodeListBean resultBean) {
                if (!ListUtil.isEmpty(resultBean.getResult())) {
                    if (resultBean.getResult().contains("VICENTER-WMS-PDA-SORTINGREGISTER"))
                        items.add(new HomeItemBean(getResources().getString(R.string.hwdj), R.mipmap.ic_hwdj));
                    if (resultBean.getResult().contains("VICENTER-WMS-PDA-BILLINPUT"))
                        items.add(new HomeItemBean(getResources().getString(R.string.rksh), R.mipmap.ic_rksh));
                    if (resultBean.getResult().contains("VICENTER-WMS-PDA-BILLOUTPUT"))
                        items.add(new HomeItemBean(getResources().getString(R.string.ckjh), R.mipmap.ic_ckjh));
                    if (resultBean.getResult().contains("VICENTER-WMS-PDA-STOCK"))
                        items.add(new HomeItemBean(getResources().getString(R.string.kccx), R.mipmap.ic_kccx));
                    if (resultBean.getResult().contains("VICENTER-WMS-PDA-BILLSTOCKCHECK"))
                        items.add(new HomeItemBean(getResources().getString(R.string.kcpd), R.mipmap.ic_kcpd));
                    if (resultBean.getResult().contains("VICENTER-WMS-PAD-BILLPUTON"))
                        items.add(new HomeItemBean(getResources().getString(R.string.sjzy), R.mipmap.ic_sjzy));
                    if (resultBean.getResult().contains("VICENTER-WMS-PAD-BILLPUTOFF"))
                        items.add(new HomeItemBean(getResources().getString(R.string.xjzy), R.mipmap.ic_xjzy));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Response response, int code, Exception e) {
            }
        });
    }


    /**
     * 退出登录
     */
    private void logout() {
        Map<String, String> params = new HashMap<>();
        OkHttpHelper.getInstance().post_json(this, Url.logout, params, new BaseCallback<PrmCodeListBean>() {

            @Override
            public void onFailure(Request request, Exception e) {
            }

            @Override
            public void onSuccess(Response response, PrmCodeListBean resultBean) {
                //退出登录成功 清空个人信息 跳转登录界面
                if (resultBean.flag) {
                    AppConsts.userId = "";
                    AppConsts.account = "";
                    AppConsts.userName = "";
                    ActivitySwitcher.start(MainActivity.this, LoginAct.class);
                    finish();
                }
            }

            @Override
            public void onError(Response response, int code, Exception e) {
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 在onStop时释放掉播放器
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        eventCenter.unregistEvent(this, EventCenter.EventType.EVT_LOGIN);
    }

    @Override
    public void onEvent(EventCenter.HcbEvent e) {
        switch (e.type) {
            case EVT_LOGOUT:
                onExit();
                break;
        }
    }


    private long backPressTime = 0;
    private static final int SECOND = 1000;

    @Override
    public void onBackPressed() {
        // 在全屏或者小窗口时按返回键要先退出全屏或小窗口，
        final long uptimeMillis = SystemClock.uptimeMillis();
        if (uptimeMillis - backPressTime > 2 * SECOND) {
            backPressTime = uptimeMillis;
            ToastUtil.show(getString(R.string.press_again_to_leave));
        } else {
            ToastUtil.cancel();
            onExit();
        }
    }

    private void onExit() {
        finish();
        if (null != beans) {
            beans.onTerminate();
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvLogOut:
                logout();
                break;
        }
    }
}