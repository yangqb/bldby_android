package com.bldby.airticket.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bldby.airticket.R;
import com.bldby.airticket.model.TimePointChargsInfo;
import com.bldby.baselibrary.core.util.MathUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * package name: com.bldby.airticket.adapter
 * user: yangqinbo
 * date: 2020/6/22
 * time: 19:28
 * email: 694125155@qq.com
 */

public class AirChangeServiceAdapter extends BaseQuickAdapter<TimePointChargsInfo, BaseViewHolder> {
    public AirChangeServiceAdapter(@Nullable List<TimePointChargsInfo> data) {
        super(R.layout.item_change_service, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, TimePointChargsInfo item) {
        helper.setText(R.id.date, item.timeText);
        helper.setText(R.id.price, "¥" + MathUtils.subZero(String.valueOf(item.changeFee)) + "/人");
    }
}