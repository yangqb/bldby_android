package com.bldby.shoplibrary.goods;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.baselibrary.constants.RouteShopConstants;
import com.bldby.baselibrary.core.ui.baseactivity.BaseActivity;
import com.bldby.shoplibrary.R;
import com.bldby.shoplibrary.databinding.ActivityGoodsSeachBinding;
import com.bldby.shoplibrary.goods.adapter.GoodsHistoryKeyAdapter;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.gyf.immersionbar.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

import static com.bldby.baselibrary.constants.RouteShopConstants.SHOPGOODSGOODSSEACH;

@Route(path = SHOPGOODSGOODSSEACH)
public class GoodsSeachActivity extends BaseActivity {


    private ActivityGoodsSeachBinding goodsSeachBinding;


    @Override
    public void bindIngView() {
        goodsSeachBinding = DataBindingUtil.setContentView(GoodsSeachActivity.this, R.layout.activity_goods_seach);
        goodsSeachBinding.setViewModel(this);
    }

    @Override
    public void initView() {
        //返回方法（）
        goodsSeachBinding.seachDeletetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //历史记录
        inithosty();

    }

    private void inithosty() {

    }

    @Override
    public ImmersionBar getOpenImmersionBar() {
        return ImmersionBar.with(this)
                .fitsSystemWindows(true)
                .statusBarDarkFont(true, 0.2f)
                .navigationBarColor(R.color.white)
                .statusBarColor(R.color.FCB432)
                .navigationBarDarkIcon(true);
    }


    @Override
    public void loadData() {

    }

    @Override
    public void initListener() {

    }
}