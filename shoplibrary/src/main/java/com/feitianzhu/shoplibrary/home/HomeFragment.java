package com.feitianzhu.shoplibrary.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.NonNull;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.feitianzhu.baselibrary.constants.RouteShopConstants;
import com.feitianzhu.baselibrary.core.ui.basefragment.Basefragment;
import com.feitianzhu.shoplibrary.R;
import com.feitianzhu.shoplibrary.adapter.HomeListAdapter;
import com.feitianzhu.shoplibrary.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;


@Route(path = RouteShopConstants.SHOPMAINFirst)
public class HomeFragment extends Basefragment {

    @Autowired()
    public String key;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        binding.setViewModel(this);
        return binding.getRoot();
    }

    @Override
    public void initView() {
        //首页banner方法（）
        initBanner();
        //首页飞机，商家，商城，油站方法（）
        initList();
    }

    private void initList() {
        List<String> list=new ArrayList<>();
        list.add(R.string.home_shoppingmall,"");
        list.add(R.string.home_business,"");
        list.add(R.string.home_aircraft,"");
        list.add(R.string.home_servicestation,"");
        list.add(R.string.home_welfare,"");
        binding.homeRecyone.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        HomeListAdapter adapter=new HomeListAdapter(list);
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