package com.lxkj.wms.ui.fragment.ckjh;

import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lxkj.wms.R;
import com.lxkj.wms.biz.ActivitySwitcher;
import com.lxkj.wms.serialportapi.SoftDecodingAPI;
import com.lxkj.wms.ui.activity.NaviActivity;
import com.lxkj.wms.ui.fragment.TitleFragment;
import com.lxkj.wms.ui.fragment.rksh.AddRkFra;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by kxn on 2020/4/25 0025.
 */
public class CkjhFra extends TitleFragment implements NaviActivity.NaviRigthImageListener, View.OnClickListener, SoftDecodingAPI.IBarCodeData {
    @BindView(R.id.tvSglr)
    TextView tvSglr;
    Unbinder unbinder;
    @BindView(R.id.tvOpen)
    TextView tvOpen;
    private int djNum = 0;

    @Override
    public String getTitleName() {
        return act.getString(R.string.xzckd);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fra_ckjh, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    private void initView() {
        tvSglr.setOnClickListener(this);
        tvOpen.setOnClickListener(this);
        api = new SoftDecodingAPI(mContext, this);
        if (api != null) {
            api.setScannerStatus(true);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        isResume = false;
    }

    @Override
    public void onResume() {
        super.onResume();
        isResume = true;
    }

    @Override
    public int rightImg() {
        return R.mipmap.ic_time;
    }

    @Override
    public void onRightClicked(View v) {
        ActivitySwitcher.startFragment(act, HistoryCkFra.class);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvSglr:
                ActivitySwitcher.startFragment(act, AddCkFra.class);
                break;
            case R.id.tvOpen:
                isOpen = false;
                //手持打开扫描
                api.scan();

                //平板打开扫描
                Intent it=new Intent("com.android.action.keyevent.KEYCODE_KEYCODE_SCAN_L_DOWN");
                act.sendBroadcast(it);
                // 添加剪贴板数据改变监听器
                clipboardManager.addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() {
                    @Override
                    public void onPrimaryClipChanged() {
                        if (!isOpen && isResume){
                            // 剪贴板中的数据被改变，此方法将被回调
                            String str=clipboardManager.getPrimaryClip().getItemAt(0).getText().toString();
                            Bundle bundle = new Bundle();
                            bundle.putString("barCode",str.replace("\n",""));
                            ActivitySwitcher.startFragment(act, AddRkFra.class,bundle);
                            isOpen = true;
                        }
                    }
                });
                break;
        }
    }

    @Override
    public void sendScan() {

    }

    @Override
    public void onBarCodeData(String data) {
        if (isResume){
            api.closeScan();
            Bundle bundle = new Bundle();
            bundle.putString("barCode",data.replace("\n",""));
            ActivitySwitcher.startFragment(act, AddCkFra.class,bundle);
        }
    }

    @Override
    public void getSettings(int PowerOnOff, int OutputMode, int TerminalChar, String Prefix, String Suffix, int Volume, int PlayoneMode) {

    }

    @Override
    public void setSettingsSuccess() {

    }
}
