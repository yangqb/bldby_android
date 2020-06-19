package com.bldby.shoplibrary.goods;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.baselibrary.constants.RouteShopConstants;
import com.bldby.baselibrary.core.network.ApiCallBack;
import com.bldby.baselibrary.core.ui.baseactivity.BaseUiActivity;
import com.bldby.shoplibrary.R;
import com.bldby.shoplibrary.databinding.ActivityEvaluateBinding;
import com.bldby.shoplibrary.goods.adapter.AdapterGoodsEvaluateDetail;
import com.bldby.shoplibrary.goods.model.GoodsComment;
import com.bldby.shoplibrary.goods.request.GoodsCommentRequest;

import org.jetbrains.annotations.NotNull;

import java.util.List;

@Route(path = RouteShopConstants.SHOPGOODSEVALUATE)
public class EvaluateActivity extends BaseUiActivity {

    private ActivityEvaluateBinding evaluateBinding;
    private AdapterGoodsEvaluateDetail adapterGoodsEvaluateDetail;

    @Override
    protected View initContentView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        evaluateBinding = ActivityEvaluateBinding.inflate(layoutInflater, viewGroup, false);
        setTitleBackground(R.color.white);
        return evaluateBinding.getRoot();
    }

    @Override
    public void initView() {
        initTitle("商品评价");
        adapterGoodsEvaluateDetail = new AdapterGoodsEvaluateDetail(null);
        evaluateBinding.recyclerView.setAdapter(adapterGoodsEvaluateDetail);
        evaluateBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    }

    @Override
    public void loadData() {
        GoodsCommentRequest goodsCommentRequest = new GoodsCommentRequest();
        goodsCommentRequest.spuId = 2;
        goodsCommentRequest.call(new ApiCallBack<GoodsComment>() {
            @Override
            public void onAPIResponse(GoodsComment response) {
                List<GoodsComment.ListBean> list = response.getList();
                if (list != null && list.size() > 0) {
                    adapterGoodsEvaluateDetail.setNewData(list);
                    adapterGoodsEvaluateDetail.notifyDataSetChanged();
                }
                evaluateBinding.smartRe.finishLoadMoreWithNoMoreData();
            }

            @Override
            public void onAPIError(int errorCode, String errorMsg) {

            }
        });

    }

    @Override
    public void initListener() {

    }


}