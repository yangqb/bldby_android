package com.bldby.airticket.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bldby.airticket.R;
import com.bldby.airticket.model.FlightSegmentInfo;
import com.bldby.baselibrary.core.util.DateUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * package name: com.bldby.airticket.adapter
 * user: yangqinbo
 * date: 2020/6/18
 * time: 20:26
 * email: 694125155@qq.com
 */
public class TransitAdapter extends BaseQuickAdapter<FlightSegmentInfo, BaseViewHolder> {
    public TransitAdapter(@Nullable List<FlightSegmentInfo> data) {
        super(R.layout.item_layout_transit_city, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, FlightSegmentInfo item) {
        //中转城市不会超过3个
        if (helper.getAdapterPosition() == 0) {
            helper.setText(R.id.btn_title1, "一程");
            helper.setBackgroundRes(R.id.btn_title1, R.drawable.shape_28b5fe_r10);
        } else if (helper.getAdapterPosition() == 1) {
            helper.setText(R.id.btn_title1, "二程");
            helper.setBackgroundRes(R.id.btn_title1, R.drawable.shape_9b65ff_r10);
        } else if (helper.getAdapterPosition() == 2) {
            helper.setText(R.id.btn_title1, "三程");
            helper.setBackgroundRes(R.id.btn_title1, R.drawable.shape_28b5fe_r10);
        } else if (helper.getAdapterPosition() == 3) {
            helper.setText(R.id.btn_title1, "四程");
            helper.setBackgroundRes(R.id.btn_title1, R.drawable.shape_9b65ff_r10);
        }
        helper.setGone(R.id.companyName, false);
        helper.setText(R.id.date, DateUtil.strToStr(item.depDate) + DateUtil.strToWeek("yyyy-MM-dd", item.depDate));
        helper.setText(R.id.cityName, item.depCityName + "-" + item.arrCityName);
        helper.setText(R.id.depTime, item.depTime);
        helper.setText(R.id.arrTime, item.arrTime);
        helper.setText(R.id.duration, DateUtil.minToHour(item.duration));
        helper.setText(R.id.depAirportName, item.depAirportName + item.depTerminal);
        helper.setText(R.id.arrAirportName, item.arrAirportName + item.arrTerminal);
        if (item.crossDays == 0) {
            helper.setVisible(R.id.crossDays, false);
        } else {
            helper.setVisible(R.id.crossDays, true);
        }
        helper.setText(R.id.crossDays, "+" + item.crossDays + "天");
    }
}
