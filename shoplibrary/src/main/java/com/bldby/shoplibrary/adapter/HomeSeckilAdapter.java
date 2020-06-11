package com.bldby.shoplibrary.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

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

    }
}
