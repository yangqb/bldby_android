package com.bldby.loginlibrary.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.baselibrary.constants.RouteLoginConstants;
import com.bldby.baselibrary.core.ui.baseactivity.BaseUiActivity;
import com.bldby.loginlibrary.R;
import com.bldby.loginlibrary.databinding.ActivityBidingAccountBinding;

/**
 * package name: com.bldby.loginlibrary.ui
 * user: yangqinbo
 * date: 2020/6/11
 * time: 14:42
 * email: 694125155@qq.com
 */
@Route(path = RouteLoginConstants.BIDINGACCOUNT)
public class BindingAccountActivity extends BaseUiActivity {
    @Override
    protected View initContentView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        ActivityBidingAccountBinding biding = ActivityBidingAccountBinding.inflate(layoutInflater, viewGroup, false);
        biding.setViewModel(this);
        return biding.getRoot();
    }

    @Override
    public void initView() {
        initTitle(getString(R.string.login_biding_title_name));
        setTitleBackground(R.color.white);
    }

    @Override
    public void loadData() {

    }

    @Override
    public void initListener() {

    }
}
