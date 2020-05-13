package com.lxkj.wms.event;

/**
 * Created by kxn on 2020/5/13 0013.
 */
public class StockSxEvent {
    public String barCode;//条形码
    public String awb;//	AWB号
    public String goodsType;//否	货物分类
    public String goodsName;//	否	货物名称
    public String productCode;//	否	商品代号
    public String wmsWarehouseId;//	否	仓库
    public String wmsWarehouseDetailName;//	否	储位
    public String stockState;//	否	库存状态
    public String  inputDateStart;//否	入库开始日期
    public String  inputDateEnd;//否	入库结束日期
    public String outputDateStart;//	否	出库开始日期
    public String outputDateEnd;//	否	出库结束日期
    public String inStockDayStart;//	否	在库天数（最小天数）
    public String inStockDayEnd;//	否	在库天数（最大天数）
    public String updaterName;//	否	最后操作人
    public String updateDateStart;//	否	最后操作开始日期
    public String updateDateEnd;//	否	最后操作结束日期
}
