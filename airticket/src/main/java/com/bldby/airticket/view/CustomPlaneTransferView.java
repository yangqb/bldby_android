package com.bldby.airticket.view;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bldby.airticket.R;
import com.lxj.xpopup.core.CenterPopupView;

/**
 * package name: com.bldby.airticket.view
 * user: yangqinbo
 * date: 2020/6/19
 * time: 9:48
 * email: 694125155@qq.com
 */
public class CustomPlaneTransferView extends CenterPopupView {
    public CustomPlaneTransferView(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.dialog_plane_transfer;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
    }
}
