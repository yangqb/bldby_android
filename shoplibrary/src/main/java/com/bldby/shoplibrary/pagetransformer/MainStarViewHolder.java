package com.bldby.shoplibrary.pagetransformer;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bldby.shoplibrary.R;


/**
 * Fixme
 * Author: LWJ
 * desc:
 * Date: 2017/09/07 11:40
 * Copyright (c) 2016 d2cmall. All rights reserved.
 */

public class MainStarViewHolder extends RecyclerView.ViewHolder {
        public com.bldby.shoplibrary.pagetransformer.CustomViewPager mViewPager;
        public TextView tvStarDesc;
        public View viewLeft;
        public View viewRight;

        public MainStarViewHolder(View view) {
            super(view);
                mViewPager= view.findViewById(R.id.viewpager);
                tvStarDesc= view.findViewById(R.id.tv_star_desc);
                viewLeft= view.findViewById(R.id.view_left);
                viewRight= view.findViewById(R.id.view_right);
        }


}
