package com.bldby.loginlibrary.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.baselibrary.constants.RouteLoginConstants;
import com.bldby.baselibrary.core.ui.baseactivity.BaseUiActivity;
import com.bldby.loginlibrary.R;
import com.bldby.loginlibrary.databinding.ActivityLoginWechantBinding;

/**
 * package name: com.bldby.loginlibrary.ui
 * user: yangqinbo
 * date: 2020/6/12
 * time: 9:34
 * email: 694125155@qq.com
 */

/*
 *
 * 微信登录页面
 * */
@Route(path = RouteLoginConstants.LOGINWECHANT)
public class WeChantLoginActivity extends BaseUiActivity {
    private ActivityLoginWechantBinding binding;

    @Override
    protected View initContentView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        binding = ActivityLoginWechantBinding.inflate(layoutInflater, viewGroup, false);
        binding.setViewModel(this);
        return binding.getRoot();
    }

    @Override
    public void initView() {
        setTitleBackground(R.color.white);
    }

    /*
     * 微信登录授权
     * */
    public void weChantLogin(View view) {
        //未绑定手机号
        start(RouteLoginConstants.BIDINGACCOUNT);
        //此微信已绑定过注册的账号直接登录
    }

    @Override
    public void loadData() {

    }

    @Override
    public void initListener() {

    }
}
