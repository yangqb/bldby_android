package com.bldby.shoplibrary.classify;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.baselibrary.constants.RouteShopConstants;
import com.bldby.baselibrary.core.ui.basefragment.Basefragment;
import com.bldby.shoplibrary.databinding.FragmentClassifyBinding;

@Route(path = RouteShopConstants.SHOPMAINCLASSIFY)
public class ClassifyFragment extends Basefragment {
    @Autowired(name = "key")
    public String key;

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
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void loadData() {
        binding.textView.setText(key);

    }

    @Override
    public void initListener() {

    }
}
