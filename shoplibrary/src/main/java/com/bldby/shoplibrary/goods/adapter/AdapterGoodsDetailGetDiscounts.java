package com.bldby.shoplibrary.goods.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.bldby.shoplibrary.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class AdapterGoodsDetailGetDiscounts extends BaseQuickAdapter<String, BaseViewHolder> {
    public AdapterGoodsDetailGetDiscounts(@Nullable List<String> data) {
        super(R.layout.item_goods_detail_youhui, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        helper.setText(R.id.des, item);
    }

}
