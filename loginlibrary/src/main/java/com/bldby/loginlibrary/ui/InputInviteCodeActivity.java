package com.bldby.loginlibrary.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bldby.baselibrary.core.ui.baseactivity.BaseUiActivity;
import com.bldby.loginlibrary.R;
import com.bldby.loginlibrary.databinding.ActivityInviteCodeBinding;

/**
 * package name: com.bldby.loginlibrary.ui
 * user: yangqinbo
 * date: 2020/6/11
 * time: 19:30
 * email: 694125155@qq.com
 */
public class InputInviteCodeActivity extends BaseUiActivity {
    @Override
    protected View initContentView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        ActivityInviteCodeBinding binding = ActivityInviteCodeBinding.inflate(layoutInflater, viewGroup, false);
        binding.setViewModel(this);
        return binding.getRoot();
    }

    @Override
    public void initView() {
        initTitle(getString(R.string.login_invite_title));
        setTitleBackground(R.color.white);
    }

    @Override
    public void loadData() {

    }

    @Override
    public void initListener() {

    }
}
