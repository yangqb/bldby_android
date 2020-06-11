package com.bldby.shoplibrary.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.bldby.shoplibrary.R;
import com.bldby.shoplibrary.bean.News;

import java.util.List;

public class HomeSeckilAdapter extends BaseQuickAdapter<News, BaseViewHolder> {


    public HomeSeckilAdapter( @Nullable List<News> data) {
        super(R.layout.item_homerecyfour_adapter, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, News news) {
        ImageView img = baseViewHolder.getView(R.id.item_recyfour_img);
        TextView name = baseViewHolder.getView(R.id.item_recyfour_text);
        TextView price = baseViewHolder.getView(R.id.item_recyfour_price);
        TextView oldprice = baseViewHolder.getView(R.id.item_recyfour_oldprice);
        loadImg(news, img);
        name.setText(news.getTitle());


    }

    private void loadImg(News news, ImageView img) {

        Glide.with(mContext).load(news.getPic()).apply(RequestOptions.placeholderOf(R.mipmap.pic_fuwutujiazaishibai)).into(img);

    }
}
