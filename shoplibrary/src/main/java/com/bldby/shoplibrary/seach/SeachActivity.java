package com.bldby.shoplibrary.seach;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.baselibrary.app.util.SPUtils;
import com.bldby.baselibrary.constants.RouteShopConstants;
import com.bldby.baselibrary.core.ui.baseactivity.BaseActivity;
import com.bldby.shoplibrary.R;
import com.bldby.shoplibrary.databinding.ActivitySeachBinding;
import com.bldby.shoplibrary.goods.GoodsSeachActivity;
import com.bldby.shoplibrary.goods.adapter.GoodsHistoryKeyAdapter;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gyf.immersionbar.ImmersionBar;
import com.lzy.okgo.OkGo;

import java.util.ArrayList;
import java.util.List;

import static com.bldby.baselibrary.constants.RouteShopConstants.SHOPGOODSSEACH;
@Route(path = SHOPGOODSSEACH)
public class SeachActivity extends BaseActivity {
    public static final String HISTORY_KEY = "history_key";
    private ActivitySeachBinding seachBinding;
    private String searchText;
    @Override
    public void bindIngView() {
        seachBinding =  DataBindingUtil.setContentView(SeachActivity.this, R.layout.activity_seach);
        seachBinding.setViewModel(this);
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
    public void initView() {
        //点击取消方法（）
        seachBinding.seachDeletetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start(RouteShopConstants.SHOPGOODSGOODSSEACH);
                finish();
            }
        });

        searchText =seachBinding.etKeyword.getText().toString().trim();
        searchData(searchText);


    }

    private void searchData(String searchText) {

    }

    @Override
    public void loadData() {

    }

    @Override
    public void initListener() {

    }
}