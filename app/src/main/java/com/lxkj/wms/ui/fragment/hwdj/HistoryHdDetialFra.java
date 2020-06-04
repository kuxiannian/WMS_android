package com.lxkj.wms.ui.fragment.hwdj;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lxkj.wms.R;
import com.lxkj.wms.bean.BaseBean;
import com.lxkj.wms.bean.ResultBean;
import com.lxkj.wms.bean.SortingRegisterBean;
import com.lxkj.wms.bean.SortingRegisterDetailBean;
import com.lxkj.wms.biz.EventCenter;
import com.lxkj.wms.http.BaseCallback;
import com.lxkj.wms.http.OkHttpHelper;
import com.lxkj.wms.http.SpotsCallBack;
import com.lxkj.wms.http.Url;
import com.lxkj.wms.ui.fragment.TitleFragment;
import com.lxkj.wms.utils.ShowErrorCodeUtil;
import com.lxkj.wms.utils.StringUtil;
import com.lxkj.wms.utils.ToastUtil;
import com.lxkj.wms.view.HangDanDetailDialog;
import com.lxkj.wms.view.HintDialog;
import com.lxkj.wms.view.SingleChooseDialog;
import com.lxkj.wms.view.TxmDialog;

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
public class HistoryHdDetialFra extends TitleFragment implements View.OnClickListener {

    @BindView(R.id.llFjdjdd)
    LinearLayout llFjdjdd;
    @BindView(R.id.llHwfl)
    LinearLayout llHwfl;
    @BindView(R.id.tvHdxq)
    TextView tvHdxq;
    @BindView(R.id.tvTxm)
    TextView tvTxm;
    @BindView(R.id.tvXg)
    TextView tvXg;
    @BindView(R.id.tvSc)
    TextView tvSc;
    Unbinder unbinder;
    SortingRegisterBean.ResultBean.ContentBean contentBean;
    @BindView(R.id.tvAwb)
    TextView tvAwb;
    @BindView(R.id.tvFlightName)
    TextView tvFlightName;
    @BindView(R.id.tvGoodsName)
    TextView tvGoodsName;
    @BindView(R.id.tvRegisterNumber)
    TextView tvRegisterNumber;
    @BindView(R.id.tvWmsWarehouseName)
    TextView tvWmsWarehouseName;
    @BindView(R.id.tvGoodsType)
    TextView tvGoodsType;
    @BindView(R.id.tvRemarks)
    TextView tvRemarks;
    private List<String> goodsTypeList;

    List<String> barCodes; //条形码集合

    private String awb;//AWB号
    private String registerNumber;//登记数量
    private String goodsType;//货物分类
    private String number;//件数
    private String departureStation;//始发站
    private String destinationStation;//目的站
    private String shipperName;//托运人姓名
    private String shipperAddress;//托运人地址
    private String shipperPhone;//托运人电话号码
    private String receiverName;//收货人姓名
    private String receiverAddress;//收货人地址
    private String receiverPhone;//收货人电话号码
    private String grossWeight;//毛重
    private String chargeableWeight;//计费重量
    private String rateClass;//运价种类
    private String productCode;//商品代号

    @Override
    public String getTitleName() {
        return act.getString(R.string.lscxxq);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fra_detial_hd_history, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    private void initView() {
        barCodes = new ArrayList<>();
        goodsTypeList = new ArrayList<>();
        goodsTypeList.add(mContext.getString(R.string.goodsTypeA));
        goodsTypeList.add(mContext.getString(R.string.goodsTypeB));
        goodsTypeList.add(mContext.getString(R.string.goodsTypeC));
        tvHdxq.setOnClickListener(this);
        tvTxm.setOnClickListener(this);
        tvXg.setOnClickListener(this);
        tvSc.setOnClickListener(this);
        contentBean = ((SortingRegisterBean.ResultBean.ContentBean) getArguments().getSerializable("bean"));
        if (null != contentBean) {
            tvAwb.setText(contentBean.getAwb());
            tvFlightName.setText(contentBean.getFlightName());
            tvGoodsName.setText(contentBean.getGoodsName());
            tvRegisterNumber.setText(contentBean.getRegisterNumber());
            tvWmsWarehouseName.setText(contentBean.getWmsWarehouseName());
            if (!StringUtil.isEmpty(contentBean.getRemarks()))
                tvRemarks.setText(contentBean.getRemarks());
            goodsType = contentBean.getGoodsType();
            switch (goodsType) {
                case "A":
                    tvGoodsType.setText(R.string.goodsTypeA);
                    break;
                case "B":
                    tvGoodsType.setText(R.string.goodsTypeB);
                    break;
                case "C":
                    tvGoodsType.setText(R.string.goodsTypeC);
                    break;
            }
            awb = contentBean.getAwb();
            registerNumber = contentBean.getRegisterNumber();
            number = contentBean.getNumber();
            departureStation = contentBean.getDepartureStation();
            destinationStation = contentBean.getDestinationStation();
            shipperName = contentBean.getShipperName();
            shipperAddress = contentBean.getShipperAddress();
            shipperPhone = contentBean.getShipperPhone();
            receiverName = contentBean.getReceiverName();
            receiverAddress = contentBean.getReceiverAddress();
            receiverPhone = contentBean.getReceiverPhone();
            grossWeight = contentBean.getGrossWeight();
            chargeableWeight = contentBean.getChargeableWeight();
            rateClass = contentBean.getRateClass();
            productCode = contentBean.getProductCode();

            findWmsSortingRegisterDetailList();
        }
    }


    /**
     * 编辑数据接口
     *
     * @param goodsType
     */
    private void updateSortingRegister(String goodsType) {
        Map<String, String> params = new HashMap<>();
        params.put("id", contentBean.getId());
        params.put("goodsType", goodsType);
        params.put("version", contentBean.getVersion());
        OkHttpHelper.getInstance().post_json(mContext, Url.updateSortingRegister, params, new SpotsCallBack<BaseBean>(mContext) {

            @Override
            public void onSuccess(Response response, BaseBean resultBean) {
                ToastUtil.show(mContext.getResources().getString(R.string.seEdit));
                eventCenter.sendType(EventCenter.EventType.EVT_EDIT);
                act.finishSelf();
            }

            @Override
            public void onError(Response response, int code, Exception e) {
            }
        });
    }

    /**
     * 删除数据接口
     */
    private void deleteSortingRegister() {
        Map<String, String> params = new HashMap<>();
        params.put("id", contentBean.getId());
        params.put("version", contentBean.getVersion());
        OkHttpHelper.getInstance().post_json(mContext, Url.deleteSortingRegister, params, new SpotsCallBack<String>(mContext) {
            @Override
            public void onSuccess(Response response, String result) {
                ResultBean resultBean = new Gson().fromJson(result, ResultBean.class);
                if (resultBean.flag) {
                    ToastUtil.show(mContext.getResources().getString(R.string.seDelete));
                    eventCenter.sendType(EventCenter.EventType.EVT_DELETE);
                    act.finishSelf();
                } else {
                    if (resultBean.errorCode.contains("?")) {
                        String[] error = resultBean.errorCode.split("\\?");
                        String errorCode = error[0];
                        Map<String, String> errorValues = ShowErrorCodeUtil.getErrorValue(error[1]);
                        String barCode = errorValues.get("barCode");
                        List<String> errors = new ArrayList<>();
                        switch (errorCode) {
                            case "SE210003":
                                errors.add(String.format(getResources().getString(R.string.SE210003), barCode));
                                break;
                        }
                        if (errors.size() > 0)
                            ToastUtil.showCustom(mContext, errors);
                    } else {
                        List<String> errors = new ArrayList<>();
                        errors.add(ShowErrorCodeUtil.getErrorString(mContext, resultBean.errorCode));
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
     * 根据分拣登记ID查询分拣登记明细信息接口
     */
    private void findWmsSortingRegisterDetailList() {
        Map<String, String> params = new HashMap<>();
        params.put("sortingRegisterId", contentBean.getId());
        OkHttpHelper.getInstance().get_json(mContext, Url.findWmsSortingRegisterDetailList, params, new BaseCallback<SortingRegisterDetailBean>() {
            @Override
            public void onFailure(Request request, Exception e) {
            }

            @Override
            public void onSuccess(Response response, SortingRegisterDetailBean resultBean) {
                if (resultBean.flag) {
                    if (null != resultBean.getResult()) {
                        for (int i = 0; i < resultBean.getResult().size(); i++) {
                            barCodes.add(resultBean.getResult().get(i).getBarCode());
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvHdxq:
                //展示航单详情
                new HangDanDetailDialog(mContext, departureStation, destinationStation, shipperName, shipperAddress, shipperPhone,
                        receiverName, receiverAddress, receiverPhone, grossWeight, chargeableWeight, rateClass, number, productCode).show();
                break;
            case R.id.tvTxm:
                //展示条形码
                new TxmDialog(mContext, barCodes, true).show();
                break;
            case R.id.tvXg:
                //修改货物分类
                SingleChooseDialog goodsTypeChooseDialog = new SingleChooseDialog(mContext, goodsTypeList, new SingleChooseDialog.OnItemClick() {
                    @Override
                    public void onItemClick(int position) {
                        switch (position) {
                            case 0:
                                updateSortingRegister("A");
                                break;
                            case 1:
                                updateSortingRegister("B");
                                break;
                            case 2:
                                updateSortingRegister("C");
                                break;
                        }
                    }
                });
                goodsTypeChooseDialog.show();
                break;
            case R.id.tvSc:
                new HintDialog(mContext, mContext.getString(R.string.hint_delete), true).setOnButtonClickListener(new HintDialog.OnButtonClick() {
                    @Override
                    public void OnRightClick() {
                    }

                    @Override
                    public void OnLeftClick() {
                        deleteSortingRegister();
                    }
                }).show();
                break;
        }
    }
}
