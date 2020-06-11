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

public class HomeMoreAdapter extends BaseQuickAdapter<News, BaseViewHolder> {
    public HomeMoreAdapter(@Nullable List<News> data) {
        super(R.layout.item_more_adapter, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, News item) {
         helper.setText(R.id.item_moretext,item.getTitle());
        ImageView img = helper.getView(R.id.item_moreimg);
        loadImg(item, img);
    }

    private void loadImg(News item, ImageView img) {
        Glide.with(mContext).load(item.getPic()).apply(RequestOptions.placeholderOf(R.mipmap.pic_fuwutujiazaishibai)).into(img);
    }
}
