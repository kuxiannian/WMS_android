package com.lxkj.wms.ui.fragment.hwdj;

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

import com.google.gson.Gson;
import com.lxkj.wms.R;
import com.lxkj.wms.bean.FindAwbBean;
import com.lxkj.wms.bean.FlightBean;
import com.lxkj.wms.bean.GoodsBean;
import com.lxkj.wms.bean.ResultBean;
import com.lxkj.wms.bean.WareHouseBean;
import com.lxkj.wms.biz.ActivitySwitcher;
import com.lxkj.wms.http.BaseCallback;
import com.lxkj.wms.http.OkHttpHelper;
import com.lxkj.wms.http.SpotsCallBack;
import com.lxkj.wms.http.Url;
import com.lxkj.wms.ui.activity.NaviActivity;
import com.lxkj.wms.ui.fragment.TitleFragment;
import com.lxkj.wms.utils.AppViewCanDoUtil;
import com.lxkj.wms.utils.ListUtil;
import com.lxkj.wms.utils.ShowErrorCodeUtil;
import com.lxkj.wms.utils.ToastUtil;
import com.lxkj.wms.view.HangDanDetailDialog;
import com.lxkj.wms.view.SingleChooseDialog;

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
 * Created by kxn on 2020/4/23 0023.
 */
public class AddHwdjFra extends TitleFragment implements NaviActivity.NaviRigthImageListener, View.OnClickListener {
    @BindView(R.id.etAwb)
    EditText etAwb;
    @BindView(R.id.tvHangBan)
    TextView tvHangBan;
    @BindView(R.id.tvHwpm)
    TextView tvHwpm;
    @BindView(R.id.tvJs)
    TextView tvJs;
    @BindView(R.id.tvDjjs)
    EditText tvDjjs;
    @BindView(R.id.tvHwfl)
    TextView tvHwfl;
    @BindView(R.id.tvHdxq)
    TextView tvHdxq;
    @BindView(R.id.tvSave)
    TextView tvSave;
    Unbinder unbinder;
    @BindView(R.id.ivAdd)
    ImageView ivAdd;
    @BindView(R.id.ivReduce)
    ImageView ivReduce;
    @BindView(R.id.tvFjdjdd)
    TextView tvFjdjdd;
    @BindView(R.id.etRemark)
    EditText etRemark;
    private int djNum = 0;

    private List<FlightBean.ResultBean> flightList;
    private List<GoodsBean.ResultBean> goodsList;
    private List<WareHouseBean.ResultBean> wareHouseList;
    private List<String> goodsTypeList;
    private String awb;//AWB号
    private String flightId;//航班
    private String goodsNameId;//货物品名（包括包装、体积或尺寸）
    private String registerNumber;//登记数量
    private String wmsWarehouseId;//分拣登记地点
    private String goodsType;//货物分类
    private String wmsManifestId;//舱单信息ID
    private String remarks;//备注
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
        return act.getString(R.string.xzhwdj);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fra_xzhwdj, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    private void initView() {
        flightList = new ArrayList<>();
        goodsList = new ArrayList<>();
        wareHouseList = new ArrayList<>();
        goodsTypeList = new ArrayList<>();
        goodsTypeList.add(mContext.getString(R.string.goodsTypeA));
        goodsTypeList.add(mContext.getString(R.string.goodsTypeB));
        goodsTypeList.add(mContext.getString(R.string.goodsTypeC));

        tvHangBan.setOnClickListener(this);
        tvHwpm.setOnClickListener(this::onClick);
        ivAdd.setOnClickListener(this);
        ivReduce.setOnClickListener(this);
        tvHwfl.setOnClickListener(this);
        tvFjdjdd.setOnClickListener(this);
        tvHdxq.setOnClickListener(this);
        tvSave.setOnClickListener(this);
        etAwb.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    if (null != etAwb && !TextUtils.isEmpty(etAwb.getText()))
                        findManifestByAwb(etAwb.getText().toString());
                }
            }
        });
        etAwb.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                AppViewCanDoUtil.setBtnCanDo(false, tvHdxq);
                AppViewCanDoUtil.setCanDo(false, tvHangBan);
                AppViewCanDoUtil.setCanDo(false, tvHwpm);
                wmsManifestId = null;
                departureStation = null;
                destinationStation = null;
                shipperName = null;
                shipperAddress = null;
                shipperPhone = null;
                receiverName = null;
                receiverAddress = null;
                receiverPhone = null;
                awb = null;
                number = null;
                tvHangBan.setText("");
                tvHwpm.setText("");
                tvJs.setText("");
//                if (!TextUtils.isEmpty(etAwb.getText()))
//                    findManifestByAwb(etAwb.getText().toString());
            }
        });
        findWarehouseList();
    }

    /**
     * 查询仓库列表接口
     */
    private void findWarehouseList() {
        Map<String, String> params = new HashMap<>();
        OkHttpHelper.getInstance().get_json(mContext, Url.findWarehouseList, params, new BaseCallback<WareHouseBean>() {
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

    /**
     * 根据AWB搜索舱单信息接口
     */
    private void findManifestByAwb(String awbStr) {
        Map<String, String> params = new HashMap<>();
        params.put("awb", awbStr);
        OkHttpHelper.getInstance().get_json(mContext, Url.findManifestByAwb, params, new BaseCallback<FindAwbBean>() {

            @Override
            public void onFailure(Request request, Exception e) {
            }

            @Override
            public void onSuccess(Response response, FindAwbBean resultBean) {
                if (null != resultBean.getResult()) {
                    findFlightByWmsManifestId(resultBean.getResult().getId());
                    findGoodsNameByWmsManifestId(resultBean.getResult().getId());
                    wmsManifestId = resultBean.getResult().getId();
                    departureStation = resultBean.getResult().getDepartureStation();
                    destinationStation = resultBean.getResult().getDestinationStation();
                    shipperName = resultBean.getResult().getShipperName();
                    shipperAddress = resultBean.getResult().getShipperAddress();
                    shipperPhone = resultBean.getResult().getShipperPhone();
                    receiverName = resultBean.getResult().getReceiverName();
                    receiverAddress = resultBean.getResult().getReceiverAddress();
                    receiverPhone = resultBean.getResult().getReceiverPhone();
                    awb = awbStr;
                    AppViewCanDoUtil.setCanDo(true, tvHangBan);
                    AppViewCanDoUtil.setCanDo(true, tvHwpm);
                }
            }

            @Override
            public void onError(Response response, int code, Exception e) {
            }
        });
    }

    /**
     * 根据舱单信息ID查询舱单航班信息接口
     *
     * @param wmsManifestId
     */
    private void findFlightByWmsManifestId(String wmsManifestId) {
        Map<String, String> params = new HashMap<>();
        params.put("wmsManifestId", wmsManifestId);
        OkHttpHelper.getInstance().get_json(mContext, Url.findFlightByWmsManifestId, params, new BaseCallback<FlightBean>() {

            @Override
            public void onFailure(Request request, Exception e) {
            }

            @Override
            public void onSuccess(Response response, FlightBean resultBean) {
                if (resultBean.flag) {
                    if (null != resultBean.getResult()) {
                        flightList = resultBean.getResult();
                        if (resultBean.getResult().size() == 1) {
                            flightId = resultBean.getResult().get(0).getId();
                            tvHangBan.setText(resultBean.getResult().get(0).getFlight());
                        }
                        AppViewCanDoUtil.setBtnCanDo(true, tvHdxq);
                    }
                }
            }

            @Override
            public void onError(Response response, int code, Exception e) {
            }
        });
    }

    /**
     * 根据舱单信息ID查询舱单货物信息接口
     *
     * @param wmsManifestId
     */
    private void findGoodsNameByWmsManifestId(String wmsManifestId) {
        Map<String, String> params = new HashMap<>();
        params.put("wmsManifestId", wmsManifestId);
        OkHttpHelper.getInstance().get_json(mContext, Url.findGoodsNameByWmsManifestId, params, new BaseCallback<GoodsBean>() {

            @Override
            public void onFailure(Request request, Exception e) {
            }

            @Override
            public void onSuccess(Response response, GoodsBean resultBean) {
                if (resultBean.flag) {
                    if (null != resultBean.getResult()) {
                        goodsList = resultBean.getResult();
                        if (resultBean.getResult().size() == 1) {
                            tvHwpm.setText(resultBean.getResult().get(0).getGoodsName());
                            goodsNameId = resultBean.getResult().get(0).getId();
                            findWmsManifestGoodsByGoodsNameId(goodsList.get(0).getId());
                            grossWeight = resultBean.getResult().get(0).getGrossWeight();
                            chargeableWeight = resultBean.getResult().get(0).getChargeableWeight();
                            rateClass = resultBean.getResult().get(0).getRateClass();
                            productCode = resultBean.getResult().get(0).getProductCode();
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
     * 根据舱单货物ID查询舱单货物信息接口
     *
     * @param goodsNameId 舱单货物ID
     */
    private void findWmsManifestGoodsByGoodsNameId(String goodsNameId) {
        Map<String, String> params = new HashMap<>();
        params.put("goodsNameId", goodsNameId);
        OkHttpHelper.getInstance().get_json(mContext, Url.findWmsManifestGoodsByGoodsNameId, params, new BaseCallback<ResultBean>() {

            @Override
            public void onFailure(Request request, Exception e) {
            }

            @Override
            public void onSuccess(Response response, ResultBean resultBean) {
                if (resultBean.flag) {
                    number = resultBean.result.number;
                    tvJs.setText(number);
                }
            }

            @Override
            public void onError(Response response, int code, Exception e) {
            }
        });
    }


    /**
     * 新增货物登记数据
     */
    private void addSortingRegister() {
        if (!TextUtils.isEmpty(tvDjjs.getText()))
            djNum = Integer.parseInt(tvDjjs.getText().toString());
        else
            djNum = 0;
        if (djNum > 0)
            registerNumber = djNum + "";
        Map<String, String> params = new HashMap<>();
        if (null == awb) {
            ToastUtil.show(mContext.getString(R.string.VE180002));
            return;
        }
        if (null == flightId) {
            ToastUtil.show(mContext.getString(R.string.VE210003));
            return;
        }

        if (TextUtils.isEmpty(tvHwpm.getText())) {
            ToastUtil.show(mContext.getString(R.string.VE210004));
            return;
        }

        if (TextUtils.isEmpty(tvDjjs.getText())) {
            ToastUtil.show(mContext.getString(R.string.VE210006));
            return;
        }
        if (TextUtils.isEmpty(tvDjjs.getText())) {
            Integer.parseInt(tvDjjs.getText().toString());
            ToastUtil.show(mContext.getString(R.string.VE210006));
            return;
        }

        if (TextUtils.isEmpty(tvHwfl.getText())) {
            ToastUtil.show(mContext.getString(R.string.VE210008));
            return;
        }
        if (null != awb)
            params.put("awb", awb);
        if (null != flightId)
            params.put("flightId", flightId);
        if (null != goodsNameId)
            params.put("goodsNameId", goodsNameId);
        if (null != registerNumber)
            params.put("registerNumber", registerNumber);
        if (null != wmsWarehouseId)
            params.put("wmsWarehouseId", wmsWarehouseId);
        if (null != goodsType)
            params.put("goodsType", goodsType);
        if (null != wmsManifestId)
            params.put("wmsManifestId", wmsManifestId);
        if (null != number)
            params.put("number", number);
        if (null != departureStation)
            params.put("departureStation", departureStation);
        if (null != destinationStation)
            params.put("destinationStation", destinationStation);
        if (null != shipperName)
            params.put("shipperName", shipperName);
        if (null != shipperAddress)
            params.put("shipperAddress", shipperAddress);
        if (null != shipperPhone)
            params.put("shipperPhone", shipperPhone);
        if (null != receiverName)
            params.put("receiverName", receiverName);
        if (null != receiverAddress)
            params.put("receiverAddress", receiverAddress);
        if (null != receiverPhone)
            params.put("receiverPhone", receiverPhone);
        if (null != grossWeight)
            params.put("grossWeight", grossWeight);
        if (null != rateClass)
            params.put("rateClass", rateClass);
        if (null != productCode)
            params.put("productCode", productCode);
        if (null != chargeableWeight)
            params.put("chargeableWeight", chargeableWeight);
        if (!TextUtils.isEmpty(etRemark.getText()))
            params.put("remarks", etRemark.getText().toString());
        OkHttpHelper.getInstance().post_json(mContext, Url.addSortingRegister, params, new SpotsCallBack<String>(mContext) {
            @Override
            public void onFailure(Request request, Exception e) {
            }

            @Override
            public void onSuccess(Response response, String result) {
                ResultBean resultBean = new Gson().fromJson(result, ResultBean.class);
                if (resultBean.flag) {
                    ToastUtil.show(mContext.getResources().getString(R.string.seSave));
                    etAwb.setText("");
                    tvDjjs.setText("");
                    tvFjdjdd.setText("");
                    tvHwfl.setText("");
                    etRemark.setText("");
                    tvHangBan.setText("");
                    tvHwpm.setText("");
                    tvJs.setText("");
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
                            case "SE210004":
                                errors.add(String.format(getResources().getString(R.string.SE210004), errorValues.get("alNumber"),errorValues.get("canNumber")));
                                break;
                        }
                        if (errors.size() > 0)
                            ToastUtil.showCustom(mContext, errors);
                    } else {
                        List<String> errors = new ArrayList<>();
                        switch (resultBean.errorCode) {
                            case "E000406":
                                if (null != resultBean.result.goodsNameId)
                                    errors.add(ShowErrorCodeUtil.getErrorString(mContext, resultBean.result.goodsNameId));
                                if (null != resultBean.result.wmsWarehouseId)
                                    errors.add(ShowErrorCodeUtil.getErrorString(mContext, resultBean.result.wmsWarehouseId));
                                if (null != resultBean.result.wmsManifestId)
                                    errors.add(ShowErrorCodeUtil.getErrorString(mContext, resultBean.result.wmsManifestId));
                                if (null != resultBean.result.flightId)
                                    errors.add(ShowErrorCodeUtil.getErrorString(mContext, resultBean.result.flightId));
                                if (null != resultBean.result.registerNumber)
                                    errors.add(ShowErrorCodeUtil.getErrorString(mContext, resultBean.result.registerNumber));
                                if (null != resultBean.result.goodsType)
                                    errors.add(ShowErrorCodeUtil.getErrorString(mContext, resultBean.result.goodsType));
                                ToastUtil.showCustom(mContext, errors);
                                break;
                            case "SE210002":
                                errors.add(getResources().getString(R.string.SE210002));
                                break;
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
            case R.id.tvHangBan:
                if (ListUtil.isEmpty(flightList))
                    return;
                List<String> hbList = new ArrayList<>();
                for (int i = 0; i < flightList.size(); i++) {
                    hbList.add(flightList.get(i).getFlight());
                }
                SingleChooseDialog gcqkChooseDialog = new SingleChooseDialog(mContext, hbList, new SingleChooseDialog.OnItemClick() {
                    @Override
                    public void onItemClick(int position) {
                        tvHangBan.setText(hbList.get(position));
                        flightId = flightList.get(position).getId();
                    }
                });
                gcqkChooseDialog.show();
                break;
            case R.id.tvHwpm:
                if (ListUtil.isEmpty(goodsList))
                    return;
                List<String> goods = new ArrayList<>();
                for (int i = 0; i < goodsList.size(); i++) {
                    goods.add(goodsList.get(i).getGoodsName());
                }
                SingleChooseDialog goodsChooseDialog = new SingleChooseDialog(mContext, goods, new SingleChooseDialog.OnItemClick() {
                    @Override
                    public void onItemClick(int position) {
                        findWmsManifestGoodsByGoodsNameId(goodsList.get(0).getId());
                        tvHwpm.setText(goods.get(position));
                        findWmsManifestGoodsByGoodsNameId(goodsList.get(position).getId());
                        goodsNameId = goodsList.get(position).getId();
                        grossWeight = goodsList.get(position).getGrossWeight();
                        chargeableWeight = goodsList.get(position).getChargeableWeight();
                        rateClass = goodsList.get(position).getRateClass();
                        productCode = goodsList.get(position).getProductCode();
                    }
                });
                goodsChooseDialog.show();
                break;
            case R.id.ivAdd:
                if (!TextUtils.isEmpty(tvDjjs.getText()))
                    djNum = Integer.parseInt(tvDjjs.getText().toString());
                else
                    djNum = 0;
                djNum++;
                if (djNum > 0)
                    tvDjjs.setText(djNum + "");
                else
                    tvDjjs.setText("");
                break;
            case R.id.ivReduce:
                if (!TextUtils.isEmpty(tvDjjs.getText()))
                    djNum = Integer.parseInt(tvDjjs.getText().toString());
                else
                    djNum = 0;
                if (djNum > 0)
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
            case R.id.tvHdxq:
                new HangDanDetailDialog(mContext, departureStation, destinationStation, shipperName, shipperAddress, shipperPhone,
                        receiverName, receiverAddress, receiverPhone, grossWeight,chargeableWeight, rateClass, number, productCode).show();
                break;
            case R.id.tvSave:
                addSortingRegister();
                break;
        }
    }
}
