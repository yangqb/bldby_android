package com.bldby.airticket.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.airticket.R;
import com.bldby.airticket.adapter.AirOrderAdapter;
import com.bldby.airticket.databinding.ActivityAirOrderBinding;
import com.bldby.airticket.model.AirOrderInfo;
import com.bldby.airticket.model.PlaneOrderTableEntity;
import com.bldby.airticket.request.AirOrderListRequest;
import com.bldby.baselibrary.constants.RouteAirConstants;
import com.bldby.baselibrary.core.network.ApiLifeCallBack;
import com.bldby.baselibrary.core.ui.baseactivity.BaseUiActivity;
import com.bldby.loginlibrary.AccountManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * package name: com.bldby.airticket.ui
 * user: yangqinbo
 * date: 2020/6/22
 * time: 13:57
 * email: 694125155@qq.com
 */
@Route(path = RouteAirConstants.AIRORDER)
public class AirOrderListActivity extends BaseUiActivity {
    private ArrayList<CustomTabEntity> tabs = new ArrayList<>();
    private ActivityAirOrderBinding binding;
    private AirOrderAdapter mAdapter;
    private int btnType;
    private List<AirOrderInfo.AirOrderModel> currOrder = new ArrayList<>();
    private List<AirOrderInfo.AirOrderModel> allOrder = new ArrayList<>();
    private List<AirOrderInfo.AirOrderModel> refundOrUpdateList = new ArrayList<>();
    private List<AirOrderInfo.AirOrderModel> waiPayList = new ArrayList<>();
    private List<AirOrderInfo.AirOrderModel> waitTicketedList = new ArrayList<>();
    private List<AirOrderInfo.AirOrderModel> ticketedList = new ArrayList<>();

    @Override
    protected View initContentView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        binding = ActivityAirOrderBinding.inflate(layoutInflater, viewGroup, false);
        binding.setViewModel(this);
        return binding.getRoot();
    }

    @Override
    public void initView() {
        initTitle("机票订单");
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new AirOrderAdapter(currOrder);
        View mEmptyView = View.inflate(this, R.layout.layout_air_empty_view, null);
        ImageView img_empty = (ImageView) mEmptyView.findViewById(R.id.img_empty);
        TextView tvNoData = mEmptyView.findViewById(R.id.no_data);
        tvNoData.setText("没有相关订单，下拉刷新试试");
        img_empty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mAdapter.setEmptyView(mEmptyView);
        mAdapter.getEmptyView().setVisibility(View.INVISIBLE);
        binding.recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        tabs.add(new PlaneOrderTableEntity("全部"));
        tabs.add(new PlaneOrderTableEntity("待付款"));
        tabs.add(new PlaneOrderTableEntity("待出票"));
        tabs.add(new PlaneOrderTableEntity("已出票"));
        tabs.add(new PlaneOrderTableEntity("退改单"));
        binding.tabLayout.setTabData(tabs);
        binding.refreshLayout.setEnableLoadMore(false);
    }

    @Override
    public void loadData() {
        AirOrderListRequest request = new AirOrderListRequest();
        request.userId = AccountManager.getInstance().getUserId();
        request.isShowLoading = true;
        request.call(new ApiLifeCallBack<AirOrderInfo>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onFinsh() {
                binding.refreshLayout.finishRefresh();
            }

            @Override
            public void onAPIResponse(AirOrderInfo response) {
                if (currOrder != null) {
                    currOrder.clear();
                }
                if (response != null) {
                    tabs.clear();
                    tabs.add(new PlaneOrderTableEntity("全部"));
                    tabs.add(new PlaneOrderTableEntity("待付款(" + response.waiPayCount + ")"));
                    tabs.add(new PlaneOrderTableEntity("待出票(" + response.waitTicketedCount + ")"));
                    tabs.add(new PlaneOrderTableEntity("已出票(" + response.ticketedCount + ")"));
                    tabs.add(new PlaneOrderTableEntity("退改单(" + response.refundOrUpdateCount + ")"));
                    binding.tabLayout.setTabData(tabs);
                    if (response.all != null) {
                        allOrder = response.all;
                    }
                    if (response.refundOrUpdateList != null) {
                        refundOrUpdateList = response.refundOrUpdateList;
                    }

                    if (response.ticketedList != null) {
                        ticketedList = response.ticketedList;
                    }

                    if (response.waiPayList != null) {
                        waiPayList = response.waiPayList;
                    }

                    if (response.waitTicketedList != null) {
                        waitTicketedList = response.waitTicketedList;
                    }
                    if (btnType == 0) {
                        currOrder = allOrder;
                    } else if (btnType == 1) {
                        currOrder = waiPayList;
                    } else if (btnType == 2) {
                        currOrder = waitTicketedList;
                    } else if (btnType == 3) {
                        currOrder = ticketedList;
                    } else {
                        currOrder = refundOrUpdateList;
                    }
                    if (currOrder != null) {
                        mAdapter.setNewData(currOrder);
                        mAdapter.notifyDataSetChanged();
                    }
                }
                mAdapter.getEmptyView().setVisibility(View.VISIBLE);
            }

            @Override
            public void onAPIError(int errorCode, String errorMsg) {

            }
        });
    }

    @Override
    public void initListener() {
        binding.tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (position == 0) {
                    btnType = 0;
                    currOrder = allOrder;
                } else if (position == 1) {
                    btnType = 1;
                    currOrder = waiPayList;
                } else if (position == 2) {
                    btnType = 2;
                    currOrder = waitTicketedList;
                } else if (position == 3) {
                    btnType = 3;
                    currOrder = ticketedList;
                } else if (position == 4) {
                    btnType = 4;
                    currOrder = refundOrUpdateList;
                }
                mAdapter.setNewData(currOrder);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startWith(RouteAirConstants.AIRORDERDETAIL).withSerializable("airOrderModel", currOrder.get(position)).navigation();
            }
        });

        binding.refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                loadData();
            }
        });
    }
}
