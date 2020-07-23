package com.lxkj.wms.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.lxkj.wms.R;
import com.lxkj.wms.bean.WareHouseBean;
import com.lxkj.wms.utils.ListUtil;
import com.lxkj.wms.utils.StringUtil;
import com.lxkj.wms.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kxn on 2020/5/16 0016.
 */
public class AddStockCheckDialog extends Dialog {
    EditText etBarCode;
    TextView tvWmsWarehouseIdName, tvWmsWarehouseDetailId, tvSure, tvCancel;
    private Context context;      // 上下文
    private List<WareHouseBean.ResultBean> warehouseDetailList;
    String wmsWarehouseDetailId;
    OnConfirmListener onConfirmListener;

    public AddStockCheckDialog(Context context, List<WareHouseBean.ResultBean> warehouseDetailList, String wmsWarehouseName, String barCode, OnConfirmListener onConfirmListener) {
        super(context, R.style.Theme_dialog); //dialog的样式
        this.context = context;
        Window window = getWindow();
        window.setGravity(Gravity.CENTER); // 此处可以设置dialog显示的位置为居中
        setContentView(R.layout.dialog_stockcheck_add);
        WindowManager windowManager = ((Activity) context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = display.getWidth() * 4 / 5; // 设置dialog宽度为屏幕的4/5
        lp.dimAmount = 0.5f;//dimAmount在0.0f和1.0f之间，0.0f完全不暗，即背景是可见的 ，1.0f时候，背景全部变黑暗。
        getWindow().setAttributes(lp);
        //要达到背景全变暗的效果，需设置getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND); 否则，背景无效果
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        //遍历控件id,添加点击事件
        etBarCode = findViewById(R.id.etBarCode);
        tvWmsWarehouseIdName = findViewById(R.id.tvWmsWarehouseIdName);
        tvWmsWarehouseDetailId = findViewById(R.id.tvWmsWarehouseDetailId);
        tvSure = findViewById(R.id.tv_sure);
        tvCancel = findViewById(R.id.tv_cancel);
        tvWmsWarehouseIdName.setText(wmsWarehouseName);
        this.onConfirmListener = onConfirmListener;
        Log.e("AddStockCheckDialogbar",barCode);
        etBarCode.setText(barCode);
        tvWmsWarehouseDetailId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ListUtil.isEmpty(warehouseDetailList))
                    return;
                List<String> codeList = new ArrayList<>();
                codeList.add("");
                for (int i = 0; i < warehouseDetailList.size(); i++) {
                    codeList.add(warehouseDetailList.get(i).getCode());
                }
                SingleChooseDialog addressDialog = new SingleChooseDialog(context, codeList, new SingleChooseDialog.OnItemClick() {
                    @Override
                    public void onItemClick(int position) {
                        tvWmsWarehouseDetailId.setText(codeList.get(position));
                        if (StringUtil.isEmpty(codeList.get(position))) {
                            wmsWarehouseDetailId = null;
                        } else {
                            wmsWarehouseDetailId = warehouseDetailList.get(position-1).getId();
                        }
                    }
                });
                addressDialog.show();
            }
        });
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(etBarCode.getText())) {
                    ToastUtil.show(context.getResources().getString(R.string.VE100002));
                    return;
                }
//                if (null == wmsWarehouseDetailId) {
//                    ToastUtil.show(context.getResources().getString(R.string.VE500003));
//                    return;
//                }
                onConfirmListener.onConfirm(etBarCode.getText().toString(), wmsWarehouseDetailId);
                dismiss();
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    public interface OnConfirmListener {
        void onConfirm(String barCode, String wmsWarehouseDetailId);
    }

}

