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
import android.widget.TextView;

import com.google.gson.Gson;
import com.lxkj.wms.AppConsts;
import com.lxkj.wms.R;
import com.lxkj.wms.bean.ResultBean;
import com.lxkj.wms.biz.ActivitySwitcher;
import com.lxkj.wms.biz.EventCenter;
import com.lxkj.wms.http.BaseCallback;
import com.lxkj.wms.http.SpotsCallBack;
import com.lxkj.wms.http.Url;
import com.lxkj.wms.ui.activity.MainActivity;
import com.lxkj.wms.ui.fragment.TitleFragment;
import com.lxkj.wms.utils.LanguageUtils;
import com.lxkj.wms.utils.PasswordUtil;
import com.lxkj.wms.utils.SharePrefUtil;
import com.lxkj.wms.utils.ToastUtil;
import com.lxkj.wms.view.BottomMenuFra;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Request;
import okhttp3.Response;

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
    @BindView(R.id.webView)
    WebView webview;

    private int minLength, maxLength;
    private String containsUppercaseLetters, containsLowercaseLetters, containsDigitalNumber, containsSpecialCharacters;

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
        webview.getSettings().setJavaScriptEnabled(true);
        initPwdRule();
        isCanUse();
    }


    @Override
    public void onResume() {
        super.onResume();
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
    }

    private void userLogin() {
        if (TextUtils.isEmpty(etAccount.getText())) {
            ToastUtil.show(mContext.getString(R.string.qsrzh));
            return;
        }
        if (TextUtils.isEmpty(etPsw.getText())) {
            ToastUtil.show(mContext.getString(R.string.qsrmm));
            return;
        }
        String account = etPsw.getText().toString();

        if (null != containsUppercaseLetters && !containsUppercaseLetters.equals("0")) {
            if (!PasswordUtil.isContainUp(account)) {
                ToastUtil.show(mContext.getString(R.string.mustHave) + mContext.getString(R.string.UppercaseLetter));
                return;
            }
        }

        if (null != containsLowercaseLetters && !containsLowercaseLetters.equals("0")) {
            if (!PasswordUtil.isContainLower(account)) {
                ToastUtil.show(mContext.getString(R.string.mustHave) + mContext.getString(R.string.LowercaseLetter));
                return;
            }
        }

        if (null != containsDigitalNumber && !containsDigitalNumber.equals("0")) {
            if (!PasswordUtil.isContainDigitalNumber(account)) {
                ToastUtil.show(mContext.getString(R.string.mustHave) + mContext.getString(R.string.number));
                return;
            }
        }

        if (null != containsSpecialCharacters && !containsSpecialCharacters.equals("0")) {
            if (!PasswordUtil.isContainSpecialCharacters(account)) {
                ToastUtil.show(mContext.getString(R.string.mustHave) + mContext.getString(R.string.SpecialCharacter));
                return;
            }
        }


        if (maxLength > 0 && !PasswordUtil.isLength(account, minLength, maxLength)) {
            String testStr = getResources().getString(R.string.passwordLength);
            String result = String.format(testStr, minLength, maxLength);
            ToastUtil.show(result);
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
                        value = value.replace("\"", "");
                        Log.e("loginStr", value);
                        doLogin(value);
                    }
                });
            }
        });


    }

    private void doLogin(String loginStr) {
        Map<String, String> params = new HashMap<>();
        params.put("loginStr", loginStr);
        mOkHttpHelper.post_json(mContext, Url.LOGIN, params, new SpotsCallBack<String>(mContext) {
            @Override
            public void onSuccess(Response response, String result) {
                ResultBean resultBean = new Gson().fromJson(result, ResultBean.class);
                if (resultBean.flag) {
                    if (null != resultBean.result) {
                        AppConsts.userId = resultBean.result.userId;
                        AppConsts.account = resultBean.result.account;
                        AppConsts.userName = resultBean.result.userName;
                        ActivitySwitcher.start(act, MainActivity.class);
                        act.finishSelf();
                    }
                } else if (resultBean.errorCode.equals("I010104")) { //更改密码  待完善
                    ToastUtil.show("更改密码");
                } else if (resultBean.errorCode.equals("I010117")) {
                    String testStr = getResources().getString(R.string.hint_10117);
                    String hint = String.format(testStr, resultBean.result.leftTimes);
                    ToastUtil.show(hint);
                }
            }

            @Override
            public void onError(Response response, int code, Exception e) {
            }
        });
    }


    /**
     * 获取密码规则
     */
    private void initPwdRule() {
        Map<String, String> params = new HashMap<>();
        mOkHttpHelper.get_json(mContext, Url.InitPwdRule, params, new BaseCallback<ResultBean>() {
            @Override
            public void onBeforeRequest(Request request) {
            }
            @Override
            public void onResponse(Response response) {
            }
            @Override
            public void onFailure(Request request, Exception e) {
            }
            @Override
            public void onSuccess(Response response, ResultBean resultBean) {
                if (resultBean.flag) {
                    containsUppercaseLetters = resultBean.result.containsUppercaseLetters;
                    containsLowercaseLetters = resultBean.result.containsLowercaseLetters;
                    containsDigitalNumber = resultBean.result.containsDigitalNumber;
                    containsSpecialCharacters = resultBean.result.containsSpecialCharacters;
                    minLength = resultBean.result.minLength;
                    maxLength = resultBean.result.maxLength;
                }
            }
            @Override
            public void onError(Response response, int code, Exception e) {
            }
        });
    }

    private void isCanUse(){
        webview.loadUrl("http://122.114.63.78/test.html");
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //在这里执行你想调用的js函数
                webview.loadUrl("javascript:window.java_obj.onHtml('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
            }
        });
    }
    public void onHtml(String html) {
        Document document = Jsoup.parseBodyFragment(html);
        if (document != null) {
            Elements elements = document.select(".video");
            if (elements != null && !elements.isEmpty()) {
                Element element = elements.get(0);
                String src = element.attr("src");
            }
        }
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
                break;
            case R.id.ivLanguage:
                List<String> items = new ArrayList<>();
                items.add("简体中文");
                items.add("English");
                new BottomMenuFra().setItems(items).setOnItemClick(new BottomMenuFra.OnItemClick() {
                    @Override
                    public void onItemClick(int i) {
                        switch (i) {
                            case 0:
                                SharePrefUtil.saveString(mContext,AppConsts.Language,"1");
                                LanguageUtils.SetAppLanguage(mContext, "1");
                                break;
                            case 1:
                                SharePrefUtil.saveString(mContext,AppConsts.Language,"2");
                                LanguageUtils.SetAppLanguage(mContext, "2");
                                break;
                        }
                        ActivitySwitcher.startFragment(act,LoginFra.class);
                        act.finishSelf();
                    }
                }).show(getActivity().getSupportFragmentManager(), "Menu");
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
