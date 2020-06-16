package com.bldby.travellibrary.activity.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bldby.travellibrary.activity.model.OilListBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class MyOilAdapter  extends BaseQuickAdapter<OilListBean, BaseViewHolder> {
    public MyOilAdapter(int layoutResId, @Nullable List<OilListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, OilListBean item) {

    }
}
