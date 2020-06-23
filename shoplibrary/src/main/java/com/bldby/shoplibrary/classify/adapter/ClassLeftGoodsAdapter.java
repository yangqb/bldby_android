package com.bldby.shoplibrary.classify.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bldby.shoplibrary.R;
import com.bldby.shoplibrary.classify.bean.ClassLeftGoodsBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class ClassLeftGoodsAdapter extends BaseQuickAdapter<ClassLeftGoodsBean, BaseViewHolder> {
    public ClassLeftGoodsAdapter(List<ClassLeftGoodsBean> data) {
        super(R.layout.item_classgoodsleft_adapter, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ClassLeftGoodsBean item) {
        helper.setText(R.id.item_left_name,item.getName());
    }
}
