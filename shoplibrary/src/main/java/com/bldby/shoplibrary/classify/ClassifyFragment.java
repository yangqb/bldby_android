package com.bldby.shoplibrary.classify;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.baselibrary.constants.RouteLoginConstants;
import com.bldby.baselibrary.constants.RouteShopConstants;
import com.bldby.baselibrary.core.network.ApiCallBack;
import com.bldby.baselibrary.core.ui.basefragment.Basefragment;
import com.bldby.loginlibrary.AccountManager;
import com.bldby.loginlibrary.model.UserInfo;
import com.bldby.shoplibrary.classify.adapter.ClassLeftGoodsAdapter;

import com.bldby.shoplibrary.classify.adapter.ClassRightAdapter;
import com.bldby.shoplibrary.classify.bean.ClassLeftGoodsBean;
import com.bldby.shoplibrary.classify.bean.ClassRightGoodsBean;
import com.bldby.shoplibrary.classify.request.ClassGoodsLeftRequset;
import com.bldby.shoplibrary.classify.request.ClassRightGoodsRequest;
import com.bldby.shoplibrary.databinding.FragmentClassifyBinding;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;

import java.util.List;

@Route(path = RouteShopConstants.SHOPMAINCLASSIFY)
public class ClassifyFragment extends Basefragment {
    @Autowired(name = "key")
    public String key;

    private FragmentClassifyBinding binding;
    private int id;
    private ClassLeftGoodsAdapter adapterleft;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentClassifyBinding.inflate(inflater, container, false);
        binding.setViewModel(this);
        return binding.getRoot();
    }

    @Override
    public void initView() {
        //left适配器方法（）
        initleft();
        initrighttop(id);
    }




    private void initleft() {
        binding.classLeft.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        ClassGoodsLeftRequset requset=new ClassGoodsLeftRequset();
        requset.call(new ApiCallBack<List<ClassLeftGoodsBean>>() {
            @Override
            public void onAPIResponse(List<ClassLeftGoodsBean>  response) {
                adapterleft = new ClassLeftGoodsAdapter(response);
                binding.classLeft.setAdapter(adapterleft);
                id = response.get(0).getId();
                initrighttop(id);
            }

            @Override
            public void onAPIError(int errorCode, String errorMsg) {

            }
        });
    /*    adapterleft.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });*/

    }
    private void initrighttop(int id) {
        ClassRightGoodsRequest rightGoodsRequest=new ClassRightGoodsRequest(1);
        rightGoodsRequest.call(new ApiCallBack<ClassRightGoodsBean>() {
            @Override
            public void onAPIResponse(ClassRightGoodsBean response) {
                binding.classRightTop.setLayoutManager(new GridLayoutManager(getActivity(),3));
                List<ClassRightGoodsBean.CategoryListBean> categoryList = response.getCategoryList();
                Log.i("ccccccc", "onAPIResponse: "+response.getCategoryList().get(1).getName());
                ClassRightAdapter topadapter=new ClassRightAdapter(categoryList);
                binding.classRightTop.setAdapter(topadapter);
            }

            @Override
            public void onAPIError(int errorCode, String errorMsg) {

            }
        });
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
    }

    @Override
    public void loadData() {

    }

    @Override
    public void initListener() {

    }
}
