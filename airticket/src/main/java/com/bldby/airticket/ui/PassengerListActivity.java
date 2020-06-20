package com.bldby.airticket.ui;

import android.content.Intent;
import android.graphics.Canvas;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.airticket.AirConstants;
import com.bldby.airticket.adapter.PassengerManagerAdapter;
import com.bldby.airticket.databinding.ActivityPassengerListBinding;
import com.bldby.airticket.model.CustomAirPriceDetailInfo;
import com.bldby.airticket.model.PassengerModel;
import com.bldby.airticket.request.DeletePassengerRequest;
import com.bldby.airticket.request.PassengerListRequest;
import com.bldby.baselibrary.constants.RouteAirConstants;
import com.bldby.baselibrary.core.network.ApiLifeCallBack;
import com.bldby.baselibrary.core.ui.baseactivity.BaseUiActivity;
import com.bldby.baselibrary.core.util.ToastUtil;
import com.bldby.loginlibrary.AccountManager;
import com.bldby.loginlibrary.model.UserInfo;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * package name: com.bldby.airticket.ui
 * user: yangqinbo
 * date: 2020/6/20
 * time: 15:26
 * email: 694125155@qq.com
 */
@Route(path = RouteAirConstants.AIRPASSENGERLIST)
public class PassengerListActivity extends BaseUiActivity {
    private ActivityPassengerListBinding binding;
    private PassengerManagerAdapter mAdapter;
    private List<PassengerModel> list = new ArrayList<>();
    private ArrayList<PassengerModel> selectPassenger = new ArrayList<>();
    private List<PassengerModel> locModelList = new ArrayList<>();
    private UserInfo userInfo;
    @Autowired
    public int airType;
    @Autowired
    public CustomAirPriceDetailInfo customPriceDetailInfo;

    @Override
    protected View initContentView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        binding = ActivityPassengerListBinding.inflate(layoutInflater, viewGroup, false);
        binding.setViewModel(this);
        return binding.getRoot();
    }

    @Override
    public void initView() {
        initTitle("选择乘机人");
        setRightText("确定");
        userInfo = AccountManager.getInstance().getUserInfo();
        mAdapter = new PassengerManagerAdapter(list);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ItemDragAndSwipeCallback itemDragAndSwipeCallback = new ItemDragAndSwipeCallback(mAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
        itemTouchHelper.attachToRecyclerView(binding.recyclerView);
        // 开启滑动删除
        mAdapter.enableSwipeItem();
        binding.recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClickRight(View view) {
        super.onClickRight(view);
        for (int i = 0; i < locModelList.size(); i++) {
            if (locModelList.get(i).isSelect) {
                selectPassenger.add(locModelList.get(i));
            }
        }
        if (selectPassenger.size() <= 0) {
            ToastUtil.show("请选择乘机人");
        } else {
            Intent data = new Intent();
            data.putParcelableArrayListExtra(AirConstants.SELECT_PASSENGER, selectPassenger);
            setResult(RESULT_OK, data);
            finish();
        }
    }

    @Override
    public void loadData() {
        PassengerListRequest request = new PassengerListRequest();
        request.accessToken = userInfo.loginInfo.accessToken;
        request.userId = userInfo.loginInfo.userId;
        request.call(new ApiLifeCallBack<List<PassengerModel>>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onFinsh() {

            }

            @Override
            public void onAPIResponse(List<PassengerModel> response) {
                locModelList.clear();
                if (response != null) {
                    locModelList.addAll(response);
                    list = response;
                    mAdapter.setNewData(list);
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onAPIError(int errorCode, String errorMsg) {

            }
        });
    }

    @Override
    public void initListener() {
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (airType == 2) {
                    if (locModelList.get(position).ageType == 1) {
                        ToastUtil.show("不支持儿童购买");
                    } else {
                        locModelList.get(position).isSelect = !locModelList.get(position).isSelect;
                    }
                } else if (airType == 1 || airType == 3) {
                    if (customPriceDetailInfo.customInterPriceInfo.cPrice == 0 && locModelList.get(position).ageType == 1) {
                        ToastUtil.show("不支持儿童购买");
                    } else {
                        locModelList.get(position).isSelect = !locModelList.get(position).isSelect;
                    }
                } else {
                    if (customPriceDetailInfo.customDocGoPriceInfo.businessExtMap != null) {
                        if (!customPriceDetailInfo.customDocGoPriceInfo.businessExtMap.supportChild && !customPriceDetailInfo.customDocGoPriceInfo.businessExtMap.supportChildBuyAdult) {
                            if (locModelList.get(position).ageType == 1) {
                                ToastUtil.show("不支持儿童购买");
                            } else {
                                locModelList.get(position).isSelect = !locModelList.get(position).isSelect;
                            }
                        } else {
                            locModelList.get(position).isSelect = !locModelList.get(position).isSelect;
                        }
                    } else {
                        if (locModelList.get(position).ageType == 1) {
                            ToastUtil.show("不支持儿童购买");
                        } else {
                            locModelList.get(position).isSelect = !locModelList.get(position).isSelect;
                        }
                    }
                }
                mAdapter.notifyItemChanged(position);

            }
        });

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startWith(RouteAirConstants.AIREDITPASSENGER).withParcelable("passengerModel", locModelList.get(position)).navigation(PassengerListActivity.this, AirConstants.REQUEST_EDIT_PASSENGER_CODE);
            }
        });

        mAdapter.setOnItemSwipeListener(new OnItemSwipeListener() {
            @Override
            public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {

            }

            @Override
            public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {

            }

            @Override
            public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {
                deletePassenger(locModelList.get(pos).id);
                locModelList.remove(pos);
            }

            @Override
            public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float dX, float dY, boolean isCurrentlyActive) {

            }
        });
    }

    /*
     * 新增乘机人信息
     * */
    public void addPassenger(View view) {
        startWith(RouteAirConstants.AIREDITPASSENGER).withInt("airType", airType)
                .withSerializable("customPlaneDetailInfo", customPriceDetailInfo)
                .navigation(PassengerListActivity.this, AirConstants.REQUEST_EDIT_PASSENGER_CODE);
    }

    /*
     * 删除乘机人
     * */
    public void deletePassenger(String id) {
        DeletePassengerRequest request = new DeletePassengerRequest();
        request.accessToken = userInfo.loginInfo.accessToken;
        request.userId = userInfo.loginInfo.userId;
        request.id = id;
        request.call(new ApiLifeCallBack<Object>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onFinsh() {

            }

            @Override
            public void onAPIResponse(Object response) {
                ToastUtil.show("删除成功");
            }

            @Override
            public void onAPIError(int errorCode, String errorMsg) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == AirConstants.REQUEST_EDIT_PASSENGER_CODE) {
                loadData();
            }
        }
    }
}
