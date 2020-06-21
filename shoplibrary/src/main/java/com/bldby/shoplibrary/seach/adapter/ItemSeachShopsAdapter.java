package com.bldby.shoplibrary.seach.adapter;

import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bldby.baselibrary.core.util.StringUtil;
import com.bldby.shoplibrary.R;
import com.bldby.shoplibrary.seach.model.GoodsSeachModel;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class ItemSeachShopsAdapter extends BaseQuickAdapter<GoodsSeachModel.ListBean, BaseViewHolder> {
    public ItemSeachShopsAdapter(@Nullable List<GoodsSeachModel.ListBean> data) {
        super(R.layout.item_seach_shops, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, GoodsSeachModel.ListBean listBean) {
        Glide.with(mContext)
                .load(listBean.getImage())
                .into((ImageView) baseViewHolder.getView(R.id.img));
        baseViewHolder.setText(R.id.title, listBean.getGoodsname());
        if (listBean.getSales() > 500) {
            baseViewHolder.setText(R.id.sales, "销量：" + listBean.getSales() + "+");
        } else {
            baseViewHolder.setText(R.id.sales, "销量：" + listBean.getSales());
        }
        TextView view = baseViewHolder.getView(R.id.minMarketPrice);
        view.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        baseViewHolder.setText(R.id.minMarketPrice, "¥" + listBean.getMinMarketPrice() + "市场参考价");
        if (listBean.getCounts() > 1) {
            baseViewHolder.setText(R.id.price, get(listBean.getMinPrice()));
            baseViewHolder.setText(R.id.minReturnPrice, "奖励" + listBean.getMinReturnPrice() + "起");

        } else {
            baseViewHolder.setText(R.id.price, get1(listBean.getMinPrice()));
            baseViewHolder.setText(R.id.minReturnPrice, "奖励" + listBean.getMinReturnPrice() + "起");
        }
    }

    public Spannable get(double i) {
        String price = "¥ " + i + "起";
        Spannable sp = new SpannableString(price);
        sp.setSpan(new AbsoluteSizeSpan(12, true), 0, 2, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        sp.setSpan(new AbsoluteSizeSpan(16, true), 2, price.length() - 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        sp.setSpan(new AbsoluteSizeSpan(12, true), price.length() - 1, price.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return sp;
    }

    public Spannable get1(double i) {
        String price = "¥ " + i;
        Spannable sp = new SpannableString(price);
        sp.setSpan(new AbsoluteSizeSpan(12, true), 0, 2, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        sp.setSpan(new AbsoluteSizeSpan(16, true), 2, price.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return sp;
    }
}
