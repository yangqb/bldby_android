package com.bldby.airticket.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
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
import com.bldby.airticket.model.AirGoBackPriceDetailInfo;
import com.bldby.airticket.model.AirInternationalPriceDetailInfo;
import com.bldby.airticket.model.AirPriceDetailInfo;
import com.bldby.airticket.model.CustomAirPriceDetailInfo;
import com.bldby.airticket.model.CustomFightCityInfo;
import com.bldby.airticket.model.FlightSegmentInfo;
import com.bldby.airticket.model.MultiGoBackFlightInfo;
import com.bldby.airticket.model.MultiPriceInfo;
import com.bldby.airticket.model.MultipleGoSearchFightInfo;
import com.bldby.airticket.model.RefundChangeInfo;
import com.bldby.airticket.model.SearchFlightModel;
import com.bldby.airticket.request.AirGoBackRefundChangeQueryRequest;
import com.bldby.airticket.request.AirGoRefundChangeQueryRequest;
import com.bldby.airticket.request.DomesticGoBackSearchPriceRequest;
import com.bldby.airticket.request.DomesticGoSearchPriceRequest;
import com.bldby.airticket.request.InternationalGoBackSearchPriceRequest;
import com.bldby.airticket.request.InternationalGoSearchPriceRequest;
import com.bldby.airticket.view.CustomCancelChangePopView;
import com.bldby.airticket.view.CustomPlaneTransferView;
import com.bldby.baselibrary.constants.RouteAirConstants;
import com.bldby.baselibrary.constants.RouteLoginConstants;
import com.bldby.baselibrary.core.network.ApiLifeCallBack;
import com.bldby.baselibrary.core.ui.baseactivity.BaseAirUiActivity;
import com.bldby.baselibrary.core.util.DateUtil;
import com.bldby.loginlibrary.AccountManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lxj.xpopup.XPopup;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

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
    private AirPriceDetailInfo priceDetailInfo;
    private AirInternationalPriceDetailInfo internationalPriceDetailInfo;
    private CustomAirPriceDetailInfo customPriceDetailInfo = new CustomAirPriceDetailInfo();
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
            binding.layoutTop1.oneWayBTime.setText(searchFlightModel.dptTime);
            binding.layoutTop1.oneWayETime.setText(searchFlightModel.arrTime);
            binding.layoutTop1.oneWayStartCityName.setText(flightInfo.depCityName);
            binding.layoutTop1.oneWayEndCityName.setText(flightInfo.arrCityName);
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
                binding.layoutTop1.oneWayBTime.setText(flightSegmentInfo.get(0).depTime);
                binding.layoutTop1.oneWayETime.setText(flightSegmentInfo.get(0).arrTime);
                binding.layoutTop1.oneWayStartCityName.setText(flightInfo.depCityName);
                binding.layoutTop1.oneWayEndCityName.setText(flightInfo.arrCityName);
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
        if (searchType == 0) {  //国内单程
            DomesticGoSearchPriceRequest request = new DomesticGoSearchPriceRequest();
            request.arr = searchFlightModel.arr;
            request.dpt = searchFlightModel.dpt;
            request.date = flightInfo.goDate;
            request.carrier = searchFlightModel.carrier;
            request.flightNum = searchFlightModel.flightNum;
            request.cabin = searchFlightModel.cabin;
            request.ex_track = "tehui";
            request.call(new ApiLifeCallBack<AirPriceDetailInfo>() {
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
                public void onAPIResponse(AirPriceDetailInfo response) {
                    multiPriceInfos.clear();
                    if (response != null) {
                        priceDetailInfo = response;
                        priceDetailInfo.flightTimes = searchFlightModel.flightTimes;
                        binding.layoutTop1.oneWayCompanyName.setText(priceDetailInfo.com + priceDetailInfo.code + searchFlightModel.flightTypeFullName + (priceDetailInfo.meal ? "有餐" : "无餐") + "\n以下价格不含机建燃油");
                        if (priceDetailInfo.vendors != null && priceDetailInfo.vendors.size() > 0) {
                            for (int i = 0; i < priceDetailInfo.vendors.size(); i++) {
                                MultiPriceInfo priceInfo = new MultiPriceInfo(MultiPriceInfo.DOMESTIC_TYPE);
                                priceInfo.venDorsInfo = priceDetailInfo.vendors.get(i);
                                multiPriceInfos.add(priceInfo);
                            }
                            mAdapter.setNewData(multiPriceInfos);
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                    mAdapter.getEmptyView().setVisibility(View.VISIBLE);
                }

                @Override
                public void onAPIError(int errorCode, String errorMsg) {

                }
            });
        } else if (searchType == 2) { //国内往返
            DomesticGoBackSearchPriceRequest request = new DomesticGoBackSearchPriceRequest();
            request.depCity = goBackSearchFlightInfo.domesticFlight.go.depAirportCode;
            request.arrCity = goBackSearchFlightInfo.domesticFlight.go.arrAirportCode;
            request.goDate = flightInfo.goDate;
            request.backDate = flightInfo.backDate;
            request.flightCodes = goBackSearchFlightInfo.domesticFlight.flightCodes;
            request.exTrack = "retehui";
            request.call(new ApiLifeCallBack<AirGoBackPriceDetailInfo>() {
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
                public void onAPIResponse(AirGoBackPriceDetailInfo goBackPriceDetailInfo) {
                    multiPriceInfos.clear();
                    if (goBackPriceDetailInfo != null) {
                        if (goBackPriceDetailInfo.packVendors != null && goBackPriceDetailInfo.packVendors.size() > 0) {
                            for (int i = 0; i < goBackPriceDetailInfo.packVendors.size(); i++) {
                                MultiPriceInfo priceInfo = new MultiPriceInfo(MultiPriceInfo.DOMESTIC_GO_BACK_TYPE);
                                priceInfo.goBackVendors = goBackPriceDetailInfo.packVendors.get(i);
                                multiPriceInfos.add(priceInfo);
                            }
                            mAdapter.setNewData(multiPriceInfos);
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                    mAdapter.getEmptyView().setVisibility(View.VISIBLE);
                }

                @Override
                public void onAPIError(int errorCode, String errorMsg) {

                }
            });
        } else if (searchType == 1) { //国际单程
            InternationalGoSearchPriceRequest request = new InternationalGoSearchPriceRequest();
            request.arrCity = goSearchFightInfo.internationalFlightModel.goTrip.flightSegments.get(goSearchFightInfo.internationalFlightModel.goTrip.flightSegments.size() - 1).arrCityCode;
            request.depCity = goSearchFightInfo.internationalFlightModel.goTrip.flightSegments.get(0).depCityCode;
            request.flightCode = goSearchFightInfo.internationalFlightModel.flightCode;
            request.depDate = flightInfo.goDate;
            request.source = "ICP_SELECT_open.3724";
            request.call(new ApiLifeCallBack<AirInternationalPriceDetailInfo>() {
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
                public void onAPIResponse(AirInternationalPriceDetailInfo response) {
                    multiPriceInfos.clear();
                    if (response != null) {
                        internationalPriceDetailInfo = response;
                        if (internationalPriceDetailInfo.priceInfo != null && internationalPriceDetailInfo.priceInfo.size() > 0) {
                            for (int i = 0; i < internationalPriceDetailInfo.priceInfo.size(); i++) {
                                MultiPriceInfo priceInfo = new MultiPriceInfo(MultiPriceInfo.INTERNATIONAL_TYPE);
                                priceInfo.internationalPriceInfo = internationalPriceDetailInfo.priceInfo.get(i);
                                multiPriceInfos.add(priceInfo);
                            }
                            mAdapter.setNewData(multiPriceInfos);
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                    mAdapter.getEmptyView().setVisibility(View.VISIBLE);
                }

                @Override
                public void onAPIError(int errorCode, String errorMsg) {

                }
            });
        } else { //国际往返
            InternationalGoBackSearchPriceRequest request = new InternationalGoBackSearchPriceRequest();
            request.arrCity = goBackSearchFlightInfo.internationalFlight.goTrip.flightSegments.get(goBackSearchFlightInfo.internationalFlight.goTrip.flightSegments.size() - 1).arrCityCode;
            request.depCity = goBackSearchFlightInfo.internationalFlight.goTrip.flightSegments.get(0).depCityCode;
            request.flightCode = goBackSearchFlightInfo.internationalFlight.flightCode;
            request.depDate = flightInfo.goDate;
            request.retDate = flightInfo.backDate;
            request.source = "ICP_SELECT_open.3724";
            request.call(new ApiLifeCallBack<AirInternationalPriceDetailInfo>() {
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
                public void onAPIResponse(AirInternationalPriceDetailInfo response) {

                    multiPriceInfos.clear();
                    if (response != null) {
                        internationalPriceDetailInfo = response;
                        if (internationalPriceDetailInfo.priceInfo != null && internationalPriceDetailInfo.priceInfo.size() > 0) {
                            for (int i = 0; i < internationalPriceDetailInfo.priceInfo.size(); i++) {
                                MultiPriceInfo priceInfo = new MultiPriceInfo(MultiPriceInfo.INTERNATIONAL_TYPE);
                                priceInfo.internationalPriceInfo = internationalPriceDetailInfo.priceInfo.get(i);
                                multiPriceInfos.add(priceInfo);
                            }
                            mAdapter.setNewData(multiPriceInfos);
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

    @Override
    public void initListener() {
        binding.refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                loadData();
            }
        });

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.ll_rebate) {
                    if (!AccountManager.isLogin()) {
                        start(RouteLoginConstants.REGISTER);
                        return;
                    }
                       /* UserInfo userInfo = AccountManager.getInstance().getUserInfo();
                        intent = new Intent(PlaneDetailActivity.this, VipActivity.class);
                        intent.putExtra(VipActivity.MINE_INFO, userInfo);
                        startActivity(intent);*/
                } else if (view.getId() == R.id.luggage_change_notice) {
                    if (searchType == 0) {
                        customPriceDetailInfo.customDocGoFlightInfo = priceDetailInfo;
                        customPriceDetailInfo.customDocGoPriceInfo = multiPriceInfos.get(position).venDorsInfo;
                    } else if (searchType == 1) {
                        customPriceDetailInfo.customInterFlightInfo = goSearchFightInfo.internationalFlightModel;
                        customPriceDetailInfo.customInterPriceInfo = multiPriceInfos.get(position).internationalPriceInfo;
                    } else if (searchType == 2) {
                        customPriceDetailInfo.customDocGoBackFlightInfo = goBackSearchFlightInfo.domesticFlight;
                        customPriceDetailInfo.customDocGoBackPriceInfo = multiPriceInfos.get(position).goBackVendors;
                    } else {
                        customPriceDetailInfo.customInterFlightInfo = goBackSearchFlightInfo.internationalFlight;
                        customPriceDetailInfo.customInterPriceInfo = multiPriceInfos.get(position).internationalPriceInfo;
                    }
                    customPriceDetailInfo.customFightCityInfo = flightInfo;
                    getRefundChange();
                } else if (view.getId() == R.id.btn_reserve) {
                    if (!AccountManager.isLogin()) {
                        start(RouteLoginConstants.REGISTER);
                        return;
                    }
                    if (searchType == 0) {
                        customPriceDetailInfo.customDocGoFlightInfo = priceDetailInfo;
                        customPriceDetailInfo.customDocGoPriceInfo = multiPriceInfos.get(position).venDorsInfo;
                    } else if (searchType == 1) {
                        customPriceDetailInfo.customInterFlightInfo = goSearchFightInfo.internationalFlightModel;
                        customPriceDetailInfo.customInterPriceInfo = multiPriceInfos.get(position).internationalPriceInfo;
                    } else if (searchType == 2) {
                        customPriceDetailInfo.customDocGoBackFlightInfo = goBackSearchFlightInfo.domesticFlight;
                        customPriceDetailInfo.customDocGoBackPriceInfo = multiPriceInfos.get(position).goBackVendors;
                    } else {
                        customPriceDetailInfo.customInterFlightInfo = goBackSearchFlightInfo.internationalFlight;
                        customPriceDetailInfo.customInterPriceInfo = multiPriceInfos.get(position).internationalPriceInfo;
                    }
                    customPriceDetailInfo.customFightCityInfo = flightInfo;
                    startWith(RouteAirConstants.AIRRESERVE).withInt("airType", searchType).withSerializable("customPriceDetailInfo", customPriceDetailInfo).navigation();
                }
            }
        });
    }


    /*
     *
     * 退改签查询
     * */
    public void getRefundChange() {
        if (searchType == 0) {
            AirGoRefundChangeQueryRequest refundChangeQueryRequest = new AirGoRefundChangeQueryRequest();
            refundChangeQueryRequest.flightNum = customPriceDetailInfo.customDocGoPriceInfo.shareShowAct ? customPriceDetailInfo.customDocGoFlightInfo.actCode : customPriceDetailInfo.customDocGoFlightInfo.code;
            refundChangeQueryRequest.cabin = customPriceDetailInfo.customDocGoPriceInfo.cabin;
            refundChangeQueryRequest.dpt = customPriceDetailInfo.customDocGoFlightInfo.depCode;
            refundChangeQueryRequest.arr = customPriceDetailInfo.customDocGoFlightInfo.arrCode;
            refundChangeQueryRequest.dptDate = customPriceDetailInfo.customDocGoFlightInfo.date;
            refundChangeQueryRequest.dptTime = customPriceDetailInfo.customDocGoFlightInfo.btime;
            refundChangeQueryRequest.policyId = customPriceDetailInfo.customDocGoPriceInfo.PolicyId;
            refundChangeQueryRequest.maxSellPrice = String.valueOf(customPriceDetailInfo.customDocGoPriceInfo.barePrice);
            refundChangeQueryRequest.minSellPrice = String.valueOf(customPriceDetailInfo.customDocGoPriceInfo.barePrice);
            refundChangeQueryRequest.printPrice = String.valueOf(customPriceDetailInfo.customDocGoPriceInfo.vppr);
            refundChangeQueryRequest.tagName = customPriceDetailInfo.customDocGoPriceInfo.prtag;
            refundChangeQueryRequest.translate = false;
            refundChangeQueryRequest.sfid = customPriceDetailInfo.customDocGoPriceInfo.groupId;
            refundChangeQueryRequest.needPercentTgqText = false;
            refundChangeQueryRequest.businessExt = customPriceDetailInfo.customDocGoPriceInfo.businessExt;
            refundChangeQueryRequest.client = customPriceDetailInfo.customDocGoPriceInfo.domain;
            refundChangeQueryRequest.call(new ApiLifeCallBack<RefundChangeInfo>() {
                @Override
                public void onStart() {

                }

                @Override
                public void onFinsh() {

                }

                @Override
                public void onAPIResponse(RefundChangeInfo response) {
                    if (response != null) {
                        new XPopup.Builder(AirFlightDetailActivity.this)
                                .asCustom(new CustomCancelChangePopView(AirFlightDetailActivity.this
                                ).setType(searchType).setGoData(response).setLuggage(false)).show();
                    }
                }

                @Override
                public void onAPIError(int errorCode, String errorMsg) {

                }
            });
        } else if (searchType == 2) {

            AirGoBackRefundChangeQueryRequest goBackRefundChangeQueryRequest = new AirGoBackRefundChangeQueryRequest();
            goBackRefundChangeQueryRequest.carrier = customPriceDetailInfo.customDocGoBackFlightInfo.go.carrier;
            goBackRefundChangeQueryRequest.depCode = customPriceDetailInfo.customDocGoBackFlightInfo.go.depAirportCode;
            goBackRefundChangeQueryRequest.arrCode = customPriceDetailInfo.customDocGoBackFlightInfo.go.arrAirportCode;
            goBackRefundChangeQueryRequest.goDate = flightInfo.goDate;
            goBackRefundChangeQueryRequest.backDate = flightInfo.backDate;
            goBackRefundChangeQueryRequest.outCabin = customPriceDetailInfo.customDocGoBackPriceInfo.outCabin;
            goBackRefundChangeQueryRequest.retCabin = customPriceDetailInfo.customDocGoBackPriceInfo.retCabin;
            goBackRefundChangeQueryRequest.businessExts = customPriceDetailInfo.customDocGoBackPriceInfo.businessExts;
            goBackRefundChangeQueryRequest.goFlightNum = customPriceDetailInfo.customDocGoBackFlightInfo.go.flightCode;
            goBackRefundChangeQueryRequest.backFlightNum = customPriceDetailInfo.customDocGoBackFlightInfo.back.flightCode;
            goBackRefundChangeQueryRequest.policyId = customPriceDetailInfo.customDocGoBackPriceInfo.policyId;
            goBackRefundChangeQueryRequest.price = customPriceDetailInfo.customDocGoBackPriceInfo.price;
            goBackRefundChangeQueryRequest.client = customPriceDetailInfo.customDocGoBackPriceInfo.domain;
            goBackRefundChangeQueryRequest.barePrice = customPriceDetailInfo.customDocGoBackPriceInfo.barePrice;
            goBackRefundChangeQueryRequest.tagName = customPriceDetailInfo.customDocGoBackPriceInfo.tagName;
            goBackRefundChangeQueryRequest.call(new ApiLifeCallBack<List<RefundChangeInfo>>() {
                @Override
                public void onStart() {

                }

                @Override
                public void onFinsh() {

                }

                @Override
                public void onAPIResponse(List<RefundChangeInfo> response) {
                    if (response != null) {
                        new XPopup.Builder(AirFlightDetailActivity.this)
                                .enableDrag(false)
                                .asCustom(new CustomCancelChangePopView(AirFlightDetailActivity.this
                                ).setType(searchType).setGoData(response.get(0)).setBackData(response.get(1)).setLuggage(false)).show();
                    }
                }

                @Override
                public void onAPIError(int errorCode, String errorMsg) {

                }
            });
        }
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
                    new XPopup.Builder(AirFlightDetailActivity.this)
                            .enableDrag(false)
                            .asCustom(new CustomPlaneTransferView(AirFlightDetailActivity.this
                            )).show();
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
