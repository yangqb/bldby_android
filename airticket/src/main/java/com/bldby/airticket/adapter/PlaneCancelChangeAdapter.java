package com.bldby.airticket.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bldby.airticket.R;
import com.bldby.airticket.model.CustomTgqChangeModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * package name: com.bldby.airticket.adapter
 * user: yangqinbo
 * date: 2020/6/19
 * time: 11:45
 * email: 694125155@qq.com
 */
public class PlaneCancelChangeAdapter extends BaseQuickAdapter<CustomTgqChangeModel, BaseViewHolder> {
    public PlaneCancelChangeAdapter(@Nullable List<CustomTgqChangeModel> data) {
        super(R.layout.item_layout_cancel_change, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CustomTgqChangeModel item) {
        helper.setText(R.id.timeText, item.timeText);
        helper.setText(R.id.amount, item.amountText);
    }
}
