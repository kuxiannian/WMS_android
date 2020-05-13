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
}
