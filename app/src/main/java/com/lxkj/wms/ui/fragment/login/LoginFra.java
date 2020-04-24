package com.lxkj.wms.ui.fragment.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lxkj.wms.R;
import com.lxkj.wms.biz.ActivitySwitcher;
import com.lxkj.wms.biz.EventCenter;
import com.lxkj.wms.ui.activity.MainActivity;
import com.lxkj.wms.ui.fragment.TitleFragment;
import com.lxkj.wms.utils.ToastUtil;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by kxn on 2020/1/9 0009.
 */
public class LoginFra extends TitleFragment implements View.OnClickListener, EventCenter.EventListener {

    Unbinder unbinder;
    @BindView(R.id.tvLogin)
    TextView tvLogin;
    @BindView(R.id.ivLanguage)
    ImageView ivLanguage;
    @BindView(R.id.etAccount)
    EditText etAccount;
    @BindView(R.id.etPsw)
    EditText etPsw;
    @BindView(R.id.ivCk)
    ImageView ivCk;
    @BindView(R.id.llCk)
    LinearLayout llCk;
    @BindView(R.id.ivCc)
    ImageView ivCc;
    @BindView(R.id.llCc)
    LinearLayout llCc;
    @BindView(R.id.webView)
    WebView webview;

    @Override
    public String getTitleName() {
        return "";
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fra_login, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        act.hindNaviBar();
        initView();
        return rootView;
    }

    public void initView() {
        act.hindNaviBar();
        eventCenter.registEvent(this, EventCenter.EventType.EVT_LOGIN);
        tvLogin.setOnClickListener(this);
        ivLanguage.setOnClickListener(this::onClick);
        llCc.setOnClickListener(this::onClick);
        llCk.setOnClickListener(this::onClick);
        webview.getSettings().setJavaScriptEnabled(true);

    }


    @Override
    public void onResume() {
        super.onResume();
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
    }

    private void userLogin() {
        if (TextUtils.isEmpty(etAccount.getText())) {
            ToastUtil.show("请输入手机号");
            return;
        }
        if (TextUtils.isEmpty(etPsw.getText())) {
            ToastUtil.show("请输入密码");
            return;
        }
        webview.loadUrl("file:///android_asset/crypto.html");
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //在这里执行你想调用的js函数
                String script = String.format("javascript:getGreetings('" + etAccount.getText().toString() + "','" + etPsw.getText().toString() + "')");
                webview.evaluateJavascript(script, new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        Log.e("onReceiveValue", value);
                        Logger.d("crypto value: " + value);
                    }
                });
            }
        });

//        Map<String, Object> params = new HashMap<>();
//        params.put("cmd", "userLogin");
//        params.put("phone", etAccount.getText().toString());
//        params.put("password", Md5.encode(etPsw.getText().toString()));
//        mOkHttpHelper.post_json(mContext, Url.THE_SERVER_URL, params, new SpotsCallBack<ResultBean>(mContext) {
//            @Override
//            public void onSuccess(Response response, ResultBean resultBean) {
//                eventCenter.sendType(EventCenter.EventType.EVT_LOGOUT); //关闭 重新打开
//                SharePrefUtil.saveString(mContext, AppConsts.UID, resultBean.uid);
//                SharePrefUtil.saveString(mContext, AppConsts.PHONE, etAccount.getText().toString());
//                ActivitySwitcher.start(act, MainActivity.class);
//            }
//            @Override
//            public void onError(Response response, int code, Exception e) {
//            }
//        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvLogin:
                userLogin();
                ActivitySwitcher.start(act, MainActivity.class);
                break;
            case R.id.ivLanguage:

                break;
            case R.id.llCk:
                ivCk.setImageResource(R.mipmap.ic_checked);
                ivCc.setImageResource(R.mipmap.ic_uncheck);
                break;
            case R.id.llCc:
                ivCc.setImageResource(R.mipmap.ic_checked);
                ivCk.setImageResource(R.mipmap.ic_uncheck);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onEvent(EventCenter.HcbEvent e) {
        switch (e.type) {
            case EVT_LOGIN:
                act.finishSelf();
                break;
        }
    }
}
