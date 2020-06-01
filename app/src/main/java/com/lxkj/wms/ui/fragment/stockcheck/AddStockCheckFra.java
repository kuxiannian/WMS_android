package com.lxkj.wms.ui.fragment.stockcheck;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lxkj.wms.R;
import com.lxkj.wms.actlink.NaviRightListener;
import com.lxkj.wms.bean.BaseBean;
import com.lxkj.wms.bean.DetailBean;
import com.lxkj.wms.bean.ResultBean;
import com.lxkj.wms.bean.SortingRegisterBean;
import com.lxkj.wms.bean.WareHouseBean;
import com.lxkj.wms.biz.ActivitySwitcher;
import com.lxkj.wms.http.BaseCallback;
import com.lxkj.wms.http.OkHttpHelper;
import com.lxkj.wms.http.SpotsCallBack;
import com.lxkj.wms.http.Url;
import com.lxkj.wms.ui.fragment.TitleFragment;
import com.lxkj.wms.ui.fragment.kcpd.AddFra;
import com.lxkj.wms.ui.fragment.stockcheck.adapter.StockCheckDetailAdapter;
import com.lxkj.wms.utils.ListUtil;
import com.lxkj.wms.utils.ShowErrorCodeUtil;
import com.lxkj.wms.utils.ToastUtil;
import com.lxkj.wms.view.AddStockCheckDialog;
import com.lxkj.wms.view.EditStockCheckDialog;
import com.lxkj.wms.view.HintDialog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

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
 * Created by kxn on 2020/5/16 0016.
 */
public class AddStockCheckFra extends TitleFragment implements NaviRightListener, View.OnClickListener {

    Unbinder unbinder;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tvSave)
    TextView tvSave;
    @BindView(R.id.tvEnd)
    TextView tvEnd;

    List<WareHouseBean.ResultBean> list, deleteList;


    private List<WareHouseBean.ResultBean> wareHouseList;
    private List<WareHouseBean.ResultBean> warehouseDetailList;
    StockCheckDetailAdapter adapter;
    private String wmsBillStockCheckId;
    SortingRegisterBean.ResultBean.ContentBean bean;

    private String wmsWarehouseName, wmsWarehouseDetailName;//仓库 和  储位
    private String wmsWarehouseDetailId, barCode;

    public String getTitleName() {
        return act.getString(R.string.modalDataTitleAdd);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fra_add_stock, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    private void initView() {
        /**
         * 设置事件的点击事件
         */
        bean = (SortingRegisterBean.ResultBean.ContentBean) getArguments().getSerializable("bean");
        if (null != bean) {
            wmsBillStockCheckId = bean.getId();
        }
        tvSave.setOnClickListener(this);
        tvEnd.setOnClickListener(this);
        list = new ArrayList<>();
        deleteList = new ArrayList<>();
        adapter = new StockCheckDetailAdapter(mContext, list);
        adapter.setOnDeleteListener(new StockCheckDetailAdapter.OnDoListener() {
            @Override
            public void onDelete(int position) {
                new HintDialog(mContext, mContext.getString(R.string.hint_delete), true).setOnButtonClickListener(new HintDialog.OnButtonClick() {
                    @Override
                    public void OnRightClick() {
                    }

                    @Override
                    public void OnLeftClick() {
                        list.get(position).setDeleteFlag("1");
                        if (null != list.get(position).getId())
                            deleteList.add(list.get(position));
                        list.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                }).show();
            }

            @Override
            public void onEdit(int position) {
                new EditStockCheckDialog(mContext, warehouseDetailList, list.get(position).getWmsWarehouseDetailId(),  list.get(position).getBarCode(), new EditStockCheckDialog.OnConfirmListener() {
                    @Override
                    public void onConfirm(String barCode, String wmsWarehouseDetailId) {
                        list.get(position).setBarCode(barCode);
                        list.get(position).setWmsWarehouseDetailId(wmsWarehouseDetailId);
                        adapter.notifyDataSetChanged();
                    }
                }).show();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(adapter);
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                findBillStockCheckDetailList();
            }
        });
        refreshLayout.autoRefresh();
        findWarehouseListStockCheck();
        findWarehouseDetailList();
    }


    /**
     * 分页查询数据接口
     */
    private void findBillStockCheckDetailList() {
        Map<String, String> params = new HashMap<>();
        params.put("wmsBillStockCheckId", wmsBillStockCheckId);
        OkHttpHelper.getInstance().get_json(mContext, Url.findBillStockCheckDetailList, params, new BaseCallback<WareHouseBean>() {
            @Override
            public void onFailure(Request request, Exception e) {
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadMore();
            }

            @Override
            public void onSuccess(Response response, WareHouseBean resultBean) {
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadMore();
                list.clear();
                adapter.notifyDataSetChanged();
                if (resultBean.flag) {
                    if (!ListUtil.isEmpty(resultBean.getResult())) {
                        list.addAll(resultBean.getResult());
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onError(Response response, int code, Exception e) {
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadMore();
            }
        });
    }

    /**
     * 查询仓库列表接口
     */
    private void findWarehouseListStockCheck() {
        Map<String, String> params = new HashMap<>();
        OkHttpHelper.getInstance().get_json(mContext, Url.findWarehouseListStockCheck, params, new BaseCallback<WareHouseBean>() {
            @Override
            public void onFailure(Request request, Exception e) {
            }

            @Override
            public void onSuccess(Response response, WareHouseBean resultBean) {
                if (resultBean.flag) {
                    if (null != resultBean.getResult()) {
                        wareHouseList = resultBean.getResult();
                        wmsWarehouseName = getWmsWarehouseName(bean.getWmsWarehouseId());
                        adapter.setWareHouseList(wareHouseList);
                    }
                }
            }

            @Override
            public void onError(Response response, int code, Exception e) {
            }
        });
    }


    /**
     * 查询储位列表接口
     */
    private void findWarehouseDetailList() {
        Map<String, String> params = new HashMap<>();
        params.put("wmsWarehouseId", bean.getWmsWarehouseId());
        params.put("state", bean.getState());

        OkHttpHelper.getInstance().get_json(mContext, Url.findWarehouseDetailList, params, new BaseCallback<WareHouseBean>() {
            @Override
            public void onFailure(Request request, Exception e) {
            }

            @Override
            public void onSuccess(Response response, WareHouseBean resultBean) {
                if (resultBean.flag) {
                    if (null != resultBean.getResult()) {
                        warehouseDetailList = resultBean.getResult();
                        adapter.setWarehouseDetailList(warehouseDetailList);
                    }
                }
            }

            @Override
            public void onError(Response response, int code, Exception e) {
            }
        });
    }


    /**
     * 保存草稿数据接口
     */
    private void updateBillStockCheckTemp() {
        Map<String, String> params = new HashMap<>();
        params.put("id", wmsBillStockCheckId);
        params.put("version", bean.getVersion());
        List<DetailBean> detailBeans = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            DetailBean detailBean = new DetailBean(null,list.get(i).getBarCode(), list.get(i).getWmsWarehouseId(), list.get(i).getWmsWarehouseDetailId());
            if (null != list.get(i).getId())
                detailBean.setId(list.get(i).getId());
            if (null != list.get(i).getVersion())
                detailBean.setVersion(list.get(i).getVersion());
            detailBeans.add(detailBean);
        }
        for (int i = 0; i < deleteList.size(); i++) {
            DetailBean detailBean = new DetailBean(null,deleteList.get(i).getBarCode(), deleteList.get(i).getWmsWarehouseId(), deleteList.get(i).getWmsWarehouseDetailId());
            detailBean.setDeleteFlag(deleteList.get(i).getDeleteFlag());
            detailBean.setId(deleteList.get(i).getId());
            detailBean.setVersion(deleteList.get(i).getVersion());
            detailBeans.add(detailBean);
        }
        params.put("detail", new Gson().toJson(detailBeans));
        OkHttpHelper.getInstance().post_json(mContext, Url.updateBillStockCheckTemp, params, new SpotsCallBack<BaseBean>(mContext) {
            @Override
            public void onFailure(Request request, Exception e) {
            }

            @Override
            public void onSuccess(Response response, BaseBean result) {
                if (result.flag) {
                    ToastUtil.show(mContext.getResources().getString(R.string.seSave));
                    refreshLayout.autoRefresh();
                    deleteList.clear();
                    barCode = null;
                }
            }

            @Override
            public void onError(Response response, int code, Exception e) {
            }
        });
    }


    /**
     * 完成盘点接口
     */
    private void updateBillStockCheckOver() {
        Map<String, String> params = new HashMap<>();
        params.put("id", wmsBillStockCheckId);
        params.put("version", bean.getVersion());
        List<DetailBean> detailBeans = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            DetailBean detailBean = new DetailBean(list.get(i).getId(),list.get(i).getBarCode(), list.get(i).getWmsWarehouseId(), list.get(i).getWmsWarehouseDetailId());
            detailBean.setVersion(list.get(i).getVersion());
            detailBeans.add(detailBean);
        }
        params.put("detail", new Gson().toJson(detailBeans));
        OkHttpHelper.getInstance().post_json(mContext, Url.updateBillStockCheckOver, params, new SpotsCallBack<String>(mContext) {
            @Override
            public void onFailure(Request request, Exception e) {
            }

            @Override
            public void onSuccess(Response response, String result) {
                ResultBean resultBean = new Gson().fromJson(result, ResultBean.class);
                if (resultBean.flag) {
                        ToastUtil.show(mContext.getResources().getString(R.string.seSave));
                        act.finishSelf();
                } else {
                    if (resultBean.errorCode.contains("?")) {
                        String[] error = resultBean.errorCode.split("\\?");
                        String errorCode = error[0];
                        Map<String, String> errorValues = ShowErrorCodeUtil.getErrorValue(error[1]);
                        String lineNumber = errorValues.get("lineNumber");
                        List<String> errors = new ArrayList<>();
                        switch (errorCode) {
                            case "VE200001":
                                errors.add(String.format(getResources().getString(R.string.VE200001), lineNumber));
                                break;
                            case "VE200002":
                                errors.add(String.format(getResources().getString(R.string.VE200002), lineNumber));
                                break;
                            case "VE200003":
                                errors.add(String.format(getResources().getString(R.string.VE200003), lineNumber));
                                break;
                            case "VE200004":
                                errors.add(String.format(getResources().getString(R.string.VE200004), lineNumber));
                                break;
                            case "VE200005":
                                errors.add(String.format(getResources().getString(R.string.VE200005), lineNumber));
                                break;
                            case "VE200006":
                                errors.add(String.format(getResources().getString(R.string.VE200006), lineNumber));
                                break;
                            case "VE200007":
                                errors.add(String.format(getResources().getString(R.string.VE200007), lineNumber));
                                break;
                            case "VE200008":
                                errors.add(String.format(getResources().getString(R.string.VE200008), lineNumber));
                                break;
                        }
                        if (errors.size() > 0)
                            ToastUtil.showCustom(mContext, errors);
                    } else {
                        List<String> errors = new ArrayList<>();
                        switch (resultBean.errorCode) {
                            case "VE170001":
                                errors.add(getResources().getString(R.string.VE170001));
                                break;
                            case "VE170002":
                                errors.add(getResources().getString(R.string.VE170002));
                                break;
                            case "VE170003":
                                errors.add(getResources().getString(R.string.VE170003));
                                break;
                            case "VE170004":
                                errors.add(getResources().getString(R.string.VE170004));
                                break;
                            case "VE170005":
                                errors.add(getResources().getString(R.string.VE170005));
                                break;
                            case "SE170001":
                                errors.add(getResources().getString(R.string.SE170001));
                                break;
                            case "SE170002":
                                errors.add(getResources().getString(R.string.SE170002));
                                break;
                            case "SE170003":
                                errors.add(getResources().getString(R.string.SE170003));
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

    private String getWmsWarehouseName(String wmsWarehouseId) {
        if (null != wareHouseList) {
            for (int i = 0; i < wareHouseList.size(); i++) {
                if (wareHouseList.get(i).getId().equals(wmsWarehouseId))
                    return wareHouseList.get(i).getName();
            }
            return "";
        }
        return "";
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 2) {
            String barCode = data.getStringExtra("barCode");
            if (null == barCode)
                barCode = "";
            new AddStockCheckDialog(mContext, warehouseDetailList, wmsWarehouseName, barCode, new AddStockCheckDialog.OnConfirmListener() {
                @Override
                public void onConfirm(String barCode, String wmsWarehouseDetailId) {
                    AddStockCheckFra.this.barCode = barCode;
                    AddStockCheckFra.this.wmsWarehouseDetailId = wmsWarehouseDetailId;
                    WareHouseBean.ResultBean resultBean = new WareHouseBean.ResultBean();
                    resultBean.setBarCode(barCode);
                    resultBean.setWmsWarehouseId(bean.getWmsWarehouseId());
                    resultBean.setWmsWarehouseDetailId(wmsWarehouseDetailId);
                    list.add(resultBean);
                    adapter.notifyDataSetChanged();
                }
            }).show();
        }
    }

    @Override
    public int rightText() {
        return R.string.add;
    }

    @Override
    public void onRightClicked(View v) {
        ActivitySwitcher.startFrgForResult(act, AddFra.class, 1);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvSave:
                updateBillStockCheckTemp();
                break;
            case R.id.tvEnd:
                updateBillStockCheckOver();
                break;
        }
    }
}
