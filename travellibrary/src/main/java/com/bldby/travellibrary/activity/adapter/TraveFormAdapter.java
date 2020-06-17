package com.bldby.travellibrary.activity.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bldby.travellibrary.R;
import com.bldby.travellibrary.activity.model.OilOrederBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class TraveFormAdapter extends BaseQuickAdapter<OilOrederBean, BaseViewHolder> {
    public TraveFormAdapter(@Nullable List<OilOrederBean> data) {
        super(R.layout.item_trave_adapter, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, OilOrederBean item) {
        helper.setText(R.id.payNum, item.getAmountPay());
        helper.setText(R.id.name, item.getGasName());
        helper.setText(R.id.level1, item.getOilNo());
        helper.setText(R.id.level2, String.valueOf(item.getGunNo())+"号");
        helper.setText(R.id.formId, "订单号: "+item.getOrderId());
        helper.setText(R.id.payState, item.getOrderStatusName());
        helper.setText(R.id.time, item.getPayTime());
    }
}
