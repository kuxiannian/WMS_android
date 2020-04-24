package com.lxkj.wms.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;

import com.lxkj.wms.GlobalBeans;
import com.lxkj.wms.R;
import com.lxkj.wms.adapter.HomeAdapter;
import com.lxkj.wms.bean.HomeItemBean;
import com.lxkj.wms.biz.ActivitySwitcher;
import com.lxkj.wms.biz.EventCenter;
import com.lxkj.wms.ui.fragment.home.MoreItemFra;
import com.lxkj.wms.ui.fragment.hwdj.AddHwdjFra;
import com.lxkj.wms.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseFragAct implements EventCenter.EventListener {

    @BindView(R.id.gv)
    GridView gv;
    List<HomeItemBean> items;
    HomeAdapter adapter;
    Context context;
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
        items.add(new HomeItemBean(getResources().getString(R.string.hwdj),R.mipmap.ic_hwdj));
        items.add(new HomeItemBean(getResources().getString(R.string.rksh),R.mipmap.ic_rksh));
        items.add(new HomeItemBean(getResources().getString(R.string.ckjh),R.mipmap.ic_ckjh));
        items.add(new HomeItemBean(getResources().getString(R.string.kccx),R.mipmap.ic_kccx));
        items.add(new HomeItemBean(getResources().getString(R.string.kcpd),R.mipmap.ic_kcpd));
        items.add(new HomeItemBean(getResources().getString(R.string.gdgn),R.mipmap.ic_gdgn));

        adapter = new HomeAdapter(this,items);
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        ActivitySwitcher.startFragment(MainActivity.this, AddHwdjFra.class);
                        break;
                    case 5:
                        ActivitySwitcher.startFragment(MainActivity.this, MoreItemFra.class);
                        break;
                }
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
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


}