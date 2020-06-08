package com.feitianzhu.shoplibrary;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.feitianzhu.baselibrary.app.RouteConstants;
import com.feitianzhu.baselibrary.app.RouteShopConstants;
import com.feitianzhu.baselibrary.core.view.baseactivity.BaseActivity;
import com.feitianzhu.baselibrary.core.view.basefragment.Basefragment;
import com.feitianzhu.shoplibrary.databinding.ActivityShopMainBinding;

@Route(path = RouteShopConstants.SHOPMAIN)
public class ShopMainActivity extends BaseActivity {

    private ActivityShopMainBinding binding;
    private Basefragment classify;


    @Override
    public void bindIngView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_shop_main);
        binding.setViewModel(this);
    }

    @Override
    public void initView() {
        classify = (Basefragment) startWith(RouteShopConstants.SHOPMAINCLASSIFY).withString("key1", "fenlei").navigation();
        loadRootFragment(R.id.root, classify);
    }

    @Override
    public void loadData() {

    }

    @Override
    public void initListener() {

    }
}