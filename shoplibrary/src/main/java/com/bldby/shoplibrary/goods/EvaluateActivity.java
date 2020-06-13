package com.bldby.shoplibrary.goods;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.baselibrary.constants.RouteShopConstants;
import com.bldby.baselibrary.core.ui.baseactivity.BaseUiActivity;
import com.bldby.shoplibrary.R;
import com.bldby.shoplibrary.databinding.ActivityEvaluateBinding;

import org.jetbrains.annotations.NotNull;

@Route(path = RouteShopConstants.SHOPGOODSEVALUATE)
public class EvaluateActivity extends BaseUiActivity {

    private ActivityEvaluateBinding evaluateBinding;

    @Override
    protected View initContentView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        evaluateBinding = ActivityEvaluateBinding.inflate(layoutInflater, viewGroup, false);
        setTitleBackground(R.color.white);
        return evaluateBinding.getRoot();
    }

    @Override
    public void initView() {
        initTitle("商品评价");
    }

    @Override
    public void loadData() {

    }

    @Override
    public void initListener() {

    }


}