package com.lxkj.wms.ui.fragment.ckjh;

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
import com.lxkj.wms.biz.ActivitySwitcher;
import com.lxkj.wms.http.OkHttpHelper;
import com.lxkj.wms.http.SpotsCallBack;
import com.lxkj.wms.http.Url;
import com.lxkj.wms.ui.activity.NaviActivity;
import com.lxkj.wms.ui.fragment.TitleFragment;
import com.lxkj.wms.utils.DateUtil;
import com.lxkj.wms.utils.ToastUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by kxn on 2020/4/25 0025.
 */
public class AddCkFra extends TitleFragment implements NaviActivity.NaviRigthImageListener, View.OnClickListener {

    Unbinder unbinder;
    @BindView(R.id.etBarCode)
    EditText etBarCode;
    @BindView(R.id.tvOutputDate)
    TextView tvOutputDate;
    @BindView(R.id.etConsignor)
    EditText etConsignor;
    @BindView(R.id.etConsignorPhone)
    EditText etConsignorPhone;
    @BindView(R.id.tvGoodsType)
    TextView tvGoodsType;
    @BindView(R.id.tvProductCode)
    TextView tvProductCode;
    @BindView(R.id.tvGoodsName)
    TextView tvGoodsName;
    @BindView(R.id.tvSuspicion)
    TextView tvSuspicion;
    @BindView(R.id.etRemark)
    EditText etRemark;
    @BindView(R.id.etSuspicionProblem)
    EditText etSuspicionProblem;
    @BindView(R.id.tvSave)
    TextView tvSave;
    @BindView(R.id.tvWmsWarehouseIdName)
    TextView tvWmsWarehouseIdName;
    @BindView(R.id.etLadingNumber)
    EditText etLadingNumber;

    private String outputDate, wmsStockId,barCode;

    public String getTitleName() {
        return act.getString(R.string.xzck);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fra_add_ck, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    private void initView() {
        barCode = getArguments().getString("barCode");
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
                if (!TextUtils.isEmpty(etBarCode.getText()))
                    findInfoByBarCode(etBarCode.getText().toString());
            }
        });
        if (null != barCode)
            etBarCode.setText(barCode);
        /**
         * 设置事件的点击事件
         */
        tvSave.setOnClickListener(this);
        tvOutputDate.setOnClickListener(this);
        tvWmsWarehouseIdName.setOnClickListener(this);
    }

    /**
     * 根据条形码查询出库所需相关信息接口
     */
    private void findInfoByBarCode(String barCode) {
        Map<String, String> params = new HashMap<>();
        params.put("barCode", barCode);
        OkHttpHelper.getInstance().get_json(mContext, Url.findInfoByBarCodeBillOut, params, new SpotsCallBack<ResultBean>(mContext) {

            @Override
            public void onFailure(Request request, Exception e) {
            }

            @Override
            public void onSuccess(Response response, ResultBean resultBean) {
                if (null != resultBean.result) {
                    tvGoodsName.setText(resultBean.result.goodsName);
                    tvGoodsType.setText(resultBean.result.goodsType);
                    tvProductCode.setText(resultBean.result.productCode);
                    tvWmsWarehouseIdName.setText(resultBean.result.wmsWarehouseIdName);
                    wmsStockId = resultBean.result.wmsStockId;
                    if (null != resultBean.result.suspicion) {
                        if (resultBean.result.suspicion.equals("1")) {//有嫌疑
                            tvSuspicion.setText(mContext.getString(R.string.suspicionY));
                            etSuspicionProblem.setEnabled(false);
                        } else {//无嫌疑
                            tvSuspicion.setText(mContext.getString(R.string.suspicionN));
                            etSuspicionProblem.setEnabled(true);
                        }
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

    private void addBillOutput() {
        Map<String, String> params = new HashMap<>();
        if (null != outputDate)
            params.put("outputDate", outputDate);
        if (null != wmsStockId)
            params.put("wmsStockId", wmsStockId);
        if (!TextUtils.isEmpty(etLadingNumber.getText().toString()))
            params.put("ladingNumber", etLadingNumber.getText().toString());
        if (!TextUtils.isEmpty(etConsignor.getText().toString()))
            params.put("consignor", etConsignor.getText().toString());
        if (!TextUtils.isEmpty(etConsignorPhone.getText().toString()))
            params.put("consignorPhone", etConsignorPhone.getText().toString());
        if (!TextUtils.isEmpty(etRemark.getText()))
            params.put("remarks", etRemark.getText().toString());
        if (!TextUtils.isEmpty(etSuspicionProblem.getText()))
            params.put("suspicionProblem", etSuspicionProblem.getText().toString());
        OkHttpHelper.getInstance().post_json(mContext, Url.addBillOutput, params, new SpotsCallBack<String>(mContext) {
            @Override
            public void onFailure(Request request, Exception e) {
            }

            @Override
            public void onSuccess(Response response, String result) {
                ResultBean resultBean = new Gson().fromJson(result, ResultBean.class);
                if (resultBean.flag) {
                    ToastUtil.show(mContext.getResources().getString(R.string.beSuccess));
                    act.finishSelf();
                } else {
                    switch (resultBean.errorCode) {
                        case "SE130001":
                            ToastUtil.show(String.format(getResources().getString(R.string.SE130001), etBarCode.getText().toString()));
                            break;
                        case "SE130002":
                            ToastUtil.show(String.format(getResources().getString(R.string.SE130002), etBarCode.getText().toString()));
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
                outputDate = DateUtil.formatDate(date.getTime(), "yyyy-MM-dd");
                tvOutputDate.setText(outputDate);
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
        ActivitySwitcher.startFragment(act, HistoryCkFra.class);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvSave:
                addBillOutput();
                break;
            case R.id.tvOutputDate:
                selectDate();
                break;
        }
    }
}
