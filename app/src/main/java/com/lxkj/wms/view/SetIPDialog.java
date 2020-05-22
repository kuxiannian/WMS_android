package com.lxkj.wms.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.lxkj.wms.R;
import com.lxkj.wms.http.Url;
import com.lxkj.wms.utils.ToastUtil;

/**
 * Created by kxn on 2020/5/22 0022.
 */
public class SetIPDialog extends Dialog {
    EditText etIP;
    private Context context;      // 上下文
    private boolean isCan = true;

    public SetIPDialog(Context context) {
        super(context, R.style.Theme_dialog); //dialog的样式
        this.context = context;
        Window window = getWindow();
        window.setGravity(Gravity.CENTER); // 此处可以设置dialog显示的位置为居中
        setContentView(R.layout.dialog_set_ip);
        WindowManager windowManager = ((Activity) context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = display.getWidth() * 4 / 5; // 设置dialog宽度为屏幕的4/5
        lp.dimAmount = 0.5f;//dimAmount在0.0f和1.0f之间，0.0f完全不暗，即背景是可见的 ，1.0f时候，背景全部变黑暗。
        getWindow().setAttributes(lp);
        //要达到背景全变暗的效果，需设置getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND); 否则，背景无效果
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        setCanceledOnTouchOutside(isCan);// 点击Dialog外部消失
        etIP = findViewById(R.id.etIp);
        etIP.setText(Url.IP);
        findViewById(R.id.tv_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(etIP.getText())) {
                    ToastUtil.show(context.getString(R.string.intputappServer));
                    return;
                }

                dismiss();
                listener.OnLeftClick(etIP.getText().toString());
            }
        });
        findViewById(R.id.tv_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                listener.OnRightClick();
            }
        });
    }

    private OnButtonClick listener;

    public interface OnButtonClick {
        void OnRightClick();

        void OnLeftClick(String ip);
    }

    public SetIPDialog setOnButtonClickListener(OnButtonClick listener) {
        this.listener = listener;
        return this;
    }

}


