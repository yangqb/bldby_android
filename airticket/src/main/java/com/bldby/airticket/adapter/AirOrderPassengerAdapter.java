package com.bldby.airticket.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.bldby.airticket.R;
import com.bldby.airticket.model.DocOrderDetailPassengersInfo;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * package name: com.bldby.airticket.adapter
 * user: yangqinbo
 * date: 2020/6/22
 * time: 16:33
 * email: 694125155@qq.com
 */
public class AirOrderPassengerAdapter extends BaseQuickAdapter<DocOrderDetailPassengersInfo, BaseViewHolder> {
    public AirOrderPassengerAdapter(@Nullable List<DocOrderDetailPassengersInfo> data) {
        super(R.layout.item_layout_order_passenger, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, DocOrderDetailPassengersInfo item) {
        helper.setText(R.id.name, item.name);
        helper.setText(R.id.ageType, "（" + item.type + "）");
        helper.setText(R.id.cardNo, item.cardNum);
        helper.setText(R.id.cardType, item.cardType);
        if (item.ticketNo == null || TextUtils.isEmpty(item.ticketNo)) {
            helper.setGone(R.id.rl_copy, false);
            helper.setGone(R.id.ticketNo, false);
            helper.setGone(R.id.tvTicketName,false);
        } else {
            helper.setGone(R.id.rl_copy, true);
            helper.setGone(R.id.ticketNo, true);
            helper.setGone(R.id.tvTicketName,true);
            helper.setText(R.id.ticketNo, item.ticketNo);
        }
        helper.addOnClickListener(R.id.rl_copy);
    }
}
