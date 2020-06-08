package com.feitianzhu.huangliwo;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.feitianzhu.baselibrary.constants.RouteConstants;
import com.feitianzhu.baselibrary.constants.RouteShopConstants;
import com.feitianzhu.baselibrary.core.ui.baseactivity.BaseActivity;
import com.feitianzhu.baselibrary.core.ui.basefragment.Basefragment;
import com.feitianzhu.huangliwo.databinding.ActivityMainBinding;

@Route(path = RouteConstants.APPMAIN)
public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;
    private Basefragment home;
    private Basefragment classify;

    @Override
    public void bindIngView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(this);
    }

    @Override
    public void initView() {

        home = (Basefragment) startWith(RouteShopConstants.SHOPMAINFirst).withString("key1", "sdfsdfdsfsd").navigation();
        classify = (Basefragment) startWith(RouteShopConstants.SHOPMAINCLASSIFY).withString("key1", "fenlei").navigation();
        loadMultipleRootFragment(R.id.root, 0, home, classify);
    }

    @Override
    public void loadData() {

    }

    @Override
    public void initListener() {
        binding.navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        showHideFragment(home);
                        break;
                    case R.id.navigation_dashboard:
                        showHideFragment(classify);
                        break;
                    case R.id.navigation_notifications:
                        showHideFragment(home);
                        break;
                    case R.id.navigation_notifications1:
                        showHideFragment(classify);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

}