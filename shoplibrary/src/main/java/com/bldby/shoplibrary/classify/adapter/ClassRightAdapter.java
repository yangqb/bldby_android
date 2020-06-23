package com.bldby.shoplibrary.classify.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.bldby.shoplibrary.R;
import com.bldby.shoplibrary.classify.bean.ClassRightGoodsBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.Collections;
import java.util.List;

public class ClassRightAdapter extends BaseQuickAdapter<List<ClassRightGoodsBean.CategoryListBean>, BaseViewHolder> {
    public ClassRightAdapter( @Nullable List<ClassRightGoodsBean.CategoryListBean> data) {
        super(R.layout.item_righttop_adapter, Collections.singletonList(data));
    }
  /*  public ClassRightAdapter(@Nullable List<ClassRightGoodsBean.CategoryListBean> data) {
        super(R.layout., data);
    }*/


    @Override
    protected void convert(@NonNull BaseViewHolder helper, List<ClassRightGoodsBean.CategoryListBean> item) {
        ImageView class_right_top_img = helper.getView(R.id.class_right_top_img);
        for (int i = 0; i <item.size(); i++) {
            Log.i("ccccccc", "onAPIResponse: "+item.get(i).getName());
        helper.setText(R.id.class_right_top_name,item.get(i).getName());
        Glide.with(mContext).load(item.get(i).getImage()).apply(RequestOptions.placeholderOf(R.mipmap.pic_fuwutujiazaishibai)).into(class_right_top_img);
        }
    }
}
