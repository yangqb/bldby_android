package com.bldby.shoplibrary.goods.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.bldby.shoplibrary.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class GoodsHistoryKeyAdapter  extends BaseQuickAdapter<String, BaseViewHolder> {
    public GoodsHistoryKeyAdapter( @Nullable List<String> data) {
        super(R.layout.item_goods_key, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        helper.setText(R.id.tvContent, item);

    }

}
