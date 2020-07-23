package com.lxkj.wms.ui.fragment.stockcheck;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.lxkj.wms.R;
import com.lxkj.wms.bean.WareHouseBean;
import com.lxkj.wms.event.StockCheckEvent;
import com.lxkj.wms.http.BaseCallback;
import com.lxkj.wms.http.OkHttpHelper;
import com.lxkj.wms.http.Url;
import com.lxkj.wms.ui.fragment.TitleFragment;
import com.lxkj.wms.utils.ListUtil;
import com.lxkj.wms.view.SingleChooseDialog;

import org.greenrobot.eventbus.EventBus;

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
 * Created by kxn on 2020/5/15 0015.
 */
public class ShaiXuanStockCheckFra extends TitleFragment implements  View.OnClickListener {
    Unbinder unbinder;
    @BindView(R.id.tvWmsWarehouseId)
    TextView tvWmsWarehouseId;
    @BindView(R.id.tvStartDateStart)
    TextView tvStartDateStart;
    @BindView(R.id.tvStartDateEnd)
    TextView tvStartDateEnd;
    @BindView(R.id.tvEndDateStart)
    TextView tvEndDateStart;
    @BindView(R.id.tvEndDateEnd)
    TextView tvEndDateEnd;
    @BindView(R.id.tvState)
    TextView tvState;
    @BindView(R.id.etUpdaterName)
    EditText etUpdaterName;
    @BindView(R.id.tvUpdateDateStart)
    TextView tvUpdateDateStart;
    @BindView(R.id.tvUpdateDateEnd)
    TextView tvUpdateDateEnd;
    @BindView(R.id.tvCx)
    TextView tvCx;

    private String wmsWarehouseId;//仓库
    private String startDateStart;//	盘点开始日期开始时间
    private String startDateEnd;//	盘点开始日期结束时间
    private String endDateStart;//	盘点结束日期开始时间
    private String endDateEnd;//	盘点结束日期结束时间
    private String state;//	状态
    private String updaterName;//	操作员姓名
    private String updateDateStart;//	操作开始时间
    private String updateDateEnd;//	操作结束时间
    private List<WareHouseBean.ResultBean> wareHouseList;
    private int type = 0; //时间筛选标识 0 startDateStart，1 startDateEnd，2 endDateStart，3 endDateEnd ,4 updateDateStart,5 updateDateEnd
    private List<String> stateList;


    @Override
    public String getTitleName() {
        return act.getString(R.string.jlsx);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fra_jlsx_stockcheck, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    private void initView() {

        tvWmsWarehouseId.setOnClickListener(this);
        tvStartDateStart.setOnClickListener(this::onClick);
        tvStartDateEnd.setOnClickListener(this::onClick);
        tvEndDateStart.setOnClickListener(this::onClick);
        tvEndDateEnd.setOnClickListener(this::onClick);
        tvUpdateDateStart.setOnClickListener(this::onClick);
        tvUpdateDateEnd.setOnClickListener(this::onClick);
        tvState.setOnClickListener(this::onClick);
        wareHouseList = new ArrayList<>();
        stateList = new ArrayList<>();
        stateList.add(mContext.getString(R.string.Inventory));
        stateList.add(mContext.getString(R.string.Einventory));
        stateList.add(mContext.getString(R.string.Void));
        tvCx.setOnClickListener(this);
        findWarehouseListStockCheck();
    }

    /**
     * 查询仓库列表接口
     */
    private void findWarehouseListStockCheck() {
        Map<String, String> params = new HashMap<>();
        OkHttpHelper.getInstance().get_json(mContext, Url.findWarehouseListStockCheck, params, new BaseCallback<WareHouseBean>() {
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
                            case 0://startDateStart
                                startDateStart = year + "-" + monthOfYear + "-" + dayOfMonth;
                                tvStartDateStart.setText(startDateStart);
                                break;
                            case 1://startDateEnd
                                startDateEnd = year + "-" + monthOfYear + "-" + dayOfMonth;
                                tvStartDateEnd.setText(startDateEnd);
                                break;
                            case 2://endDateStart
                                endDateStart = year + "-" + monthOfYear + "-" + dayOfMonth;
                                tvEndDateStart.setText(endDateStart);
                                break;
                            case 3://endDateEnd
                                endDateEnd = year + "-" + monthOfYear + "-" + dayOfMonth;
                                tvEndDateEnd.setText(endDateEnd);
                                break;
                            case 4://updateDateStart
                                updateDateStart = year + "-" + monthOfYear + "-" + dayOfMonth;
                                tvUpdateDateStart.setText(updateDateStart);
                                break;
                            case 5://updateDateEnd
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
//                    case 0://startDateStart
//                        startDateStart = DateUtil.formatDate(date.getTime(), "yyyy-MM-dd");
//                        tvStartDateStart.setText(startDateStart);
//                        break;
//                    case 1://startDateEnd
//                        startDateEnd = DateUtil.formatDate(date.getTime(), "yyyy-MM-dd");
//                        tvStartDateEnd.setText(startDateEnd);
//                        break;
//                    case 2://endDateStart
//                        endDateStart = DateUtil.formatDate(date.getTime(), "yyyy-MM-dd");
//                        tvEndDateStart.setText(endDateStart);
//                        break;
//                    case 3://endDateEnd
//                        endDateEnd = DateUtil.formatDate(date.getTime(), "yyyy-MM-dd");
//                        tvEndDateEnd.setText(endDateEnd);
//                        break;
//                    case 4://updateDateStart
//                        updateDateStart = DateUtil.formatDate(date.getTime(), "yyyy-MM-dd");
//                        tvUpdateDateStart.setText(updateDateStart);
//                        break;
//                    case 5://updateDateEnd
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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
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
            case R.id.tvStartDateStart:
                type = 0;
                selectDate();
                break;
            case R.id.tvStartDateEnd:
                type = 1;
                selectDate();
                break;
            case R.id.tvEndDateStart:
                type = 2;
                selectDate();
                break;
            case R.id.tvEndDateEnd:
                type = 3;
                selectDate();
                break;
            case R.id.tvUpdateDateStart:
                type = 4;
                selectDate();
                break;
            case R.id.tvUpdateDateEnd:
                type = 5;
                selectDate();
                break;
            case R.id.tvState:
                SingleChooseDialog stateChooseDialog = new SingleChooseDialog(mContext, stateList, new SingleChooseDialog.OnItemClick() {
                    @Override
                    public void onItemClick(int position) {
                        tvState.setText(stateList.get(position));
                        switch (position) {
                            case 0:
                                state = "1";
                                break;
                            case 1:
                                state = "2";
                                break;
                            case 2:
                                state = "3";
                                break;
                        }
                    }
                });
                stateChooseDialog.show();
                break;
            case R.id.tvCx:
                updaterName = etUpdaterName.getText().toString();
                EventBus.getDefault().post(new StockCheckEvent(wmsWarehouseId, startDateStart, startDateEnd, endDateStart,
                        endDateEnd, state, updaterName, updateDateStart, updateDateEnd));
                act.finishSelf();
                break;
        }
    }
}


