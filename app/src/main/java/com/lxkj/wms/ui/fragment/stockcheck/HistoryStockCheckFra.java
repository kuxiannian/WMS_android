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
import com.lxkj.wms.actlink.NaviRightListener;
import com.lxkj.wms.bean.SortingRegisterBean;
import com.lxkj.wms.bean.WareHouseBean;
import com.lxkj.wms.biz.ActivitySwitcher;
import com.lxkj.wms.event.StockCheckEvent;
import com.lxkj.wms.http.BaseCallback;
import com.lxkj.wms.http.OkHttpHelper;
import com.lxkj.wms.http.Url;
import com.lxkj.wms.ui.fragment.TitleFragment;
import com.lxkj.wms.ui.fragment.stockcheck.adapter.StockCheckAdapter;
import com.lxkj.wms.utils.ListUtil;
import com.lxkj.wms.utils.StringUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
 * Created by kxn on 2020/5/15 0015.
 */
public class HistoryStockCheckFra  extends TitleFragment implements NaviRightListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;
    List<SortingRegisterBean.ResultBean.ContentBean> list;
    StockCheckAdapter adapter;
    private int page = 0;
    private boolean isMore = true;//是否有更多数据

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
        EventBus.getDefault().register(this);
        list = new ArrayList<>();
        adapter = new StockCheckAdapter(mContext, list);
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSelectSx(StockCheckEvent stockCheckEvent) {
        wmsWarehouseId = stockCheckEvent.wmsWarehouseId;
        startDateStart = stockCheckEvent.startDateStart;
        startDateEnd = stockCheckEvent.startDateEnd;
        endDateStart = stockCheckEvent.endDateStart;
        endDateEnd = stockCheckEvent.endDateEnd;
        state = stockCheckEvent.state;
        updaterName = stockCheckEvent.updaterName;
        updateDateStart = stockCheckEvent.updateDateStart;
        updateDateEnd = stockCheckEvent.updateDateEnd;
        refreshLayout.autoRefresh();
    }


    /**
     * 分页查询数据接口
     */
    private void findBillStockCheckPage() {
        Map<String, String> params = new HashMap<>();
        params.put("page", page + "");
        params.put("size", "10");
        if (null != wmsWarehouseId)
            params.put("wmsWarehouseId", wmsWarehouseId);
        if (!StringUtil.isEmpty(startDateStart))
            params.put("startDateStart", startDateStart);
        if (!StringUtil.isEmpty(startDateEnd))
            params.put("startDateEnd", startDateEnd);
        if (!StringUtil.isEmpty(endDateStart))
            params.put("endDateStart", endDateStart);
        if (null != endDateEnd)
            params.put("endDateEnd", endDateEnd);
        if (null != state)
            params.put("state", state);
        if (!StringUtil.isEmpty(updaterName))
            params.put("updaterName", updaterName);
        if (!StringUtil.isEmpty(updaterName))
            params.put("updaterName", updaterName);
        if (!StringUtil.isEmpty(updateDateStart))
            params.put("updateDateStart", updateDateStart);
        if (!StringUtil.isEmpty(updateDateEnd))
            params.put("updateDateEnd", updateDateEnd);

        OkHttpHelper.getInstance().get_json(mContext, Url.findBillStockCheckPage, params, new BaseCallback<SortingRegisterBean>() {
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



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public int rightText() {
        return R.string.sx;
    }

    @Override
    public void onRightClicked(View v) {
        ActivitySwitcher.startFragment(act, ShaiXuanStockCheckFra.class);
    }

}



