package com.bldby.shoplibrary.goods.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bldby.shoplibrary.R;
import com.bldby.shoplibrary.goods.model.GoodsComment;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class AdapterGoodsEvaluateDetail extends BaseQuickAdapter<GoodsComment, BaseViewHolder> {
    public AdapterGoodsEvaluateDetail(@Nullable List<GoodsComment> data) {
        super(R.layout.item_goods_evaluate_detail, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, GoodsComment item) {
//        helper.setText(R.id.des, item);
        RecyclerView view = helper.getView(R.id.recyclerView2);
        ArrayList newsList = new ArrayList();

        newsList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1592150018281&di=191016011e26f8f035cddb89f08f5e90&imgtype=0&src=http%3A%2F%2Fbos.pgzs.com%2Frbpiczy%2FWallpaper%2F2011%2F12%2F8%2Faa69906a9dc34b8d8fad0e0650a03863-2.jpg");
        newsList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1592150018281&di=191016011e26f8f035cddb89f08f5e90&imgtype=0&src=http%3A%2F%2Fbos.pgzs.com%2Frbpiczy%2FWallpaper%2F2011%2F12%2F8%2Faa69906a9dc34b8d8fad0e0650a03863-2.jpg");
        newsList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1592150018281&di=191016011e26f8f035cddb89f08f5e90&imgtype=0&src=http%3A%2F%2Fbos.pgzs.com%2Frbpiczy%2FWallpaper%2F2011%2F12%2F8%2Faa69906a9dc34b8d8fad0e0650a03863-2.jpg");
        AdapterGoodsEvaluateItemDetail adapterGoodsEvaluateItemDetail = new AdapterGoodsEvaluateItemDetail(newsList);
        view.setAdapter(adapterGoodsEvaluateItemDetail);
        adapterGoodsEvaluateItemDetail.notifyDataSetChanged();
    }

}
