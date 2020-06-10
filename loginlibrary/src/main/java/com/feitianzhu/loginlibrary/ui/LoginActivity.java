package com.feitianzhu.loginlibrary.ui;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.View;
import android.widget.Toast;

import com.feitianzhu.baselibrary.core.ui.baseactivity.BaseActivity;
import com.feitianzhu.loginlibrary.R;
import com.feitianzhu.loginlibrary.databinding.ActivityLoginBinding;

public class LoginActivity extends BaseActivity {

    private ActivityLoginBinding bingding;

    @Override
    public void bindIngView() {
        bingding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        bingding.setViewModel(this);
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
