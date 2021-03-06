package com.lxkj.wms.utils;

import android.content.Context;
import android.os.Bundle;

import com.lxkj.wms.GlobalBeans;
import com.lxkj.wms.R;
import com.lxkj.wms.biz.ActivitySwitcher;
import com.lxkj.wms.ui.activity.LoginAct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kxn on 2020/5/8 0008.
 */
public class ShowErrorCodeUtil {
    private String errorCode;

    public static void showError(Context context, String errorCode) {
       List<String> errors = new ArrayList<>();
        String error = "";
        switch (errorCode) {
            case "E000000":
                error= context.getString(R.string.hint_00000);
                break;
            case "E029999":
                error= context.getString(R.string.hint_00001);
                break;
            case "E000001":
                error= context.getString(R.string.hint_00001);
                break;
            case "E000406":
                error= context.getString(R.string.hint_00406);
                break;
            case "E020001":
                GlobalBeans.getSelf().getEventCenter().evtLogout();
                error= context.getString(R.string.hint_20001);
                ActivitySwitcher.start(context, LoginAct.class, new Bundle());
                break;
            case "E020002":
                error= context.getString(R.string.hint_20002);
                break;
            case "E020003":
                error= context.getString(R.string.hint_20003);
                break;
            case "E020005":
                error= context.getString(R.string.hint_20005);
                break;
            case "E020006":
                error= context.getString(R.string.hint_20006);
                break;
            case "E020010":
                error= context.getString(R.string.hint_20010);
                break;
            case "I010000":
                error= context.getString(R.string.hint_10000);
                break;
            case "I010001":
                error= context.getString(R.string.hint_10001);
                break;
            case "I010100":
                error= context.getString(R.string.hint_10100);
                break;
            case "I010101":
                error= context.getString(R.string.hint_10101);
                break;
            case "I010102":
                error= context.getString(R.string.hint_10102);
                break;
            case "I010103":
                error= context.getString(R.string.hint_10103);
                break;
            case "I010104":
                error= context.getString(R.string.hint_10104);
                break;
            case "I010105":
                error= context.getString(R.string.hint_10105);
                break;
            case "I010106":
                error= context.getString(R.string.hint_10106);
                break;
            case "I010107":
                error= context.getString(R.string.hint_10107);
                break;
            case "I010108":
                error= context.getString(R.string.hint_10108);
                break;
            case "I010109":
                error= context.getString(R.string.hint_10109);
                break;
            case "I010110":
                error= context.getString(R.string.hint_10110);
                break;
            case "I010111":
                error= context.getString(R.string.hint_10111);
                break;
            case "I010112":
                error= context.getString(R.string.hint_10112);
                break;
            case "I010113":
                error= context.getString(R.string.hint_10113);
                break;
            case "I010114":
                error= context.getString(R.string.hint_10114);
                break;
            case "I010115":
                error= context.getString(R.string.hint_10115);
                break;
            case "I010116":
                error= context.getString(R.string.hint_10116);
                break;
            case "I010117":
                error= context.getString(R.string.hint_10117);
                break;
            case "I011103":
                error= context.getString(R.string.hint_11103);
                break;
            case "I011102":
                error= context.getString(R.string.hint_11102);
                break;
            case "I011101":
                error= context.getString(R.string.hint_11101);
                break;
            case "I011100":
                error= context.getString(R.string.hint_11100);
                break;
            case "I011303":
                error= context.getString(R.string.hint_10111);
                break;
            case "I011304":
                error= context.getString(R.string.hint_10111);
                break;
            case "I010303":
                error= context.getString(R.string.hint_10111);
                break;
            case "I010304":
                error= context.getString(R.string.hint_10111);
                break;
            case "I010305":
                error= context.getString(R.string.hint_10111);
                break;
            case "I011200":
                error= context.getString(R.string.hint_10111);
                break;
            case "I011201":
                error= context.getString(R.string.hint_10111);
                break;
            case "I010306":
                error= context.getString(R.string.hint_10111);
                break;
            case "E010200":
                error= context.getString(R.string.hint_10111);
                break;
            case "E010300":
                error= context.getString(R.string.hint_10111);
                break;
            // 分拣登记错误提示
            case "VE210001":
                error= context.getString(R.string.VE210001);
                break;
            case "VE210002":
                error= context.getString(R.string.VE210002);
                break;
            case "VE210003":
                error= context.getString(R.string.VE210003);
                break;
            case "VE210004":
                error= context.getString(R.string.VE210004);
                break;
            case "VE210005":
                error= context.getString(R.string.VE210005);
                break;
            case "VE210006":
                error= context.getString(R.string.VE210006);
                break;
            case "VE210007":
                error= context.getString(R.string.VE210007);
                break;
            case "VE210008":
                error= context.getString(R.string.VE210008);
                break;
            case "VE210009":
                error= context.getString(R.string.VE210009);
                break;
            case "VE210010":
                error= context.getString(R.string.VE210010);
                break;
            case "VE210011":
                error= context.getString(R.string.VE210011);
                break;
            case "SE210001":
                error= context.getString(R.string.SE210001);
                break;
            case "SE210002":
                error= context.getString(R.string.SE210002);
                break;
            case "SE210003":
                error= context.getString(R.string.SE210003);
                break;
            case "SE210004":
                error= context.getString(R.string.SE210004);
                break;
            case "SE180009":
                error= context.getString(R.string.SE180009);
                break;
            case "SE180010":
                error= context.getString(R.string.SE180010);
                break;
            // 入库单错误提示
            case "VE100001":
                error= context.getString(R.string.VE100001);
                break;
            case "VE100002":
                error= context.getString(R.string.VE100002);
                break;
            case "VE100003":
                error= context.getString(R.string.VE100003);
                break;
            case "VE100004":
                error= context.getString(R.string.VE100004);
                break;
            case "VE100005":
                error= context.getString(R.string.VE100005);
                break;
            case "CE100001":
                error= context.getString(R.string.CE100001);
                break;
            case "VE100006":
                error= context.getString(R.string.VE100006);
                break;
            case "VE100007":
                error= context.getString(R.string.VE100007);
                break;
            case "VE100008":
                error= context.getString(R.string.VE100008);
                break;
            case "VE100009":
                error= context.getString(R.string.VE100009);
                break;
            case "VE100010":
                error= context.getString(R.string.VE100010);
                break;
            case "CE100002":
                error= context.getString(R.string.CE100002);
                break;
            case "CE100003":
                error= context.getString(R.string.CE100003);
                break;
            case "CE210004":
                error= context.getString(R.string.CE210004);
                break;
            case "CE210005":
                error= context.getString(R.string.CE210005);
                break;
            case "CE100005":
                error= context.getString(R.string.CE210005);
                break;
            // 出库单错误提示
            case "VE130001":
                error= context.getString(R.string.VE130001);
                break;
            case "VE130002":
                error= context.getString(R.string.VE130002);
                break;
            case "VE130003":
                error= context.getString(R.string.VE130003);
                break;
            case "VE130004":
                error= context.getString(R.string.VE130004);
                break;
            case "VE130005":
                error= context.getString(R.string.VE130005);
                break;
            case "VE130006":
                error= context.getString(R.string.VE130006);
                break;
            case "VE130007":
                error= context.getString(R.string.VE130007);
                break;
            case "VE130008":
                error= context.getString(R.string.VE130008);
                break;
            case "VE130009":
                error= context.getString(R.string.VE130009);
                break;
            case "VE130010":
                error= context.getString(R.string.VE130010);
                break;
            case "VE130011":
                error= context.getString(R.string.VE130011);
                break;
            case "VE130012":
                error= context.getString(R.string.VE130012);
                break;
            case "VE130013":
                error= context.getString(R.string.VE130013);
                break;
            case "VE130014":
                error= context.getString(R.string.VE130014);
                break;
            case "SE130003":
                error= context.getString(R.string.SE130003);
                break;
            //上架单错误提示
            case "VE110001":
                error= context.getString(R.string.VE110001);
                break;
            case "VE110002":
                error= context.getString(R.string.VE110002);
                break;
            case "VE110003":
                error= context.getString(R.string.VE110003);
                break;
            case "VE110004":
                error= context.getString(R.string.VE110004);
                break;
            case "VE110005":
                error= context.getString(R.string.VE110005);
                break;
            case "VE110006":
                error= context.getString(R.string.VE110006);
                break;
            case "VE110007":
                error= context.getString(R.string.VE110007);
                break;
            case "VE110008":
                error= context.getString(R.string.VE110008);
                break;
            case "VE110009":
                error= context.getString(R.string.VE110009);
                break;
            case "VE110010":
                error= context.getString(R.string.VE110010);
                break;
            case "VE110011":
                error= context.getString(R.string.VE110011);
                break;
            case "VE110012":
                error= context.getString(R.string.VE110012);
                break;
            case "VE110013":
                error= context.getString(R.string.VE110013);
                break;
            case "VE110014":
                error= context.getString(R.string.VE110014);
                break;
            case "SE110003":
                error= context.getString(R.string.SE110003);
                break;
            case "SE110004":
                error= context.getString(R.string.SE110004);
                break;

            // 下架单错误提示
            case "VE120001":
                error= context.getString(R.string.VE120001);
                break;
            case "VE120002":
                error= context.getString(R.string.VE120002);
                break;
            case "VE120003":
                error= context.getString(R.string.VE120003);
                break;
            case "VE120004":
                error= context.getString(R.string.VE120004);
                break;
            case "VE120005":
                error= context.getString(R.string.VE120005);
                break;
            case "VE120006":
                error= context.getString(R.string.VE120006);
                break;
            case "VE120007":
                error= context.getString(R.string.VE120007);
                break;
            case "VE120008":
                error= context.getString(R.string.VE120008);
                break;
            case "VE120009":
                error= context.getString(R.string.VE120009);
                break;
            case "SE100010":
                error= context.getString(R.string.SE100010);
                break;
            case "SE110005":
                error= context.getString(R.string.SE110005);
                break;
            case "SE120003":
                error= context.getString(R.string.SE120003);
                break;
            case "SE130004":
                error= context.getString(R.string.SE130004);
                break;


        }
        errors.add(error);
        if (errors.size() > 0)
            ToastUtil.showCustom(context, errors);
    }

    public static String getErrorString(Context context, String errorCode) {
        String error = "";
        switch (errorCode) {
            //分拣登记
            case "VE210001":
                error = context.getString(R.string.VE210001);
                break;
            case "VE210002":
                error = context.getString(R.string.VE210002);
                break;
            case "VE210003":
                error = context.getString(R.string.VE210003);
                break;
            case "VE210004":
                error = context.getString(R.string.VE210004);
                break;
            case "VE210005":
                error = context.getString(R.string.VE210005);
                break;
            case "VE210006":
                error = context.getString(R.string.VE210006);
                break;
            case "VE210007":
                error = context.getString(R.string.VE210007);
                break;
            case "VE210008":
                error = context.getString(R.string.VE210008);
                break;
            case "VE210009":
                error = context.getString(R.string.VE210008);
                break;
            case "VE210010":
                error = context.getString(R.string.VE210008);
                break;
            case "VE210011":
                error = context.getString(R.string.VE210008);
                break;
            case "SE180010":
                error = context.getString(R.string.SE180010);
                break;
            //入库单
            case "VE100001":
                error = context.getString(R.string.VE100001);
                break;
            case "VE100002":
                error = context.getString(R.string.VE100002);
                break;
            case "VE100003":
                error = context.getString(R.string.VE100003);
                break;
            case "VE100004":
                error = context.getString(R.string.VE100004);
                break;
            case "VE100005":
                error = context.getString(R.string.VE100005);
                break;
            case "VE100006":
                error = context.getString(R.string.VE100006);
                break;
            case "VE100007":
                error = context.getString(R.string.VE100007);
                break;
            case "VE100008":
                error = context.getString(R.string.VE100008);
                break;
            case "VE100009":
                error = context.getString(R.string.VE100009);
                break;
            case "VE100010":
                error = context.getString(R.string.VE100010);
                break;
            //上架单
            case "VE110001":
                error = context.getString(R.string.VE110001);
                break;
            case "VE110002":
                error = context.getString(R.string.VE110002);
                break;
            case "VE110003":
                error = context.getString(R.string.VE110003);
                break;
            case "VE110004":
                error = context.getString(R.string.VE110004);
                break;
            case "VE110005":
                error = context.getString(R.string.VE110005);
                break;
            case "VE110006":
                error = context.getString(R.string.VE110006);
                break;
            case "VE110007":
                error = context.getString(R.string.VE110007);
                break;
            case "VE110008":
                error = context.getString(R.string.VE110008);
                break;
            case "VE110009":
                error = context.getString(R.string.VE110009);
                break;
            case "VE110010":
                error = context.getString(R.string.VE110010);
                break;
            case "VE110011":
                error = context.getString(R.string.VE110011);
                break;
            case "VE110012":
                error = context.getString(R.string.VE110012);
                break;
            case "VE110013":
                error = context.getString(R.string.VE110013);
                break;
            case "VE110014":
                error = context.getString(R.string.VE110014);
                break;
            //下架单提示
            case "VE120001":
                error = context.getString(R.string.VE120001);
                break;
            case "VE120002":
                error = context.getString(R.string.VE120002);
                break;
            case "VE120003":
                error = context.getString(R.string.VE120003);
                break;
            case "VE120004":
                error = context.getString(R.string.VE120004);
                break;
            case "VE120005":
                error = context.getString(R.string.VE120005);
                break;
            case "VE120006":
                error = context.getString(R.string.VE120006);
                break;
            case "VE120007":
                error = context.getString(R.string.VE120007);
                break;
            case "VE120008":
                error = context.getString(R.string.VE120008);
                break;
            case "VE120009":
                error = context.getString(R.string.VE120009);
                break;
            //出库单
            case "VE130001":
                error = context.getString(R.string.VE130001);
                break;
            case "VE130002":
                error = context.getString(R.string.VE130002);
                break;
            case "VE130003":
                error = context.getString(R.string.VE130003);
                break;
            case "VE130004":
                error = context.getString(R.string.VE130004);
                break;
            case "VE130005":
                error = context.getString(R.string.VE130005);
                break;
            case "VE130006":
                error = context.getString(R.string.VE130006);
                break;
            case "VE130007":
                error = context.getString(R.string.VE130007);
                break;
            case "VE130008":
                error = context.getString(R.string.VE130008);
                break;
            case "VE130009":
                error = context.getString(R.string.VE130009);
                break;
            case "VE130010":
                error = context.getString(R.string.VE130010);
                break;
            case "VE130011":
                error = context.getString(R.string.VE130011);
                break;
            case "VE130012":
                error = context.getString(R.string.VE130012);
                break;
            case "VE130013":
                error = context.getString(R.string.VE130013);
                break;
            case "VE130014":
                error = context.getString(R.string.VE130014);
                break;
            //盘点单
            case "VE170001":
                error = context.getString(R.string.VE170001);
                break;
            case "VE170002":
                error = context.getString(R.string.VE170002);
                break;
            case "VE170003":
                error = context.getString(R.string.VE170003);
                break;
            case "VE170004":
                error = context.getString(R.string.VE170004);
                break;
            case "VE170005":
                error = context.getString(R.string.VE170005);
                break;
            //通用错误信息
            case "CE100001":
                error = context.getString(R.string.CE100001);
                break;
            case "CE100002":
                error = context.getString(R.string.CE100002);
                break;
            case "CE100003":
                error = context.getString(R.string.CE100003);
                break;
            case "CE210004":
                error = context.getString(R.string.CE210004);
                break;
            case "CE210005":
                error = context.getString(R.string.CE210005);
                break;
            //登录相关
            case "E000000":
                error = context.getString(R.string.E000000);
                break;
            case "E000001":
                error = context.getString(R.string.E000001);
                break;
            case "E000406":
                error = context.getString(R.string.E000406);
                break;
            case "E020001":
                error = context.getString(R.string.E020001);
                GlobalBeans.getSelf().getEventCenter().evtLogout();
                ActivitySwitcher.start(context, LoginAct.class, new Bundle());
                break;
            case "E020002":
                error = context.getString(R.string.E020002);
                break;
            case "E020003":
                error = context.getString(R.string.E020003);
                break;
            case "E020005":
                error = context.getString(R.string.E020005);
                break;
            case "E020006":
                error = context.getString(R.string.E020006);
                break;
            case "E020010":
                error = context.getString(R.string.E020010);
                break;
            case "E029999":
                error = context.getString(R.string.E029999);
                break;
            case "I010000":
                error = context.getString(R.string.I010000);
                break;
            case "I010001":
                error = context.getString(R.string.I010001);
                break;
            case "I010100":
                error = context.getString(R.string.I010100);
                break;
            case "I010101":
                error = context.getString(R.string.I010101);
                break;
            case "I010102":
                error = context.getString(R.string.I010102);
                break;
            case "I010103":
                error = context.getString(R.string.I010103);
                break;
            case "I010104":
                error = context.getString(R.string.I010104);
                break;
            case "I010106":
                error = context.getString(R.string.I010106);
                break;
            case "I010107":
                error = context.getString(R.string.I010107);
                break;
            case "I010108":
                error = context.getString(R.string.I010108);
                break;
            case "I010109":
                error = context.getString(R.string.I010109);
                break;
            case "I010110":
                error = context.getString(R.string.I010110);
                break;
            case "I010111":
                error = context.getString(R.string.I010111);
                break;
            case "I010112":
                error = context.getString(R.string.I010112);
                break;
            case "I010113":
                error = context.getString(R.string.I010113);
                break;
            case "I010114":
                error = context.getString(R.string.I010114);
                break;
            case "I010115":
                error = context.getString(R.string.I010115);
                break;
            case "I010116":
                error = context.getString(R.string.I010116);
                break;
            case "I010117":
                error = context.getString(R.string.I010117);
                break;
            case "I010200":
                error = context.getString(R.string.I010200);
                break;
            case "I010201":
                error = context.getString(R.string.I010201);
                break;
            case "I010202":
                error = context.getString(R.string.I010202);
                break;
            case "I010300":
                error = context.getString(R.string.I010300);
                break;
            case "I010301":
                error = context.getString(R.string.I010301);
                break;
            case "I010302":
                error = context.getString(R.string.I010302);
                break;
            case "I011300":
                error = context.getString(R.string.I011300);
                break;
            case "I011301":
                error = context.getString(R.string.I011301);
                break;
            case "I011302":
                error = context.getString(R.string.I011302);
                break;
            case "I011303":
                error = context.getString(R.string.I011303);
                break;
            case "I011304":
                error = context.getString(R.string.I011304);
                break;
            case "I010303":
                error = context.getString(R.string.I010303);
                break;
            case "I010304":
                error = context.getString(R.string.I010304);
                break;
            case "I010305":
                error = context.getString(R.string.I010305);
                break;
            case "I011200":
                error = context.getString(R.string.I011200);
                break;
            case "I011201":
                error = context.getString(R.string.I011201);
                break;
            case "I010306":
                error = context.getString(R.string.I010306);
                break;
            case "E010200":
                error = context.getString(R.string.E010200);
                break;
            case "E010300":
                error = context.getString(R.string.E010300);
                break;
            case "E010301":
                error = context.getString(R.string.E010301);
                break;
            case "E010302":
                error = context.getString(R.string.E010302);
                break;
            case "I010307":
                error = context.getString(R.string.I010307);
                break;
            case "I011100":
                error = context.getString(R.string.I011100);
                break;
            case "I011101":
                error = context.getString(R.string.I011101);
                break;
            case "I011102":
                error = context.getString(R.string.I011102);
                break;
            case "I011103":
                error = context.getString(R.string.I011103);
                break;


        }
        return error;
    }


    public static Map<String, String> getErrorValue(String errorString) {
        Map<String, String> errorValuesMap = new HashMap<>();
        if (errorString.contains("&")) {
            String[] errors = errorString.split("&");
            for (int i = 0; i < errors.length; i++) {
                String error = errors[i];
                if (error.contains("=")) {
                    String[] errorValues = error.split("=");
                    errorValuesMap.put(errorValues[0], errorValues[1]);
                }
            }
        } else {
            if (errorString.contains("=")) {
                String[] errorValues = errorString.split("=");
                errorValuesMap.put(errorValues[0], errorValues[1]);
            }
        }
        return errorValuesMap;
    }

    public static String getGoodsType(Context context, String goodsType) {
        String value = "";
        switch (goodsType) {
            case "A":
                value = context.getString(R.string.goodsTypeA);
                break;
            case "B":
                value = context.getString(R.string.goodsTypeB);
                break;
            case "C":
                value = context.getString(R.string.goodsTypeC);
                break;
        }
        return value;
    }
}
