package com.bldby.airticket.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bldby.airticket.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * package name: com.bldby.airticket.adapter
 * user: yangqinbo
 * date: 2020/6/20
 * time: 13:14
 * email: 694125155@qq.com
 */
public class CustomRefundViewAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public CustomRefundViewAdapter(@Nullable List<String> data) {
        super(R.layout.item_dialog_refund, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        helper.setText(R.id.tvContent, item);

    }
}
