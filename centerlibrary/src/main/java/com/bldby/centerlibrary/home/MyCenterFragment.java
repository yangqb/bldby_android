package com.bldby.centerlibrary.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.baselibrary.constants.RouteCenterConstants;
import com.bldby.baselibrary.constants.RouteShopConstants;
import com.bldby.baselibrary.core.ui.basefragment.Basefragment;
import com.bldby.centerlibrary.databinding.FragmentCenterBinding;

/**
 * package name: com.bldby.centerlibrary.home
 * user: yangqinbo
 * date: 2020/6/19
 * time: 17:13
 * email: 694125155@qq.com
 */
@Route(path = RouteCenterConstants.CENTERMAIN)
public class MyCenterFragment extends Basefragment {
    private FragmentCenterBinding binding;
    @Autowired
    public String key;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCenterBinding.inflate(inflater, container, false);
        binding.setViewModel(this);
        return binding.getRoot();
    }

    @Override
    public void initView() {
        binding.content.setText(key);
    }

    @Override
    public void loadData() {

    }

    @Override
    public void initListener() {

    }
}
