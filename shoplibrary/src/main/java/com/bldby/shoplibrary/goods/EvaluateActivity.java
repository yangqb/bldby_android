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
import com.bldby.baselibrary.core.network.ParamsBuilder;
import com.bldby.baselibrary.core.ui.baseactivity.BaseUiActivity;
import com.bldby.shoplibrary.R;
import com.bldby.shoplibrary.databinding.ActivityEvaluateBinding;
import com.bldby.shoplibrary.goods.adapter.AdapterGoodsEvaluateDetail;
import com.bldby.shoplibrary.goods.model.GoodsComment;
import com.bldby.shoplibrary.goods.request.GoodsCommentRequest;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;

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
        evaluateBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void loadData() {
//        ParamsBuilder httpParams = new ParamsBuilder();
//        httpParams.append("xxx","xxxx");
//        httpParams.append("zzz","zzzzz");
//        httpParams.append("qqq","qqqq");
//        String json = new Gson().toJson(httpParams.build());
//        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
//        RequestBody body = RequestBody.create(mediaType, json);
//        OkGo.<String>post("http://219.234.82.84:10001/goodsApi/goodsComment")
//                .tag(this)
//                .params("token", "token")
//                .params("userId", "userId")
//                .upRequestBody(body)
//                .execute(new AbsCallback<String>() {
//                    @Override
//                    public void onSuccess(Response<String> response) {
//
//                    }
//
//                    @Override
//                    public String convertResponse(okhttp3.Response response) throws Throwable {
//                        return null;
//                    }
//                });
        GoodsCommentRequest goodsCommentRequest = new GoodsCommentRequest();
        goodsCommentRequest.spuId = 2;
        goodsCommentRequest.call(new ApiCallBack<List<GoodsComment>>() {
            @Override
            public void onAPIResponse(List<GoodsComment> response) {
                if (response != null && response.size() > 0) {
                    adapterGoodsEvaluateDetail.setNewData(response);
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