package com.lxkj.wms.ui.fragment.puton;

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
import com.lxkj.wms.bean.TopBean;
import com.lxkj.wms.biz.ActivitySwitcher;
import com.lxkj.wms.http.OkHttpHelper;
import com.lxkj.wms.http.SpotsCallBack;
import com.lxkj.wms.http.Url;
import com.lxkj.wms.ui.activity.NaviActivity;
import com.lxkj.wms.ui.fragment.TitleFragment;
import com.lxkj.wms.utils.DateUtil;
import com.lxkj.wms.utils.ListUtil;
import com.lxkj.wms.utils.ShowErrorCodeUtil;
import com.lxkj.wms.utils.StringUtil;
import com.lxkj.wms.utils.ToastUtil;
import com.lxkj.wms.view.SingleChooseDialog;

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
 * Created by kxn on 2020/5/14 0014.
 */
public class AddPutOnBillFra extends TitleFragment implements NaviActivity.NaviRigthImageListener, View.OnClickListener {

    Unbinder unbinder;
    @BindView(R.id.etBarCode)
    EditText etBarCode;
    @BindView(R.id.tvPutOnDate)
    TextView tvPutOnDate;
    @BindView(R.id.etWeight)
    EditText etWeight;
    @BindView(R.id.ivAdd)
    ImageView ivAdd;
    @BindView(R.id.ivReduce)
    ImageView ivReduce;
    @BindView(R.id.tvPalletNumber)
    TextView tvPalletNumber;
    @BindView(R.id.tvProductCode)
    TextView tvProductCode;
    @BindView(R.id.tvGoodsName)
    TextView tvGoodsName;
    @BindView(R.id.etLength)
    EditText etLength;
    @BindView(R.id.ivAddLength)
    ImageView ivAddLength;
    @BindView(R.id.ivReduceLength)
    ImageView ivReduceLength;
    @BindView(R.id.etWidth)
    EditText etWidth;
    @BindView(R.id.ivAddWidth)
    ImageView ivAddWidth;
    @BindView(R.id.ivReduceWidth)
    ImageView ivReduceWidth;
    @BindView(R.id.etHeight)
    EditText etHeight;
    @BindView(R.id.ivAddHeight)
    ImageView ivAddHeight;
    @BindView(R.id.ivReduceHeight)
    ImageView ivReduceHeight;
    @BindView(R.id.tvWmsWarehouseId)
    TextView tvWmsWarehouseId;
    @BindView(R.id.tvWmsWarehouseDetailId)
    TextView tvWmsWarehouseDetailId;
    @BindView(R.id.etRemark)
    EditText etRemark;
    @BindView(R.id.tvSave)
    TextView tvSave;
    private String barCode;//条形码
    private String putOnDate;//上架日期
    private String palletNumber;//托盘号
    private String weight;//重量
    private String length;//长
    private String width;//宽
    private String height;//高
    private String wmsWarehouseDetailId;//储位
    private String remarks;//备注
    private String wmsStockId;//库存表ID
    private String wmsWarehouseId;//仓库Id
    private List<TopBean.ResultBean> topList;//储位列表
    private double djNum = 0;

    public String getTitleName() {
        return act.getString(R.string.appAddTitle);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fra_add_bill_puton, container, false);
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
                    findInfoByBarCodeBillPutOn(etBarCode.getText().toString());
            }
        });
        etLength.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(etBarCode.getText()))
                    findTopByPriority();
            }
        });
        etWidth.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(etBarCode.getText()))
                    findTopByPriority();
            }
        });
        etHeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(etBarCode.getText()))
                    findTopByPriority();
            }
        });

        if (null != barCode)
            etBarCode.setText(barCode);
        /**
         * 设置事件的点击事件
         */
        tvSave.setOnClickListener(this);
        tvPutOnDate.setOnClickListener(this);
        tvWmsWarehouseDetailId.setOnClickListener(this);
        ivAdd.setOnClickListener(this);
        ivReduce.setOnClickListener(this);
        ivAddLength.setOnClickListener(this);
        ivReduceLength.setOnClickListener(this);
        ivAddWidth.setOnClickListener(this);
        ivReduceWidth.setOnClickListener(this);
        ivAddHeight.setOnClickListener(this);
        ivReduceHeight.setOnClickListener(this);

    }


    /**
     * 根据条形码查询入库所需相关信息接口
     */
    private void findInfoByBarCodeBillPutOn(String barCode) {
        Map<String, String> params = new HashMap<>();
        params.put("barCode", barCode);
        OkHttpHelper.getInstance().get_json(mContext, Url.findInfoByBarCodeBillPutOn, params, new SpotsCallBack<ResultBean>(mContext) {

            @Override
            public void onFailure(Request request, Exception e) {
            }

            @Override
            public void onSuccess(Response response, ResultBean resultBean) {
                if (null != resultBean.result) {
                    tvGoodsName.setText(resultBean.result.goodsName);
                    tvPalletNumber.setText(resultBean.result.palletNumber);
                    tvProductCode.setText(resultBean.result.productCode);
                    wmsStockId = resultBean.result.wmsStockId;
                    wmsWarehouseId = resultBean.result.wmsWarehouseId;
                    findTopByPriority();
                }
            }

            @Override
            public void onError(Response response, int code, Exception e) {
            }
        });
    }

    /**
     * 上架时输入长、宽、高自动分配储位接口
     */
    private void findTopByPriority() {
        if (TextUtils.isEmpty(etLength.getText()) || TextUtils.isEmpty(etWidth.getText()) || TextUtils.isEmpty(etHeight.getText()) || null != wmsWarehouseId)
            return;
        Map<String, String> params = new HashMap<>();
        params.put("length", etLength.getText().toString());//长
        params.put("width", etWidth.getText().toString());//宽
        params.put("height", etHeight.getText().toString());//高
        params.put("wmsWarehouseId", wmsWarehouseId);//仓库ID
        OkHttpHelper.getInstance().get_json(mContext, Url.findTopByPriority, params, new SpotsCallBack<TopBean>(mContext) {


            @Override
            public void onSuccess(Response response, TopBean resultBean) {
                if (resultBean.flag) {
                    if (null != resultBean.getResult()) {
                        topList = resultBean.getResult();
                        if (resultBean.getResult().size() == 1) {
                            wmsWarehouseDetailId = resultBean.getResult().get(0).getId();
                            tvWmsWarehouseDetailId.setText(resultBean.getResult().get(0).getCode());
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
    private void addBillPutOn() {
        Map<String, String> params = new HashMap<>();
        if (null != barCode)
            params.put("barCode", barCode);
        if (null != putOnDate)
            params.put("putOnDate", putOnDate);
        if (StringUtil.isEmpty(etWeight.getText().toString()))
            params.put("weight", etWeight.getText().toString());
        if (StringUtil.isEmpty(etHeight.getText().toString()))
            params.put("length", etHeight.getText().toString());
        if (StringUtil.isEmpty(etHeight.getText().toString()))
            params.put("width", etHeight.getText().toString());
        if (StringUtil.isEmpty(etHeight.getText().toString()))
            params.put("height", etHeight.getText().toString());
        if (null != wmsWarehouseDetailId)
            params.put("wmsWarehouseDetailId", wmsWarehouseDetailId);
        if (!TextUtils.isEmpty(etRemark.getText()))
            params.put("remarks", etRemark.getText().toString());
        if (null != wmsStockId)
            params.put("wmsStockId", wmsStockId);
        OkHttpHelper.getInstance().post_json(mContext, Url.addBillPutOn, params, new SpotsCallBack<String>(mContext) {
            @Override
            public void onSuccess(Response response, String result) {
                ResultBean resultBean = new Gson().fromJson(result, ResultBean.class);
                if (resultBean.flag) {
                    ToastUtil.show(mContext.getResources().getString(R.string.seSave));
                    act.finishSelf();
                } else {
                    switch (resultBean.errorCode) {
                        case "SE110001":
                            ToastUtil.show(String.format(getResources().getString(R.string.SE110001), resultBean.result.barCod, resultBean.result.code));
                            break;
                        case "SE110002":
                            ToastUtil.show(String.format(getResources().getString(R.string.SE110002), resultBean.result.code));
                            break;

                        case "E000406":
                            List<String> errors = new ArrayList<>();
                            if (null != resultBean.result.wmsStockId)
                                errors.add(ShowErrorCodeUtil.getErrorString(mContext,resultBean.result.wmsStockId));
                            if (null != resultBean.result.wmsWarehouseId)
                                errors.add(ShowErrorCodeUtil.getErrorString(mContext,resultBean.result.wmsWarehouseId));
                            if (null != resultBean.result.putOnDate)
                                errors.add(ShowErrorCodeUtil.getErrorString(mContext,resultBean.result.putOnDate));
                            if (null != resultBean.result.weight)
                                errors.add(ShowErrorCodeUtil.getErrorString(mContext,resultBean.result.weight));
                            if (null != resultBean.result.wmsWarehouseDetailId)
                                errors.add(ShowErrorCodeUtil.getErrorString(mContext,resultBean.result.wmsWarehouseDetailId));
                            if (null != resultBean.result.barCode)
                                errors.add(ShowErrorCodeUtil.getErrorString(mContext,resultBean.result.barCode));
                            ToastUtil.showCustom(mContext,errors);
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
                putOnDate = DateUtil.formatDate(date.getTime(), "yyyy-MM-dd");
                tvPutOnDate.setText(putOnDate);
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
        ActivitySwitcher.startFragment(act, HistoryPutOnFra.class);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvSave:
                addBillPutOn();
                break;
            case R.id.tvPutOnDate:
                selectDate();
                break;
            case R.id.ivAdd:
                if (TextUtils.isEmpty(etWeight.getText()))
                    djNum = 0;
                else
                    djNum = Double.parseDouble(etWeight.getText().toString());
                djNum++;
                etWeight.setText(djNum + "");
                break;
            case R.id.ivReduce:
                if (TextUtils.isEmpty(etWeight.getText()))
                    djNum = 0;
                else
                    djNum = Double.parseDouble(etWeight.getText().toString());
                if (djNum > 0)
                    djNum--;
                etWeight.setText(djNum + "");
                break;
            case R.id.ivAddLength:
                if (TextUtils.isEmpty(etHeight.getText()))
                    djNum = 0;
                else
                    djNum = Double.parseDouble(etHeight.getText().toString());
                djNum++;
                etHeight.setText(djNum + "");
                break;
            case R.id.ivReduceLength:
                if (TextUtils.isEmpty(etLength.getText()))
                    djNum = 0;
                else
                    djNum = Double.parseDouble(etLength.getText().toString());
                if (djNum > 0)
                    djNum--;
                etLength.setText(djNum + "");
                break;
            case R.id.ivAddWidth:
                if (TextUtils.isEmpty(etWidth.getText()))
                    djNum = 0;
                else
                    djNum = Double.parseDouble(etWidth.getText().toString());
                djNum++;
                etWidth.setText(djNum + "");
                break;
            case R.id.ivReduceWidth:
                if (TextUtils.isEmpty(etWidth.getText()))
                    djNum = 0;
                else
                    djNum = Double.parseDouble(etWidth.getText().toString());
                if (djNum > 0)
                    djNum--;
                etWidth.setText(djNum + "");
                break;
            case R.id.ivAddHeight:
                if (TextUtils.isEmpty(etHeight.getText()))
                    djNum = 0;
                else
                    djNum = Double.parseDouble(etHeight.getText().toString());
                djNum++;
                etHeight.setText(djNum + "");
                break;
            case R.id.ivReduceHeight:
                if (TextUtils.isEmpty(etHeight.getText()))
                    djNum = 0;
                else
                    djNum = Double.parseDouble(etHeight.getText().toString());
                if (djNum > 0)
                    djNum--;
                etHeight.setText(djNum + "");
                break;
            case R.id.tvWmsWarehouseDetailId:
                if (ListUtil.isEmpty(topList))
                    return;
                List<String> codeList = new ArrayList<>();
                for (int i = 0; i < topList.size(); i++) {
                    codeList.add(topList.get(i).getCode());
                }
                SingleChooseDialog addressDialog = new SingleChooseDialog(mContext, codeList, new SingleChooseDialog.OnItemClick() {
                    @Override
                    public void onItemClick(int position) {
                        tvWmsWarehouseDetailId.setText(codeList.get(position));
                        wmsWarehouseDetailId = topList.get(position).getId();
                    }
                });
                addressDialog.show();
                break;
        }
    }
}
