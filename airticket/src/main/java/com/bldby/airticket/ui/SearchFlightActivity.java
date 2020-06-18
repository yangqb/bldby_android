package com.bldby.airticket.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.airticket.R;
import com.bldby.airticket.adapter.SearchPlaneResultAdapter;
import com.bldby.airticket.databinding.ActivitySearchFlightBinding;
import com.bldby.airticket.model.CustomFightCityInfo;
import com.bldby.airticket.model.MultipleGoSearchFightInfo;
import com.bldby.airticket.model.SearchFlightModel;
import com.bldby.airticket.model.SearchInternationalFlightModel;
import com.bldby.airticket.request.DomesticSearchRequest;
import com.bldby.baselibrary.constants.RouteAirConstants;
import com.bldby.baselibrary.core.network.ApiCallBack;
import com.bldby.baselibrary.core.network.ApiLifeCallBack;
import com.bldby.baselibrary.core.ui.baseactivity.BaseAirUiActivity;
import com.bldby.baselibrary.core.ui.baseactivity.BaseUiActivity;

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
        /*View mEmptyView = View.inflate(this, R.layout.view_common_nodata, null);
        ImageView img_empty = (ImageView) mEmptyView.findViewById(R.id.img_empty);
        TextView noData = mEmptyView.findViewById(R.id.no_data);
        noData.setText("当前搜索无直飞航线");
        img_empty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
        //mAdapter.setEmptyView(mEmptyView);
        //mAdapter.getEmptyView().setVisibility(View.INVISIBLE);
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
                    showloadDialog("");
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

                    //mAdapter.getEmptyView().setVisibility(View.VISIBLE);
                }

                @Override
                public void onAPIError(int errorCode, String errorMsg) {

                }
            });
        } else if (searchType == 1) {//国际单程

        }

    }

    @Override
    public void initListener() {

    }
}
