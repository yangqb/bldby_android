package com.bldby.airticket.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.airticket.databinding.ActivityAirFlightDetailBinding;
import com.bldby.airticket.model.CustomFightCityInfo;
import com.bldby.airticket.model.MultiGoBackFlightInfo;
import com.bldby.airticket.model.MultipleGoSearchFightInfo;
import com.bldby.baselibrary.constants.RouteAirConstants;
import com.bldby.baselibrary.core.ui.baseactivity.BaseAirUiActivity;

/**
 * package name: com.bldby.airticket.ui
 * user: yangqinbo
 * date: 2020/6/18
 * time: 17:04
 * email: 694125155@qq.com
 */

/*
 * 航班详情
 * */
@Route(path = RouteAirConstants.AIRFLIGHTDETAIL)
public class AirFlightDetailActivity extends BaseAirUiActivity {
    private ActivityAirFlightDetailBinding binding;
    @Autowired
    public int searchType;
    @Autowired
    public CustomFightCityInfo flightInfo;
    @Autowired
    public MultipleGoSearchFightInfo goSearchFightInfo;
    @Autowired
    public MultiGoBackFlightInfo goBackSearchFlightInfo;

    @Override
    protected View initContentView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        binding = ActivityAirFlightDetailBinding.inflate(layoutInflater, viewGroup, false);
        binding.setViewModel(this);
        return binding.getRoot();
    }

    @Override
    public void initView() {

    }

    @Override
    public void loadData() {

    }

    @Override
    public void initListener() {

    }
}
