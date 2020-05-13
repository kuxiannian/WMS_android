package com.lxkj.wms.bean;

import java.util.List;

/**
 * Created by kxn on 2020/5/9 0009.
 */
public class GoodsBean extends BaseBean{

    /**
     * flag : true
     * message : null
     * errorCode : null
     * result : [{"id":"ae695445-96c3-4de0-b05e-c1621c01c304","wmsManifestId":"5c14ced0-7040-4986-a9f5-61ddd37056e9","lineNumber":"1","number":100,"rcp":1,"grossWeight":1000,"rateClass":"E","productCode":"E","chargeableWeight":1000,"rate":1,"total":1000,"goodsName":"E","createrId":"USER","createrName":"USER","createDate":1579067528670,"updaterId":"USER","updaterName":"USER","updateDate":1579067528670,"deleteFlag":"0","version":0}]
     */

    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : ae695445-96c3-4de0-b05e-c1621c01c304
         * wmsManifestId : 5c14ced0-7040-4986-a9f5-61ddd37056e9
         * lineNumber : 1
         * number : 100
         * rcp : 1
         * grossWeight : 1000.0
         * rateClass : E
         * productCode : E
         * chargeableWeight : 1000.0
         * rate : 1.0
         * total : 1000.0
         * goodsName : E
         * createrId : USER
         * createrName : USER
         * createDate : 1579067528670
         * updaterId : USER
         * updaterName : USER
         * updateDate : 1579067528670
         * deleteFlag : 0
         * version : 0
         */

        private String id;
        private String wmsManifestId;
        private String lineNumber;
        private int number;
        private int rcp;
        private String grossWeight;
        private String rateClass;
        private String productCode;
        private String chargeableWeight;
        private double rate;
        private double total;
        private String goodsName;
        private String createrId;
        private String createrName;
        private long createDate;
        private String updaterId;
        private String updaterName;
        private long updateDate;
        private String deleteFlag;
        private int version;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getWmsManifestId() {
            return wmsManifestId;
        }

        public void setWmsManifestId(String wmsManifestId) {
            this.wmsManifestId = wmsManifestId;
        }

        public String getLineNumber() {
            return lineNumber;
        }

        public void setLineNumber(String lineNumber) {
            this.lineNumber = lineNumber;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getRcp() {
            return rcp;
        }

        public void setRcp(int rcp) {
            this.rcp = rcp;
        }

        public String getGrossWeight() {
            return grossWeight;
        }

        public void setGrossWeight(String grossWeight) {
            this.grossWeight = grossWeight;
        }

        public String getRateClass() {
            return rateClass;
        }

        public void setRateClass(String rateClass) {
            this.rateClass = rateClass;
        }

        public String getProductCode() {
            return productCode;
        }

        public void setProductCode(String productCode) {
            this.productCode = productCode;
        }

        public String getChargeableWeight() {
            return chargeableWeight;
        }

        public void setChargeableWeight(String chargeableWeight) {
            this.chargeableWeight = chargeableWeight;
        }

        public double getRate() {
            return rate;
        }

        public void setRate(double rate) {
            this.rate = rate;
        }

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
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

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
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

        public long getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(long updateDate) {
            this.updateDate = updateDate;
        }

        public String getDeleteFlag() {
            return deleteFlag;
        }

        public void setDeleteFlag(String deleteFlag) {
            this.deleteFlag = deleteFlag;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }
    }
}
