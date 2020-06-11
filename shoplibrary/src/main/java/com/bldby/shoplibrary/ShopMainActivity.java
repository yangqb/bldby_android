package com.bldby.shoplibrary;

import android.databinding.DataBindingUtil;

import com.bldby.baselibrary.constants.RouteShopConstants;
import com.bldby.baselibrary.core.ui.baseactivity.BaseActivity;
import com.bldby.baselibrary.core.ui.basefragment.Basefragment;
import com.bldby.shoplibrary.databinding.ActivityShopMainBinding;

//@Route(path = RouteShopConstants.SHOPMAIN)
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