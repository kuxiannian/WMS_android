package com.lxkj.wms.ui.fragment.hwdj;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lxkj.wms.R;
import com.lxkj.wms.bean.WareHouseBean;
import com.lxkj.wms.event.FjdjSxEvent;
import com.lxkj.wms.http.BaseCallback;
import com.lxkj.wms.http.OkHttpHelper;
import com.lxkj.wms.http.Url;
import com.lxkj.wms.ui.activity.NaviActivity;
import com.lxkj.wms.ui.fragment.TitleFragment;
import com.lxkj.wms.utils.ListUtil;
import com.lxkj.wms.view.SingleChooseDialog;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
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
public class ShaiXuanFra extends TitleFragment implements NaviActivity.NaviRigthImageListener, View.OnClickListener {

    Unbinder unbinder;
    @BindView(R.id.etAwb)
    EditText etAwb;
    @BindView(R.id.etFlightName)
    EditText etFlightName;
    @BindView(R.id.etGoodsName)
    EditText etGoodsName;
    @BindView(R.id.tvFjdjdd)
    TextView tvFjdjdd;
    @BindView(R.id.tvHwfl)
    TextView tvHwfl;
    @BindView(R.id.etDepartureStation)
    EditText etDepartureStation;
    @BindView(R.id.etDestinationStation)
    EditText etDestinationStation;
    @BindView(R.id.tvDjjs)
    EditText tvDjjs;
    @BindView(R.id.ivAdd)
    ImageView ivAdd;
    @BindView(R.id.ivReduce)
    ImageView ivReduce;
    @BindView(R.id.etProductCode)
    TextView etProductCode;
    @BindView(R.id.tvCx)
    TextView tvCx;
    private List<WareHouseBean.ResultBean> wareHouseList;
    private List<String> goodsTypeList;

    private int djNum = 0;
    private String awb;//	AWB号
    private String flightName;//	航班
    private String goodsName;//	货物品名（包括包装、体积或尺寸）
    private String registerNumber;//	否	登记件数
    private String wmsWarehouseId;//	否	分拣登记地点
    private String goodsType;//	否	货物分类
    private String departureStation;//	否	始发站、第一承运人地址及要求的路线
    private String destinationStation;//	否	目的站
    private String productCode;//	否	商品代号


    @Override
    public String getTitleName() {
        return act.getString(R.string.jlsx);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fra_jlsx, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    private void initView() {
        wareHouseList = new ArrayList<>();
        goodsTypeList = new ArrayList<>();
        goodsTypeList.add(mContext.getString(R.string.goodsTypeA));
        goodsTypeList.add(mContext.getString(R.string.goodsTypeB));
        goodsTypeList.add(mContext.getString(R.string.goodsTypeC));
        ivAdd.setOnClickListener(this);
        ivReduce.setOnClickListener(this);
        tvHwfl.setOnClickListener(this);
        tvFjdjdd.setOnClickListener(this);
        tvCx.setOnClickListener(this);
        findWarehouseList();
    }

    /**
     * 查询仓库列表接口
     */
    private void findWarehouseList() {
        Map<String, String> params = new HashMap<>();
        OkHttpHelper.getInstance().get_json(mContext, Url.findWarehouseList, params, new BaseCallback<WareHouseBean>() {
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
                            tvFjdjdd.setText(resultBean.getResult().get(0).getName());
                        }
                    }
                }
            }

            @Override
            public void onError(Response response, int code, Exception e) {
            }
        });
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
            case R.id.ivAdd:
                if (!TextUtils.isEmpty(tvDjjs.getText()))
                    djNum = Integer.parseInt(tvDjjs.getText().toString());
                else
                    djNum = 0;
                if (djNum < 9999999)
                    djNum++;
                tvDjjs.setText(djNum + "");
                break;
            case R.id.ivReduce:
                if (!TextUtils.isEmpty(tvDjjs.getText()))
                    djNum = Integer.parseInt(tvDjjs.getText().toString());
                else
                    djNum = 0;
                if (djNum > 1)
                    djNum--;
                if (djNum > 0)
                    tvDjjs.setText(djNum + "");
                else
                    tvDjjs.setText("");
                break;
            case R.id.tvHwfl:
                SingleChooseDialog goodsTypeChooseDialog = new SingleChooseDialog(mContext, goodsTypeList, new SingleChooseDialog.OnItemClick() {
                    @Override
                    public void onItemClick(int position) {
                        tvHwfl.setText(goodsTypeList.get(position));
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
            case R.id.tvFjdjdd:
                if (ListUtil.isEmpty(wareHouseList))
                    return;
                List<String> djddList = new ArrayList<>();
                for (int i = 0; i < wareHouseList.size(); i++) {
                    djddList.add(wareHouseList.get(i).getName());
                }
                SingleChooseDialog addressDialog = new SingleChooseDialog(mContext, djddList, new SingleChooseDialog.OnItemClick() {
                    @Override
                    public void onItemClick(int position) {
                        tvFjdjdd.setText(wareHouseList.get(position).getName());
                        wmsWarehouseId = wareHouseList.get(position).getId();
                    }
                });
                addressDialog.show();
                break;
            case R.id.tvCx:
                awb = etAwb.getText().toString();
                flightName = etFlightName.getText().toString();
                goodsName = etGoodsName.getText().toString();
                registerNumber = tvDjjs.getText().toString();
                departureStation = etDepartureStation.getText().toString();
                destinationStation = etDestinationStation.getText().toString();
                productCode = etProductCode.getText().toString();
                EventBus.getDefault().post(new FjdjSxEvent(awb, flightName, goodsName, registerNumber, wmsWarehouseId, goodsType, departureStation, destinationStation, productCode));
                act.finishSelf();
                break;
        }
    }
}

