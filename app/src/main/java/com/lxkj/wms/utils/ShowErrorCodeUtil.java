package com.lxkj.wms.utils;

import android.content.Context;
import android.os.Bundle;

import com.lxkj.wms.GlobalBeans;
import com.lxkj.wms.R;
import com.lxkj.wms.biz.ActivitySwitcher;
import com.lxkj.wms.ui.fragment.login.LoginFra;

/**
 * Created by kxn on 2020/5/8 0008.
 */
public class ShowErrorCodeUtil {
    private String errorCode;

    public static void showError(Context context, String errorCode) {
        switch (errorCode) {
            case "E000000":
                ToastUtil.show(context.getString(R.string.hint_00000));
                break;
            case "E029999":
                ToastUtil.show(context.getString(R.string.hint_00001));
                break;
            case "E000001":
                ToastUtil.show(context.getString(R.string.hint_00001));
                break;
            case "E000406":
                ToastUtil.show(context.getString(R.string.hint_00406));
                break;
            case "E020001":
                GlobalBeans.getSelf().getEventCenter().evtLogout();
                ToastUtil.show(context.getString(R.string.hint_20001));
                ActivitySwitcher.startFragment(context, LoginFra.class,new Bundle());
                break;
            case "E020002":
                ToastUtil.show(context.getString(R.string.hint_20002));
                break;
            case "E020003":
                ToastUtil.show(context.getString(R.string.hint_20003));
                break;
            case "E020005":
                ToastUtil.show(context.getString(R.string.hint_20005));
                break;
            case "E020006":
                ToastUtil.show(context.getString(R.string.hint_20006));
                break;
            case "E020010":
                ToastUtil.show(context.getString(R.string.hint_20010));
                break;
            case "I010000":
                ToastUtil.show(context.getString(R.string.hint_10000));
                break;
            case "I010001":
                ToastUtil.show(context.getString(R.string.hint_10001));
                break;
            case "I010100":
                ToastUtil.show(context.getString(R.string.hint_10100));
                break;
            case "I010101":
                ToastUtil.show(context.getString(R.string.hint_10101));
                break;
            case "I010102":
                ToastUtil.show(context.getString(R.string.hint_10102));
                break;
            case "I010103":
                ToastUtil.show(context.getString(R.string.hint_10103));
                break;
            case "I010104":
                ToastUtil.show(context.getString(R.string.hint_10104));
                break;
            case "I010105":
                ToastUtil.show(context.getString(R.string.hint_10105));
                break;
            case "I010106":
                ToastUtil.show(context.getString(R.string.hint_10106));
                break;
            case "I010107":
                ToastUtil.show(context.getString(R.string.hint_10107));
                break;
            case "I010108":
                ToastUtil.show(context.getString(R.string.hint_10108));
                break;
            case "I010109":
                ToastUtil.show(context.getString(R.string.hint_10109));
                break;
            case "I010110":
                ToastUtil.show(context.getString(R.string.hint_10110));
                break;
            case "I010111":
                ToastUtil.show(context.getString(R.string.hint_10111));
                break;
            case "I010112":
                ToastUtil.show(context.getString(R.string.hint_10112));
                break;
            case "I010113":
                ToastUtil.show(context.getString(R.string.hint_10113));
                break;
            case "I010114":
                ToastUtil.show(context.getString(R.string.hint_10114));
                break;
            case "I010115":
                ToastUtil.show(context.getString(R.string.hint_10115));
                break;
            case "I010116":
                ToastUtil.show(context.getString(R.string.hint_10116));
                break;
            case "I010117":
                ToastUtil.show(context.getString(R.string.hint_10117));
                break;
            case "I011103":
                ToastUtil.show(context.getString(R.string.hint_11103));
                break;
            case "I011102":
                ToastUtil.show(context.getString(R.string.hint_11102));
                break;
            case "I011101":
                ToastUtil.show(context.getString(R.string.hint_11101));
                break;
            case "I011100":
                ToastUtil.show(context.getString(R.string.hint_11100));
                break;
            case "I011303":
                ToastUtil.show(context.getString(R.string.hint_10111));
                break;
            case "I011304":
                ToastUtil.show(context.getString(R.string.hint_10111));
                break;
            case "I010303":
                ToastUtil.show(context.getString(R.string.hint_10111));
                break;
            case "I010304":
                ToastUtil.show(context.getString(R.string.hint_10111));
                break;
            case "I010305":
                ToastUtil.show(context.getString(R.string.hint_10111));
                break;
            case "I011200":
                ToastUtil.show(context.getString(R.string.hint_10111));
                break;
            case "I011201":
                ToastUtil.show(context.getString(R.string.hint_10111));
                break;
            case "I010306":
                ToastUtil.show(context.getString(R.string.hint_10111));
                break;
            case "E010200":
                ToastUtil.show(context.getString(R.string.hint_10111));
                break;
            case "E010300":
                ToastUtil.show(context.getString(R.string.hint_10111));
                break;
            // 分拣登记错误提示
            case "VE210001":
                ToastUtil.show(context.getString(R.string.VE210001));
                break;
            case "VE210002":
                ToastUtil.show(context.getString(R.string.VE210002));
                break;
            case "VE210003":
                ToastUtil.show(context.getString(R.string.VE210003));
                break;
            case "VE210004":
                ToastUtil.show(context.getString(R.string.VE210004));
                break;
            case "VE210005":
                ToastUtil.show(context.getString(R.string.VE210005));
                break;
            case "VE210006":
                ToastUtil.show(context.getString(R.string.VE210006));
                break;
            case "VE210007":
                ToastUtil.show(context.getString(R.string.VE210007));
                break;
            case "VE210008":
                ToastUtil.show(context.getString(R.string.VE210008));
                break;
            case "VE210009":
                ToastUtil.show(context.getString(R.string.VE210009));
                break;
            case "VE210010":
                ToastUtil.show(context.getString(R.string.VE210010));
                break;
            case "VE210011":
                ToastUtil.show(context.getString(R.string.VE210011));
                break;
            case "SE210001":
                ToastUtil.show(context.getString(R.string.SE210001));
                break;
            case "SE210002":
                ToastUtil.show(context.getString(R.string.SE210002));
                break;
            case "SE210003":
                ToastUtil.show(context.getString(R.string.SE210003));
                break;
            case "SE210004":
                ToastUtil.show(context.getString(R.string.SE210004));
                break;
            case "SE180009":
                ToastUtil.show(context.getString(R.string.SE180009));
                break;
            case "SE180010":
                ToastUtil.show(context.getString(R.string.SE180010));
                break;


        }
    }
}
