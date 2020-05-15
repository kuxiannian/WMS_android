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
import com.lxkj.wms.utils.ShowErrorCodeUtil;
import com.lxkj.wms.utils.ToastUtil;

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
                    findInfoByBarCodeBillPutOn(etBarCode.getText().toString());
            }
        });
        /**
         * 设置事件的点击事件
         */
        tvSave.setOnClickListener(this);
        tvPutOffDate.setOnClickListener(this);

    }


    /**
     * 根据条形码查询入库所需相关信息接口
     */
    private void findInfoByBarCodeBillPutOn(String barCode) {
        Map<String, String> params = new HashMap<>();
        params.put("barCode", barCode);
        OkHttpHelper.getInstance().get_json(mContext, Url.findInfoByBarCodeBillPutOff, params, new SpotsCallBack<ResultBean>(mContext) {

            @Override
            public void onFailure(Request request, Exception e) {
            }

            @Override
            public void onSuccess(Response response, ResultBean resultBean) {
                if (null != resultBean.result) {
                    tvGoodsName.setText(resultBean.result.goodsName);
                    tvProductCode.setText(resultBean.result.productCode);
                    wmsStockId = resultBean.result.wmsStockId;
                    wmsWarehouseId = resultBean.result.wmsWarehouseId;
                    tvWmsWarehouseId.setText(resultBean.result.wmsWarehouseName);
                    tvWmsWarehouseDetailId.setText(resultBean.result.wmsWarehouseDetailName);
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
        if (null != barCode)
            params.put("barCode", barCode);
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
                    switch (resultBean.errorCode) {
                        case "SE120001":
                            ToastUtil.show(String.format(getResources().getString(R.string.SE110001), resultBean.result.barCod, resultBean.result.code));
                            break;
                        case "SE120002":
                            ToastUtil.show(String.format(getResources().getString(R.string.SE110002), resultBean.result.code));
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
                putOffDate = DateUtil.formatDate(date.getTime(), "yyyy-MM-dd");
                tvPutOffDate.setText(putOffDate);
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
        ActivitySwitcher.startFragment(act, HistoryPutOffFra.class);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvSave:
                addBillPutOff();
                break;
            case R.id.tvPutOnDate:
                selectDate();
                break;
        }
    }
}
