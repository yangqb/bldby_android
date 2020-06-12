package com.bldby.loginlibrary.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.baselibrary.constants.RouteLoginConstants;
import com.bldby.baselibrary.core.ui.baseactivity.BaseUiActivity;
import com.bldby.loginlibrary.databinding.ActivityLoginWechantBinding;

/**
 * package name: com.bldby.loginlibrary.ui
 * user: yangqinbo
 * date: 2020/6/12
 * time: 9:34
 * email: 694125155@qq.com
 */
@Route(path = RouteLoginConstants.LOGINWECHANT)
public class WeChantLoginActivity extends BaseUiActivity {
    @Override
    protected View initContentView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        ActivityLoginWechantBinding binding = ActivityLoginWechantBinding.inflate(layoutInflater, viewGroup, false);
        binding.setViewModel(this);
        return binding.getRoot();
    }

    @Override
    public void initView() {

    }

    @Override
    public void loadData() {

    }

    @Override
    public void initListener() {

    }
}
