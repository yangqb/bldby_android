package com.bldby.centerlibrary.address.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.baselibrary.constants.RouteCenterConstants;
import com.bldby.baselibrary.core.ui.baseactivity.BaseUiActivity;
import com.bldby.baselibrary.core.util.ToastUtil;
import com.bldby.centerlibrary.R;
import com.bldby.centerlibrary.address.adapter.AddressListAdapter;
import com.bldby.centerlibrary.databinding.ActivityAddressListBinding;
import com.bldby.centerlibrary.model.AddressInfo;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * package name: com.bldby.centerlibrary.address.ui
 * user: yangqinbo
 * date: 2020/6/20
 * time: 15:42
 * email: 694125155@qq.com
 */
@Route(path = RouteCenterConstants.CENTERADDRESSMANAGERMENT)
public class AddressManagementActivity extends BaseUiActivity {
    private ActivityAddressListBinding binding;
    private AddressListAdapter adapter;
    private List<AddressInfo.ShopAddressListBean> addressInfos = new ArrayList<>();
    @Autowired
    public boolean isSelect;

    @Override
    protected View initContentView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        binding = ActivityAddressListBinding.inflate(layoutInflater, viewGroup, false);
        binding.setViewModel(this);
        return binding.getRoot();
    }

    @Override
    public void initView() {
        initTitle(getString(R.string.address_list_title));
        setRightText(getString(R.string.address_list_add));
        for (int i = 0; i < 3; i++) {
            AddressInfo.ShopAddressListBean shopAddressListBean = new AddressInfo.ShopAddressListBean();
            shopAddressListBean.setAreaName("大大大");
            shopAddressListBean.setPhone("13100680321");
            shopAddressListBean.setDetailAddress("大大大");
            shopAddressListBean.setProvinceName("的简欧凹断");
            shopAddressListBean.setAreaName("东海岛");
            shopAddressListBean.setCityName("也会掉带哦");
            addressInfos.add(shopAddressListBean);
        }


        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AddressListAdapter(addressInfos);
        View mEmptyView = View.inflate(this, R.layout.view_common_nodata, null);
        ImageView img_empty = (ImageView) mEmptyView.findViewById(R.id.img_empty);
        img_empty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        adapter.setEmptyView(mEmptyView);
        adapter.getEmptyView().setVisibility(View.INVISIBLE);
        binding.recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        binding.refreshLayout.setEnableLoadMore(false);
    }

    @Override
    public void loadData() {

    }

    @Override
    public void initListener() {
        adapter.setOnDeleteListener(new AddressListAdapter.OnDeleteListener() {
            @Override
            public void delete(int position) {
                addressInfos.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
