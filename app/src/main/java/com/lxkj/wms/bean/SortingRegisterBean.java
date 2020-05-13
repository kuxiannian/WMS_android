package com.lxkj.wms.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kxn on 2020/5/11 0011.
 */
public class SortingRegisterBean extends BaseBean {
    public ResultBean result;

    public class ResultBean {
        private List<ContentBean> content;

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> result) {
            this.content = content;
        }

        public class ContentBean implements Serializable {
            private String awb;//	AWB号
            private String chargeableWeight;//	计费重量
            private String createDate;//	创建日期
            private String createrId;//	创建人Id
            private String createrName;//	创建人姓名
            private String deleteFlag;//	删除标识
            private String departureStation;//	始发站
            private String destinationStation;//	目的站
            private String flightId;//	航班Id
            private String flightName;//	航班
            private String goodsName;//	货物品名（包括包装、体积或尺寸）
            private String goodsNameId;//	货物品名Id
            private String goodsType;//	货物分类
            private String grossWeight;//	毛重
            private String id;//	分拣登记ID
            private String number;//	件数
            private String productCode;//	商品代号
            private String rateClass;//	运价种类
            private String receiverAddress;//	收货人地址
            private String receiverName;//	收货人姓名
            private String receiverPhone;//	收货人电话号码
            private String registerNumber;//	登记数量
            private String remarks;//	备注
            private String shipperAddress;//托运人地址
            private String shipperName;//	托运人姓名
            private String shipperPhone;//	托运人电话号码
            private String updateDate;//	更新日期
            private String updaterId;//	更新人Id
            private String updaterName;//更新人姓名
            private String version;//版本号
            private String wmsManifestId;//	舱单信息ID
            private String wmsWarehouseId;//	仓库ID
            private String wmsWarehouseName;//	仓库名称

            private String barCode;//条形码
            private String inputDate;//	入库日期
            private String palletNumber;//托盘号
            private String weight;//	重量
            private String wmsStockId;//	库存表ID

            private String consignor;//	提货人
            private String consignorPhone;//	提货人联系方式
            private String ladingNumber;//	提货单编号
            private String outputDate;//	出库日期
            private String suspicion;//	是否有嫌疑
            private String suspicionProblem;//	嫌疑问题
            private String inStockDay;//	在库天数
            private String stockState;//	库存状态
            private String wmsWarehouseDetailName;//	储位



            public boolean isOpen;

            public String getInStockDay() {
                return inStockDay;
            }

            public void setInStockDay(String inStockDay) {
                this.inStockDay = inStockDay;
            }

            public String getStockState() {
                return stockState;
            }

            public void setStockState(String stockState) {
                this.stockState = stockState;
            }

            public String getWmsWarehouseDetailName() {
                return wmsWarehouseDetailName;
            }

            public void setWmsWarehouseDetailName(String wmsWarehouseDetailName) {
                this.wmsWarehouseDetailName = wmsWarehouseDetailName;
            }

            public String getConsignor() {
                return consignor;
            }

            public void setConsignor(String consignor) {
                this.consignor = consignor;
            }

            public String getConsignorPhone() {
                return consignorPhone;
            }

            public void setConsignorPhone(String consignorPhone) {
                this.consignorPhone = consignorPhone;
            }

            public String getLadingNumber() {
                return ladingNumber;
            }

            public void setLadingNumber(String ladingNumber) {
                this.ladingNumber = ladingNumber;
            }

            public String getOutputDate() {
                return outputDate;
            }

            public void setOutputDate(String outputDate) {
                this.outputDate = outputDate;
            }

            public String getSuspicion() {
                return suspicion;
            }

            public void setSuspicion(String suspicion) {
                this.suspicion = suspicion;
            }

            public String getSuspicionProblem() {
                return suspicionProblem;
            }

            public void setSuspicionProblem(String suspicionProblem) {
                this.suspicionProblem = suspicionProblem;
            }

            public String getBarCode() {
                return barCode;
            }

            public void setBarCode(String barCode) {
                this.barCode = barCode;
            }

            public String getInputDate() {
                return inputDate;
            }

            public void setInputDate(String inputDate) {
                this.inputDate = inputDate;
            }

            public String getPalletNumber() {
                return palletNumber;
            }

            public void setPalletNumber(String palletNumber) {
                this.palletNumber = palletNumber;
            }

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }

            public String getWmsStockId() {
                return wmsStockId;
            }

            public void setWmsStockId(String wmsStockId) {
                this.wmsStockId = wmsStockId;
            }

            public boolean isOpen() {
                return isOpen;
            }

            public void setOpen(boolean open) {
                isOpen = open;
            }

            public String getAwb() {
                return awb;
            }

            public void setAwb(String awb) {
                this.awb = awb;
            }

            public String getChargeableWeight() {
                return chargeableWeight;
            }

            public void setChargeableWeight(String chargeableWeight) {
                this.chargeableWeight = chargeableWeight;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public String getCreaterId() {
                return createrId;
            }

            public void setCreaterId(String createrId) {
                this.createrId = createrId;
            }

            public String getCreaterName() {
                return createrName;
            }

            public void setCreaterName(String createrName) {
                this.createrName = createrName;
            }

            public String getDeleteFlag() {
                return deleteFlag;
            }

            public void setDeleteFlag(String deleteFlag) {
                this.deleteFlag = deleteFlag;
            }

            public String getDepartureStation() {
                return departureStation;
            }

            public void setDepartureStation(String departureStation) {
                this.departureStation = departureStation;
            }

            public String getDestinationStation() {
                return destinationStation;
            }

            public void setDestinationStation(String destinationStation) {
                this.destinationStation = destinationStation;
            }

            public String getFlightId() {
                return flightId;
            }

            public void setFlightId(String flightId) {
                this.flightId = flightId;
            }

            public String getFlightName() {
                return flightName;
            }

            public void setFlightName(String flightName) {
                this.flightName = flightName;
            }

            public String getGoodsName() {
                return goodsName;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }

            public String getGoodsNameId() {
                return goodsNameId;
            }

            public void setGoodsNameId(String goodsNameId) {
                this.goodsNameId = goodsNameId;
            }

            public String getGoodsType() {
                return goodsType;
            }

            public void setGoodsType(String goodsType) {
                this.goodsType = goodsType;
            }

            public String getGrossWeight() {
                return grossWeight;
            }

            public void setGrossWeight(String grossWeight) {
                this.grossWeight = grossWeight;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public String getProductCode() {
                return productCode;
            }

            public void setProductCode(String productCode) {
                this.productCode = productCode;
            }

            public String getRateClass() {
                return rateClass;
            }

            public void setRateClass(String rateClass) {
                this.rateClass = rateClass;
            }

            public String getReceiverAddress() {
                return receiverAddress;
            }

            public void setReceiverAddress(String receiverAddress) {
                this.receiverAddress = receiverAddress;
            }

            public String getReceiverName() {
                return receiverName;
            }

            public void setReceiverName(String receiverName) {
                this.receiverName = receiverName;
            }

            public String getReceiverPhone() {
                return receiverPhone;
            }

            public void setReceiverPhone(String receiverPhone) {
                this.receiverPhone = receiverPhone;
            }

            public String getRegisterNumber() {
                return registerNumber;
            }

            public void setRegisterNumber(String registerNumber) {
                this.registerNumber = registerNumber;
            }

            public String getRemarks() {
                return remarks;
            }

            public void setRemarks(String remarks) {
                this.remarks = remarks;
            }

            public String getShipperAddress() {
                return shipperAddress;
            }

            public void setShipperAddress(String shipperAddress) {
                this.shipperAddress = shipperAddress;
            }

            public String getShipperName() {
                return shipperName;
            }

            public void setShipperName(String shipperName) {
                this.shipperName = shipperName;
            }

            public String getShipperPhone() {
                return shipperPhone;
            }

            public void setShipperPhone(String shipperPhone) {
                this.shipperPhone = shipperPhone;
            }

            public String getUpdateDate() {
                return updateDate;
            }

            public void setUpdateDate(String updateDate) {
                this.updateDate = updateDate;
            }

            public String getUpdaterId() {
                return updaterId;
            }

            public void setUpdaterId(String updaterId) {
                this.updaterId = updaterId;
            }

            public String getUpdaterName() {
                return updaterName;
            }

            public void setUpdaterName(String updaterName) {
                this.updaterName = updaterName;
            }

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
            }

            public String getWmsManifestId() {
                return wmsManifestId;
            }

            public void setWmsManifestId(String wmsManifestId) {
                this.wmsManifestId = wmsManifestId;
            }

            public String getWmsWarehouseId() {
                return wmsWarehouseId;
            }

            public void setWmsWarehouseId(String wmsWarehouseId) {
                this.wmsWarehouseId = wmsWarehouseId;
            }

            public String getWmsWarehouseName() {
                return wmsWarehouseName;
            }

            public void setWmsWarehouseName(String wmsWarehouseName) {
                this.wmsWarehouseName = wmsWarehouseName;
            }
        }
    }


}
