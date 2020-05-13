package com.lxkj.wms.bean;

import java.util.List;

/**
 * Created by kxn on 2020/5/12 0012.
 */
public class SortingRegisterDetailBean extends BaseBean {

    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {

        private String barCode;//	条形码
        private String createDate;//	创建日期
        private String createrId;//	创建人Id
        private String createrName;//	创建人姓名
        private String deleteFlag;//	删除标识符
        private String flightId;//	航班
        private String goodsNameId;//	货物品名（包括包装、体积或尺寸）
        private String id;//	分拣登记明细ID
        private String lineNumber;//	行号
        private String updateDate;//	更新日期
        private String updaterId;//	更新人Id
        private String updaterName;//	更新人姓名
        private String version;//	版本号
        private String wmsSortingRegisterId;//	分拣登记ID

        public String getBarCode() {
            return barCode;
        }

        public void setBarCode(String barCode) {
            this.barCode = barCode;
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

        public String getFlightId() {
            return flightId;
        }

        public void setFlightId(String flightId) {
            this.flightId = flightId;
        }

        public String getGoodsNameId() {
            return goodsNameId;
        }

        public void setGoodsNameId(String goodsNameId) {
            this.goodsNameId = goodsNameId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLineNumber() {
            return lineNumber;
        }

        public void setLineNumber(String lineNumber) {
            this.lineNumber = lineNumber;
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

        public String getWmsSortingRegisterId() {
            return wmsSortingRegisterId;
        }

        public void setWmsSortingRegisterId(String wmsSortingRegisterId) {
            this.wmsSortingRegisterId = wmsSortingRegisterId;
        }
    }
}
