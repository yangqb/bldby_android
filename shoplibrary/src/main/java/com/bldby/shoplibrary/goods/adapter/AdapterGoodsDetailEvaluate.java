package com.bldby.shoplibrary.goods.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bldby.shoplibrary.R;
import com.bldby.shoplibrary.goods.model.GoodsDetailModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class AdapterGoodsDetailEvaluate extends BaseQuickAdapter<GoodsDetailModel.EvalsBean, BaseViewHolder> {
    public AdapterGoodsDetailEvaluate(@Nullable List<GoodsDetailModel.EvalsBean> data) {
        super(R.layout.item_goods_detail_evaluate, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, GoodsDetailModel.EvalsBean item) {
//        helper.setText(R.id.des, item);
    }

}
