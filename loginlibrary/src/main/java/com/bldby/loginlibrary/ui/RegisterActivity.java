package com.bldby.loginlibrary.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.baselibrary.constants.RouteLoginConstants;
import com.bldby.baselibrary.core.ui.baseactivity.BaseUiActivity;
import com.bldby.loginlibrary.R;
import com.bldby.loginlibrary.databinding.ActivityRegisterBinding;

/**
 * package name: com.bldby.loginlibrary.ui
 * user: yangqinbo
 * date: 2020/6/11
 * time: 18:39
 * email: 694125155@qq.com
 */
@Route(path = RouteLoginConstants.REGISTER)
public class RegisterActivity extends BaseUiActivity {
    private ActivityRegisterBinding binding;

    @Override
    protected View initContentView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        binding = ActivityRegisterBinding.inflate(layoutInflater, viewGroup, false);
        binding.setViewModel(this);
        return binding.getRoot();
    }

    @Override
    public void initView() {
        initTitle(getString(R.string.register_title));
        setTitleBackground(R.color.white);
    }

    @Override
    public void loadData() {

    }

    @Override
    public void initListener() {

    }
}
