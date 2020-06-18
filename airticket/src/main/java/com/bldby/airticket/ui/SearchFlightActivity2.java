package com.bldby.airticket.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.airticket.R;
import com.bldby.airticket.databinding.ActivitySearchFlight2Binding;
import com.bldby.airticket.model.CustomFightCityInfo;
import com.bldby.baselibrary.constants.RouteAirConstants;
import com.bldby.baselibrary.core.ui.baseactivity.BaseAirUiActivity;

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
    }

    @Override
    public void loadData() {

    }

    @Override
    public void initListener() {

    }
}
