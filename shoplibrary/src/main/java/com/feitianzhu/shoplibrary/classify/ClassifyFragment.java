package com.feitianzhu.shoplibrary.classify;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.feitianzhu.baselibrary.constants.RouteShopConstants;
import com.feitianzhu.baselibrary.core.view.basefragment.Basefragment;
import com.feitianzhu.shoplibrary.databinding.FragmentClassifyBinding;

@Route(path = RouteShopConstants.SHOPMAINCLASSIFY)
public class ClassifyFragment extends Basefragment {
    @Autowired(name = "key1")
    public String data;

    private FragmentClassifyBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentClassifyBinding.inflate(inflater, container, false);
        binding.setViewModel(this);
        return binding.getRoot();
    }

    @Override
    public void initView() {

    }

    @Override
    public void loadData() {
        binding.textView.setText(data);

    }

    @Override
    public void initListener() {

    }
}
