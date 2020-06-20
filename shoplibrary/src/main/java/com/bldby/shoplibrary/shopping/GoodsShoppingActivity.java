package com.bldby.shoplibrary.shopping;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.baselibrary.constants.RouteShopConstants;
import com.bldby.baselibrary.core.ui.baseactivity.BaseActivity;
import com.bldby.baselibrary.core.ui.baseactivity.BaseUiActivity;
import com.bldby.shoplibrary.R;
import com.bldby.shoplibrary.bean.News;
import com.bldby.shoplibrary.databinding.ActivityGoodsShoppingBinding;
import com.bldby.shoplibrary.shopping.adapter.ShoppingAdapter;

import java.util.ArrayList;
import java.util.List;

@Route(path = RouteShopConstants.SHOPGOODSSHOPPING)
public class GoodsShoppingActivity extends BaseActivity {

    private ActivityGoodsShoppingBinding binding;
    private List<News> newsList;


    @Override
    public void bindIngView() {
        binding = DataBindingUtil.setContentView(GoodsShoppingActivity.this,R.layout.activity_goods_shopping);
        binding.setViewModel(this);
    }
    @Override
    public void initView() {
        binding.titleName.setText("购物车");
        binding.leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        newsList = new ArrayList();
        newsList.add(new News("新闻标题1", R.mipmap.a21_03riyong));
        newsList.add(new News("新闻标题2", R.mipmap.a21_04hufu));
        newsList.add(new News("新闻标题3", R.mipmap.a21_05muying));
        //购物车条目
        initshopping();
    }

    private void initshopping() {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(GoodsShoppingActivity.this,LinearLayoutManager.VERTICAL,false));
        ShoppingAdapter adapter=new ShoppingAdapter(newsList);
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    public void loadData() {

    }

    @Override
    public void initListener() {

    }
}