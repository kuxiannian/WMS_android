package com.lxkj.wms.ui.fragment.kccx;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.lxkj.wms.R;
import com.lxkj.wms.bean.WareHouseBean;
import com.lxkj.wms.biz.ActivitySwitcher;
import com.lxkj.wms.event.StockSxEvent;
import com.lxkj.wms.http.BaseCallback;
import com.lxkj.wms.http.OkHttpHelper;
import com.lxkj.wms.http.Url;
import com.lxkj.wms.ui.activity.NaviActivity;
import com.lxkj.wms.ui.fragment.TitleFragment;
import com.lxkj.wms.ui.fragment.hwdj.HistoryHdFra;
import com.lxkj.wms.utils.DateUtil;
import com.lxkj.wms.utils.ListUtil;
import com.lxkj.wms.view.SingleChooseDialog;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
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
public class ShaiXuanKcFra extends TitleFragment implements NaviActivity.NaviRigthImageListener, View.OnClickListener {
    Unbinder unbinder;
    @BindView(R.id.etBarCode)
    EditText etBarCode;
    @BindView(R.id.etAwb)
    EditText etAwb;
    @BindView(R.id.tvGoodsType)
    TextView tvGoodsType;
    @BindView(R.id.etGoodsName)
    EditText etGoodsName;
    @BindView(R.id.etProductCode)
    EditText etProductCode;
    @BindView(R.id.tvWmsWarehouseId)
    TextView tvWmsWarehouseId;
    @BindView(R.id.etWmsWarehouseDetailName)
    EditText etWmsWarehouseDetailName;
    @BindView(R.id.tvStockState)
    TextView tvStockState;
    @BindView(R.id.tvInputDateStart)
    TextView tvInputDateStart;
    @BindView(R.id.tvInputDateEnd)
    TextView tvInputDateEnd;
    @BindView(R.id.tvOutputDateStart)
    TextView tvOutputDateStart;
    @BindView(R.id.tvOutputDateEnd)
    TextView tvOutputDateEnd;
    @BindView(R.id.tvInStockDayStart)
    EditText tvInStockDayStart;
    @BindView(R.id.ivAddStockDayStart)
    ImageView ivAddStockDayStart;
    @BindView(R.id.ivReduceStockDayStart)
    ImageView ivReduceStockDayStart;
    @BindView(R.id.tvInStockDayEnd)
    EditText tvInStockDayEnd;
    @BindView(R.id.ivAddStockDayEnd)
    ImageView ivAddStockDayEnd;
    @BindView(R.id.ivReduceStockDayEnd)
    ImageView ivReduceStockDayEnd;
    @BindView(R.id.etUpdaterName)
    EditText etUpdaterName;
    @BindView(R.id.tvUpdateDateStart)
    TextView tvUpdateDateStart;
    @BindView(R.id.tvUpdateDateEnd)
    TextView tvUpdateDateEnd;
    @BindView(R.id.tvCx)
    TextView tvCx;

    private int djNum = 0;

    private String barCode;//	条形码
    private String awb;//	AWB号
    private String goodsType;//	货物分类
    private String goodsName;//	货物名称
    private String productCode;//	商品代号
    private String wmsWarehouseId;//	仓库
    private String wmsWarehouseDetailName;//	储位
    private String stockState;//	库存状态
    private String inputDateStart;//	入库开始日期
    private String inputDateEnd;//	入库结束日期
    private String outputDateStart;//	出库开始日期
    private String outputDateEnd;//	出库结束日期
    private String inStockDayStart;//	在库天数（最小天数）
    private String inStockDayEnd;//	在库天数（最大天数）
    private String updaterName;//	最后操作人
    private String updateDateStart;//	最后操作开始日期
    private String updateDateEnd;//	最后操作结束日期

    private List<WareHouseBean.ResultBean> wareHouseList;
    private List<String> goodsTypeList;
    private List<String> stockStateList;
    private int type = 0; //时间筛选标识 0 inputDateStart，1 inputDateEnd，2 updateDateStart，3 updateDateEnd,4 outputDateStart，5 outputDateEnd

    @Override
    public String getTitleName() {
        return act.getString(R.string.jlsx);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fra_jlsx_kc, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    private void initView() {
        tvWmsWarehouseId.setOnClickListener(this);
        tvGoodsType.setOnClickListener(this::onClick);
        tvInputDateStart.setOnClickListener(this::onClick);
        tvInputDateEnd.setOnClickListener(this::onClick);
        tvUpdateDateStart.setOnClickListener(this::onClick);
        tvUpdateDateEnd.setOnClickListener(this::onClick);
        tvOutputDateStart.setOnClickListener(this::onClick);
        tvOutputDateEnd.setOnClickListener(this::onClick);
        wareHouseList = new ArrayList<>();
        goodsTypeList = new ArrayList<>();
        stockStateList = new ArrayList<>();
        goodsTypeList.add(mContext.getString(R.string.goodsTypeA));
        goodsTypeList.add(mContext.getString(R.string.goodsTypeB));
        goodsTypeList.add(mContext.getString(R.string.goodsTypeC));
        stockStateList.add(mContext.getString(R.string.InStock));
        stockStateList.add(mContext.getString(R.string.Outbound));
        ivAddStockDayStart.setOnClickListener(this);
        ivReduceStockDayStart.setOnClickListener(this);
        ivAddStockDayEnd.setOnClickListener(this);
        ivReduceStockDayEnd.setOnClickListener(this);
        tvStockState.setOnClickListener(this);
        tvCx.setOnClickListener(this);
        findWarehouseListStock();
    }

    /**
     * 查询仓库列表接口
     */
    private void findWarehouseListStock() {
        Map<String, String> params = new HashMap<>();
        OkHttpHelper.getInstance().get_json(mContext, Url.findWarehouseListStock, params, new BaseCallback<WareHouseBean>() {
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
            public void onSuccess(Response response, WareHouseBean resultBean) {
                if (resultBean.flag) {
                    if (null != resultBean.getResult()) {
                        wareHouseList = resultBean.getResult();
                        if (resultBean.getResult().size() == 1) {
                            wmsWarehouseId = resultBean.getResult().get(0).getId();
                            tvWmsWarehouseId.setText(resultBean.getResult().get(0).getName());
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
     * 选择日期
     */
    private void selectDate() {
//        Calendar startDate = Calendar.getInstance();
//        Calendar endDate = Calendar.getInstance();
//        startDate.set(DateUtil.getYear() - 10, Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        final TimePickerView startTimePickerView = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                switch (type) {
                    case 0://inputDateStart
                        inputDateStart = DateUtil.formatDate(date.getTime(), "yyyy-MM-dd");
                        tvInputDateStart.setText(inputDateStart);
                        break;
                    case 1://inputDateEnd
                        inputDateEnd = DateUtil.formatDate(date.getTime(), "yyyy-MM-dd");
                        tvInputDateEnd.setText(inputDateEnd);
                        break;
                    case 2://updateDateStart
                        updateDateStart = DateUtil.formatDate(date.getTime(), "yyyy-MM-dd");
                        tvUpdateDateStart.setText(updateDateStart);
                        break;
                    case 3://updateDateEnd
                        updateDateEnd = DateUtil.formatDate(date.getTime(), "yyyy-MM-dd");
                        tvUpdateDateEnd.setText(updateDateEnd);
                        break;
                    case 4://outputDateStart
                        outputDateStart = DateUtil.formatDate(date.getTime(), "yyyy-MM-dd");
                        tvOutputDateStart.setText(outputDateStart);
                        break;
                    case 5://outputDateEnd
                        outputDateEnd = DateUtil.formatDate(date.getTime(), "yyyy-MM-dd");
                        tvOutputDateEnd.setText(outputDateEnd);
                        break;

                }
            }
        })
                .setCancelColor(R.color.txt_lv1)//取消按钮文字颜色
//                .setRangDate(startDate, endDate)//起始终止年月日设定
//                .setDate(endDate)
                .setTextColorCenter(0xffFF8201)
                .setTitleBgColor(0xffffffff)
                .setSubmitColor(0xffFF8201)
                .setSubCalSize(22)
                .setType(new boolean[]{true, true, true, false, false, false})
                .build();
        startTimePickerView.show();
    }

    @Override
    public int rightImg() {
        return R.mipmap.ic_time;
    }

    @Override
    public void onRightClicked(View v) {
        ActivitySwitcher.startFragment(act, HistoryHdFra.class);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivAddStockDayStart:
                if (TextUtils.isEmpty(tvInStockDayStart.getText()))
                    djNum = 0;
                else
                    djNum = Integer.parseInt(tvInStockDayStart.getText().toString());
                if (djNum < 9999999)
                    djNum++;
                inStockDayStart = djNum + "";
                tvInStockDayStart.setText(djNum + "");
                break;
            case R.id.ivReduceStockDayStart:
                if (TextUtils.isEmpty(tvInStockDayStart.getText()))
                    djNum = 0;
                else
                    djNum = Integer.parseInt(tvInStockDayStart.getText().toString());
                if (djNum > 0)
                    djNum--;
                inStockDayStart = djNum + "";
                tvInStockDayStart.setText(djNum + "");
                break;
            case R.id.ivAddStockDayEnd:
                if (TextUtils.isEmpty(tvInStockDayEnd.getText()))
                    djNum = 0;
                else
                    djNum = Integer.parseInt(tvInStockDayEnd.getText().toString());
                if (djNum < 9999999)
                    djNum++;
                inStockDayEnd = djNum + "";
                tvInStockDayEnd.setText(djNum + "");
                break;
            case R.id.ivReduceStockDayEnd:
                if (TextUtils.isEmpty(tvInStockDayStart.getText()))
                    djNum = 0;
                else
                    djNum = Integer.parseInt(tvInStockDayStart.getText().toString());
                if (djNum > 0)
                    djNum--;
                inStockDayEnd = djNum + "";
                tvInStockDayStart.setText(djNum + "");
                break;
            case R.id.tvWmsWarehouseId:
                if (ListUtil.isEmpty(wareHouseList))
                    return;
                List<String> warehouse = new ArrayList<>();
                for (int i = 0; i < wareHouseList.size(); i++) {
                    warehouse.add(wareHouseList.get(i).getName());
                }
                SingleChooseDialog addressDialog = new SingleChooseDialog(mContext, warehouse, new SingleChooseDialog.OnItemClick() {
                    @Override
                    public void onItemClick(int position) {
                        tvWmsWarehouseId.setText(wareHouseList.get(position).getName());
                        wmsWarehouseId = wareHouseList.get(position).getId();
                    }
                });
                addressDialog.show();
                break;
            case R.id.tvGoodsType:
                SingleChooseDialog goodsTypeChooseDialog = new SingleChooseDialog(mContext, goodsTypeList, new SingleChooseDialog.OnItemClick() {
                    @Override
                    public void onItemClick(int position) {
                        tvGoodsType.setText(goodsTypeList.get(position));
                        switch (position) {
                            case 0:
                                goodsType = "A";
                                break;
                            case 1:
                                goodsType = "B";
                                break;
                            case 2:
                                goodsType = "C";
                                break;
                        }
                    }
                });
                goodsTypeChooseDialog.show();
                break;
            case R.id.tvStockState:
                SingleChooseDialog stockStateDialog = new SingleChooseDialog(mContext, stockStateList, new SingleChooseDialog.OnItemClick() {
                    @Override
                    public void onItemClick(int position) {
                        tvStockState.setText(stockStateList.get(position));
                        switch (position) {
                            case 0:
                                stockState = "0";
                                break;
                            case 1:
                                stockState = "1";
                                break;
                        }
                    }
                });
                stockStateDialog.show();
                break;
            case R.id.tvInputDateStart:
                type = 0;
                selectDate();
                break;
            case R.id.tvInputDateEnd:
                type = 1;
                selectDate();
                break;
            case R.id.tvUpdateDateStart:
                type = 2;
                selectDate();
                break;
            case R.id.tvUpdateDateEnd:
                type = 3;
                selectDate();
                break;
            case R.id.tvOutputDateStart:
                type = 4;
                selectDate();
                break;
            case R.id.tvOutputDateEnd:
                type = 5;
                selectDate();
                break;
            case R.id.tvCx:
                barCode = etBarCode.getText().toString();
                awb = etAwb.getText().toString();
                goodsName = etGoodsName.getText().toString();
                productCode = etProductCode.getText().toString();
                wmsWarehouseDetailName = etWmsWarehouseDetailName.getText().toString();
                updaterName = etUpdaterName.getText().toString();
                if (TextUtils.isEmpty(tvInStockDayEnd.getText()))
                    inStockDayEnd = "";
                else
                    inStockDayEnd = tvInStockDayEnd.getText().toString();
                if (TextUtils.isEmpty(tvInStockDayStart.getText()))
                    inStockDayStart = "";
                else
                    inStockDayStart = tvInStockDayStart.getText().toString();

                EventBus.getDefault().post(new StockSxEvent(barCode, awb, goodsType, goodsName,
                        productCode, wmsWarehouseId, wmsWarehouseDetailName, stockState, inputDateStart, inputDateEnd
                        , outputDateStart, outputDateEnd, inStockDayStart, inStockDayEnd, updaterName, updateDateStart, updateDateEnd));
                act.finishSelf();
                break;
        }
    }
}

