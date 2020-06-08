package com.lxkj.wms.ui.fragment.kcpd;

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
import com.lxkj.wms.biz.ActivitySwitcher;
import com.lxkj.wms.event.StockSxEvent;
import com.lxkj.wms.http.BaseCallback;
import com.lxkj.wms.http.OkHttpHelper;
import com.lxkj.wms.http.Url;
import com.lxkj.wms.ui.fragment.TitleFragment;
import com.lxkj.wms.ui.fragment.kccx.ShaiXuanKcFra;
import com.lxkj.wms.ui.fragment.kccx.adapter.KccxAdapter;
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
 * Created by kxn on 2020/4/25 0025.
 */
public class KcpdFra  extends TitleFragment implements NaviRightListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;
    List<SortingRegisterBean.ResultBean.ContentBean> list;
    KccxAdapter adapter;
    private int page = 0;
    private boolean isMore = true;//是否有更多数据

    private String barCode;//条形码
    private String awb;//	AWB号
    private String goodsType;//否	货物分类
    private String goodsName;//	否	货物名称
    private String productCode;//	否	商品代号
    private String wmsWarehouseId;//	否	仓库
    private String wmsWarehouseDetailName;//	否	储位
    private String stockState;//	否	库存状态
    private String inputDateStart;//否	入库开始日期
    private String inputDateEnd;//否	入库结束日期
    private String outputDateStart;//	否	出库开始日期
    private String outputDateEnd;//	否	出库结束日期
    private String inStockDayStart;//	否	在库天数（最小天数）
    private String inStockDayEnd;//	否	在库天数（最大天数）
    private String updaterName;//	否	最后操作人
    private String updateDateStart;//	否	最后操作开始日期
    private String updateDateEnd;//	否	最后操作结束日期


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
        EventBus.getDefault().register(this);
        list = new ArrayList<>();
        adapter = new KccxAdapter(mContext, list);
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
                findStockPage();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                findStockPage();
                refreshLayout.setNoMoreData(false);
            }
        });
        refreshLayout.autoRefresh();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSelectSx(StockSxEvent stockSxEvent) {
        barCode = stockSxEvent.barCode;
        inputDateEnd = stockSxEvent.inputDateEnd;
        inputDateStart = stockSxEvent.inputDateStart;
        wmsWarehouseId = stockSxEvent.wmsWarehouseId;
        awb = stockSxEvent.awb;
        wmsWarehouseDetailName = stockSxEvent.wmsWarehouseDetailName;
        goodsType = stockSxEvent.goodsType;
        goodsName = stockSxEvent.goodsName;
        updaterName = stockSxEvent.updaterName;
        updateDateStart = stockSxEvent.updateDateStart;
        updateDateEnd = stockSxEvent.updateDateEnd;
        stockState = stockSxEvent.updateDateEnd;
        inputDateStart = stockSxEvent.inputDateStart;
        inputDateEnd = stockSxEvent.inputDateEnd;
        outputDateStart = stockSxEvent.outputDateStart;
        outputDateEnd = stockSxEvent.outputDateEnd;
        inStockDayStart = stockSxEvent.inStockDayStart;
        inStockDayEnd = stockSxEvent.inStockDayEnd;
        refreshLayout.autoRefresh();
    }


    /**
     * 分页查询数据接口
     */
    private void findStockPage() {
        Map<String, String> params = new HashMap<>();
        params.put("page", page + "");
        params.put("size", "10");
        if (null != barCode)
            params.put("barCode", barCode);
        if (!StringUtil.isEmpty(awb))
            params.put("awb", awb);
        if (!StringUtil.isEmpty(goodsType))
            params.put("goodsType", goodsType);
        if (!StringUtil.isEmpty(goodsName))
            params.put("goodsName", goodsName);
        if (null != productCode)
            params.put("productCode", productCode);
        if (null != wmsWarehouseId)
            params.put("wmsWarehouseId", wmsWarehouseId);
        if (!StringUtil.isEmpty(wmsWarehouseDetailName))
            params.put("wmsWarehouseDetailName", wmsWarehouseDetailName);
        if (!StringUtil.isEmpty(stockState))
            params.put("stockState", stockState);
        if (!StringUtil.isEmpty(inputDateStart))
            params.put("inputDateStart", inputDateStart);
        if (!StringUtil.isEmpty(inputDateEnd))
            params.put("inputDateEnd", inputDateEnd);
        if (!StringUtil.isEmpty(outputDateStart))
            params.put("outputDateStart", outputDateStart);
        if (!StringUtil.isEmpty(outputDateEnd))
            params.put("outputDateEnd", outputDateEnd);
        if (!StringUtil.isEmpty(inStockDayStart))
            params.put("inStockDayStart", inStockDayStart);
        if (!StringUtil.isEmpty(inStockDayEnd))
            params.put("inStockDayEnd", inStockDayEnd);
        if (!StringUtil.isEmpty(updaterName))
            params.put("updaterName", updaterName);
        if (!StringUtil.isEmpty(updateDateStart))
            params.put("updateDateStart", updateDateStart);
        if (!StringUtil.isEmpty(updateDateEnd))
            params.put("updateDateEnd", updateDateEnd);

        OkHttpHelper.getInstance().get_json(mContext, Url.findStockPage, params, new BaseCallback<SortingRegisterBean>() {
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
        ActivitySwitcher.startFragment(act, ShaiXuanKcFra.class);
    }

}
