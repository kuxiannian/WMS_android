package com.lxkj.wms.ui.fragment.rksh;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lxkj.wms.R;
import com.lxkj.wms.bean.WareHouseBean;
import com.lxkj.wms.event.BillInputSxEvent;
import com.lxkj.wms.http.BaseCallback;
import com.lxkj.wms.http.OkHttpHelper;
import com.lxkj.wms.http.Url;
import com.lxkj.wms.ui.activity.NaviActivity;
import com.lxkj.wms.ui.fragment.TitleFragment;
import com.lxkj.wms.utils.EditTextUtil;
import com.lxkj.wms.utils.ListUtil;
import com.lxkj.wms.view.SingleChooseDialog;

import org.greenrobot.eventbus.EventBus;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
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
public class ShaiXuanRkFra extends TitleFragment implements NaviActivity.NaviRigthImageListener, View.OnClickListener {
    Unbinder unbinder;
    @BindView(R.id.etBarCode)
    EditText etBarCode;
    @BindView(R.id.tvInputDateStart)
    TextView tvInputDateStart;
    @BindView(R.id.tvInputDateEnd)
    TextView tvInputDateEnd;
    @BindView(R.id.tvWmsWarehouseId)
    TextView tvWmsWarehouseId;
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
    @BindView(R.id.etGoodsName)
    EditText etGoodsName;
    @BindView(R.id.etUpdaterName)
    EditText etUpdaterName;
    @BindView(R.id.tvUpdateDateStart)
    TextView tvUpdateDateStart;
    @BindView(R.id.tvUpdateDateEnd)
    TextView tvUpdateDateEnd;
    @BindView(R.id.tvCx)
    TextView tvCx;
    private double djNum = 0;

    private String barCode;//条形码
    private String inputDateStart;//入库开始日期
    private String inputDateEnd;//入库结束日期
    private String wmsWarehouseId;//入库仓库
    private String palletNumber;//托盘号
    private String weight;//	重量
    private String goodsType;//	货物分类
    private String goodsName;//	货物品名（包括包装、体积或尺寸）
    private String updaterName;//	操作员姓名
    private String updateDateStart;//	操作开始时间
    private String updateDateEnd;//	操作结束时间
    private List<WareHouseBean.ResultBean> wareHouseList;
    private List<String> goodsTypeList;
    private int type = 0; //时间筛选标识 0 inputDateStart，1 inputDateEnd，2 updateDateStart，3 updateDateEnd

    @Override
    public String getTitleName() {
        return act.getString(R.string.jlsx);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fra_jlsx_rk, container, false);
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
        wareHouseList = new ArrayList<>();
        goodsTypeList = new ArrayList<>();
        goodsTypeList.add(mContext.getString(R.string.goodsTypeA));
        goodsTypeList.add(mContext.getString(R.string.goodsTypeB));
        goodsTypeList.add(mContext.getString(R.string.goodsTypeC));
        ivAdd.setOnClickListener(this);
        ivReduce.setOnClickListener(this);
        tvCx.setOnClickListener(this);
        tvWeight.addTextChangedListener(textWatcher);
        findWarehouseListBillInput();
    }

    /**
     * 查询仓库列表接口
     */
    private void findWarehouseListBillInput() {
        Map<String, String> params = new HashMap<>();
        OkHttpHelper.getInstance().get_json(mContext, Url.findWarehouseListBillInput, params, new BaseCallback<WareHouseBean>() {
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

    Calendar c = Calendar.getInstance();

    int mYear = c.get(Calendar.YEAR);
    int mMonth = c.get(Calendar.MONTH);
    int mDay = c.get(Calendar.DAY_OF_MONTH);

    /**
     * 选择日期
     */
    private void selectDate() {
        new DatePickerDialog(mContext,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear,
                                          int dayOfMonth) {
                        monthOfYear++;
                        switch (type) {
                            case 0://inputDateStart
                                inputDateStart = year + "-" + monthOfYear + "-" + dayOfMonth;
                                tvInputDateStart.setText(inputDateStart);
                                break;
                            case 1://inputDateEnd
                                inputDateEnd = year + "-" + monthOfYear + "-" + dayOfMonth;
                                tvInputDateEnd.setText(inputDateEnd);
                                break;
                            case 2://updateDateStart
                                updateDateStart = year + "-" + monthOfYear + "-" + dayOfMonth;
                                tvUpdateDateStart.setText(updateDateStart);
                                break;
                            case 3://updateDateEnd
                                updateDateEnd = year + "-" + monthOfYear + "-" + dayOfMonth;
                                tvUpdateDateEnd.setText(updateDateEnd);
                                break;
                        }
                    }
                }, mYear, mMonth, mDay).show();

//        Calendar startDate = Calendar.getInstance();
//        Calendar endDate = Calendar.getInstance();
//        startDate.set(DateUtil.getYear() - 10, Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
//        final TimePickerView startTimePickerView = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
//            @Override
//            public void onTimeSelect(Date date, View v) {
//                switch (type) {
//                    case 0://inputDateStart
//                        inputDateStart = DateUtil.formatDate(date.getTime(), "yyyy-MM-dd");
//                        tvInputDateStart.setText(inputDateStart);
//                        break;
//                    case 1://inputDateEnd
//                        inputDateEnd = DateUtil.formatDate(date.getTime(), "yyyy-MM-dd");
//                        tvInputDateEnd.setText(inputDateEnd);
//                        break;
//                    case 2://updateDateStart
//                        updateDateStart = DateUtil.formatDate(date.getTime(), "yyyy-MM-dd");
//                        tvUpdateDateStart.setText(updateDateStart);
//                        break;
//                    case 3://updateDateEnd
//                        updateDateEnd = DateUtil.formatDate(date.getTime(), "yyyy-MM-dd");
//                        tvUpdateDateEnd.setText(updateDateEnd);
//                        break;
//
//                }
//            }
//        })
//                .setCancelColor(R.color.txt_lv1)//取消按钮文字颜色
////                .setRangDate(startDate, endDate)//起始终止年月日设定
//                .setTextColorCenter(0xffFF8201)
//                .setTitleBgColor(0xffffffff)
//                .setSubmitColor(0xffFF8201)
//                .setSubCalSize(22)
//                .setType(new boolean[]{true, true, true, false, false, false})
//                .build();
//        startTimePickerView.show();
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            EditTextUtil.keepTwoDecimals(tvWeight, 10);
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    @Override
    public int rightImg() {
        return R.mipmap.ic_time;
    }

    @Override
    public void onRightClicked(View v) {
        act.finishSelf();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
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
            case R.id.tvCx:
                barCode = etBarCode.getText().toString();
                palletNumber = etPalletNumber.getText().toString();
                goodsName = etGoodsName.getText().toString();
                weight = tvWeight.getText().toString();
                goodsName = etGoodsName.getText().toString();
                updaterName = etUpdaterName.getText().toString();
                EventBus.getDefault().post(new BillInputSxEvent(barCode, inputDateStart, inputDateEnd, wmsWarehouseId,
                        palletNumber, weight, goodsType, goodsName, updaterName, updateDateStart, updateDateEnd));
                act.finishSelf();
                break;
        }
    }
}


