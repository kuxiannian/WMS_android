package com.lxkj.wms.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.lxkj.wms.R;

/**
 * Created by kxn on 2020/4/24 0024.
 */
public class HangDanDetailDialog extends Dialog {

    TextView tvDepartureStation;
    TextView tvDestinationStation;
    TextView tvShipperName;
    TextView tvShipperAddress;
    TextView tvShipperPhone;
    TextView tvReceiverName;
    TextView tvReceiverAddress;
    TextView tvReceiverPhone;
    TextView tvGrossWeight;
    TextView tvRateClass;
    TextView tvNumber;
    TextView tvProductCode;
    TextView tvConfirm;
    private Context context;      // 上下文

    public HangDanDetailDialog(Context context, String departureStation, String destinationStation, String shipperName, String shipperAddress, String shipperPhone,
                               String receiverName, String receiverAddress,String receiverPhone, String grossWeight, String rateClass, String number, String productCode) {
        super(context, R.style.Theme_dialog); //dialog的样式
        this.context = context;
        Window window = getWindow();
        window.setGravity(Gravity.CENTER); // 此处可以设置dialog显示的位置为居中
        setContentView(R.layout.dialog_detail_hd);
        WindowManager windowManager = ((Activity) context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = display.getWidth() * 4 / 5; // 设置dialog宽度为屏幕的4/5
        lp.dimAmount = 0.5f;//dimAmount在0.0f和1.0f之间，0.0f完全不暗，即背景是可见的 ，1.0f时候，背景全部变黑暗。
        getWindow().setAttributes(lp);
        //要达到背景全变暗的效果，需设置getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND); 否则，背景无效果
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        //遍历控件id,添加点击事件
        tvConfirm = findViewById(R.id.tvConfirm);
        tvDepartureStation = findViewById(R.id.tvDepartureStation);
        tvDestinationStation = findViewById(R.id.tvDestinationStation);
        tvShipperName = findViewById(R.id.tvShipperName);
        tvShipperAddress = findViewById(R.id.tvShipperAddress);
        tvShipperPhone = findViewById(R.id.tvShipperPhone);
        tvReceiverName = findViewById(R.id.tvReceiverName);
        tvReceiverAddress = findViewById(R.id.tvReceiverAddress);
        tvReceiverPhone = findViewById(R.id.tvReceiverPhone);
        tvGrossWeight = findViewById(R.id.tvGrossWeight);
        tvRateClass = findViewById(R.id.tvRateClass);
        tvNumber = findViewById(R.id.tvNumber);
        tvProductCode = findViewById(R.id.tvProductCode);

        tvDepartureStation.setText(departureStation);
        tvDestinationStation.setText(destinationStation);
        tvShipperName.setText(shipperName);
        tvShipperAddress.setText(shipperAddress);
        tvShipperPhone.setText(shipperPhone);
        tvReceiverName.setText(receiverName);
        tvReceiverAddress.setText(receiverAddress);
        tvReceiverPhone.setText(receiverPhone);
        tvGrossWeight.setText(grossWeight);
        tvRateClass.setText(rateClass);
        tvNumber.setText(number);
        tvProductCode.setText(productCode);


        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

}

