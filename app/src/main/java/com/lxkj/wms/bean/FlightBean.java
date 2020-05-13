package com.lxkj.wms.bean;

import java.util.List;

/**
 * Created by kxn on 2020/5/9 0009.
 */
public class FlightBean extends BaseBean{

    /**
     * flag : true
     * message : null
     * errorCode : null
     * result : [{"id":"2a1835ce-002e-462a-90c9-725ee9aca560","wmsManifestId":"5c14ced0-7040-4986-a9f5-61ddd37056e9","lineNumber":"1","flight":"E","flightDate":1579017600000,"createrId":"USER","createrName":"USER","createDate":1579067528670,"updaterId":"USER","updaterName":"USER","updateDate":1579067528670,"deleteFlag":"0","version":0}]
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
         * id : 2a1835ce-002e-462a-90c9-725ee9aca560
         * wmsManifestId : 5c14ced0-7040-4986-a9f5-61ddd37056e9
         * lineNumber : 1
         * flight : E
         * flightDate : 1579017600000
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
        private String flight;
        private long flightDate;
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

        public String getFlight() {
            return flight;
        }

        public void setFlight(String flight) {
            this.flight = flight;
        }

        public long getFlightDate() {
            return flightDate;
        }

        public void setFlightDate(long flightDate) {
            this.flightDate = flightDate;
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
