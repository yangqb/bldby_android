package com.bldby.centerlibrary;

import android.database.DatabaseUtils;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;

import com.bldby.baselibrary.constants.RouteCenterConstants;
import com.bldby.baselibrary.constants.RouteShopConstants;
import com.bldby.baselibrary.core.ui.baseactivity.BaseActivity;
import com.bldby.baselibrary.core.ui.basefragment.Basefragment;
import com.bldby.centerlibrary.databinding.ActivityCenterMainBinding;

/**
 * package name: com.bldby.centerlibrary
 * user: yangqinbo
 * date: 2020/6/19
 * time: 18:02
 * email: 694125155@qq.com
 */
public class MyCenterMainActivity extends BaseActivity {
    private Basefragment center;
    @Override
    public void bindIngView() {
        ActivityCenterMainBinding dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_center_main);
        dataBinding.setViewModel(this);
    }

    @Override
    public void initView() {
        center = (Basefragment) startWith(RouteCenterConstants.CENTERMAIN).withString("key", "center").navigation();
        loadRootFragment(R.id.root, center);
    }

    @Override
    public void loadData() {

    }

    @Override
    public void initListener() {

    }
}
