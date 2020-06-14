package com.bldby.shoplibrary.goods.model;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bldby.shoplibrary.R;
import com.bldby.shoplibrary.bean.News;
import com.bumptech.glide.Glide;
import com.zhpan.bannerview.holder.ViewHolder;

public class BannerViewHolder implements ViewHolder<String> {
    private ImageView mImageView;
    public Context context;

    public BannerViewHolder() {
    }

    public BannerViewHolder(Context context) {
        this.context = context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_banner;
    }

    @Override
    public void onBind(View itemView, String data, int position, int size) {
        mImageView = itemView.findViewById(R.id.banner_image);
        Glide.with(context).load(data).into(mImageView);
    }
}
