package com.feitianzhu.shoplibrary.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.feitianzhu.shoplibrary.R;

import java.util.List;

public class HomeListAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {


    public HomeListAdapter( @Nullable List<Integer> data) {
        super(R.layout.item_homerecyone_adapter, data);
    }
    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, Integer integer) {
        TextView textview = baseViewHolder.getView(R.id.item_recyone_textview);
        ImageView img = baseViewHolder.getView(R.id.item_recyone_img);
        loadImg(integer, img);
        switch (baseViewHolder.getAdapterPosition()){
            case 0:
                textview.setText(R.string.home_shoppingmall);
                break;
            case 1:
                textview.setText(R.string.home_business);
                break;
            case 2:
                textview.setText(R.string.home_aircraft);
                break;
            case 3:
                textview.setText(R.string.home_servicestation);
                break;
           // Glide.with(mContext).load(integer).into((ImageView) helper.getView(R.id.iv));
        }

    }

    private void loadImg(Integer integer, ImageView img) {
        Glide.with(mContext).load(integer).apply(RequestOptions.placeholderOf(R.mipmap.pic_fuwutujiazaishibai)).into(img);
    }
}
