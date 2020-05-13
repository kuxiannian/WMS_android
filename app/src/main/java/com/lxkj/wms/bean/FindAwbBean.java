package com.lxkj.wms.bean;

/**
 * Created by kxn on 2020/5/9 0009.
 */
public class FindAwbBean {

    /**
     * flag : true
     * message : null
     * errorCode : null
     * result : {"id":"5c14ced0-7040-4986-a9f5-61ddd37056e9","awb":"TestE","shipperName":"E","shipperAddress":"E","shipperPhone":"E","receiverName":"E","receiverAddress":"E","receiverPhone":"E","carrierAgent":"E","account":"E","itat":"E","departureStation":"E","arrive":"E","firstCarrier":"E","routing":"E","destinationStation":"E","insurance":"E","currency":"E","nvd":"E","nvc":"E","handling":"E","createrId":"USER","createrName":"USER","createDate":1579067528670,"updaterId":"USER","updaterName":"USER","updateDate":1579067528670,"deleteFlag":"0","version":0}
     */

    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 5c14ced0-7040-4986-a9f5-61ddd37056e9
         * awb : TestE
         * shipperName : E
         * shipperAddress : E
         * shipperPhone : E
         * receiverName : E
         * receiverAddress : E
         * receiverPhone : E
         * carrierAgent : E
         * account : E
         * itat : E
         * departureStation : E
         * arrive : E
         * firstCarrier : E
         * routing : E
         * destinationStation : E
         * insurance : E
         * currency : E
         * nvd : E
         * nvc : E
         * handling : E
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
        private String awb;//AWB号
        private String shipperName;//托运人姓名
        private String shipperAddress;//托运人地址
        private String shipperPhone;//托运人联系方式
        private String receiverName;//收货人姓名
        private String receiverAddress;//收货人地址
        private String receiverPhone;//收货人联系方式
        private String carrierAgent;//填开货运单的代理人名称、城市
        private String account;//结算注意事项
        private String itat;//代理的ITAT号
        private String departureStation;//始发站、第一承运人地址及要求的线路
        private String arrive;//到达站
        private String firstCarrier;//第一承运人
        private String routing;//路线和目的站
        private String destinationStation;//目的站
        private String insurance;//保险金额
        private String currency;//货币
        private String nvd;//托运人向承运人声明的货物价值
        private String nvc;//托运人向目的站海关声明的货物价值
        private String handling;//处理情况
        private String createrId;//创建人Id
        private String createrName;//创建人名称
        private long createDate;//创建日期
        private String updaterId;//更新人Id
        private String updaterName;//更新人姓名
        private long updateDate;//更新日期
        private String deleteFlag;//删除标识符
        private int version;//版本号

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAwb() {
            return awb;
        }

        public void setAwb(String awb) {
            this.awb = awb;
        }

        public String getShipperName() {
            return shipperName;
        }

        public void setShipperName(String shipperName) {
            this.shipperName = shipperName;
        }

        public String getShipperAddress() {
            return shipperAddress;
        }

        public void setShipperAddress(String shipperAddress) {
            this.shipperAddress = shipperAddress;
        }

        public String getShipperPhone() {
            return shipperPhone;
        }

        public void setShipperPhone(String shipperPhone) {
            this.shipperPhone = shipperPhone;
        }

        public String getReceiverName() {
            return receiverName;
        }

        public void setReceiverName(String receiverName) {
            this.receiverName = receiverName;
        }

        public String getReceiverAddress() {
            return receiverAddress;
        }

        public void setReceiverAddress(String receiverAddress) {
            this.receiverAddress = receiverAddress;
        }

        public String getReceiverPhone() {
            return receiverPhone;
        }

        public void setReceiverPhone(String receiverPhone) {
            this.receiverPhone = receiverPhone;
        }

        public String getCarrierAgent() {
            return carrierAgent;
        }

        public void setCarrierAgent(String carrierAgent) {
            this.carrierAgent = carrierAgent;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getItat() {
            return itat;
        }

        public void setItat(String itat) {
            this.itat = itat;
        }

        public String getDepartureStation() {
            return departureStation;
        }

        public void setDepartureStation(String departureStation) {
            this.departureStation = departureStation;
        }

        public String getArrive() {
            return arrive;
        }

        public void setArrive(String arrive) {
            this.arrive = arrive;
        }

        public String getFirstCarrier() {
            return firstCarrier;
        }

        public void setFirstCarrier(String firstCarrier) {
            this.firstCarrier = firstCarrier;
        }

        public String getRouting() {
            return routing;
        }

        public void setRouting(String routing) {
            this.routing = routing;
        }

        public String getDestinationStation() {
            return destinationStation;
        }

        public void setDestinationStation(String destinationStation) {
            this.destinationStation = destinationStation;
        }

        public String getInsurance() {
            return insurance;
        }

        public void setInsurance(String insurance) {
            this.insurance = insurance;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getNvd() {
            return nvd;
        }

        public void setNvd(String nvd) {
            this.nvd = nvd;
        }

        public String getNvc() {
            return nvc;
        }

        public void setNvc(String nvc) {
            this.nvc = nvc;
        }

        public String getHandling() {
            return handling;
        }

        public void setHandling(String handling) {
            this.handling = handling;
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
