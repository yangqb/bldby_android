package com.bldby.shoplibrary.classify;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SectionIndexer;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.bldby.baselibrary.constants.RouteShopConstants;
import com.bldby.baselibrary.core.ui.basefragment.Basefragment;
import com.bldby.shoplibrary.R;
import com.bldby.shoplibrary.classify.adapter.ClassLeftGoodsAdapter;

import com.bldby.shoplibrary.classify.adapter.SimpleArrayAdapter;
import com.bldby.shoplibrary.classify.base.BaseScrollableContainer;
import com.bldby.shoplibrary.classify.content.RecyclerViewContentContainer;
import com.bldby.shoplibrary.classify.tab.ListViewTabContainer;
import com.bldby.shoplibrary.classify.widget.RealSectionIndexer;
import com.bldby.shoplibrary.databinding.FragmentClassifyBinding;

import java.util.Arrays;
import java.util.List;

@Route(path = RouteShopConstants.SHOPMAINCLASSIFY)
public class ClassifyFragment extends Basefragment {
    @Autowired(name = "key")
    public String key;

    private FragmentClassifyBinding binding;
    private int id;
    private ClassLeftGoodsAdapter adapterleft;

    private BaseScrollableContainer mTabContainer;      // 左边的 Tab 页
    private BaseScrollableContainer mContentContainer;  // 右边的 content 页

    List<Integer> mData = Stream.iterate(0, item -> item+1)
            .limit(500)
            .collect(Collectors.toList());

    private SectionIndexer mSectionIndexer = new RealSectionIndexer(mData);

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
        //initleft();
      // initrighttop(1);


        initTabContainer();
        initContentContainer();
        initLinkedLayout();
    }

    private void initTabContainer() {
        ListView mListView = new ListView(getActivity());
        mListView.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.item_common,
                Arrays.asList(mSectionIndexer.getSections())
        ));

        mTabContainer = new ListViewTabContainer(getActivity(), mListView);
    }

    private void initContentContainer() {
        RecyclerView mRecyclerView = new RecyclerView(getActivity());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(new SimpleArrayAdapter<>(getActivity(), mData, mSectionIndexer));

        mContentContainer = new RecyclerViewContentContainer(getActivity(), mRecyclerView);
    }

    private void initLinkedLayout() {
        binding.linkedLayout.setContainers(mTabContainer, mContentContainer);
        binding.linkedLayout.setSectionIndexer(mSectionIndexer);
    }



  /*  private void initleft() {
        binding.classLeft.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        ClassGoodsLeftRequset requset=new ClassGoodsLeftRequset();
        requset.call(new ApiCallBack<List<ClassLeftGoodsBean>>() {
            @Override
            public void onAPIResponse(List<ClassLeftGoodsBean>  response) {
                adapterleft = new ClassLeftGoodsAdapter(response);
                binding.classLeft.setAdapter(adapterleft);
                id = response.get(0).getId();
               // initrighttop(id);
            }

            @Override
            public void onAPIError(int errorCode, String errorMsg) {

            }
        });
    *//*    adapterleft.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });*//*

    }*/
/*    private void initrighttop(int id) {
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
    }*/

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
