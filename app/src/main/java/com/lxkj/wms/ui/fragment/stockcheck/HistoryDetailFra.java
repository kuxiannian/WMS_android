package com.lxkj.wms.ui.fragment.stockcheck;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lxkj.wms.R;
import com.lxkj.wms.bean.SortingRegisterBean;
import com.lxkj.wms.bean.WareHouseBean;
import com.lxkj.wms.http.BaseCallback;
import com.lxkj.wms.http.OkHttpHelper;
import com.lxkj.wms.http.Url;
import com.lxkj.wms.ui.fragment.TitleFragment;
import com.lxkj.wms.ui.fragment.stockcheck.adapter.HistoryDetailAdapter;
import com.lxkj.wms.utils.ListUtil;
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
 * Created by kxn on 2020/5/29 0029.
 */
public class HistoryDetailFra extends TitleFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;
    List<WareHouseBean.ResultBean> list;
    HistoryDetailAdapter adapter;
    private String wmsBillStockCheckId;
    SortingRegisterBean.ResultBean.ContentBean bean;
    private List<WareHouseBean.ResultBean> wareHouseList;
    private List<WareHouseBean.ResultBean> warehouseDetailList;
    @Override
    public String getTitleName() {
        return act.getString(R.string.lscx);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.layout_recyclerview, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    private void initView() {
        bean = (SortingRegisterBean.ResultBean.ContentBean) getArguments().getSerializable("bean");
        if (null != bean) {
            wmsBillStockCheckId = bean.getId();
        }
        list = new ArrayList<>();
        adapter = new HistoryDetailAdapter(mContext, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(adapter);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                findBillStockCheckPage();
            }
        });
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.autoRefresh();
        findWarehouseListStockCheck();
        findWarehouseDetailList();
    }


    /**
     * 分页查询数据接口
     */
    private void findBillStockCheckPage() {
        Map<String, String> params = new HashMap<>();
        params.put("wmsBillStockCheckId", wmsBillStockCheckId);
        OkHttpHelper.getInstance().get_json(mContext, Url.findBillStockCheckDetailList, params, new BaseCallback<WareHouseBean>() {
            @Override
            public void onBeforeRequest(Request request) {

            }

            @Override
            public void onResponse(Response response) {

            }

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
                    if (null != resultBean.getResult()) {
                        if (!ListUtil.isEmpty(resultBean.getResult())) {
                            list.addAll(resultBean.getResult());
                            adapter.notifyDataSetChanged();
                        }
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
        params.put("state", "1");

        OkHttpHelper.getInstance().get_json(mContext, Url.findWarehouseDetailList, params, new BaseCallback<WareHouseBean>() {
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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}



