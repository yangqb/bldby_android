package com.bldby.shoplibrary.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bldby.shoplibrary.R;
import com.bldby.shoplibrary.bean.News;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter1 extends BaseQuickAdapter<News, BaseViewHolder> {


    public MainAdapter1(@Nullable List<News> data) {
        super(R.layout.item_banner1, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, News data) {
        Glide.with(mContext)
                .load(data.getPic())
                .into((ImageView) helper.getView(R.id.banner_image));
    }

}
