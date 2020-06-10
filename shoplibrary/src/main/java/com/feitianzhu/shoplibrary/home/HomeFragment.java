package com.feitianzhu.shoplibrary.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.NonNull;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bumptech.glide.Glide;
import com.feitianzhu.baselibrary.constants.RouteShopConstants;
import com.feitianzhu.baselibrary.core.addresspick.AddressPickerUtil;
import com.feitianzhu.baselibrary.core.ui.basefragment.Basefragment;
import com.feitianzhu.baselibrary.core.util.ToastUtil;
import com.feitianzhu.shoplibrary.R;
import com.feitianzhu.shoplibrary.adapter.HomeListAdapter;
import com.feitianzhu.shoplibrary.adapter.HomeSeckilAdapter;
import com.feitianzhu.shoplibrary.bean.News;
import com.feitianzhu.shoplibrary.databinding.FragmentHomeBinding;
import com.feitianzhu.shoplibrary.seach.SeachHeaderView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Route(path = RouteShopConstants.SHOPMAINFirst)
public class HomeFragment extends Basefragment {

    @Autowired()
    public String key;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        binding.setViewModel(this);
        SeachHeaderView seachHeaderView = new SeachHeaderView(getActivity());
        seachHeaderView.onOptionsSelectListener = new AddressPickerUtil.OnOptionsTextSelectListener() {
            @Override
            public void onOptionsSelect(String options1, String options2, String options3) {
                ToastUtil.show(options1 + options2 + options3);
            }
        };
        binding.headers.addView(seachHeaderView.getView(getLayoutInflater()));
        return binding.getRoot();
    }


    @Override
    public void initView() {
        //首页banner方法（）
        initBanner();
        //首页飞机，商家，商城，油站方法（）
        initList();
        //秒杀产品方法();
        initseckill();
    }

    private void initseckill() {
        binding.homeRecyfour.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        List<News> newsList = new ArrayList();
        newsList.add(new News("新闻标题1", R.mipmap.home_oilimg));
        newsList.add(new News("新闻标题2", R.mipmap.home_bannerimg));
        newsList.add(new News("新闻标题3", R.mipmap.home_flayimg));
        newsList.add(new News("新闻标题4", R.mipmap.home_shoppiingimg));
        HomeSeckilAdapter adaptertwo = new HomeSeckilAdapter(newsList);
        binding.homeRecyfour.setAdapter(adaptertwo);
    }

    /*
        不可展示页面
     */
    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();
    }

    /**
     * 展示页面方法
     */
    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
    }

    private void initList() {
        //图片集合
        Integer[] integers = {R.mipmap.home_shoppiingimg, R.mipmap.home_shoppingimg, R.mipmap.home_flayimg, R.mipmap.home_oilimg};
        //适配器设置
        binding.homeRecyone.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        //适配器
        HomeListAdapter adapter = new HomeListAdapter(Arrays.asList(integers));
        binding.homeRecyone.setAdapter(adapter);
    }

    private void initBanner() {

    }

    @Override
    public void loadData() {

    }

    @Override
    public void initListener() {

    }
}