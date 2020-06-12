package com.bldby.shoplibrary.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bldby.shoplibrary.R;
import com.bldby.shoplibrary.bean.News;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class HomeRecommendAdapter extends BaseQuickAdapter<News, BaseViewHolder> {
    public HomeRecommendAdapter( @Nullable List<News> data) {
        super(R.layout.item_recommed_adapter, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, News item) {
        ImageView img = helper.getView(R.id.item_recommedimg);
        helper.setText(R.id.item_recommedtitle,item.getTitle());
        loadImg(item, img);

    }

    private void loadImg(News item, ImageView img) {
        Glide.with(mContext).load(item.getPic()).apply(RequestOptions.placeholderOf(R.mipmap.pic_fuwutujiazaishibai)).into(img);

    }
}
