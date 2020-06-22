package com.bldby.airticket.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.widget.TextView;

import com.bldby.airticket.R;
import com.bldby.airticket.model.AirOrderInfo;
import com.bldby.airticket.model.AirOrderStatus;
import com.bldby.baselibrary.core.util.MathUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * package name: com.bldby.airticket.adapter
 * user: yangqinbo
 * date: 2020/6/22
 * time: 15:26
 * email: 694125155@qq.com
 */
public class AirOrderAdapter extends BaseQuickAdapter<AirOrderInfo.AirOrderModel, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public AirOrderAdapter(List<AirOrderInfo.AirOrderModel> data) {
        super(R.layout.item_air_order, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, AirOrderInfo.AirOrderModel item) {
        if (item.status == AirOrderStatus.BOOK_OK) {
            helper.setText(R.id.tvStatus, "等待付款");
            helper.setVisible(R.id.btn_pay, true);
        } else if (item.status == AirOrderStatus.PAY_OK || item.status == AirOrderStatus.TICKET_LOCK) {
            helper.setText(R.id.tvStatus, "等待出票");
            helper.setVisible(R.id.btn_pay, false);
        } else if (item.status == AirOrderStatus.TICKET_OK) {
            helper.setText(R.id.tvStatus, "出票完成");
            helper.setVisible(R.id.btn_pay, false);
        } else if (item.status == AirOrderStatus.CANCEL_OK) {
            helper.setText(R.id.tvStatus, "订单取消");
            helper.setVisible(R.id.btn_pay, false);
        } else if (item.status == AirOrderStatus.WAIT_REFUNDMENT || item.status == AirOrderStatus.APPLY_RETURN_PAY || item.status == AirOrderStatus.APPLY_REFUNDMENT) {
            helper.setText(R.id.tvStatus, "等待退款");
            helper.setVisible(R.id.btn_pay, false);
        } else if (item.status == AirOrderStatus.REFUND_OK) {
            helper.setText(R.id.tvStatus, "退款完成");
            helper.setVisible(R.id.btn_pay, false);
        } else if (item.status == AirOrderStatus.APPLY_CHANGE) {
            helper.setText(R.id.tvStatus, "改签中");
            helper.setVisible(R.id.btn_pay, false);
        } else if (item.status == AirOrderStatus.CHANGE_OK) {
            helper.setText(R.id.tvStatus, "改签完成");
            helper.setVisible(R.id.btn_pay, false);
        }
        helper.setText(R.id.orderDate, "下单时间" + item.createTime);
        setSpannableString(MathUtils.subZero(String.valueOf(item.noPayAmount)), helper.getView(R.id.price));
        helper.setText(R.id.goFlightCode, item.flightNum);
        helper.setText(R.id.goFlightDate, item.goFlyTime);
        helper.setText(R.id.cityName, item.goCityInfo);
        if (item.isGoBack == 0) {
            helper.setGone(R.id.ll_back, false);
            helper.setGone(R.id.goTitle, false);
        } else {
            helper.setGone(R.id.ll_back, true);
            helper.setGone(R.id.goTitle, true);
            helper.setText(R.id.backFlightDate, item.backFlyTime);
            helper.setText(R.id.backFlightCode, item.backFlightNum);
            helper.setText(R.id.cityName, item.backCityInfo);
        }

        // helper.addOnClickListener(R.id.btn_pay);

    }

    @SuppressLint("SetTextI18n")
    private void setSpannableString(String str3, TextView view) {
        String str1 = "¥";
        view.setText("");
        SpannableString span1 = new SpannableString(str1);
        SpannableString span2 = new SpannableString(str3);
        ForegroundColorSpan colorSpan1 = new ForegroundColorSpan(Color.parseColor("#FF8300"));
        span1.setSpan(new AbsoluteSizeSpan(13, true), 0, str1.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        span1.setSpan(new StyleSpan(Typeface.BOLD), 0, str1.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        span1.setSpan(colorSpan1, 0, str1.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);


        span2.setSpan(new AbsoluteSizeSpan(18, true), 0, str3.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        span2.setSpan(new StyleSpan(Typeface.BOLD), 0, str3.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        span2.setSpan(colorSpan1, 0, str3.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);


        span1.setSpan(new AbsoluteSizeSpan(12, true), 0, str1.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        span1.setSpan(new StyleSpan(Typeface.BOLD), 0, str1.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        span1.setSpan(colorSpan1, 0, str1.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);


        span2.setSpan(new AbsoluteSizeSpan(15, true), 0, str3.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        span2.setSpan(new StyleSpan(Typeface.BOLD), 0, str3.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        span2.setSpan(colorSpan1, 0, str3.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        view.append(span1);
        view.append(span2);

    }
}
