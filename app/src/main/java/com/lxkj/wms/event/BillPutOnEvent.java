package com.lxkj.wms.event;

/**
 * Created by kxn on 2020/5/14 0014.
 */
public class BillPutOnEvent {
    public String barCode;//条形码
    public String putOnDateStart;//上架开始日期
    public String putOnDateEnd;//上架结束日期
    public String weight;//重量
    public String palletNumber;//托盘号
    public String productCode;//商品代号
    public String wmsWarehouseId;//仓库Id
    public String wmsWarehouseDetailIdName;//储位名称
    public String updaterName;//操作员姓名
    public String updateDateStart;//操作开始时间
    public String updateDateEnd;//操作结束时间

    public BillPutOnEvent(String barCode, String putOnDateStart, String putOnDateEnd, String weight, String palletNumber, String productCode, String wmsWarehouseId, String wmsWarehouseDetailIdName, String updaterName, String updateDateStart, String updateDateEnd) {
        this.barCode = barCode;
        this.putOnDateStart = putOnDateStart;
        this.putOnDateEnd = putOnDateEnd;
        this.weight = weight;
        this.palletNumber = palletNumber;
        this.productCode = productCode;
        this.wmsWarehouseId = wmsWarehouseId;
        this.wmsWarehouseDetailIdName = wmsWarehouseDetailIdName;
        this.updaterName = updaterName;
        this.updateDateStart = updateDateStart;
        this.updateDateEnd = updateDateEnd;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getPutOnDateStart() {
        return putOnDateStart;
    }

    public void setPutOnDateStart(String putOnDateStart) {
        this.putOnDateStart = putOnDateStart;
    }

    public String getPutOnDateEnd() {
        return putOnDateEnd;
    }

    public void setPutOnDateEnd(String putOnDateEnd) {
        this.putOnDateEnd = putOnDateEnd;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPalletNumber() {
        return palletNumber;
    }

    public void setPalletNumber(String palletNumber) {
        this.palletNumber = palletNumber;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getWmsWarehouseId() {
        return wmsWarehouseId;
    }

    public void setWmsWarehouseId(String wmsWarehouseId) {
        this.wmsWarehouseId = wmsWarehouseId;
    }

    public String getWmsWarehouseDetailIdName() {
        return wmsWarehouseDetailIdName;
    }

    public void setWmsWarehouseDetailIdName(String wmsWarehouseDetailIdName) {
        this.wmsWarehouseDetailIdName = wmsWarehouseDetailIdName;
    }

    public String getUpdaterName() {
        return updaterName;
    }

    public void setUpdaterName(String updaterName) {
        this.updaterName = updaterName;
    }

    public String getUpdateDateStart() {
        return updateDateStart;
    }

    public void setUpdateDateStart(String updateDateStart) {
        this.updateDateStart = updateDateStart;
    }

    public String getUpdateDateEnd() {
        return updateDateEnd;
    }

    public void setUpdateDateEnd(String updateDateEnd) {
        this.updateDateEnd = updateDateEnd;
    }
}
