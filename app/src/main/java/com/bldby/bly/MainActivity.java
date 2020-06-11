package com.bldby.bly;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.baselibrary.constants.RouteConstants;
import com.bldby.baselibrary.constants.RouteShopConstants;
import com.bldby.baselibrary.constants.RouteTravelConstants;
import com.bldby.baselibrary.core.share.ShareUtils;
import com.bldby.baselibrary.core.ui.baseactivity.BaseActivity;
import com.bldby.baselibrary.core.ui.basefragment.Basefragment;
import com.bldby.baselibrary.core.util.ShareImageUtils;
import com.bldby.bly.databinding.ActivityMainBinding;
import com.gyf.immersionbar.ImmersionBar;

@Route(path = RouteConstants.APPMAIN)
public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;
    private Basefragment home;
    private Basefragment classify;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void bindIngView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(this);
    }

    @Override
    public void initView() {
        home = (Basefragment) startWith(RouteShopConstants.SHOPMAINFirst).withString("key", "sdjfsf").navigation(this, this);
        classify = (Basefragment) startWith(RouteShopConstants.SHOPMAINCLASSIFY).withString("key", "fenlei").navigation();
        loadMultipleRootFragment(R.id.root, 0, home, classify);

    }

    @Override
    public void loadData() {

    }

    @Override
    public ImmersionBar getOpenImmersionBar() {
        return ImmersionBar.with(this)
                .fitsSystemWindows(true)
                .statusBarDarkFont(true, 0.2f)
                .navigationBarColor(R.color.white)
                .statusBarColor(R.color.FCB432)
                .navigationBarDarkIcon(true);
    }

    @Override
    public void initListener() {
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.e("TAG", "onTabSelected: " + tab.getPosition());

                switch (tab.getPosition()) {
                    case 0:
                        showHideFragment(home);
                        break;
                    case 1:
                        showHideFragment(classify);
                        break;
                    case 2:
                        showHideFragment(home);
                        break;
                    case 3:
//                        start(RouteTravelConstants.TRAVELMAIN);
//                        showHideFragment(classify);
                        Bitmap bitmap = ShareImageUtils.viewToBitmap(binding.container);
                        ShareUtils.shareImg(MainActivity.this, bitmap, "便利大本营");
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.e("TAG", "onTabUnselected: " + tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.e("TAG", "onTabReselected: " + tab.getPosition());

            }
        });

    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
//            if (videoPlayActivity != null) {
//                getSupportFragmentManager().popBackStack();
//                diaglo.setVisibility(View.GONE);
//
//                videoPlayActivity = null;
//                return false;
//            }
//
//            long backPressed = System.currentTimeMillis();
//            if (backPressed - lastBackPressed > QUIT_INTERVAL) {
//                lastBackPressed = backPressed;
//                ToastUtils.show("再按一次退出程序");
//            } else {
//                finish();
//                System.exit(0);
//            }
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
}