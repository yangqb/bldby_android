package com.bldby.airticket.ui;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.airticket.R;
import com.bldby.airticket.adapter.PlaneDetailAdapter;
import com.bldby.airticket.adapter.TransitAdapter;
import com.bldby.airticket.databinding.ActivityAirFlightDetailBinding;
import com.bldby.airticket.model.CustomFightCityInfo;
import com.bldby.airticket.model.FlightSegmentInfo;
import com.bldby.airticket.model.MultiGoBackFlightInfo;
import com.bldby.airticket.model.MultiPriceInfo;
import com.bldby.airticket.model.MultipleGoSearchFightInfo;
import com.bldby.airticket.model.SearchFlightModel;
import com.bldby.baselibrary.constants.RouteAirConstants;
import com.bldby.baselibrary.core.ui.baseactivity.BaseAirUiActivity;
import com.bldby.baselibrary.core.util.DateUtil;
import com.lxj.xpopup.XPopup;

import java.util.ArrayList;
import java.util.List;

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
    private List<FlightSegmentInfo> flightSegmentInfo;
    private SearchFlightModel.FlightModel searchFlightModel;
    private TransitAdapter transitAdapter;
    private PlaneDetailAdapter mAdapter;
    private List<MultiPriceInfo> multiPriceInfos = new ArrayList<>();
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
        setTitleBackground(R.color.white);
        initTitle(flightInfo.depCityName, flightInfo.arrCityName);
        binding.refreshLayout.setEnableLoadMore(false);

        if (searchType == 0 || searchType == 1) {
            //单程
            setCenterImg(R.mipmap.air_one_way_arrow);
        } else {
            //往返
            setCenterImg(R.mipmap.air_search_goback_arrow);
        }

        if (searchType == 0) {
            searchFlightModel = goSearchFightInfo.flightModel;
            //国内单程直达
            binding.layoutTop1.getRoot().setVisibility(View.VISIBLE);
            binding.layoutTop2.getRoot().setVisibility(View.GONE);
            binding.layoutTop1.oneWayCrossDays.setVisibility(View.GONE);
            binding.transitRecyclerView.setVisibility(View.GONE);
            binding.promptContent.setText("【机场提示】该航班到达机场为北京大兴国际机场，距市区约46公里，搭乘地铁到市区约30分钟。");
            binding.layoutTop1.oneWayStartDate.setText(flightInfo.goDate + DateUtil.strToWeek("yyyy-MM-dd", flightInfo.goDate));
            //oneWayEndDate.setText(customFightCityInfo.goDate + DateUtils.strToDate2(customFightCityInfo.goDate));
            binding.layoutTop1.oneWayEndDate.setVisibility(View.INVISIBLE);
            binding.layoutTop1.oneWayBTime.setText(searchFlightModel.dptTime);
            binding.layoutTop1.oneWayETime.setText(searchFlightModel.arrTime);
            binding.layoutTop1.oneWayDepAirport.setText(searchFlightModel.dptAirport + searchFlightModel.dptTerminal);
            binding.layoutTop1.oneWayArrAirport.setText(searchFlightModel.arrAirport + searchFlightModel.arrTerminal);
            binding.layoutTop1.oneWayFlightTimes.setText(searchFlightModel.flightTimes);
          /*  //国内中转
            layoutTop2.setVisibility(View.VISIBLE);
            btnTitle1.setText("一程");
            btnTitle2.setText("二程");
            String str1 = "如遇其中一程航班调整，您需自行办理另一程退改事宜并承担相应费用。请阅读";
            String str2 = "中转预定须知";
            setSpannableString(str1, str2, type);*/
        } else if (searchType == 1) {
            flightSegmentInfo = goSearchFightInfo.internationalFlightModel.goTrip.flightSegments;
            if (goSearchFightInfo.internationalFlightModel.goTrip.transitCities != null && goSearchFightInfo.internationalFlightModel.goTrip.transitCities.size() > 0) {
                //国际中转
                binding.layoutTop2.getRoot().setVisibility(View.GONE);
                binding.layoutTop1.getRoot().setVisibility(View.GONE);
                binding.layoutTop2.btnTitle1.setText("一程");
                binding.layoutTop2.btnTitle2.setText("二程");
                String str3 = "【托运行李提示】";
                String str4 = "中转深圳，需在机场重新托运行李";
                setSpannableString(str4, str3, searchType);
                binding.transitRecyclerView.setVisibility(View.VISIBLE);
                binding.transitRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                transitAdapter = new TransitAdapter(flightSegmentInfo);
                binding.transitRecyclerView.setAdapter(transitAdapter);
                transitAdapter.notifyDataSetChanged();
                binding.transitRecyclerView.setNestedScrollingEnabled(false);
            } else {
                //国际直达
                binding.transitRecyclerView.setVisibility(View.GONE);
                binding.layoutTop1.getRoot().setVisibility(View.VISIBLE);
                binding.layoutTop2.getRoot().setVisibility(View.GONE);
                binding.promptContent.setText("【机场提示】该航班到达机场为北京大兴国际机场，距市区约46公里，搭乘地铁到市区约30分钟。");
                binding.layoutTop1.oneWayCompanyName.setText(flightSegmentInfo.get(0).carrierShortName + flightSegmentInfo.get(0).flightNum + flightSegmentInfo.get(0).planeTypeName + "无餐\n以下价格含机建燃油");
                binding.layoutTop1.oneWayStartDate.setText(DateUtil.strToStr(flightSegmentInfo.get(0).depDate) + DateUtil.strToWeek("yyyy-MM-dd", flightSegmentInfo.get(0).depDate));
                binding.layoutTop1.oneWayEndDate.setText(DateUtil.strToStr(flightSegmentInfo.get(0).arrDate) + DateUtil.strToWeek("yyyy-MM-dd", flightSegmentInfo.get(0).arrDate));
                binding.layoutTop1.oneWayBTime.setText(flightSegmentInfo.get(0).depTime);
                binding.layoutTop1.oneWayETime.setText(flightSegmentInfo.get(0).arrTime);
                binding.layoutTop1.oneWayDepAirport.setText(flightSegmentInfo.get(0).depAirportName + flightSegmentInfo.get(0).depTerminal);
                binding.layoutTop1.oneWayArrAirport.setText(flightSegmentInfo.get(0).arrAirportName + flightSegmentInfo.get(0).arrTerminal);
                binding.layoutTop1.oneWayFlightTimes.setText(DateUtil.minToHour(flightSegmentInfo.get(0).duration));
                if (flightSegmentInfo.get(0).crossDays == 0) {
                    binding.layoutTop1.oneWayCrossDays.setVisibility(View.GONE);
                } else {
                    binding.layoutTop1.oneWayCrossDays.setVisibility(View.VISIBLE);
                    binding.layoutTop1.oneWayCrossDays.setText("+" + flightSegmentInfo.get(0).crossDays + "天");
                }
            }
        } else if (searchType == 2) {
            //国内往返
            binding.layoutTop2.btnTitle1.setText("去程");
            binding.layoutTop2.btnTitle2.setText("返程");
            binding.promptContent.setText("【风险提示】此报价是组合产品，如遇其中一程航班调整，您可办理另一程退改事宜，需自行承担相应费用，详见退改详情。");
            binding.layoutTop2.getRoot().setVisibility(View.VISIBLE);
            binding.layoutTop1.getRoot().setVisibility(View.GONE);
            binding.transitRecyclerView.setVisibility(View.GONE);
            binding.layoutTop2.twoWayGoDepTime.setText(goBackSearchFlightInfo.domesticFlight.go.depTime);
            binding.layoutTop2.twoWayGoArrTime.setText(goBackSearchFlightInfo.domesticFlight.go.arrTime);
            binding.layoutTop2.twoWayGoDepAirportName.setText(goBackSearchFlightInfo.domesticFlight.go.depAirport + goBackSearchFlightInfo.domesticFlight.go.depTerminal);
            binding.layoutTop2.twoWayGoArrAirportName.setText(goBackSearchFlightInfo.domesticFlight.go.arrAirport + goBackSearchFlightInfo.domesticFlight.go.arrTerminal);
            binding.layoutTop2.twoWayGoFlightTime.setText(goBackSearchFlightInfo.domesticFlight.go.flightTimes);
            binding.layoutTop2.twoWayGoDate.setText(DateUtil.strToStr(flightInfo.goDate) + DateUtil.strToWeek("yyyy-MM-dd", flightInfo.goDate));
            binding.layoutTop2.twoWayGoCityName.setText(flightInfo.depCityName + "-" + flightInfo.arrCityName);
            binding.layoutTop2.twoWayBackDate.setText(DateUtil.strToStr(flightInfo.backDate) + DateUtil.strToWeek("yyyy-MM-dd", flightInfo.backDate));
            binding.layoutTop2.twoWayBackCityName.setText(flightInfo.arrCityName + "-" + flightInfo.depCityName);
            binding.layoutTop2.twoWayBackDepTime.setText(goBackSearchFlightInfo.domesticFlight.back.depTime);
            binding.layoutTop2.twoWayBackArrTime.setText(goBackSearchFlightInfo.domesticFlight.back.arrTime);
            binding.layoutTop2.twoWayBackFlightTime.setText(goBackSearchFlightInfo.domesticFlight.back.flightTimes);
            binding.layoutTop2.twoWayBackDepAirportName.setText(goBackSearchFlightInfo.domesticFlight.back.depAirport + goBackSearchFlightInfo.domesticFlight.back.depTerminal);
            binding.layoutTop2.twoWayBackArrAirportName.setText(goBackSearchFlightInfo.domesticFlight.back.arrAirport + goBackSearchFlightInfo.domesticFlight.back.arrTerminal);
            binding.layoutTop2.twoWayGoCrossDays.setVisibility(View.GONE);
            binding.layoutTop2.twoWayBackCrossDays.setVisibility(View.GONE);
            binding.layoutTop2.twoWayGoCom.setText(goBackSearchFlightInfo.domesticFlight.go.carrierName + goBackSearchFlightInfo.domesticFlight.go.flightCode + goBackSearchFlightInfo.domesticFlight.go.flightTypeFullName + "无餐\n以下价格不含机建燃油");
            binding.layoutTop2.twoWayBackCom.setText(goBackSearchFlightInfo.domesticFlight.back.carrierName + goBackSearchFlightInfo.domesticFlight.back.flightCode + goBackSearchFlightInfo.domesticFlight.back.flightTypeFullName + "无餐\n以下价格不含机建燃油");
        } else {
            //国际往返
            binding.layoutTop2.btnTitle1.setText("去程");
            binding.layoutTop2.btnTitle2.setText("返程");
            binding.promptContent.setText("【风险提示】此报价是组合产品，如遇其中一程航班调整，您可办理另一程退改事宜，需自行承担相应费用，详见退改详情。");
            binding.layoutTop2.getRoot().setVisibility(View.VISIBLE);
            binding.layoutTop1.getRoot().setVisibility(View.GONE);
            binding.transitRecyclerView.setVisibility(View.GONE);
            binding.layoutTop2.twoWayGoDepTime.setText(goBackSearchFlightInfo.internationalFlight.goTrip.flightSegments.get(0).depTime);
            binding.layoutTop2.twoWayGoArrTime.setText(goBackSearchFlightInfo.internationalFlight.goTrip.flightSegments.get(goBackSearchFlightInfo.internationalFlight.goTrip.flightSegments.size() - 1).arrTime);
            binding.layoutTop2.twoWayGoDepAirportName.setText(goBackSearchFlightInfo.internationalFlight.goTrip.flightSegments.get(0).depAirportName);
            binding.layoutTop2.twoWayGoArrAirportName.setText(goBackSearchFlightInfo.internationalFlight.goTrip.flightSegments.get(goBackSearchFlightInfo.internationalFlight.goTrip.flightSegments.size() - 1).arrAirportName);
            binding.layoutTop2.twoWayGoFlightTime.setText(DateUtil.minToHour(goBackSearchFlightInfo.internationalFlight.goTrip.duration));
            binding.layoutTop2.twoWayGoDate.setText(DateUtil.strToStr(flightInfo.goDate) + DateUtil.strToWeek("yyyy-MM-dd", flightInfo.goDate));
            binding.layoutTop2.twoWayGoCityName.setText(goBackSearchFlightInfo.internationalFlight.goTrip.flightSegments.get(0).depCityName + "-" + goBackSearchFlightInfo.internationalFlight.goTrip.flightSegments.get(goBackSearchFlightInfo.internationalFlight.goTrip.flightSegments.size() - 1).arrCityName);
            binding.layoutTop2.twoWayBackDate.setText(DateUtil.strToStr(flightInfo.backDate) + DateUtil.strToWeek("yyyy-MM-dd", flightInfo.backDate));
            binding.layoutTop2.twoWayBackCityName.setText(goBackSearchFlightInfo.internationalFlight.backTrip.flightSegments.get(0).depCityName + "-" + goBackSearchFlightInfo.internationalFlight.backTrip.flightSegments.get(goBackSearchFlightInfo.internationalFlight.backTrip.flightSegments.size() - 1).arrCityName);
            binding.layoutTop2.twoWayBackDepTime.setText(goBackSearchFlightInfo.internationalFlight.backTrip.flightSegments.get(0).depTime);
            binding.layoutTop2.twoWayBackArrTime.setText(goBackSearchFlightInfo.internationalFlight.backTrip.flightSegments.get(goBackSearchFlightInfo.internationalFlight.backTrip.flightSegments.size() - 1).arrTime);
            binding.layoutTop2.twoWayBackFlightTime.setText(DateUtil.minToHour(goBackSearchFlightInfo.internationalFlight.backTrip.duration));
            binding.layoutTop2.twoWayBackDepAirportName.setText(goBackSearchFlightInfo.internationalFlight.backTrip.flightSegments.get(0).depAirportName);
            binding.layoutTop2.twoWayBackArrAirportName.setText(goBackSearchFlightInfo.internationalFlight.backTrip.flightSegments.get(goBackSearchFlightInfo.internationalFlight.backTrip.flightSegments.size() - 1).arrAirportName);
            binding.layoutTop2.twoWayGoCom.setText(goBackSearchFlightInfo.internationalFlight.goTrip.flightSegments.get(0).carrierShortName + goBackSearchFlightInfo.internationalFlight.goTrip.flightSegments.get(0).flightNum + goBackSearchFlightInfo.internationalFlight.goTrip.flightSegments.get(0).planeTypeName + "无餐\n以下价格含机建燃油");
            binding.layoutTop2.twoWayBackCom.setText(goBackSearchFlightInfo.internationalFlight.backTrip.flightSegments.get(0).carrierShortName + goBackSearchFlightInfo.internationalFlight.backTrip.flightSegments.get(0).flightNum + goBackSearchFlightInfo.internationalFlight.backTrip.flightSegments.get(0).planeTypeName + "无餐\n以下价格含机建燃油");

            if (goBackSearchFlightInfo.internationalFlight.goTrip.crossDays == 0) {
                binding.layoutTop2.twoWayGoCrossDays.setVisibility(View.GONE);
            } else {
                binding.layoutTop2.twoWayGoCrossDays.setVisibility(View.VISIBLE);
                binding.layoutTop2.twoWayGoCrossDays.setText("+" + goBackSearchFlightInfo.internationalFlight.goTrip.crossDays + "天");
            }
            if (goBackSearchFlightInfo.internationalFlight.backTrip.crossDays == 0) {
                binding.layoutTop2.twoWayBackCrossDays.setVisibility(View.GONE);
            } else {
                binding.layoutTop2.twoWayBackCrossDays.setVisibility(View.VISIBLE);
                binding.layoutTop2.twoWayBackCrossDays.setText("+" + goBackSearchFlightInfo.internationalFlight.backTrip.crossDays + "天");
            }
        }
       binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new PlaneDetailAdapter(multiPriceInfos);
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
        binding.recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        binding.recyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    public void loadData() {

    }

    @Override
    public void initListener() {

    }

    @SuppressLint("SetTextI18n")
    private void setSpannableString(String str1, String str2, int type) {
        binding.promptContent.setText("");
        SpannableString span1 = new SpannableString(str1);
        SpannableString span2 = new SpannableString(str2);
        ForegroundColorSpan colorSpan1 = new ForegroundColorSpan(Color.parseColor("#666666"));
        span1.setSpan(new AbsoluteSizeSpan(11, true), 0, str1.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        span1.setSpan(colorSpan1, 0, str1.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        ForegroundColorSpan colorSpan3 = new ForegroundColorSpan(Color.parseColor("#28B5FE"));
        span2.setSpan(new AbsoluteSizeSpan(11, true), 0, str2.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        span2.setSpan(colorSpan3, 0, str2.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        span2.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                if (type == 3) {
                    /*new XPopup.Builder(AirFlightDetailActivity.this)
                            .enableDrag(false)
                            .asCustom(new CustomPlaneTransferView(AirFlightDetailActivity.this
                            )).show();*/
                } else {

                }
            }
        }, 0, span2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        if (type == 3) {
            binding.promptContent.append(span1);
            binding.promptContent.append(span2);
        } else if (type == 4) {
            binding.promptContent.append(span2);
            binding.promptContent.append(span1);
        }
        binding.promptContent.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
