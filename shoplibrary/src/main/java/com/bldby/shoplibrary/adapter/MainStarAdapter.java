package com.bldby.shoplibrary.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bldby.shoplibrary.R;
import com.bldby.shoplibrary.pagetransformer.CustomViewPager;

import java.util.List;

/**
 * Fixme
 * Author: LWJ
 * desc:
 * Date: 2017/09/19 10:16
 * Copyright (c) 2016 d2cmall. All rights reserved.
 */

public class MainStarAdapter extends PagerAdapter {
    private List<String> mDatas;
    private Context mContext;
    private ImageView mImageView;
    private TextView mTvIndex;
    private TextView mTvTotal;
    private ImageView mIvCover;
    private CustomViewPager mViewPager;
    private int mOriginSize ;
    private boolean hasInitTotal=false;
    private String tag;

    public MainStarAdapter(List<String> data, Context context, CustomViewPager viewPager, int originSize) {
        mDatas = data;
        mContext = context;
        mViewPager=viewPager;
        this.mOriginSize=originSize;
    }


    @Override
    public int getCount() {
        return mDatas.size();
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_stat_adapter, container, false);
        mImageView = (ImageView) view.findViewById(R.id.iv_star_item);
        mTvIndex = (TextView) view.findViewById(R.id.tv_index);
        mTvTotal = (TextView) view.findViewById(R.id.tv_star_total);
        mIvCover=(ImageView) view.findViewById(R.id.iv_cover);
        Glide.with(mContext).load(mDatas.get(position)).into(mImageView);
        mTvIndex.setText(""+(1+position));
        if(!hasInitTotal) {
            mTvTotal.setText("/" + (mDatas.size()));
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
