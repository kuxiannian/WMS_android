package com.lxkj.wms.http;


/**
 * Created by kxn on 2018/5/17 0017.
 */

public class Url {
    //服务器地址
    public static String IP = "https://218.93.19.166:8080";
    //用户登录
    public static String LOGIN = IP + "/upmsapi/user/login";
    //获取密码规则
    public static String InitPwdRule = IP + "/upmsapi/user/initPwdRule";
    //获取权限编码列表
    public static String getPrmCodeList = IP + "/upmsapi/prm/getPrmCodeList";
    //登出
    public static String logout = IP + "/upmsapi/user/logout";
    /**
     * 分拣登记
     */
    //根据AWB搜索舱单信息接口
    public static String findManifestByAwb = IP + "/wms/manifest/findManifestByAwb";
    //新增数据接口
    public static String addSortingRegister = IP + "/wms/sortingRegister/addSortingRegister";
    //分页查询数据接口
    public static String findSortingRegisterPage = IP + "/wms/sortingRegister/findSortingRegisterPage";
    //编辑数据接口
    public static String updateSortingRegister = IP + "/wms/sortingRegister/updateSortingRegister";
    //删除数据接口
    public static String deleteSortingRegister = IP + "/wms/sortingRegister/deleteSortingRegister";
    //查询仓库列表接口
    public static String findWarehouseList = IP + "/wms/sortingRegister/findWarehouseList";
    //根据舱单信息ID查询舱单航班信息接口
    public static String findFlightByWmsManifestId = IP + "/wms/manifest/findFlightByWmsManifestId";
    //根据舱单信息ID查询舱单货物信息接口
    public static String findGoodsNameByWmsManifestId = IP + "/wms/manifest/findGoodsNameByWmsManifestId";
    //根据舱单货物ID查询舱单货物信息接口
    public static String findWmsManifestGoodsByGoodsNameId = IP + "/wms/manifest/findWmsManifestGoodsByGoodsNameId";
    //根据分拣登记ID查询分拣登记明细信息接口
    public static String findWmsSortingRegisterDetailList = IP + "/wms/sortingRegister/findWmsSortingRegisterDetailList";

    /**
     * 入库单
     */
    //分页查询数据接口
    public static String findBillInputPage = IP + "/wms/billInput/findBillInputPage";
    //新增数据接口
    public static String addBillInput = IP + "/wms/billInput/addBillInput";
    //查询仓库列表接口
    public static String findWarehouseListBillInput = IP + "/wms/billInput/findWarehouseList";
    //根据条形码查询入库所需相关信息接口
    public static String findInfoByBarCode = IP + "/wms/billInput/findInfoByBarCode";


    /**
     * 出库单
     */
    //分页查询数据接口
    public static String findBillOutputPage = IP + "/wms/billOutput/findBillOutputPage";
    //新增数据接口
    public static String addBillOutput = IP + "/wms/billOutput/addBillOutput";
    //查询仓库列表接口
    public static String findWarehouseListBillOnput = IP + "/wms/billOutput/findWarehouseList";
    //根据条形码查询入库所需相关信息接口
    public static String findInfoByBarCodeBillOut = IP + "/wms/billOutput/findInfoByBarCode";

    /**
     *库存查询
     */
    //分页查询数据接口
    public static String findStockPage = IP + "/wms/stock/findStockPage";
    //查询仓库列表接口
    public static String findWarehouseListStock = IP + "/wms/stock/findWarehouseList";

    /**
     * 盘点单
     */
    //分页查询数据接口
    public static String findBillStockCheckPage = IP + "/wms/billStockCheck/findBillStockCheckPage";
    //新增数据接口
    public static String addBillStockCheck = IP + "/wms/billStockCheck/addBillStockCheck";
    //保存草稿数据接口
    public static String updateBillStockCheckTemp = IP + "/wms/billStockCheck/updateBillStockCheckTemp";
    //完成盘点接口
    public static String updateBillStockCheckOver = IP + "/wms/billStockCheck/updateBillStockCheckOver";
    //盘点作废接口
//    public static String deleteBillStockCheck = IP + "/wms/billStockCheck/deleteBillStockCheck";
    //查询仓库列表接口
    public static String findWarehouseListStockCheck = IP + "/wms/billStockCheck/findWarehouseList";
    //查询储位列表接口
    public static String findWarehouseDetailList = IP + "/wms/warehouseDetail/findWarehouseDetailList";
    //查询盘点明细数据接口
    public static String findBillStockCheckDetailList = IP + "/wms/billStockCheck/findBillStockCheckDetailList";

    /**
     * 上架单
     */
    //分页查询数据接口
    public static String findBillPutOnPage = IP + "/wms/billPutOn/findBillPutOnPage";
    //新增数据接口
    public static String addBillPutOn = IP + "/wms/billPutOn/addBillPutOn";
    //根据条形码查询上架单所需相关信息接口
    public static String findInfoByBarCodeBillPutOn = IP + "/wms/billPutOn/findInfoByBarCode";
    //上架时输入长、宽、高自动分配储位接口
    public static String findTopByPriority = IP + "/wms/warehouseDetail/findTopByPriority";
    //查询仓库列表接口
    public static String findWarehouseListBillPutOn = IP + "/wms/billPutOn/findWarehouseList";

    /**
     * 下架单
     */
    //分页查询数据接口
    public static String findBillPutOffPage = IP + "/wms/billPutOff/findBillPutOffPage";
    //根据条形码查询所需相关信息接口
    public static String findInfoByBarCodeBillPutOff = IP + "wms/billPutOff/findInfoByBarCode";
    //新增数据接口
    public static String addBillPutOff = IP + "/wms/billPutOff/addBillPutOff";
    //查询仓库列表接口
    public static String findWarehouseListBillPutOff = IP + "/wms/billPutOff/findWarehouseList";

}
