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
import com.bldby.airticket.adapter.SearchPlaneResultAdapter2;
import com.bldby.airticket.databinding.ActivitySearchFlight2Binding;
import com.bldby.airticket.model.CustomFightCityInfo;
import com.bldby.airticket.model.GoBackFlightInfo;
import com.bldby.airticket.model.GoBackFlightList;
import com.bldby.airticket.model.MultiGoBackFlightInfo;
import com.bldby.airticket.model.SearchInternationalFlightModel;
import com.bldby.airticket.request.DomesticGoBackSearchRequest;
import com.bldby.airticket.request.InternationGoBackSearchRequest;
import com.bldby.baselibrary.constants.RouteAirConstants;
import com.bldby.baselibrary.core.network.ApiLifeCallBack;
import com.bldby.baselibrary.core.ui.baseactivity.BaseAirUiActivity;
import com.bldby.baselibrary.core.util.DateUtil;
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
 * time: 19:10
 * email: 694125155@qq.com
 */
@Route(path = RouteAirConstants.AIRSEARCHFLIGHT2)
public class SearchFlightActivity2 extends BaseAirUiActivity {
    private ActivitySearchFlight2Binding binding;
    private SearchPlaneResultAdapter2 mAdapter;
    private List<MultiGoBackFlightInfo> goBackFlightList = new ArrayList<>();
    private int sortType = 1;
    @Autowired
    public int searchType;
    @Autowired
    public CustomFightCityInfo flightInfo;

    @Override
    protected View initContentView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        binding = ActivitySearchFlight2Binding.inflate(layoutInflater, viewGroup, false);
        binding.setViewModel(this);
        return binding.getRoot();
    }

    @Override
    public void initView() {
        initTitle(flightInfo.depCityName, flightInfo.arrCityName);
        setTitleBackground(R.color.color_ffffff);
        setCenterImg(R.mipmap.air_search_goback_arrow);
        initTitle(flightInfo.depCityName, flightInfo.arrCityName);
        binding.goCityTitle.setText(flightInfo.depCityName + "-" + flightInfo.arrCityName);
        binding.backCityTitle.setText(flightInfo.arrCityName + "-" + flightInfo.depCityName);
        binding.depDate.setText(DateUtil.strToStr(flightInfo.goDate) + DateUtil.strToWeek("yyyy-MM-dd", flightInfo.goDate));
        binding.arrDate.setText(DateUtil.strToStr(flightInfo.backDate) + DateUtil.strToWeek("yyyy-MM-dd", flightInfo.backDate));
        mAdapter = new SearchPlaneResultAdapter2(goBackFlightList);
        View mEmptyView = View.inflate(this, R.layout.layout_air_empty_view, null);
        ImageView img_empty = (ImageView) mEmptyView.findViewById(R.id.img_empty);
        TextView noData = mEmptyView.findViewById(R.id.no_data);
        noData.setText("当前搜索直飞无航线");
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
        if (searchType == 2) { //国内往返
            DomesticGoBackSearchRequest request = new DomesticGoBackSearchRequest();
            request.depCity = flightInfo.depAirPortCode;
            request.arrCity = flightInfo.arrAirPortCode;
            request.goDate = flightInfo.goDate;
            request.backDate = flightInfo.backDate;
            request.exTrack = "retehui";
            request.sort = sortType;
            request.call(new ApiLifeCallBack<GoBackFlightInfo>() {
                @Override
                public void onStart() {
                    showloadDialog("");
                }

                @Override
                public void onFinsh() {
                    goneloadDialog();
                    binding.refreshLayout.finishRefresh();
                }

                @Override
                public void onAPIResponse(GoBackFlightInfo response) {
                    if (response != null) {
                        goBackFlightList.clear();
                        List<GoBackFlightList> flightList = response.flightList;
                        if (flightList != null && flightList.size() > 0) {
                            for (int i = 0; i < flightList.size(); i++) {
                                MultiGoBackFlightInfo multiGoFlightInfo = new MultiGoBackFlightInfo(MultiGoBackFlightInfo.DOMESTIC);
                                multiGoFlightInfo.domesticFlight = flightList.get(i);
                                goBackFlightList.add(multiGoFlightInfo);
                            }
                            mAdapter.setNewData(goBackFlightList);
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                    mAdapter.getEmptyView().setVisibility(View.VISIBLE);
                }

                @Override
                public void onAPIError(int errorCode, String errorMsg) {

                }
            });

        } else {//国际往返
            InternationGoBackSearchRequest request = new InternationGoBackSearchRequest();
            request.depCity = "PAR";
            request.arrCity = "BER";
            request.depDate = flightInfo.goDate;
            request.retDate = flightInfo.backDate;
            request.source = "ICP_SELECT_open.3724";
            request.call(new ApiLifeCallBack<List<SearchInternationalFlightModel>>() {
                @Override
                public void onStart() {
                    showloadDialog("");
                }

                @Override
                public void onFinsh() {
                    goneloadDialog();
                    binding.refreshLayout.finishRefresh();
                }

                @Override
                public void onAPIResponse(List<SearchInternationalFlightModel> response) {
                    if (response != null) {
                        goBackFlightList.clear();
                        if (response.size() > 0) {
                            for (int i = 0; i < response.size(); i++) {
                                MultiGoBackFlightInfo multiGoFlightInfo = new MultiGoBackFlightInfo(MultiGoBackFlightInfo.INTERNATIONAL);
                                multiGoFlightInfo.internationalFlight = response.get(i);
                                goBackFlightList.add(multiGoFlightInfo);
                            }
                            mAdapter.setNewData(goBackFlightList);
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

    }


    public void onClickBtn(View view) {
        if(view.getId() == R.id.depDate || view.getId()==R.id.arrDate){
            startWith(RouteAirConstants.SELECTDATE)
                    .withString("goDate", flightInfo.goDate)
                    .withString("comeDate", flightInfo.backDate)
                    .withInt("searchType", searchType)
                    .navigation(SearchFlightActivity2.this, AirConstants.REQUEST_DATE__CODE);
        }else if(view.getId() == R.id.sortPrice){
            binding.tvTimeTitle.setTextColor(getResources().getColor(R.color.color_333333));
            binding.line2.setBackgroundColor(getResources().getColor(R.color.white));
            binding.tvPriceTitle.setTextColor(getResources().getColor(R.color.color_ff8300));
            binding.line1.setBackgroundColor(getResources().getColor(R.color.color_ff8300));
            sortType = 1;
            loadData();
        }else if(view.getId() == R.id.sortTime){
            binding.tvTimeTitle.setTextColor(getResources().getColor(R.color.color_ff8300));
            binding.line2.setBackgroundColor(getResources().getColor(R.color.color_ff8300));
            binding.tvPriceTitle.setTextColor(getResources().getColor(R.color.color_333333));
            binding.line1.setBackgroundColor(getResources().getColor(R.color.white));
            sortType = 2;
            loadData();
        }
    }

    @Override
    public void initListener() {
        binding.refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                loadData();
            }
        });

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startWith(RouteAirConstants.AIRFLIGHTDETAIL)
                        .withInt("searchType", searchType)
                        .withSerializable("goBackSearchFlightInfo", goBackFlightList.get(position))
                        .withSerializable("flightInfo", flightInfo).navigation();
            }
        });

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                /*UserInfo userInfo = AccountManager.getInstance().getUserInfo();
                Intent intent = new Intent(SearchFlightActivity2.this, VipActivity.class);
                intent.putExtra(VipActivity.MINE_INFO, userInfo);
                startActivity(intent);*/
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == AirConstants.REQUEST_DATE__CODE) {
                flightInfo.goDate = data.getStringExtra(AirConstants.SELECT_DATE).split("=")[0];
                flightInfo.backDate = data.getStringExtra(AirConstants.SELECT_DATE).split("=")[1];
                binding.depDate.setText(DateUtil.strToStr(flightInfo.goDate) + DateUtil.strToWeek("yyyy-MM-dd", flightInfo.goDate));
                binding.arrDate.setText(DateUtil.strToStr(flightInfo.backDate) + DateUtil.strToWeek("yyyy-MM-dd", flightInfo.backDate));
                loadData();
            }
        }
    }
}
