package com.lxkj.wms.ui.fragment.kcpd;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lxkj.wms.R;
import com.lxkj.wms.ui.fragment.TitleFragment;
import com.lxkj.wms.utils.ToastUtil;
import com.lxkj.wms.view.HintDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by kxn on 2020/4/25 0025.
 */
public class PdxqFra extends TitleFragment implements View.OnClickListener {

    @BindView(R.id.ivDelete)
    ImageView ivDelete;
    Unbinder unbinder;

    @Override
    public String getTitleName() {
        return act.getString(R.string.pdxq);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fra_pdxq, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    private void initView() {
ivDelete.setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ivDelete:
                new HintDialog(mContext, mContext.getString(R.string.hint_delete), true).setOnButtonClickListener(new HintDialog.OnButtonClick() {
                    @Override
                    public void OnRightClick() {
                        ToastUtil.show("取消");
                    }

                    @Override
                    public void OnLeftClick() {
                        ToastUtil.show("确认");
                    }
                }).show();
                break;
        }
    }
}
