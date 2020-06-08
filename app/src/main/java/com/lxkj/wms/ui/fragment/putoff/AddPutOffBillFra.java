package com.lxkj.wms.ui.fragment.putoff;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.google.gson.Gson;
import com.lxkj.wms.R;
import com.lxkj.wms.bean.ResultBean;
import com.lxkj.wms.bean.TopBean;
import com.lxkj.wms.biz.ActivitySwitcher;
import com.lxkj.wms.http.OkHttpHelper;
import com.lxkj.wms.http.SpotsCallBack;
import com.lxkj.wms.http.Url;
import com.lxkj.wms.ui.activity.NaviActivity;
import com.lxkj.wms.ui.fragment.TitleFragment;
import com.lxkj.wms.utils.DateUtil;
import com.lxkj.wms.utils.KeyboardUtil;
import com.lxkj.wms.utils.ShowErrorCodeUtil;
import com.lxkj.wms.utils.ToastUtil;

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
 * Created by kxn on 2020/5/15 0015.
 */
public class AddPutOffBillFra extends TitleFragment implements NaviActivity.NaviRigthImageListener, View.OnClickListener {

    Unbinder unbinder;
    @BindView(R.id.etRemark)
    EditText etRemark;
    @BindView(R.id.tvSave)
    TextView tvSave;
    @BindView(R.id.etBarCode)
    EditText etBarCode;
    @BindView(R.id.tvPutOffDate)
    TextView tvPutOffDate;
    @BindView(R.id.tvProductCode)
    TextView tvProductCode;
    @BindView(R.id.tvWmsWarehouseId)
    TextView tvWmsWarehouseId;
    @BindView(R.id.tvWmsWarehouseDetailId)
    TextView tvWmsWarehouseDetailId;
    @BindView(R.id.tvGoodsName)
    TextView tvGoodsName;
    private String barCode;//条形码
    private String putOffDate;//上架日期
    private String wmsWarehouseDetailId;//储位
    private String remarks;//备注
    private String wmsStockId;//库存表ID
    private String wmsWarehouseId;//仓库Id
    private List<TopBean.ResultBean> topList;//储位列表
    private double djNum = 0;

    public String getTitleName() {
        return act.getString(R.string.appPutOffTitle);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fra_add_bill_putoff, container, false);
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
                        findInfoByBarCodeBillPutOff(etBarCode.getText().toString());
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
                tvProductCode.setText("");
                wmsStockId = null;
                wmsWarehouseId = null;
                tvWmsWarehouseId.setText("");
                tvWmsWarehouseDetailId.setText("");
            }
        });
        if (null != barCode)
            etBarCode.setText(barCode);
        /**
         * 设置事件的点击事件
         */
        tvSave.setOnClickListener(this);
        tvPutOffDate.setOnClickListener(this);
    }


    /**
     * 根据条形码查询入库所需相关信息接口
     */
    private void findInfoByBarCodeBillPutOff(String barCode) {
        Map<String, String> params = new HashMap<>();
        params.put("barCode", barCode);
        OkHttpHelper.getInstance().get_json(mContext, Url.findInfoByBarCodeBillPutOff, params, new SpotsCallBack<String>(mContext) {
            @Override
            public void onFailure(Request request, Exception e) {
            }

            @Override
            public void onSuccess(Response response, String result) {
                ResultBean resultBean = new Gson().fromJson(result, ResultBean.class);
                if (resultBean.flag) {
                    if (null != resultBean.result) {
                        tvGoodsName.setText(resultBean.result.goodsName);
                        tvProductCode.setText(resultBean.result.productCode);
                        wmsStockId = resultBean.result.wmsStockId;
                        wmsWarehouseId = resultBean.result.wmsWarehouseId;
                        wmsWarehouseDetailId = resultBean.result.wmsWarehouseDetailId;
                        tvWmsWarehouseId.setText(resultBean.result.wmsWarehouseName);
                        tvWmsWarehouseDetailId.setText(resultBean.result.wmsWarehouseDetailName);
                    }
                } else {
                    etBarCode.setText("");
                    tvGoodsName.setText("");
                    tvProductCode.setText("");
                    wmsStockId = null;
                    wmsWarehouseId = null;
                    tvWmsWarehouseId.setText("");
                    tvWmsWarehouseDetailId.setText("");
                    if (resultBean.errorCode.contains("?")) {
                        String[] error = resultBean.errorCode.split("\\?");
                        String errorCode = error[0];
                        Map<String, String> errorValues = ShowErrorCodeUtil.getErrorValue(error[1]);
                        String barCode = errorValues.get("barCode");
                        List<String> errors = new ArrayList<>();
                        switch (errorCode) {
                            case "SE120001":
                                errors.add(String.format(getResources().getString(R.string.SE120001), barCode));
                                break;
                            case "SE120002":
                                errors.add(String.format(getResources().getString(R.string.SE120002), barCode));
                                break;
                        }
                        if (errors.size() > 0)
                            ToastUtil.showCustom(mContext, errors);
                    } else {
                        List<String> errors = new ArrayList<>();
                        switch (resultBean.errorCode) {
                            default:
                                errors.add(ShowErrorCodeUtil.getErrorString(mContext, resultBean.errorCode));
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
    private void addBillPutOff() {
        Map<String, String> params = new HashMap<>();
        params.put("barCode", etBarCode.getText().toString());
        if (null != putOffDate)
            params.put("putOffDate", putOffDate);
        if (null != wmsWarehouseId)
            params.put("wmsWarehouseId", wmsWarehouseId);
        if (null != wmsWarehouseDetailId)
            params.put("wmsWarehouseDetailId", wmsWarehouseDetailId);
        if (!TextUtils.isEmpty(etRemark.getText()))
            params.put("remarks", etRemark.getText().toString());
        if (null != wmsStockId)
            params.put("wmsStockId", wmsStockId);
        OkHttpHelper.getInstance().post_json(mContext, Url.addBillPutOff, params, new SpotsCallBack<String>(mContext) {
            @Override
            public void onSuccess(Response response, String result) {
                ResultBean resultBean = new Gson().fromJson(result, ResultBean.class);
                if (resultBean.flag) {
                    ToastUtil.show(mContext.getResources().getString(R.string.seSave));
                    act.finishSelf();
                } else {
                    List<String> errors = new ArrayList<>();
                    switch (resultBean.errorCode) {
                        case "E000406":

                            if (null != resultBean.result.wmsWarehouseId)
                                errors.add(ShowErrorCodeUtil.getErrorString(mContext, resultBean.result.wmsWarehouseId));
                            if (null != resultBean.result.wmsStockId)
                                errors.add(ShowErrorCodeUtil.getErrorString(mContext, resultBean.result.wmsStockId));
                            if (null != resultBean.result.putOffDate)
                                errors.add(ShowErrorCodeUtil.getErrorString(mContext, resultBean.result.putOffDate));
                            if (null != resultBean.result.wmsWarehouseDetailId)
                                errors.add(ShowErrorCodeUtil.getErrorString(mContext, resultBean.result.wmsWarehouseDetailId));
                            if (null != resultBean.result.barCode)
                                errors.add(ShowErrorCodeUtil.getErrorString(mContext, resultBean.result.barCode));

                            break;
                        default:
                            errors.add(ShowErrorCodeUtil.getErrorString(mContext, resultBean.errorCode));
                            break;
                    }
                    if (errors.size() > 0)
                        ToastUtil.showCustom(mContext, errors);
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
        KeyboardUtil.hideKeyboard(act);
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        startDate.set(DateUtil.getYear() - 100, Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        final TimePickerView startTimePickerView = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                putOffDate = DateUtil.formatDate(date.getTime(), "yyyy-MM-dd");
                tvPutOffDate.setText(putOffDate);
            }
        })
                .setCancelColor(R.color.txt_lv1)//取消按钮文字颜色
                .setRangDate(startDate, endDate)//起始终止年月日设定
                .setDate(endDate)
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
        ActivitySwitcher.startFragment(act, HistoryPutOffFra.class);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvSave:
                addBillPutOff();
                break;
            case R.id.tvPutOffDate:
                selectDate();
                break;
        }
    }
}

