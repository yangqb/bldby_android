package com.bldby.shoplibrary.goods.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bldby.shoplibrary.R;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class AdapterGoodsEvaluateItemDetail extends BaseQuickAdapter<String, BaseViewHolder> {
    public AdapterGoodsEvaluateItemDetail(@Nullable List<String> data) {
        super(R.layout.item_goods_evaluate_detail_item, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
//        helper.setText(R.id.des, item);
        Glide.with(mContext)
                .load(item)
                .into((ImageView) helper.getView(R.id.img));
    }

}
