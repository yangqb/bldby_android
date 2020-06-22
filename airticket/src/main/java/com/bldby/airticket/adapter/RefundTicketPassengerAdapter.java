package com.bldby.airticket.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bldby.airticket.R;
import com.bldby.airticket.model.DocOrderDetailPassengersInfo;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * package name: com.bldby.airticket.adapter
 * user: yangqinbo
 * date: 2020/6/22
 * time: 18:26
 * email: 694125155@qq.com
 */
public class RefundTicketPassengerAdapter extends BaseQuickAdapter<DocOrderDetailPassengersInfo, BaseViewHolder> {
    public RefundTicketPassengerAdapter(@Nullable List<DocOrderDetailPassengersInfo> data) {
        super(R.layout.item_refund_ticket, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, DocOrderDetailPassengersInfo item) {
        helper.setText(R.id.name, item.name + "（" + item.type + "）");
    }
}
