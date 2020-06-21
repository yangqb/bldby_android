package com.bldby.airticket.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.airticket.AirConstants;
import com.bldby.airticket.R;
import com.bldby.airticket.adapter.SearchPlaneResultAdapter;
import com.bldby.airticket.databinding.ActivitySearchFlightBinding;
import com.bldby.airticket.model.CustomFightCityInfo;
import com.bldby.airticket.model.MultipleGoSearchFightInfo;
import com.bldby.airticket.model.SearchFlightModel;
import com.bldby.airticket.model.SearchInternationalFlightModel;
import com.bldby.airticket.request.DomesticSearchRequest;
import com.bldby.airticket.request.InternationSearchRequest;
import com.bldby.baselibrary.constants.RouteAirConstants;
import com.bldby.baselibrary.core.network.ApiCallBack;
import com.bldby.baselibrary.core.network.ApiLifeCallBack;
import com.bldby.baselibrary.core.ui.baseactivity.BaseAirUiActivity;
import com.bldby.baselibrary.core.ui.baseactivity.BaseUiActivity;
import com.bldby.loginlibrary.AccountManager;
import com.bldby.loginlibrary.model.UserInfo;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * package name: com.bldby.airticket.ui
 * user: yangqinbo
 * date: 2020/6/17
 * time: 19:09
 * email: 694125155@qq.com
 */
@Route(path = RouteAirConstants.AIRSEARCHFLIGHT)
public class SearchFlightActivity extends BaseAirUiActivity {
    private ActivitySearchFlightBinding binding;
    private List<MultipleGoSearchFightInfo> goSearchFightInfoList = new ArrayList<>();
    private List<SearchFlightModel.FlightModel> flightInfos = new ArrayList<>();
    private List<SearchInternationalFlightModel> internationalFlightModels = new ArrayList<>();
    private SearchPlaneResultAdapter mAdapter;
    @Autowired
    public int searchType;
    @Autowired
    public CustomFightCityInfo flightInfo;

    @Override
    protected View initContentView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        binding = ActivitySearchFlightBinding.inflate(layoutInflater, viewGroup, false);
        binding.setViewModel(this);
        return binding.getRoot();
    }

    @Override
    public void initView() {
        initTitle(flightInfo.depCityName, flightInfo.arrCityName);
        setTitleBackground(R.color.color_ffffff);
        setVisibleRightImg(true);
        setCenterImg(R.mipmap.air_one_way_arrow);

        mAdapter = new SearchPlaneResultAdapter(goSearchFightInfoList);
        View mEmptyView = View.inflate(this, R.layout.layout_air_empty_view, null);
        ImageView img_empty = (ImageView) mEmptyView.findViewById(R.id.img_empty);
        TextView noData = mEmptyView.findViewById(R.id.no_data);
        noData.setText("当前搜索无直飞航线");
        img_empty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mAdapter.setEmptyView(mEmptyView);
        mAdapter.getEmptyView().setVisibility(View.INVISIBLE);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        binding.refreshLayout.setEnableLoadMore(false);
    }

    @Override
    public void loadData() {
        if (searchType == 0) { //国内单程
            DomesticSearchRequest request = new DomesticSearchRequest();
            request.dpt = flightInfo.depAirPortCode;
            request.arr = flightInfo.arrAirPortCode;
            request.date = flightInfo.goDate;
            request.ex_track = "tehui";
            request.call(new ApiLifeCallBack<SearchFlightModel>() {
                @Override
                public void onStart() {
                    showloadDialog();
                }

                @Override
                public void onFinsh() {
                    goneloadDialog();
                    binding.refreshLayout.finishRefresh();
                }

                @Override
                public void onAPIResponse(SearchFlightModel response) {
                    if (response != null && response.flightInfos != null) {
                        flightInfos = response.flightInfos;
                        goSearchFightInfoList.clear();
                        for (int i = 0; i < flightInfos.size(); i++) {
                            MultipleGoSearchFightInfo goSearchFightInfo = new MultipleGoSearchFightInfo(MultipleGoSearchFightInfo.DOMESTIC_TYPE);
                            goSearchFightInfo.flightModel = flightInfos.get(i);
                            goSearchFightInfoList.add(goSearchFightInfo);
                        }
                        mAdapter.setNewData(goSearchFightInfoList);
                        mAdapter.notifyDataSetChanged();
                    }
                    mAdapter.getEmptyView().setVisibility(View.VISIBLE);
                }

                @Override
                public void onAPIError(int errorCode, String errorMsg) {

                }
            });
        } else if (searchType == 1) {//国际单程
            InternationSearchRequest request = new InternationSearchRequest();
            request.depCity = "PAR";
            request.arrCity = "BER";
            request.depDate = flightInfo.goDate;
            request.source = "ICP_SELECT_open.3724";
            request.call(new ApiLifeCallBack<List<SearchInternationalFlightModel>>() {
                @Override
                public void onStart() {
                    showloadDialog();
                }

                @Override
                public void onFinsh() {
                    goneloadDialog();
                    binding.refreshLayout.finishRefresh();
                }

                @Override
                public void onAPIResponse(List<SearchInternationalFlightModel> response) {
                    if (response != null) {
                        goSearchFightInfoList.clear();
                        internationalFlightModels = response;
                        for (int i = 0; i < internationalFlightModels.size(); i++) {
                            MultipleGoSearchFightInfo goSearchFightInfo = new MultipleGoSearchFightInfo(MultipleGoSearchFightInfo.INTERNATIONAL_TYPE);
                            goSearchFightInfo.internationalFlightModel = internationalFlightModels.get(i);
                            goSearchFightInfoList.add(goSearchFightInfo);
                        }
                        mAdapter.setNewData(goSearchFightInfoList);
                        mAdapter.notifyDataSetChanged();
                    }
                    mAdapter.getEmptyView().setVisibility(View.VISIBLE);
                }

                @Override
                public void onAPIError(int errorCode, String errorMsg) {

                }
            });
        }

    }

    @Override
    public void onClickRightBtn(View view) {
        super.onClickRightBtn(view);
        startWith(RouteAirConstants.SELECTDATE)
                .withString("goDate", flightInfo.goDate)
                .withInt("searchType", searchType)
                .navigation(SearchFlightActivity.this, AirConstants.REQUEST_DATE__CODE);
    }

    @Override
    public void initListener() {
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startWith(RouteAirConstants.AIRFLIGHTDETAIL)
                        .withInt("searchType", searchType)
                        .withSerializable("goSearchFightInfo", goSearchFightInfoList.get(position))
                        .withSerializable("flightInfo", flightInfo).navigation();
            }
        });

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                /*UserInfo userInfo = AccountManager.getInstance().getUserInfo();
                Intent intent = new Intent(SearchPlanActivity.this, VipActivity.class);
                intent.putExtra(VipActivity.MINE_INFO, userInfo);
                startActivity(intent);*/
            }
        });

        binding.refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                loadData();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == AirConstants.REQUEST_DATE__CODE) {
                flightInfo.goDate = data.getStringExtra(AirConstants.SELECT_DATE).split("=")[0];
                loadData();
            }
        }
    }
}
