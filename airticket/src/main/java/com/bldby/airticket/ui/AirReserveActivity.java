package com.bldby.airticket.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.LinearLayoutManager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.airticket.AirConstants;
import com.bldby.airticket.R;
import com.bldby.airticket.adapter.SelectPassengerAdapter;
import com.bldby.airticket.databinding.ActivityAirReserveBinding;
import com.bldby.airticket.model.BaggageRuleInfo;
import com.bldby.airticket.model.BaggageRuleReqParamModel;
import com.bldby.airticket.model.CityModel;
import com.bldby.airticket.model.ContactModel;
import com.bldby.airticket.model.CreateOrderInfo;
import com.bldby.airticket.model.CustomAirPriceDetailInfo;
import com.bldby.airticket.model.DocBookingPriceTagInfo;
import com.bldby.airticket.model.DocGoBackCreateOrderInfo;
import com.bldby.airticket.model.DocPassengerInfo;
import com.bldby.airticket.model.DocResultBookingInfo;
import com.bldby.airticket.model.GoBackBaggageRuleInfo;
import com.bldby.airticket.model.GoBackBookingInfo;
import com.bldby.airticket.model.GoBackFlight;
import com.bldby.airticket.model.GoBackTripInfo;
import com.bldby.airticket.model.InterContactModel;
import com.bldby.airticket.model.InterPassengerInfo;
import com.bldby.airticket.model.InterXcdInfo;
import com.bldby.airticket.model.InternationalCreateOrderRequest;
import com.bldby.airticket.model.PassengerModel;
import com.bldby.airticket.model.PayModel;
import com.bldby.airticket.model.PriceDetailInfo;
import com.bldby.airticket.model.RefundChangeInfo;
import com.bldby.airticket.model.ReimbursementModel;
import com.bldby.airticket.model.UserClientInfo;
import com.bldby.airticket.request.AirGoBackBaggageRuleRequest;
import com.bldby.airticket.request.AirGoBackRefundChangeQueryRequest;
import com.bldby.airticket.request.AirGoBaggageRuleRequest;
import com.bldby.airticket.request.AirGoRefundChangeQueryRequest;
import com.bldby.airticket.request.AirValidateRequest;
import com.bldby.airticket.request.DomesticBookRequest;
import com.bldby.airticket.request.DomesticCreateOrderRequest;
import com.bldby.airticket.request.DomesticGoBackBookRequest;
import com.bldby.airticket.request.DomesticGoBackCreateOrderRequest;
import com.bldby.airticket.request.InternationalBookRequest;
import com.bldby.airticket.request.PaySignRequest;
import com.bldby.airticket.view.CustomCancelChangePopView;
import com.bldby.airticket.view.CustomDialog;
import com.bldby.airticket.view.CustomListDialog;
import com.bldby.airticket.view.CustomLuggageBuyTicketNoticeView;
import com.bldby.airticket.view.CustomPayView;
import com.bldby.airticket.view.CustomPlaneInfoView;
import com.bldby.airticket.view.CustomPlaneProtocolDialog;
import com.bldby.airticket.view.CustomTicketPriceDetailView;
import com.bldby.airticket.view.CustomTotalPriceInfoView;
import com.bldby.baselibrary.app.NetworkConfig;
import com.bldby.baselibrary.app.util.RegUtils;
import com.bldby.baselibrary.constants.RouteAirConstants;
import com.bldby.baselibrary.constants.RouteCenterConstants;
import com.bldby.baselibrary.core.network.ApiCallBack;
import com.bldby.baselibrary.core.network.ApiLifeCallBack;
import com.bldby.baselibrary.core.pay.PayHelper;
import com.bldby.baselibrary.core.ui.baseactivity.BaseAirUiActivity;
import com.bldby.baselibrary.core.util.DateUtil;
import com.bldby.baselibrary.core.util.MathUtils;
import com.bldby.baselibrary.core.util.SoftKeyBoardListener;
import com.bldby.baselibrary.core.util.ToastUtil;
import com.bldby.centerlibrary.model.AddressInfo;
import com.bldby.centerlibrary.request.AddressListRequest;
import com.bldby.loginlibrary.AccountManager;
import com.bldby.loginlibrary.model.UserInfo;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnCancelListener;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.PostRequest;
import com.lzy.okgo.request.base.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * package name: com.bldby.airticket.ui
 * user: yangqinbo
 * date: 2020/6/19
 * time: 11:49
 * email: 694125155@qq.com
 */

/*
 * 机票预订填写信息页面
 * */
@Route(path = RouteAirConstants.AIRRESERVE)
public class AirReserveActivity extends BaseAirUiActivity {
    private ActivityAirReserveBinding binding;
    private UserInfo userInfo;
    private int invoicePosition;
    private String cabinStr = "";
    private PriceDetailInfo priceDetailInfo = new PriceDetailInfo();
    private SelectPassengerAdapter mAdapter;
    private List<PassengerModel> passengerList = new ArrayList<>();
    private List<AddressInfo.ShopAddressListBean> addressInfos = new ArrayList<>();
    private CityModel cityModel;
    private AddressInfo.ShopAddressListBean addressBean;
    private int count;//成人数量
    private int cCount;//儿童数量
    @Autowired
    public int airType;
    @Autowired
    public CustomAirPriceDetailInfo customPriceDetailInfo;

    @Override
    protected View initContentView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        binding = ActivityAirReserveBinding.inflate(layoutInflater, viewGroup, false);
        binding.setViewModel(this);
        return binding.getRoot();
    }

    @Override
    public void initView() {
        userInfo = AccountManager.getInstance().getUserInfo();
        initTitle(customPriceDetailInfo.customFightCityInfo.depCityName, customPriceDetailInfo.customFightCityInfo.arrCityName);
        setSpannableString("0", binding.totalPrice);
        binding.switchButton.setBackgroundColorChecked(getResources().getColor(R.color.bg_yellow));
        binding.switchButton.setBackgroundColorUnchecked(getResources().getColor(R.color.color_F1EFEF));
        binding.switchButton.setAnimateDuration(300);
        binding.switchButton.setButtonColor(getResources().getColor(R.color.white));

        if (airType == 0) {
            binding.postagePrice.setText("¥20");
            priceDetailInfo.price = customPriceDetailInfo.customDocGoPriceInfo.barePrice;
            priceDetailInfo.arf = customPriceDetailInfo.customDocGoFlightInfo.arf;
            priceDetailInfo.tof = customPriceDetailInfo.customDocGoFlightInfo.tof;
            if (customPriceDetailInfo.customDocGoPriceInfo.businessExtMap != null) {
                if (customPriceDetailInfo.customDocGoPriceInfo.businessExtMap.supportChild) {
                    priceDetailInfo.cPrice = customPriceDetailInfo.customDocGoPriceInfo.businessExtMap.childPrice;
                } else {
                    if (customPriceDetailInfo.customDocGoPriceInfo.businessExtMap.supportChildBuyAdult) {
                        priceDetailInfo.cPrice = customPriceDetailInfo.customDocGoPriceInfo.businessExtMap.childByAdultPrice;
                    } else {
                        priceDetailInfo.cPrice = 0;
                    }
                }
            } else {
                priceDetailInfo.cPrice = 0;
            }
            setCenterImg(R.mipmap.air_one_way_arrow);
            binding.llGoPlane.setVisibility(View.VISIBLE);
            binding.llBackPlane.setVisibility(View.GONE);
            binding.goTitle.setVisibility(View.GONE);
            binding.price.setText("¥" + MathUtils.subZero(String.valueOf(customPriceDetailInfo.customDocGoPriceInfo.barePrice)));
            binding.arfAndTof.setText("¥" + MathUtils.subZero(String.valueOf(customPriceDetailInfo.customDocGoFlightInfo.tof + customPriceDetailInfo.customDocGoFlightInfo.arf)));
            if (customPriceDetailInfo.customDocGoPriceInfo.cabinType == 0) {
                cabinStr = "经济舱(" + customPriceDetailInfo.customDocGoPriceInfo.cabin + ")";
            } else if (customPriceDetailInfo.customDocGoPriceInfo.cabinType == 1) {
                cabinStr = "头等舱(" + customPriceDetailInfo.customDocGoPriceInfo.cabin + ")";
            } else if (customPriceDetailInfo.customDocGoPriceInfo.cabinType == 2) {
                cabinStr = "商务舱(" + customPriceDetailInfo.customDocGoPriceInfo.cabin + ")";
            } else if (customPriceDetailInfo.customDocGoPriceInfo.cabinType == 3) {
                cabinStr = "经济舱精选(" + customPriceDetailInfo.customDocGoPriceInfo.cabin + ")";
            } else if (customPriceDetailInfo.customDocGoPriceInfo.cabinType == 4) {
                cabinStr = "经济舱y舱(" + customPriceDetailInfo.customDocGoPriceInfo.cabin + ")";
            } else if (customPriceDetailInfo.customDocGoPriceInfo.cabinType == 5) {
                cabinStr = "超值头等舱(" + customPriceDetailInfo.customDocGoPriceInfo.cabin + ")";
            } else if (customPriceDetailInfo.customDocGoPriceInfo.cabinType == -1) {
                cabinStr = "未配置";
            }
            binding.tvGoInfo.setText(DateUtil.strToStr(customPriceDetailInfo.customDocGoFlightInfo.date) + DateUtil.strToWeek("yyyy-MM-dd", customPriceDetailInfo.customDocGoFlightInfo.date) + "丨" + cabinStr);
        } else if (airType == 1) {
            priceDetailInfo.price = customPriceDetailInfo.customInterPriceInfo.price;
            priceDetailInfo.arf = 0;
            priceDetailInfo.tof = 0;
            priceDetailInfo.cPrice = customPriceDetailInfo.customInterPriceInfo.cPrice;
            GoBackTripInfo interGo = customPriceDetailInfo.customInterFlightInfo.goTrip;
            setCenterImg(R.mipmap.air_one_way_arrow);
            binding.llGoPlane.setVisibility(View.VISIBLE);
            binding.llBackPlane.setVisibility(View.GONE);
            binding.goTitle.setVisibility(View.GONE);
            binding.price.setText("¥" + MathUtils.subZero(String.valueOf(customPriceDetailInfo.customInterPriceInfo.price)));
            binding.arfAndTof.setText("¥0");
            if ("economy".equals(customPriceDetailInfo.customInterPriceInfo.cabinLevel)) {
                cabinStr = "经济舱(" + customPriceDetailInfo.customInterPriceInfo.cabin + ")";
            } else if ("first".equals(customPriceDetailInfo.customInterPriceInfo.cabinLevel)) {
                cabinStr = "头等舱(" + customPriceDetailInfo.customInterPriceInfo.cabin + ")";
            } else if ("business".equals(customPriceDetailInfo.customInterPriceInfo.cabinLevel)) {
                cabinStr = "商务舱(" + customPriceDetailInfo.customInterPriceInfo.cabin + ")";
            } else {
                cabinStr = "未配置";
            }
            binding.tvGoInfo.setText(DateUtil.strToStr(interGo.flightSegments.get(0).depDate) + DateUtil.strToWeek("yyyy-MM-dd", interGo.flightSegments.get(0).depDate) + "丨" + cabinStr);
        } else {
            setCenterImg(R.mipmap.air_search_goback_arrow);
            binding.llGoPlane.setVisibility(View.VISIBLE);
            binding.llBackPlane.setVisibility(View.VISIBLE);
            binding.goTitle.setVisibility(View.VISIBLE);
            if (airType == 2) {
                binding.postagePrice.setText("¥0");
                priceDetailInfo.price = customPriceDetailInfo.customDocGoBackPriceInfo.barePrice;
                priceDetailInfo.arf = customPriceDetailInfo.customDocGoBackFlightInfo.arf;
                priceDetailInfo.tof = customPriceDetailInfo.customDocGoBackFlightInfo.tof;
                priceDetailInfo.cPrice = 0;
                GoBackFlight docGoFlight = customPriceDetailInfo.customDocGoBackFlightInfo.go;
                GoBackFlight docBackFlight = customPriceDetailInfo.customDocGoBackFlightInfo.back;
                binding.price.setText("¥" + MathUtils.subZero(String.valueOf(customPriceDetailInfo.customDocGoBackPriceInfo.barePrice)));
                binding.arfAndTof.setText("¥" + MathUtils.subZero(String.valueOf(customPriceDetailInfo.customDocGoBackFlightInfo.tof + customPriceDetailInfo.customDocGoBackFlightInfo.arf)));
                binding.tvGoInfo.setText(DateUtil.strToStr(customPriceDetailInfo.customFightCityInfo.goDate) + DateUtil.strToWeek("yyyy-MM-dd", customPriceDetailInfo.customFightCityInfo.goDate) + "丨" + customPriceDetailInfo.customDocGoBackPriceInfo.cabinDesc);
                binding.tvBackInfo.setText(DateUtil.strToStr(customPriceDetailInfo.customFightCityInfo.backDate) + DateUtil.strToWeek("yyyy-MM-dd", customPriceDetailInfo.customFightCityInfo.backDate) + "丨" + customPriceDetailInfo.customDocGoBackPriceInfo.cabinDesc);
            } else {
                priceDetailInfo.price = customPriceDetailInfo.customInterPriceInfo.price;
                priceDetailInfo.arf = 0;
                priceDetailInfo.tof = 0;
                priceDetailInfo.cPrice = customPriceDetailInfo.customInterPriceInfo.cPrice;
                GoBackTripInfo interGo = customPriceDetailInfo.customInterFlightInfo.goTrip;
                GoBackTripInfo interBack = customPriceDetailInfo.customInterFlightInfo.backTrip;
                binding.price.setText("¥" + MathUtils.subZero(String.valueOf(customPriceDetailInfo.customInterPriceInfo.price)));
                binding.arfAndTof.setText("¥0");


                if ("economy".equals(customPriceDetailInfo.customInterPriceInfo.cabinLevel.split("/")[0])) {
                    cabinStr = "经济舱(" + customPriceDetailInfo.customInterPriceInfo.cabin.split("/")[0] + ")";
                } else if ("first".equals(customPriceDetailInfo.customInterPriceInfo.cabinLevel.split("/")[0])) {
                    cabinStr = "头等舱(" + customPriceDetailInfo.customInterPriceInfo.cabin.split("/")[0] + ")";
                } else if ("business".equals(customPriceDetailInfo.customInterPriceInfo.cabinLevel.split("/")[0])) {
                    cabinStr = "商务舱(" + customPriceDetailInfo.customInterPriceInfo.cabin.split("/")[0] + ")";
                } else {
                    cabinStr = "未配置";
                }
                binding.tvGoInfo.setText(DateUtil.strToStr(interGo.flightSegments.get(0).depDate) + DateUtil.strToWeek("yyyy-MM-dd", interGo.flightSegments.get(0).depDate)
                        + "丨" + cabinStr);
                binding.tvBackInfo.setText(DateUtil.strToStr(interBack.flightSegments.get(0).depDate) + DateUtil.strToWeek("yyyy-MM-dd", interBack.flightSegments.get(0).depDate)
                        + "丨" + cabinStr);
            }
        }

        if (priceDetailInfo.cPrice == 0) {
            binding.llChildPrice.setVisibility(View.GONE);
            binding.cprice.setText("¥0");
        } else {
            binding.llChildPrice.setVisibility(View.VISIBLE);
            binding.cprice.setText("¥" + MathUtils.subZero(String.valueOf(priceDetailInfo.cPrice)));
        }

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new SelectPassengerAdapter(passengerList);
        binding.recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadData() {
        AddressListRequest request = new AddressListRequest();
        request.accessToken = userInfo.loginInfo.accessToken;
        request.userId = userInfo.loginInfo.userId;
        request.call(new ApiLifeCallBack<AddressInfo>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onFinsh() {

            }

            @Override
            public void onAPIResponse(AddressInfo response) {
                if (response != null) {
                    addressInfos = response.getShopAddressList();
                    if (addressInfos.size() > 0) {
                        for (AddressInfo.ShopAddressListBean address : addressInfos
                        ) {
                            if (address.getIsDefalt() == 1) {
                                addressBean = address;
                                binding.name.setText(addressBean.getUserName());
                                binding.address.setText(addressBean.getProvinceName() + addressBean.getCityName() + addressBean.getAreaName() + addressBean.getDetailAddress());
                                binding.phone.setText(addressBean.getPhone());
                                break;
                            }
                        }
                    }
                }
            }

            @Override
            public void onAPIError(int errorCode, String errorMsg) {

            }
        });
    }

    /*
     * 退改签规则查询
     * */
    public void getRefundChange() {
        if (airType == 0) {
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
                        new XPopup.Builder(AirReserveActivity.this)
                                .asCustom(new CustomCancelChangePopView(AirReserveActivity.this
                                ).setType(airType).setGoData(response).setLuggage(false)).show();
                    }
                }

                @Override
                public void onAPIError(int errorCode, String errorMsg) {

                }
            });
        } else if (airType == 2) {

            AirGoBackRefundChangeQueryRequest goBackRefundChangeQueryRequest = new AirGoBackRefundChangeQueryRequest();
            goBackRefundChangeQueryRequest.carrier = customPriceDetailInfo.customDocGoBackFlightInfo.go.carrier;
            goBackRefundChangeQueryRequest.depCode = customPriceDetailInfo.customDocGoBackFlightInfo.go.depAirportCode;
            goBackRefundChangeQueryRequest.arrCode = customPriceDetailInfo.customDocGoBackFlightInfo.go.arrAirportCode;
            goBackRefundChangeQueryRequest.goDate = customPriceDetailInfo.customFightCityInfo.goDate;
            goBackRefundChangeQueryRequest.backDate = customPriceDetailInfo.customFightCityInfo.backDate;
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
                        new XPopup.Builder(AirReserveActivity.this)
                                .enableDrag(false)
                                .asCustom(new CustomCancelChangePopView(AirReserveActivity.this
                                ).setType(airType).setGoData(response.get(0)).setBackData(response.get(1)).setLuggage(false)).show();
                    }
                }

                @Override
                public void onAPIError(int errorCode, String errorMsg) {

                }
            });
        }
    }

    /*
     * 行李额查询
     * */
    public void getBaggagerule() {
        if (airType == 0) {
            AirGoBaggageRuleRequest request = new AirGoBaggageRuleRequest();
            request.airlineCode = customPriceDetailInfo.customDocGoFlightInfo.carrier;
            request.arrCode = customPriceDetailInfo.customDocGoFlightInfo.arrCode;
            request.cabin = customPriceDetailInfo.customDocGoPriceInfo.cabin;
            request.depCode = customPriceDetailInfo.customDocGoFlightInfo.depCode;
            request.depDate = customPriceDetailInfo.customDocGoFlightInfo.date;
            request.luggage = customPriceDetailInfo.customDocGoPriceInfo.luggage;
            request.saleDate = customPriceDetailInfo.customDocGoFlightInfo.date;
            request.call(new ApiLifeCallBack<BaggageRuleInfo>() {
                @Override
                public void onStart() {

                }

                @Override
                public void onFinsh() {

                }

                @Override
                public void onAPIResponse(BaggageRuleInfo response) {
                    if (response != null) {
                        new XPopup.Builder(AirReserveActivity.this)
                                .asCustom(new CustomLuggageBuyTicketNoticeView(AirReserveActivity.this
                                ).setType(airType).setGoData(response)).show();
                    }
                }

                @Override
                public void onAPIError(int errorCode, String errorMsg) {

                }
            });
        } else if (airType == 2) {
            BaggageRuleReqParamModel goBaggageRuleReqParamModel = new BaggageRuleReqParamModel();
            goBaggageRuleReqParamModel.airlineCode = customPriceDetailInfo.customDocGoBackFlightInfo.go.codeShare ? customPriceDetailInfo.customDocGoBackFlightInfo.go.mainCarrier : customPriceDetailInfo.customDocGoBackFlightInfo.go.carrier;
            goBaggageRuleReqParamModel.arrCode = customPriceDetailInfo.customDocGoBackFlightInfo.go.depAirportCode;
            goBaggageRuleReqParamModel.cabin = customPriceDetailInfo.customDocGoBackPriceInfo.cabin;
            goBaggageRuleReqParamModel.depCode = customPriceDetailInfo.customDocGoBackFlightInfo.go.arrAirportCode;
            goBaggageRuleReqParamModel.depDate = customPriceDetailInfo.customFightCityInfo.goDate;
            goBaggageRuleReqParamModel.saleDate = customPriceDetailInfo.customFightCityInfo.goDate;

            BaggageRuleReqParamModel backBaggageRuleReqParamModel = new BaggageRuleReqParamModel();
            backBaggageRuleReqParamModel.airlineCode = customPriceDetailInfo.customDocGoBackFlightInfo.back.codeShare ? customPriceDetailInfo.customDocGoBackFlightInfo.back.mainCarrier : customPriceDetailInfo.customDocGoBackFlightInfo.back.carrier;
            backBaggageRuleReqParamModel.arrCode = customPriceDetailInfo.customDocGoBackFlightInfo.back.depAirportCode;
            backBaggageRuleReqParamModel.cabin = customPriceDetailInfo.customDocGoBackPriceInfo.cabin;
            backBaggageRuleReqParamModel.depCode = customPriceDetailInfo.customDocGoBackFlightInfo.back.arrAirportCode;
            backBaggageRuleReqParamModel.depDate = customPriceDetailInfo.customFightCityInfo.backDate;
            backBaggageRuleReqParamModel.saleDate = customPriceDetailInfo.customFightCityInfo.backDate;

            List<BaggageRuleReqParamModel> baggageRuleReqParamModelList = new ArrayList<>();
            baggageRuleReqParamModelList.add(goBaggageRuleReqParamModel);
            baggageRuleReqParamModelList.add(backBaggageRuleReqParamModel);

            String baggageRuleReqJson = new Gson().toJson(baggageRuleReqParamModelList);


            AirGoBackBaggageRuleRequest request = new AirGoBackBaggageRuleRequest();
            request.listStr = baggageRuleReqJson;
            request.call(new ApiLifeCallBack<List<GoBackBaggageRuleInfo>>() {
                @Override
                public void onStart() {

                }

                @Override
                public void onFinsh() {

                }

                @Override
                public void onAPIResponse(List<GoBackBaggageRuleInfo> response) {
                    if (response != null) {
                        new XPopup.Builder(AirReserveActivity.this)
                                .asCustom(new CustomLuggageBuyTicketNoticeView(AirReserveActivity.this
                                ).setType(airType).setGoData(response.get(0).baggageRuleInfo).setBackData(response.get(1).baggageRuleInfo)).show();
                    }
                }

                @Override
                public void onAPIError(int errorCode, String errorMsg) {

                }
            });
        }
    }

    public void onClickReserve(View view) {
        if (view.getId() == R.id.cancel_change) {
            getRefundChange();
        } else if (view.getId() == R.id.rl_plane_info) {
            new XPopup.Builder(AirReserveActivity.this)
                    .enableDrag(false)
                    .asCustom(new CustomPlaneInfoView(this
                    ).setType(airType).setData(customPriceDetailInfo)).show();
        } else if (view.getId() == R.id.luggage_buyTicket_notice) {
            getBaggagerule();
        } else if (view.getId() == R.id.ticketPrice_detail) {
            new XPopup.Builder(AirReserveActivity.this)
                    .asCustom(new CustomTicketPriceDetailView(this
                    ).setType(airType).setData(priceDetailInfo)).show();
        } else if (view.getId() == R.id.selectUser) {
            startWith(RouteAirConstants.AIRPASSENGERLIST)
                    .withSerializable("customPriceDetailInfo", customPriceDetailInfo)
                    .withInt("airType", airType)
                    .navigation(AirReserveActivity.this, AirConstants.REQUEST_PASSENGER_CODE);
        } else if (view.getId() == R.id.tvReserveNotice) {
            new CustomPlaneProtocolDialog(AirReserveActivity.this)
                    .setTitle("机票预订须知")
                    .setData(NetworkConfig.getInstance().getBaseUrl() + "fhwl/static/html/jipiaoyuding.html")
                    .show();
        } else if (view.getId() == R.id.btn_submit) {
            if (passengerList.size() == 0) {
                ToastUtil.show("请选择乘机人");
                return;
            }
            if (TextUtils.isEmpty(binding.contactName.getText().toString().trim())) {
                ToastUtil.show("请填写联系人姓名");
                return;
            }
            if (TextUtils.isEmpty(binding.contactPhone.getText().toString().trim())) {
                ToastUtil.show("请填写联系人电话");
                return;
            } else {
                if (!RegUtils.isPhone(binding.contactPhone.getText().toString().trim())) {
                    ToastUtil.show("请填写正确的联系人电话");
                    return;
                }
            }

            if (priceDetailInfo.num == 0 && priceDetailInfo.cnum > 0) {
                String content = "儿童乘机须由18岁以上成人陪同，\n请添加成人";
                new XPopup.Builder(this)
                        .asCustom(new CustomDialog(AirReserveActivity.this).hideCancelBtn().setConfirmText("确定").setTitle("提示").setContent(content).setListener(null, null)).show();
                return;
            }

            if (priceDetailInfo.num > 0 && priceDetailInfo.cnum > priceDetailInfo.num * 2) {
                ToastUtil.show("一名成人最多携带2名儿童");
                return;
            }

            if (binding.switchButton.isChecked()) {
                if (invoicePosition == 0) {
                    ToastUtil.show("请选择发票类型");
                    return;
                }
                if ((invoicePosition == 1 || invoicePosition == 3 || invoicePosition == 4) && TextUtils.isEmpty(binding.editInvoiceTitle.getText().toString().trim())) {
                    ToastUtil.show("请填写发票抬头");
                    return;
                }
                if (invoicePosition == 1 || invoicePosition == 3 && TextUtils.isEmpty(binding.editNum.getText().toString().trim())) {
                    ToastUtil.show("请填写纳税人识别号");
                    return;
                }
                if (addressBean == null) {
                    ToastUtil.show("请选择收货地址");
                    return;
                }
                if (airType == 0 || airType == 2) {
                    docSubmit();
                } else {
                    interSubmit();
                }
            } else {
                new XPopup.Builder(this)
                        .asCustom(new CustomDialog(AirReserveActivity.this)
                                .setConfirmText("确定")
                                .setCancelText("取消")
                                .setContent("确认不需要报销凭证提交订单吗?")
                                .setListener(new CustomDialog.OnConfirmListener() {
                                    @Override
                                    public void onConfirm() {
                                        if (airType == 0 || airType == 2) {
                                            docSubmit();
                                        } else {
                                            interSubmit();
                                        }
                                    }
                                }, null))
                        .show();
            }
        } else if (view.getId() == R.id.priceInfo) {
            if (passengerList.size() == 0) {
                ToastUtil.show("请选择乘机人");
            } else {
                new XPopup.Builder(this)
                        .enableDrag(false)
                        .asCustom(new CustomTotalPriceInfoView(AirReserveActivity.this).setData(priceDetailInfo)).show();
            }
        } else if (view.getId() == R.id.invoiceType) {
            String[] strings1 = new String[]{"单位", "个人", "企业", "政府机关行政单位"};
            new XPopup.Builder(this)
                    .asCustom(new CustomListDialog(AirReserveActivity.this)
                            .setData(Arrays.asList(strings1))
                            .setOnItemClickListener(new CustomListDialog.OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {
                                    binding.tvInvoice.setText(strings1[position]);
                                    if (position == 2 || position == 0) {
                                        invoicePosition = 3;
                                    } else {
                                        invoicePosition = position + 1;
                                    }
                                    if (invoicePosition == 1 || invoicePosition == 3 || invoicePosition == 4) {
                                        binding.llInvoiceTitle.setVisibility(View.VISIBLE);
                                        binding.line1.setVisibility(View.VISIBLE);
                                    } else {
                                        binding.llInvoiceTitle.setVisibility(View.GONE);
                                        binding.line1.setVisibility(View.GONE);
                                    }
                                    if (invoicePosition == 1 || invoicePosition == 3) {
                                        binding.llIdentificationNum.setVisibility(View.VISIBLE);
                                        binding.line2.setVisibility(View.VISIBLE);
                                    } else {
                                        binding.llIdentificationNum.setVisibility(View.GONE);
                                        binding.line2.setVisibility(View.GONE);
                                    }
                                }
                            }))
                    .show();
        } else if (view.getId() == R.id.rl_address) {
            startWith(RouteCenterConstants.CENTERADDRESSMANAGERMENT)
                    .withBoolean("isSelect", true)
                    .navigation(AirReserveActivity.this, AirConstants.REQUEST_ADDRESS_CODE);
        } else if (view.getId() == R.id.identification_num_explain) {
            String content = "纳税人识别号是企业税务登记证上唯一识别企业的税号，由15/18或20位数码组成。根据国家税务局规定，自2017年7月1日起，开具增值税普通发票必须有纳税人识别号或统一社会信用代码，否则该发票为不符合规定的发票，不得作为税收凭证。纳税人识别号可登录纳税人信息查询网www.yibannashuiren.com 查询，或向公司财务人员咨询。";
            new XPopup.Builder(this)
                    .asCustom(new CustomDialog(AirReserveActivity.this).setContent(content)).show();
        } else if (view.getId() == R.id.phoneAreaCode) {
            start(RouteAirConstants.AIRCOUNTRYCODE, AirConstants.REQUEST_COUNTYR_CODE);
        }
    }


    /*
     * 国内预订
     * */
    public void docSubmit() {
        if (airType == 0) {
            DomesticBookRequest request = new DomesticBookRequest();
            request.accessToken = userInfo.loginInfo.accessToken;
            request.userId = userInfo.loginInfo.userId;
            request.depCode = customPriceDetailInfo.customDocGoFlightInfo.depCode;
            request.arrCode = customPriceDetailInfo.customDocGoFlightInfo.arrCode;
            request.code = customPriceDetailInfo.customDocGoFlightInfo.code;
            request.date = customPriceDetailInfo.customDocGoFlightInfo.date;
            request.carrier = customPriceDetailInfo.customDocGoFlightInfo.carrier;
            request.btime = customPriceDetailInfo.customDocGoFlightInfo.btime;
            request.isShowLoading = true;
            request.json = new Gson().toJson(customPriceDetailInfo.customDocGoPriceInfo);
            request.call(new ApiLifeCallBack<Object>() {
                @Override
                public void onStart() {
                    showloadDialog();
                }

                @Override
                public void onFinsh() {
                    goneloadDialog();
                }

                @Override
                public void onAPIResponse(Object response) {
                    String json = new Gson().toJson(response);
                    docCreateOrder(json);
                }

                @Override
                public void onAPIError(int errorCode, String errorMsg) {

                }
            });
        } else if (airType == 2) {
            DomesticGoBackBookRequest request = new DomesticGoBackBookRequest();
            request.accessToken = userInfo.loginInfo.accessToken;
            request.userId = userInfo.loginInfo.userId;
            request.bookingParamKey = customPriceDetailInfo.customDocGoBackPriceInfo.bookingParamKey;
            request.call(new ApiLifeCallBack<DocResultBookingInfo>() {
                @Override
                public void onStart() {

                }

                @Override
                public void onFinsh() {

                }

                @Override
                public void onAPIResponse(DocResultBookingInfo response) {
                    if (response != null) {
                        docGoBackCreateOrder(response);
                    }
                }

                @Override
                public void onAPIError(int errorCode, String errorMsg) {

                }
            });
        }
    }

    /*
     * 国际预订
     * */
    public void interSubmit() {
        InternationalBookRequest request = new InternationalBookRequest();
        request.accessToken = userInfo.loginInfo.accessToken;
        request.userId = userInfo.loginInfo.accessToken;
        request.source = "ICP_SELECT_open.3724";
        request.priceKey = customPriceDetailInfo.customInterPriceInfo.priceKey;
        request.call(new ApiLifeCallBack<Object>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onFinsh() {

            }

            @Override
            public void onAPIResponse(Object response) {
                if (response != null) {
                    String json = new Gson().toJson(response);
                    interCreateOrder(json);
                }
            }

            @Override
            public void onAPIError(int errorCode, String errorMsg) {

            }
        });
    }


    /*
     * 国内单程生单
     * */
    private String sjr = "";
    private String sjrPhone = "";
    private String address = "";
    private String receiverTitle = "";
    private String taxpayerId = "";

    public void docCreateOrder(String bkResult) {
        String passengerJson = new Gson().toJson(passengerList);

        if (binding.switchButton.isChecked()) {
            sjr = addressBean.getUserName();
            sjrPhone = addressBean.getPhone();
            address = binding.address.getText().toString().trim();
            if (invoicePosition == 2) {
                receiverTitle = "";
                taxpayerId = "";
            } else if (invoicePosition == 3) {
                receiverTitle = binding.editInvoiceTitle.getText().toString().trim();
                taxpayerId = binding.editNum.getText().toString().trim();
            } else {
                receiverTitle = binding.editInvoiceTitle.getText().toString().trim();
                taxpayerId = "";
            }
        } else {
            sjr = "";
            sjrPhone = "";
            address = "";
            receiverTitle = "";
            taxpayerId = "";
        }

        DomesticCreateOrderRequest request = new DomesticCreateOrderRequest();
        if (binding.switchButton.isChecked()) {
            request.receiverType = invoicePosition;
        } else {
            request.receiverType = -1;
        }
        request.isShowLoading = true;
        request.accessToken = userInfo.loginInfo.accessToken;
        request.userId = userInfo.loginInfo.userId;
        //request.contactPreNum = cityModel == null ? "86" : cityModel.getExtra().toString()
        request.contact = binding.contactName.getText().toString().trim();
        request.contactMob = binding.contactPhone.getText().toString().trim();
        request.cardNo = passengerList.get(0).cardNo;
        request.bookingResult = bkResult;
        request.needXcd = binding.switchButton.isChecked();
        request.address = address;
        request.passengerStr = passengerJson;
        request.receiverTitle = receiverTitle;
        request.taxpayerId = taxpayerId;
        request.sjr = sjr;
        request.sjrPhone = sjrPhone;
        request.call(new ApiLifeCallBack<CreateOrderInfo>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onFinsh() {

            }

            @Override
            public void onAPIResponse(CreateOrderInfo response) {
                if (response != null) {
                    payValidate(response.orderNo, response.noPayAmount);
                }
            }

            @Override
            public void onAPIError(int errorCode, String errorMsg) {

            }
        });
    }

    /*
     * 国内往返生单
     * */
    List<DocBookingPriceTagInfo> adultTagInfo;
    List<DocBookingPriceTagInfo> childrenTagInfo;
    List<DocBookingPriceTagInfo> babyTagInfo;

    public void docGoBackCreateOrder(DocResultBookingInfo docResultBookingInfo) {
        GoBackBookingInfo goBackBookingInfo = new GoBackBookingInfo();
        goBackBookingInfo.flightType = docResultBookingInfo.type;
        goBackBookingInfo.bookingTag = docResultBookingInfo.bookingTag;
        goBackBookingInfo.soloChild = docResultBookingInfo.tripInfos.get(0).clientBookingResult.get(0).policyInfo.soloChild ? 1 : 0;
        goBackBookingInfo.orderFrom = "DOMESTIC_ROUND_WAY_PACKAGE";

        String bookJson = new Gson().toJson(goBackBookingInfo);

        List<DocPassengerInfo> docPassengerInfoList = new ArrayList<>();
        // ②成人取ADU节点，儿童取CHI，婴儿取INF；key为：ADU，CHI，INF, CBA分别代表成人，儿童，婴儿，儿童买成人
        adultTagInfo = docResultBookingInfo.tripInfos.get(0).clientBookingResult.get(0).priceInfo.priceTag.get("ADU");
        if (docResultBookingInfo.tripInfos.get(0).clientBookingResult.get(0).priceInfo.priceTag.get("CHI") != null) {
            childrenTagInfo = docResultBookingInfo.tripInfos.get(0).clientBookingResult.get(0).priceInfo.priceTag.get("CHI");
        } else if (docResultBookingInfo.tripInfos.get(0).clientBookingResult.get(0).priceInfo.priceTag.get("CBA") != null) {
            childrenTagInfo = docResultBookingInfo.tripInfos.get(0).clientBookingResult.get(0).priceInfo.priceTag.get("CBA");
        }
        if (docResultBookingInfo.tripInfos.get(0).clientBookingResult.get(0).priceInfo.priceTag.get("INF") != null) {
            babyTagInfo = docResultBookingInfo.tripInfos.get(0).clientBookingResult.get(0).priceInfo.priceTag.get("INF");
        }

        List<String> aduPriceTags = new ArrayList<>();
        for (int j = 0; j < adultTagInfo.size(); j++) {
            aduPriceTags.add(adultTagInfo.get(j).productPackageCode);
        }
        List<String> childPriceTags = new ArrayList<>();
        if (babyTagInfo != null) {
            for (int j = 0; j < babyTagInfo.size(); j++) {
                childPriceTags.add(babyTagInfo.get(j).productPackageCode);
            }
        }
        for (int i = 0; i < passengerList.size(); i++) {
            if (passengerList.get(i).ageType == 1 && babyTagInfo == null) {
                ToastUtil.show("不支持儿童生单");
                return;
            }
        }

        for (int i = 0; i < passengerList.size(); i++) {
            DocPassengerInfo docPassengerInfo = new DocPassengerInfo();
            docPassengerInfo.ageType = passengerList.get(i).ageType;
            docPassengerInfo.birthday = passengerList.get(i).birthday;
            docPassengerInfo.cardNo = passengerList.get(i).cardNo;
            docPassengerInfo.mobile = binding.contactPhone.getText().toString().trim();
            docPassengerInfo.mobilePreNum = "86";
            docPassengerInfo.name = passengerList.get(i).name;
            docPassengerInfo.sex = passengerList.get(i).sex;
            docPassengerInfo.cardType = passengerList.get(i).cardType;
            if (passengerList.get(i).ageType == 0) {
                docPassengerInfo.priceTags = aduPriceTags;
            } else {
                docPassengerInfo.priceTags = childPriceTags;
            }
            docPassengerInfoList.add(docPassengerInfo);
        }

        String passengerJson = new Gson().toJson(docPassengerInfoList);
        ContactModel contactModel = new ContactModel();
        contactModel.mobile = binding.contactPhone.getText().toString().trim();
        //contactModel.mobilePreNum = cityModel == null ? "86" : cityModel.getExtra().toString();
        contactModel.mobilePreNum = "86";
        contactModel.name = binding.contactName.getText().toString().trim();
        String contactJson = new Gson().toJson(contactModel);

        if (binding.switchButton.isChecked()) {
            sjr = addressBean.getUserName();
            sjrPhone = addressBean.getPhone();
            address = binding.address.getText().toString().trim();
            if (invoicePosition == 2) {
                receiverTitle = "";
                taxpayerId = "";
            } else if (invoicePosition == 1 || invoicePosition == 3) {
                receiverTitle = binding.editInvoiceTitle.getText().toString().trim();
                taxpayerId = binding.editNum.getText().toString().trim();
            } else {
                receiverTitle = binding.editInvoiceTitle.getText().toString().trim();
                taxpayerId = "";
            }
        } else {
            sjr = "";
            sjrPhone = "";
            address = "";
            receiverTitle = "";
            taxpayerId = "";
        }

        ReimbursementModel reimbursementModel = new ReimbursementModel();
        reimbursementModel.xcd = binding.switchButton.isChecked();
        if (binding.switchButton.isChecked()) {
            reimbursementModel.receiverType = invoicePosition + "";
        }
        reimbursementModel.receiverTitle = receiverTitle;
        reimbursementModel.sjr = sjr;
        reimbursementModel.sjrPhone = sjrPhone;
        reimbursementModel.sjrAddress = address;
        reimbursementModel.taxPayerID = taxpayerId;
        reimbursementModel.xcdEmail = "";

        String reimbursementJson = new Gson().toJson(reimbursementModel);

        String tripItemsJson = new Gson().toJson(docResultBookingInfo.tripInfos.get(0).tripItems);

        UserClientInfo userClientInfo = new UserClientInfo();
        String userClientInfoJson = new Gson().toJson(userClientInfo);

        DomesticGoBackCreateOrderRequest request = new DomesticGoBackCreateOrderRequest();
        request.accessToken = userInfo.loginInfo.accessToken;
        request.userId = userInfo.loginInfo.userId;
        request.bookingInfo = bookJson;
        request.passengerInfos = passengerJson;
        request.contact = contactJson;
        request.tripItems = tripItemsJson;
        request.reimbursement = reimbursementJson;
        request.call(new ApiLifeCallBack<DocGoBackCreateOrderInfo>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onFinsh() {

            }

            @Override
            public void onAPIResponse(DocGoBackCreateOrderInfo response) {
                if (response != null) {
                    payValidate(response.orders.get(0).orderNo, response.noPayAmount);
                }
            }

            @Override
            public void onAPIError(int errorCode, String errorMsg) {

            }
        });
    }

    /*
     * 国际生单
     * */
    String bookingTagKey;

    public void interCreateOrder(String bkResult) {
        InterContactModel interContactModel = new InterContactModel();
        interContactModel.email = "694125155@qq.com";
        interContactModel.mobCountryCode = "86";
        interContactModel.mobile = binding.contactPhone.getText().toString().trim();
        interContactModel.name = "YANGQINBO";
        String contactJson = new Gson().toJson(interContactModel);
        List<InterPassengerInfo> interPassengerInfoList = new ArrayList<>();
        InterPassengerInfo passengerModel = new InterPassengerInfo();
        passengerModel.name = "YANG/QINBO";
        passengerModel.ageType = 0;
        passengerModel.birthday = "2001-08-03";
        passengerModel.gender = "M";
        passengerModel.cardNum = "E95920837";
        passengerModel.cardType = "PP";
        interPassengerInfoList.add(passengerModel);

        String passengerJson = new Gson().toJson(interPassengerInfoList);

        InterXcdInfo interXcdInfo = new InterXcdInfo();
        interXcdInfo.reimburseType = 0;
        String xcdJson = new Gson().toJson(interXcdInfo);
        try {
            JSONObject object = new JSONObject(bkResult);
            bookingTagKey = object.getString("bookingTagKey");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        InternationalCreateOrderRequest request = new InternationalCreateOrderRequest();
        request.accessToken = userInfo.loginInfo.accessToken;
        request.userId = userInfo.loginInfo.userId;
        request.bookingResult = bkResult;
        request.bookingTagKey = bookingTagKey;
        request.passengersStr = passengerJson;
        request.contact = contactJson;
        request.xcd = xcdJson;
        request.call(new ApiLifeCallBack<Object>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onFinsh() {

            }

            @Override
            public void onAPIResponse(Object response) {

            }

            @Override
            public void onAPIError(int errorCode, String errorMsg) {

            }
        });
    }

    /*
     * 飞机票验价
     * */
    public void payValidate(String orderNo, String noPayAmount) {
        AirValidateRequest request = new AirValidateRequest();
        request.isShowLoading = true;
        request.accessToken = AccountManager.getInstance().getToken();
        request.userId = AccountManager.getInstance().getUserId();
        request.orderNo = orderNo;
        request.bankCode = "ALIPAY";
        request.pmCode = "OUTDAIKOU";
        request.call(new ApiLifeCallBack<Object>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onFinsh() {

            }

            @Override
            public void onAPIResponse(Object response) {
                /*
                 * 1007是变价的code，需要提示用户机票变价
                 * */
                new XPopup.Builder(AirReserveActivity.this)
                        .enableDrag(false)
                        .asCustom(new CustomPayView(AirReserveActivity.this)
                                .setData(MathUtils.subZero(noPayAmount))
                                .setOnConfirmClickListener(new CustomPayView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(String payChannel) {
                                        paySign(orderNo, noPayAmount);
                                    }
                                })).show();
            }

            @Override
            public void onAPIError(int errorCode, String errorMsg) {

            }
        });
    }


    /*
     * 支付宝支付验证
     * */
    public void paySign(String orderNo, String noPayAmount) {
        PaySignRequest request = new PaySignRequest();
        request.appId = "";
        request.accessToken = AccountManager.getInstance().getToken();
        request.userId = AccountManager.getInstance().getUserId();
        request.orderNo = orderNo;
        request.channel = "alipay";
        request.amount = noPayAmount;
        request.type = "1";
        request.call(new ApiLifeCallBack<PayModel>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onFinsh() {

            }

            @Override
            public void onAPIResponse(PayModel response) {
                if (response != null) {
                    aliPay(response.payParam, response.orderNo);
                }
            }

            @Override
            public void onAPIError(int errorCode, String errorMsg) {

            }
        });
    }

    private void aliPay(String payProof, String orderNo) {
        PayHelper.aliPay(AirReserveActivity.this, payProof, new PayHelper.OnPayListener() {

            @Override
            public void onSuccess(int code, Object result) {
                ToastUtil.show("支付成功");
                finish();
            }

            @Override
            public void onFail(int code, String result) {
                ToastUtil.show("支付失败");
            }
        });
    }


    @Override
    public void initListener() {
        SoftKeyBoardListener.setListener(AirReserveActivity.this, new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {
                //Toast.makeText(AppActivity.this, "键盘显示 高度" + height, Toast.LENGTH_SHORT).show();
                binding.rlBottom.setVisibility(View.GONE);
            }

            @Override
            public void keyBoardHide(int height) {
                //Toast.makeText(AppActivity.this, "键盘隐藏 高度" + height, Toast.LENGTH_SHORT).show();
                binding.rlBottom.setVisibility(View.VISIBLE);
            }
        });

        binding.switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    binding.llReimbursement.setVisibility(View.VISIBLE);
                    if (airType == 2) {
                        priceDetailInfo.postage = 0;
                    } else {
                        priceDetailInfo.postage = 20;
                    }
                } else {
                    binding.llReimbursement.setVisibility(View.GONE);
                    priceDetailInfo.postage = 0;
                }
                if (passengerList != null && passengerList.size() > 0) {
                    calculationPrice(passengerList);
                }
            }
        });

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                passengerList.remove(position);
                mAdapter.notifyDataSetChanged();
                calculationPrice(passengerList);
            }
        });
    }

    public void calculationPrice(List<PassengerModel> list) {
        cCount = 0;
        count = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).ageType == 0) {
                count++;
            } else {
                cCount++;
            }
        }
        priceDetailInfo.num = count;
        priceDetailInfo.cnum = cCount;
        String totalAmount = MathUtils.subZero(String.valueOf((priceDetailInfo.price * priceDetailInfo.num) + (priceDetailInfo.cPrice * priceDetailInfo.cnum) + (priceDetailInfo.tof + priceDetailInfo.arf) * count + priceDetailInfo.postage));
        setSpannableString(totalAmount, binding.totalPrice);

    }

    public List<PassengerModel> removeDuplicate(List<PassengerModel> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = list.size() - 1; j > i; j--) {
                if (list.get(j).equals(list.get(i))) {
                    list.remove(j);
                }
            }
        }
        return list;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == AirConstants.REQUEST_PASSENGER_CODE) {
                ArrayList<PassengerModel> selectPassenger = data.getParcelableArrayListExtra(AirConstants.SELECT_PASSENGER);
                passengerList.addAll(selectPassenger);
                removeDuplicate(passengerList);
                mAdapter.notifyDataSetChanged();
                calculationPrice(passengerList);
            } else if (requestCode == AirConstants.REQUEST_ADDRESS_CODE) {
                addressBean = (AddressInfo.ShopAddressListBean) data.getSerializableExtra(AirConstants.SELECT_ADDRESS);
                if (addressBean != null) {
                    binding.name.setText(addressBean.getUserName());
                    binding.address.setText(addressBean.getProvinceName() + addressBean.getCityName() + addressBean.getAreaName() + addressBean.getDetailAddress());
                    binding.phone.setText(addressBean.getPhone());
                }
            } else if (requestCode == AirConstants.REQUEST_COUNTYR_CODE) {
                cityModel = (CityModel) data.getSerializableExtra(AirConstants.SELECT_COUNTRY_CODE);
                binding.phoneAreaCode.setText("+" + cityModel.getExtra().toString());
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private void setSpannableString(String str3, TextView view) {
        String str1 = "¥";
        view.setText("");
        SpannableString span1 = new SpannableString(str1);
        SpannableString span3 = new SpannableString(str3);
        ForegroundColorSpan colorSpan1 = new ForegroundColorSpan(Color.parseColor("#EA362C"));
        span1.setSpan(new AbsoluteSizeSpan(12, true), 0, str1.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        span1.setSpan(colorSpan1, 0, str1.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        ForegroundColorSpan colorSpan3 = new ForegroundColorSpan(Color.parseColor("#EA362C"));
        span3.setSpan(new AbsoluteSizeSpan(18, true), 0, str3.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        span3.setSpan(new StyleSpan(Typeface.BOLD), 0, str3.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        span3.setSpan(colorSpan3, 0, str3.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        view.append(span1);
        view.append(span3);

    }
}
