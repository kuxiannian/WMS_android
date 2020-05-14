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

    public StockSxEvent(String barCode, String awb, String goodsType, String goodsName, String productCode, String wmsWarehouseId, String wmsWarehouseDetailName, String stockState, String inputDateStart, String inputDateEnd, String outputDateStart, String outputDateEnd, String inStockDayStart,
                        String inStockDayEnd, String updaterName, String updateDateStart, String updateDateEnd) {
        this.barCode = barCode;
        this.awb = awb;
        this.goodsType = goodsType;
        this.goodsName = goodsName;
        this.productCode = productCode;
        this.wmsWarehouseId = wmsWarehouseId;
        this.wmsWarehouseDetailName = wmsWarehouseDetailName;
        this.stockState = stockState;
        this.inputDateStart = inputDateStart;
        this.inputDateEnd = inputDateEnd;
        this.outputDateStart = outputDateStart;
        this.outputDateEnd = outputDateEnd;
        this.inStockDayStart = inStockDayStart;
        this.inStockDayEnd = inStockDayEnd;
        this.updaterName = updaterName;
        this.updateDateStart = updateDateStart;
        this.updateDateEnd = updateDateEnd;
    }
}
