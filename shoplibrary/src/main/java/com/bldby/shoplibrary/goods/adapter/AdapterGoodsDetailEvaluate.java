package com.bldby.shoplibrary.goods.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bldby.shoplibrary.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class AdapterGoodsDetailEvaluate extends BaseQuickAdapter<String, BaseViewHolder> {
    public AdapterGoodsDetailEvaluate(@Nullable List<String> data) {
        super(R.layout.item_goods_detail_evaluate, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
//        helper.setText(R.id.des, item);
    }

}
