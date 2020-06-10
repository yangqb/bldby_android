package com.feitianzhu.shoplibrary.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.feitianzhu.shoplibrary.R;

import java.util.List;

public class HomeListAdapter extends BaseQuickAdapter<List<String>, BaseViewHolder> {
    private int [] img={R.mipmap.home_shoppiingimg,R.mipmap.home_shoppingimg,R.mipmap.home_flayimg,R.mipmap.home_oilimg};

    public HomeListAdapter( @Nullable List data) {
        super(R.layout.item_homerecyone_adapter, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, List<String> homeItem) {
        for (int i = 0; i <homeItem.size() ; i++) {
            baseViewHolder.setText(R.id.item_recyone_textview,homeItem.get(i));
            //Glide.with(mContext).load(img[i]).into(baseViewHolder.getView(R.id.item_recyone_img));

        }

    }
}
