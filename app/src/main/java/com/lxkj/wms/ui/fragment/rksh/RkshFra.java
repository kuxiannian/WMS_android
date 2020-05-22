package com.lxkj.wms.ui.fragment.rksh;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by kxn on 2020/4/25 0025.
 */
public class RkshFra extends TitleFragment implements NaviActivity.NaviRigthImageListener, View.OnClickListener, SoftDecodingAPI.IBarCodeData {
    @BindView(R.id.tvSglr)
    TextView tvSglr;
    Unbinder unbinder;
    @BindView(R.id.tvOpen)
    TextView tvOpen;
    private int djNum = 0;


    @Override
    public String getTitleName() {
        return act.getString(R.string.rksh);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fra_rksh, container, false);
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
    public int rightImg() {
        return R.mipmap.ic_time;
    }

    @Override
    public void onRightClicked(View v) {
        ActivitySwitcher.startFragment(act, HistoryRkFra.class);
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
                ActivitySwitcher.startFragment(act, AddRkFra.class);
                break;
            case R.id.tvOpen:
                isOpen = false;
                api.scan();
                Intent it=new Intent("com.android.action.keyevent.KEYCODE_KEYCODE_SCAN_L_DOWN");
                act.sendBroadcast(it);
                // 添加剪贴板数据改变监听器
                clipboardManager.addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() {
                    @Override
                    public void onPrimaryClipChanged() {
                        if (!isOpen){
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
        api.closeScan();
        Bundle bundle = new Bundle();
        bundle.putString("barCode",data.replace("\n",""));
        ActivitySwitcher.startFragment(act, AddRkFra.class,bundle);
    }

    @Override
    public void getSettings(int PowerOnOff, int OutputMode, int TerminalChar, String Prefix, String Suffix, int Volume, int PlayoneMode) {

    }

    @Override
    public void setSettingsSuccess() {

    }
}
