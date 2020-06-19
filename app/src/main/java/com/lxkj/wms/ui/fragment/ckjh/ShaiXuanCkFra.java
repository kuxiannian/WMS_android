package com.lxkj.wms.ui.fragment.ckjh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.lxkj.wms.R;
import com.lxkj.wms.bean.WareHouseBean;
import com.lxkj.wms.event.BillOutputSxEvent;
import com.lxkj.wms.http.BaseCallback;
import com.lxkj.wms.http.OkHttpHelper;
import com.lxkj.wms.http.Url;
import com.lxkj.wms.ui.activity.NaviActivity;
import com.lxkj.wms.ui.fragment.TitleFragment;
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
public class ShaiXuanCkFra extends TitleFragment implements NaviActivity.NaviRigthImageListener, View.OnClickListener {
    Unbinder unbinder;
    @BindView(R.id.etBarCode)
    EditText etBarCode;
    @BindView(R.id.tvOutputDateStart)
    TextView tvOutputDateStart;
    @BindView(R.id.tvOutputDateEnd)
    TextView tvOutputDateEnd;
    @BindView(R.id.etLadingNumber)
    EditText etLadingNumber;
    @BindView(R.id.etConsignor)
    EditText etConsignor;
    @BindView(R.id.etConsignorPhone)
    EditText etConsignorPhone;
    @BindView(R.id.tvGoodsType)
    TextView tvGoodsType;
    @BindView(R.id.tvWmsWarehouseId)
    TextView tvWmsWarehouseId;
    @BindView(R.id.etGoodsName)
    EditText etGoodsName;
    @BindView(R.id.etupdaterName)
    EditText etupdaterName;
    @BindView(R.id.tvUpdateDateStart)
    TextView tvUpdateDateStart;
    @BindView(R.id.tvUpdateDateEnd)
    TextView tvUpdateDateEnd;
    @BindView(R.id.tvCx)
    TextView tvCx;

    public String barCode;//条形码
    public String outputDateStart;//出库开始日期
    public String outputDateEnd;//出库结束日期
    public String ladingNumber;//提货单编号
    public String consignor;//提货人
    public String consignorPhone;//提货人联系方式
    public String goodsType;//货物分类
    public String wmsWarehouseId;//出库仓库
    public String goodsName;//货物品名（包括包装、体积或尺寸）
    public String updaterName;//操作员姓名
    public String updateDateStart;//操作开始时间
    public String updateDateEnd;//操作结束时间

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
        rootView = inflater.inflate(R.layout.fra_jlsx_ck, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    private void initView() {
        tvWmsWarehouseId.setOnClickListener(this);
        tvGoodsType.setOnClickListener(this::onClick);
        tvOutputDateStart.setOnClickListener(this::onClick);
        tvOutputDateEnd.setOnClickListener(this::onClick);
        tvUpdateDateStart.setOnClickListener(this::onClick);
        tvUpdateDateEnd.setOnClickListener(this::onClick);
        wareHouseList = new ArrayList<>();
        goodsTypeList = new ArrayList<>();
        goodsTypeList.add(mContext.getString(R.string.goodsTypeA));
        goodsTypeList.add(mContext.getString(R.string.goodsTypeB));
        goodsTypeList.add(mContext.getString(R.string.goodsTypeC));
        tvCx.setOnClickListener(this);
        findWarehouseListBillInput();
    }

    /**
     * 查询仓库列表接口
     */
    private void findWarehouseListBillInput() {
        Map<String, String> params = new HashMap<>();
        OkHttpHelper.getInstance().get_json(mContext, Url.findWarehouseListBillOnput, params, new BaseCallback<WareHouseBean>() {
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
                        outputDateStart = DateUtil.formatDate(date.getTime(), "yyyy-MM-dd");
                        tvOutputDateStart.setText(outputDateStart);
                        break;
                    case 1://inputDateEnd
                        outputDateEnd = DateUtil.formatDate(date.getTime(), "yyyy-MM-dd");
                        tvOutputDateEnd.setText(outputDateEnd);
                        break;
                    case 2://updateDateStart
                        updateDateStart = DateUtil.formatDate(date.getTime(), "yyyy-MM-dd");
                        tvUpdateDateStart.setText(updateDateStart);
                        break;
                    case 3://updateDateEnd
                        updateDateEnd = DateUtil.formatDate(date.getTime(), "yyyy-MM-dd");
                        tvUpdateDateEnd.setText(updateDateEnd);
                        break;

                }
            }
        })
                .setCancelColor(R.color.txt_lv1)//取消按钮文字颜色
//                .setRangDate(startDate, endDate)//起始终止年月日设定
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
            case R.id.tvOutputDateStart:
                type = 0;
                selectDate();
                break;
            case R.id.tvOutputDateEnd:
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
                ladingNumber = etLadingNumber.getText().toString();
                consignor = etConsignor.getText().toString();
                consignorPhone= etConsignorPhone.getText().toString();
                goodsName = etGoodsName.getText().toString();
                goodsName = etGoodsName.getText().toString();
                updaterName = etupdaterName.getText().toString();
                EventBus.getDefault().post(new BillOutputSxEvent(barCode, outputDateStart,outputDateEnd, ladingNumber,
                        consignor, consignorPhone, goodsType,wmsWarehouseId, goodsName, updaterName, updateDateStart, updateDateEnd));
                act.finishSelf();
                break;
        }
    }
}


