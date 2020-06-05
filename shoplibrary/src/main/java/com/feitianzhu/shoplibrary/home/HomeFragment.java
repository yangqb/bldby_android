package com.feitianzhu.shoplibrary.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.feitianzhu.baselibrary.app.RouteConstants;
import com.feitianzhu.baselibrary.core.view.basefragment.Basefragment;
import com.feitianzhu.shoplibrary.databinding.FragmentHomeBinding;


@Route(path = RouteConstants.SHOPMAINFirst)
public class HomeFragment extends Basefragment {

    @Autowired()
    public String key1;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        binding.setViewModel(this);
        return binding.getRoot();
    }

    @Override
    public void initView() {

    }

    @Override
    public void loadData() {
        binding.textView.setText(key1);
    }

    @Override
    public void initListener() {

    }
}