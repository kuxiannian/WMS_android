package com.lxkj.wms.ui.fragment.rksh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.google.gson.Gson;
import com.lxkj.wms.R;
import com.lxkj.wms.bean.ResultBean;
import com.lxkj.wms.biz.ActivitySwitcher;
import com.lxkj.wms.http.OkHttpHelper;
import com.lxkj.wms.http.SpotsCallBack;
import com.lxkj.wms.http.Url;
import com.lxkj.wms.ui.activity.NaviActivity;
import com.lxkj.wms.ui.fragment.TitleFragment;
import com.lxkj.wms.utils.DateUtil;
import com.lxkj.wms.utils.ShowErrorCodeUtil;
import com.lxkj.wms.utils.StringUtil;
import com.lxkj.wms.utils.ToastUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by kxn on 2020/4/25 0025.
 */
public class AddRkFra extends TitleFragment implements NaviActivity.NaviRigthImageListener, View.OnClickListener {

    Unbinder unbinder;
    @BindView(R.id.etBarCode)
    EditText etBarCode;
    @BindView(R.id.tvInputDate)
    TextView tvInputDate;
    @BindView(R.id.tvWmsWarehouseIdName)
    TextView tvWmsWarehouseIdName;
    @BindView(R.id.etPalletNumber)
    EditText etPalletNumber;
    @BindView(R.id.tvWeight)
    EditText tvWeight;
    @BindView(R.id.ivAdd)
    ImageView ivAdd;
    @BindView(R.id.ivReduce)
    ImageView ivReduce;
    @BindView(R.id.tvGoodsType)
    TextView tvGoodsType;
    @BindView(R.id.tvProductCode)
    TextView tvProductCode;
    @BindView(R.id.tvGoodsName)
    TextView tvGoodsName;
    @BindView(R.id.etRemark)
    EditText etRemark;
    @BindView(R.id.tvSave)
    TextView tvSave;
    private String barCode;//条形码
    private String inputDate;//入库日期
    private String palletNumber;//托盘号
    private String weight;//重量
    private double djNum = 0;

    public String getTitleName() {
        return act.getString(R.string.xzrk);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fra_add_rk, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    private void initView() {
        barCode = getArguments().getString("barCode");
        etBarCode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    if (null != etBarCode && !TextUtils.isEmpty(etBarCode.getText()))
                        findInfoByBarCode(etBarCode.getText().toString());
                }
            }
        });
        //条形码输入框 输入监听
        etBarCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                tvGoodsName.setText("");
                tvGoodsType.setText("");
                tvProductCode.setText("");
                tvWmsWarehouseIdName.setText("");
                tvWeight.setText("");
            }
        });
        if (null != barCode)
            etBarCode.setText(barCode);
        /**
         * 设置事件的点击事件
         */
        tvSave.setOnClickListener(this);
        tvInputDate.setOnClickListener(this);
        tvWmsWarehouseIdName.setOnClickListener(this);
        ivAdd.setOnClickListener(this);
        ivReduce.setOnClickListener(this);
    }


    /**
     * 根据条形码查询入库所需相关信息接口
     */
    private void findInfoByBarCode(String barCode) {
        Map<String, String> params = new HashMap<>();
        params.put("barCode", barCode);
        OkHttpHelper.getInstance().get_json(mContext, Url.findInfoByBarCode, params, new SpotsCallBack<String>(mContext) {

            @Override
            public void onFailure(Request request, Exception e) {
            }

            @Override
            public void onSuccess(Response response, String result) {
                ResultBean resultBean = new Gson().fromJson(result, ResultBean.class);
                if (resultBean.flag) {
                    if (null != resultBean.result) {
                        tvGoodsName.setText(resultBean.result.goodsName);
                        tvGoodsType.setText(ShowErrorCodeUtil.getGoodsType(mContext,resultBean.result.goodsType));
                        tvProductCode.setText(resultBean.result.productCode);
                        tvWmsWarehouseIdName.setText(resultBean.result.wmsWarehouseIdName);
                    }
                } else {
                    etBarCode.setText("");
                    if (resultBean.errorCode.contains("?")) {
                        String[] error = resultBean.errorCode.split("\\?");
                        String errorCode = error[0];
                        Map<String, String> errorValues = ShowErrorCodeUtil.getErrorValue(error[1]);
                        String barCode = errorValues.get("barCode");
                        List<String> errors = new ArrayList<>();
                        switch (errorCode) {
                            case "SE100001":
                                errors.add(String.format(getResources().getString(R.string.SE100001), barCode));
                                break;
                            case "SE100002":
                                errors.add(String.format(getResources().getString(R.string.SE100002), barCode));
                                break;
                            case "SE100003":
                                errors.add(String.format(getResources().getString(R.string.SE100003), barCode));
                                break;
                            case "SE100004":
                                errors.add(String.format(getResources().getString(R.string.SE100004), barCode));
                                break;
                            case "SE100005":
                                errors.add(String.format(getResources().getString(R.string.SE100005), barCode));
                                break;
                            case "SE100007":
                                errors.add(String.format(getResources().getString(R.string.SE100007), barCode));
                                break;
                            case "SE100008":
                                errors.add(String.format(getResources().getString(R.string.SE100008), barCode));
                                break;
                            case "SE100009":
                                errors.add(String.format(getResources().getString(R.string.SE100009), barCode));
                                break;
                        }
                        if (errors.size() > 0)
                            ToastUtil.showCustom(mContext, errors);
                    } else {
                        List<String> errors = new ArrayList<>();
                        switch (resultBean.errorCode) {
                            case "SE100006":
                                errors.add(getResources().getString(R.string.SE100006));
                                break;
                        }
                        if (errors.size() > 0)
                            ToastUtil.showCustom(mContext, errors);
                    }
                }
            }

            @Override
            public void onError(Response response, int code, Exception e) {
            }
        });
    }

    /**
     * 新增数据接口
     */
    private void addBillInput() {
        Map<String, String> params = new HashMap<>();
        if (!TextUtils.isEmpty(etBarCode.getText()))
            params.put("barCode", etBarCode.getText().toString());
        if (null != inputDate)
            params.put("inputDate", inputDate);
        if (!StringUtil.isEmpty(etPalletNumber.getText().toString()))
            params.put("palletNumber", etPalletNumber.getText().toString());
        if (!StringUtil.isEmpty(tvWeight.getText().toString()))
            params.put("weight", tvWeight.getText().toString());
        if (!TextUtils.isEmpty(etRemark.getText()))
            params.put("remarks", etRemark.getText().toString());
        OkHttpHelper.getInstance().post_json(mContext, Url.addBillInput, params, new SpotsCallBack<String>(mContext) {
            @Override
            public void onFailure(Request request, Exception e) {
            }

            @Override
            public void onSuccess(Response response, String result) {
                ResultBean resultBean = new Gson().fromJson(result, ResultBean.class);
                if (resultBean.flag) {
                    ToastUtil.show(mContext.getResources().getString(R.string.seSave));
                    act.finishSelf();
                } else {
                    if (resultBean.errorCode.contains("?")) {
                        String[] error = resultBean.errorCode.split("\\?");
                        String errorCode = error[0];
                        Map<String, String> errorValues = ShowErrorCodeUtil.getErrorValue(error[1]);
                        String barCode = errorValues.get("barCode");
                        switch (errorCode) {
                            case "SE100001":
                                ToastUtil.show(String.format(getResources().getString(R.string.SE100001), barCode));
                                break;
                            case "SE100002":
                                ToastUtil.show(String.format(getResources().getString(R.string.SE100002), barCode));
                                break;
                            case "SE100003":
                                ToastUtil.show(String.format(getResources().getString(R.string.SE100003), barCode));
                                break;
                            case "SE100004":
                                ToastUtil.show(String.format(getResources().getString(R.string.SE100004), barCode));
                                break;
                            case "SE100005":
                                ToastUtil.show(String.format(getResources().getString(R.string.SE100005), barCode));
                                break;
                            case "SE100007":
                                ToastUtil.show(String.format(getResources().getString(R.string.SE100007), barCode));
                                break;
                            case "SE100008":
                                ToastUtil.show(String.format(getResources().getString(R.string.SE100008), barCode));
                                break;
                            case "SE100009":
                                ToastUtil.show(String.format(getResources().getString(R.string.SE100009), barCode));
                                break;
                        }
                    }
                    switch (resultBean.errorCode) {
                        case "E000406":
                            List<String> errors = new ArrayList<>();
                            if (null != resultBean.result.palletNumber)
                                errors.add(ShowErrorCodeUtil.getErrorString(mContext, resultBean.result.palletNumber));
                            if (null != resultBean.result.barCode)
                                errors.add(ShowErrorCodeUtil.getErrorString(mContext, resultBean.result.barCode));
                            if (null != resultBean.result.inputDate)
                                errors.add(ShowErrorCodeUtil.getErrorString(mContext, resultBean.result.inputDate));
                            if (null != resultBean.result.weight)
                                errors.add(ShowErrorCodeUtil.getErrorString(mContext, resultBean.result.weight));
                            ToastUtil.showCustom(mContext, errors);
                            break;
                        default:
                            ShowErrorCodeUtil.showError(mContext, resultBean.errorCode);
                            break;
                    }
                }
            }

            @Override
            public void onError(Response response, int code, Exception e) {
            }
        });
    }


    /**
     * 选择日期
     */
    private void selectDate() {
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        endDate.set(DateUtil.getYear() + 5, Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        final TimePickerView startTimePickerView = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                inputDate = DateUtil.formatDate(date.getTime(), "yyyy-MM-dd");
                tvInputDate.setText(inputDate);
            }
        })
                .setCancelColor(R.color.txt_lv1)//取消按钮文字颜色
                .setRangDate(startDate, endDate)//起始终止年月日设定
                .setTextColorCenter(0xffFF8201)
                .setTitleBgColor(0xffffffff)
                .setSubmitColor(0xffFF8201)
                .setType(new boolean[]{true, true, true, false, false, false})
                .build();
        startTimePickerView.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvSave:
                addBillInput();
                break;
            case R.id.tvInputDate:
                selectDate();
                break;
            case R.id.ivAdd:
                if (!TextUtils.isEmpty(tvWeight.getText()))
                    djNum = Double.parseDouble(tvWeight.getText().toString());
                else
                    djNum = 0;
                djNum++;
                tvWeight.setText(new BigDecimal(djNum + "").setScale(3, RoundingMode.FLOOR).toString());
                break;
            case R.id.ivReduce:
                if (!TextUtils.isEmpty(tvWeight.getText()))
                    djNum = Double.parseDouble(tvWeight.getText().toString());
                else {
                    djNum = 0;

                }

                if (djNum > 0)
                    djNum--;

                if (djNum > 0)
                    tvWeight.setText(new BigDecimal(djNum + "").setScale(3, RoundingMode.FLOOR).toString());
                else
                    tvWeight.setText("");
                break;
        }
    }
}
