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
import com.lxkj.wms.biz.ActivitySwitcher;
import com.lxkj.wms.http.BaseCallback;
import com.lxkj.wms.http.OkHttpHelper;
import com.lxkj.wms.http.Url;
import com.lxkj.wms.ui.activity.NaviActivity;
import com.lxkj.wms.ui.fragment.TitleFragment;
import com.lxkj.wms.ui.fragment.stockcheck.adapter.InventoryStockAdapter;
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
 * Created by kxn on 2020/5/16 0016.
 */
public class StockCheckFra extends TitleFragment implements NaviActivity.NaviRigthImageListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;
    List<SortingRegisterBean.ResultBean.ContentBean> list;
    InventoryStockAdapter adapter;
    private int page = 0;
    private boolean isMore = true;//是否有更多数据

    private String state = "1";//	状态

    private List<WareHouseBean.ResultBean> wareHouseList;

    @Override
    public String getTitleName() {
        return act.getString(R.string.kcpd);
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
        list = new ArrayList<>();
        adapter = new InventoryStockAdapter(mContext, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(adapter);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (!isMore) {
                    refreshLayout.setNoMoreData(true);
                    return;
                }
                page++;
                findBillStockCheckPage();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                findBillStockCheckPage();
                refreshLayout.setNoMoreData(false);
            }
        });
        refreshLayout.autoRefresh();
        findWarehouseListStockCheck();
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshLayout.autoRefresh();
    }

    /**
     * 分页查询数据接口
     */
    private void findBillStockCheckPage() {
        Map<String, String> params = new HashMap<>();
        params.put("page", page + "");
        params.put("size", "10");
        params.put("state", state);

        OkHttpHelper.getInstance().get_json(mContext, Url.findBillStockCheckPage, params, new BaseCallback<SortingRegisterBean>() {
            @Override
            public void onFailure(Request request, Exception e) {
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadMore();
            }

            @Override
            public void onSuccess(Response response, SortingRegisterBean resultBean) {
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadMore();
                if (page == 0) {
                    list.clear();
                    adapter.notifyDataSetChanged();
                }
                if (resultBean.flag) {
                    if (null != resultBean.result) {
                        if (!ListUtil.isEmpty(resultBean.result.getContent())) {
                            isMore = true;
                            list.addAll(resultBean.result.getContent());
                            adapter.notifyDataSetChanged();
                        } else
                            isMore = false;
                    } else {
                        isMore = false;
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
                        adapter.setWareHouseList(wareHouseList);
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
    public int rightImg() {
        return R.mipmap.ic_time;
    }

    @Override
    public void onRightClicked(View v) {
        ActivitySwitcher.startFragment(act, HistoryStockCheckFra.class);
    }

}



