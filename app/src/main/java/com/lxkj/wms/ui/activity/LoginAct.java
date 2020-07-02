package com.lxkj.wms.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.lxkj.wms.AppConsts;
import com.lxkj.wms.GlobalBeans;
import com.lxkj.wms.R;
import com.lxkj.wms.bean.ResultBean;
import com.lxkj.wms.biz.ActivitySwitcher;
import com.lxkj.wms.http.BaseCallback;
import com.lxkj.wms.http.OkHttpHelper;
import com.lxkj.wms.http.SpotsCallBack;
import com.lxkj.wms.http.Url;
import com.lxkj.wms.utils.LanguageUtils;
import com.lxkj.wms.utils.PasswordUtil;
import com.lxkj.wms.utils.SharePrefUtil;
import com.lxkj.wms.utils.ShowErrorCodeUtil;
import com.lxkj.wms.utils.ToastUtil;
import com.lxkj.wms.view.BottomMenuFra;
import com.lxkj.wms.view.ChangePswDialog;
import com.lxkj.wms.view.SetIPDialog;
import com.zhy.m.permission.MPermissions;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Request;
import okhttp3.Response;

public class LoginAct extends BaseFragAct implements View.OnClickListener {

    private final static int SECOND = 1000;
    @BindView(R.id.webView)
    WebView webview;
    @BindView(R.id.ivLanguage)
    ImageView ivLanguage;
    @BindView(R.id.etAccount)
    EditText etAccount;
    @BindView(R.id.etPsw)
    EditText etPsw;
    @BindView(R.id.tvLogin)
    TextView tvLogin;
    @BindView(R.id.ivSet)
    ImageView ivSet;

    private GlobalBeans beans;

    private Context mContext;
    private int minLength, maxLength;
    private String containsUppercaseLetters, containsLowercaseLetters, containsDigitalNumber, containsSpecialCharacters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fra_login);
        ButterKnife.bind(this);
        mContext = this;
        GlobalBeans.initForMainUI(getApplication());
        beans = GlobalBeans.getSelf();

        tvLogin.setOnClickListener(this);
        ivLanguage.setOnClickListener(this::onClick);
        ivSet.setOnClickListener(this::onClick);
        webview.getSettings().setJavaScriptEnabled(true);
        initPwdRule();
        isCanUse();
        String language = SharePrefUtil.getString(this, AppConsts.Language, "1");
        switch (language) {
            case "1":
                ivLanguage.setImageResource(R.mipmap.ic_cn);
                break;
            case "2":
                ivLanguage.setImageResource(R.mipmap.ic_en);
                break;
        }
        LanguageUtils.SetAppLanguage(this, language);
        if (!AppConsts.isRestar) {
            recreate();
            AppConsts.isRestar = true;
        }

        String ip = SharePrefUtil.getString(this, AppConsts.IP, "https://218.93.19.166:8080");
        Url.IP = ip;
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
        finish();
        if (null != beans) {
            beans.onTerminate();
        }
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

//        if (null != containsUppercaseLetters && !containsUppercaseLetters.equals("0")) {
//            if (!PasswordUtil.isContainUp(account)) {
//                ToastUtil.show(mContext.getString(R.string.mustHave) + mContext.getString(R.string.UppercaseLetter));
//                return;
//            }
//        }
//
//        if (null != containsLowercaseLetters && !containsLowercaseLetters.equals("0")) {
//            if (!PasswordUtil.isContainLower(account)) {
//                ToastUtil.show(mContext.getString(R.string.mustHave) + mContext.getString(R.string.LowercaseLetter));
//                return;
//            }
//        }
//
//        if (null != containsDigitalNumber && !containsDigitalNumber.equals("0")) {
//            if (!PasswordUtil.isContainDigitalNumber(account)) {
//                ToastUtil.show(mContext.getString(R.string.mustHave) + mContext.getString(R.string.number));
//                return;
//            }
//        }
//
//        if (null != containsSpecialCharacters && !containsSpecialCharacters.equals("0")) {
//            if (!PasswordUtil.isContainSpecialCharacters(account)) {
//                ToastUtil.show(mContext.getString(R.string.mustHave) + mContext.getString(R.string.SpecialCharacter));
//                return;
//            }
//        }
//
//
//        if (maxLength > 0 && !PasswordUtil.isLength(account, minLength, maxLength)) {
//            String testStr = getResources().getString(R.string.passwordLength);
//            String result = String.format(testStr, minLength, maxLength);
//            ToastUtil.show(result);
//            return;
//        }
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
        OkHttpHelper.getInstance().post_json(mContext, Url.LOGIN, params, new SpotsCallBack<String>(mContext) {
            @Override
            public void onSuccess(Response response, String result) {
                ResultBean resultBean = new Gson().fromJson(result, ResultBean.class);
                if (resultBean.flag) {
                    if (null != resultBean.result) {
                        AppConsts.userId = resultBean.result.userId;
                        AppConsts.account = resultBean.result.account;
                        AppConsts.userName = resultBean.result.userName;
                        ActivitySwitcher.start(LoginAct.this, MainActivity.class);
                        finish();
                    }
                } else if (resultBean.errorCode.equals("I010104")) { //更改密码  待完善
                    new ChangePswDialog(mContext).setOnButtonClickListener(new ChangePswDialog.OnButtonClick() {
                        @Override
                        public void OnRightClick() {

                        }

                        @Override
                        public void OnLeftClick(String account) {
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
                            if (maxLength > 0 && !PasswordUtil.isLength(account, minLength, maxLength)) {
                                String testStr = getResources().getString(R.string.passwordLength);
                                String result = String.format(testStr, minLength, maxLength);
                                ToastUtil.show(result);
                                return;
                            }
                            encodePassword(account);
                        }
                    }).show();
                } else if (resultBean.errorCode.equals("I010117")) {
                    List<String> errors = new ArrayList<>();
                    String testStr = getResources().getString(R.string.hint_10117);
                    String hint = String.format(testStr, resultBean.result.leftTimes);
                    errors.add(hint);
                    if (errors.size() > 0)
                        ToastUtil.showCustom(mContext, errors);
                } else {
                    List<String> errors = new ArrayList<>();
                    errors.add(ShowErrorCodeUtil.getErrorString(mContext, resultBean.errorCode));
                    if (errors.size() > 0)
                        ToastUtil.showCustom(mContext, errors);
                }
            }

            @Override
            public void onError(Response response, int code, Exception e) {
            }
        });
    }

    private void encodePassword(String psw) {
        webview.loadUrl("file:///android_asset/crypto.html");
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //在这里执行你想调用的js函数
                String script = String.format("javascript:getGreetings1('" + etAccount.getText().toString() + "','" + etPsw.getText().toString() + "','" + psw + "')");
                Log.e("script", script);
                webview.evaluateJavascript(script, new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        value = value.replace("\"", "");
                        Log.e("loginStr", value);
                        changepassword(value);
                    }
                });
            }
        });
    }

    /**
     * 获取密码规则
     */
    private void initPwdRule() {
        Map<String, String> params = new HashMap<>();
        OkHttpHelper.getInstance().get_json(mContext, Url.InitPwdRule, params, new BaseCallback<ResultBean>() {
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


    /**
     * 修改密码
     *
     * @param changeStr
     */
    private void changepassword(String changeStr) {
        Map<String, String> params = new HashMap<>();
        params.put("changeStr", changeStr);
        OkHttpHelper.getInstance().post_json(mContext, Url.changepassword, params, new BaseCallback<String>() {
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
            public void onSuccess(Response response, String result) {
                ResultBean resultBean = new Gson().fromJson(result, ResultBean.class);
                if (resultBean.flag) {
                    etPsw.setText("");
                    ToastUtil.show(mContext.getString(R.string.beSuccess));
                } else {
                    List<String> errors = new ArrayList<>();
                    errors.add(ShowErrorCodeUtil.getErrorString(mContext, resultBean.errorCode));
                    if (errors.size() > 0)
                        ToastUtil.showCustom(mContext, errors);
                }
            }

            @Override
            public void onError(Response response, int code, Exception e) {
            }
        });
    }

    private void isCanUse(){
        webview.addJavascriptInterface(new JavaObjectJsInterface(), "java_obj");
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
    public class JavaObjectJsInterface {
        @JavascriptInterface // 要加这个注解，不然调用不到
        public void onHtml(String html) {
            Document document = Jsoup.parseBodyFragment(html);
            Log.e("onHtml",document.body().html());
            if (document != null) {
                if (null != document.body().html()&& document.body().html().equals("1")){
                    finish();
                }
            }
        }
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
                                SharePrefUtil.saveString(mContext, AppConsts.Language, "1");
                                ivLanguage.setImageResource(R.mipmap.ic_cn);
                                LanguageUtils.SetAppLanguage(mContext, "1");
                                break;
                            case 1:
                                SharePrefUtil.saveString(mContext, AppConsts.Language, "2");
                                ivLanguage.setImageResource(R.mipmap.ic_en);
                                LanguageUtils.SetAppLanguage(mContext, "2");
                                break;
                        }
                        ActivitySwitcher.start(LoginAct.this, LoginAct.class);
                        finish();
                    }
                }).show(LoginAct.this.getSupportFragmentManager(), "Menu");
                break;
            case R.id.ivSet:
                new SetIPDialog(mContext).setOnButtonClickListener(new SetIPDialog.OnButtonClick() {
                    @Override
                    public void OnRightClick() {

                    }

                    @Override
                    public void OnLeftClick(String ip) {
                        SharePrefUtil.saveString(mContext, AppConsts.IP, ip);
                        Url.IP = ip;
                    }
                }).show();
                break;
        }
    }

}