package com.bldby.loginlibrary.ui;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.baselibrary.constants.RouteLoginConstants;
import com.bldby.baselibrary.core.ui.baseactivity.BaseActivity;
import com.bldby.baselibrary.core.ui.baseactivity.BaseUiActivity;
import com.bldby.loginlibrary.R;
import com.bldby.loginlibrary.databinding.ActivityLoginBinding;

@Route(path = RouteLoginConstants.LOGINMAIN)
public class LoginActivity extends BaseUiActivity {

    private ActivityLoginBinding bingding;

    @Override
    protected View initContentView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        bingding = ActivityLoginBinding.inflate(layoutInflater, viewGroup, false);
        bingding.setViewModel(this);
        return bingding.getRoot();
    }

    @Override
    public void initView() {
        initTitle(getString(R.string.app_name));
        setTitleBackground(R.color.white);
    }

    @Override
    public void loadData() {

    }

    @Override
    public void initListener() {

    }
}
