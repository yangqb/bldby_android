package com.bldby.shoplibrary.settlement;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bldby.baselibrary.core.ui.baseactivity.BaseActivity;
import com.bldby.shoplibrary.R;
import com.bldby.shoplibrary.databinding.ActivitySettlementMainBinding;

public class SettlementMainActivity extends BaseActivity {

    private ActivitySettlementMainBinding binding;

    @Override
    public void bindIngView() {
        binding = DataBindingUtil.setContentView(SettlementMainActivity.this, R.layout.activity_settlement_main);
        binding.setViewModel(this);
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