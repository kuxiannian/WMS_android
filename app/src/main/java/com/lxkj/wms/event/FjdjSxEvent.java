package com.lxkj.wms.event;

/**
 * Created by kxn on 2020/5/12 0012.
 */
public class FjdjSxEvent{
    public String awb;//	AWB号
    public String flightName;//	航班
    public String goodsName;//	货物品名（包括包装、体积或尺寸）
    public String registerNumber;//	否	登记件数
    public String wmsWarehouseId;//	否	分拣登记地点
    public String goodsType;//	否	货物分类
    public String departureStation;//	否	始发站、第一承运人地址及要求的路线
    public String destinationStation;//	否	目的站
    public String productCode;//	否	商品代号

    public FjdjSxEvent(String awb, String flightName, String goodsName, String registerNumber, String wmsWarehouseId, String goodsType, String departureStation, String destinationStation, String productCode) {
        this.awb = awb;
        this.flightName = flightName;
        this.goodsName = goodsName;
        this.registerNumber = registerNumber;
        this.wmsWarehouseId = wmsWarehouseId;
        this.goodsType = goodsType;
        this.departureStation = departureStation;
        this.destinationStation = destinationStation;
        this.productCode = productCode;
    }
}
