package com.bldby.shoplibrary.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bldby.shoplibrary.R;
import com.bldby.shoplibrary.bean.News;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class HomeTodayAdapter extends BaseQuickAdapter<News, BaseViewHolder> {
    public HomeTodayAdapter( @Nullable List<News> data) {
        super(R.layout.item_today_adapter, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, News item) {
        helper.setText(R.id.item_todayname,item.getTitle());
        ImageView img = helper.getView(R.id.item_todayimg);
        loadImg(item, img);

    }

    private void loadImg(News item, ImageView img) {
        Glide.with(mContext).load(item.getPic()).apply(RequestOptions.placeholderOf(R.mipmap.pic_fuwutujiazaishibai)).into(img);

    }
}
