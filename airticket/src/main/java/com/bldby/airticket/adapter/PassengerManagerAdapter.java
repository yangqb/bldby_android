package com.bldby.airticket.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bldby.airticket.R;
import com.bldby.airticket.model.PassengerModel;
import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * package name: com.bldby.airticket.adapter
 * user: yangqinbo
 * date: 2020/6/20
 * time: 16:28
 * email: 694125155@qq.com
 */
public class PassengerManagerAdapter extends BaseItemDraggableAdapter<PassengerModel, BaseViewHolder> {
    public PassengerManagerAdapter(@Nullable List<PassengerModel> data) {
        super(R.layout.item_layout_passenger_manager, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, PassengerModel item) {
        helper.addOnClickListener(R.id.rl_select);
        helper.setText(R.id.name, item.name);
        if (item.ageType == 0) {
            helper.setText(R.id.passenger_type, "成人");
        } else {
            helper.setText(R.id.passenger_type, "儿童");
        }
        if ("NI".equals(item.cardType)) {
            helper.setText(R.id.cardType, "身份证");
        } else if ("PP".equals(item.cardType)) {
            helper.setText(R.id.cardType, "护照");
        } else if ("GA".equals(item.cardType)) {
            helper.setText(R.id.cardType, "港澳通行证");
        } else if ("TW".equals(item.cardType)) {
            helper.setText(R.id.cardType, "台湾通行证");
        } else if ("TB".equals(item.cardType)) {
            helper.setText(R.id.cardType, "台胞证");
        } else if ("HX".equals(item.cardType)) {
            helper.setText(R.id.cardType, "回乡证");
        } else if ("HY".equals(item.cardType)) {
            helper.setText(R.id.cardType, "国际海员证");
        } else {
            helper.setText(R.id.cardType, "其他");
        }
        helper.setText(R.id.cardNo, item.cardNo);

        if (item.isSelect) {
            helper.setBackgroundRes(R.id.select_img, R.mipmap.g07_02quan);
        } else {
            helper.setBackgroundRes(R.id.select_img, R.mipmap.g07_01quan);
        }
    }
}

