package com.bldby.airticket.ui;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.LinearLayoutManager;
import android.text.SpannableString;
import android.text.Spanned;
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
import com.bldby.airticket.R;
import com.bldby.airticket.adapter.SelectPassengerAdapter;
import com.bldby.airticket.databinding.ActivityAirReserveBinding;
import com.bldby.airticket.model.BaggageRuleInfo;
import com.bldby.airticket.model.BaggageRuleReqParamModel;
import com.bldby.airticket.model.CustomAirPriceDetailInfo;
import com.bldby.airticket.model.GoBackBaggageRuleInfo;
import com.bldby.airticket.model.GoBackFlight;
import com.bldby.airticket.model.GoBackTripInfo;
import com.bldby.airticket.model.PassengerModel;
import com.bldby.airticket.model.PriceDetailInfo;
import com.bldby.airticket.model.RefundChangeInfo;
import com.bldby.airticket.request.AirGoBackBaggageRuleRequest;
import com.bldby.airticket.request.AirGoBackRefundChangeQueryRequest;
import com.bldby.airticket.request.AirGoBaggageRuleRequest;
import com.bldby.airticket.request.AirGoRefundChangeQueryRequest;
import com.bldby.airticket.view.CustomCancelChangePopView;
import com.bldby.airticket.view.CustomLuggageBuyTicketNoticeView;
import com.bldby.baselibrary.constants.RouteAirConstants;
import com.bldby.baselibrary.core.network.ApiLifeCallBack;
import com.bldby.baselibrary.core.ui.baseactivity.BaseAirUiActivity;
import com.bldby.baselibrary.core.util.DateUtil;
import com.bldby.baselibrary.core.util.MathUtils;
import com.bldby.baselibrary.core.util.SoftKeyBoardListener;
import com.bldby.centerlibrary.model.AddressInfo;
import com.bldby.centerlibrary.request.AddressListRequest;
import com.bldby.loginlibrary.AccountManager;
import com.bldby.loginlibrary.model.UserInfo;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lxj.xpopup.XPopup;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
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
    private PriceDetailInfo priceDetailInfo = new PriceDetailInfo();
    private SelectPassengerAdapter mAdapter;
    private List<PassengerModel> passengerList = new ArrayList<>();
    private List<AddressInfo.ShopAddressListBean> addressInfos = new ArrayList<>();
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
            binding.tvGoInfo.setText(DateUtil.strToStr(customPriceDetailInfo.customDocGoFlightInfo.date) + DateUtil.strToWeek("yyyy-MM-dd", customPriceDetailInfo.customDocGoFlightInfo.date) + customPriceDetailInfo.customDocGoFlightInfo.btime + customPriceDetailInfo.customDocGoFlightInfo.depAirport + customPriceDetailInfo.customDocGoFlightInfo.depTerminal + "-" + customPriceDetailInfo.customDocGoFlightInfo.arrAirport + customPriceDetailInfo.customDocGoFlightInfo.arrTerminal);
            binding.price.setText("¥" + MathUtils.subZero(String.valueOf(customPriceDetailInfo.customDocGoPriceInfo.barePrice)));
            binding.arfAndTof.setText("机建+燃油 ¥" + MathUtils.subZero(String.valueOf(customPriceDetailInfo.customDocGoFlightInfo.tof + customPriceDetailInfo.customDocGoFlightInfo.arf)));
            if (customPriceDetailInfo.customDocGoPriceInfo.cabinType == 0) {
                binding.tvCabinType.setText("经济舱(" + customPriceDetailInfo.customDocGoPriceInfo.cabin + ")");
            } else if (customPriceDetailInfo.customDocGoPriceInfo.cabinType == 1) {
                binding.tvCabinType.setText("头等舱(" + customPriceDetailInfo.customDocGoPriceInfo.cabin + ")");
            } else if (customPriceDetailInfo.customDocGoPriceInfo.cabinType == 2) {
                binding.tvCabinType.setText("商务舱(" + customPriceDetailInfo.customDocGoPriceInfo.cabin + ")");
            } else if (customPriceDetailInfo.customDocGoPriceInfo.cabinType == 3) {
                binding.tvCabinType.setText("经济舱精选(" + customPriceDetailInfo.customDocGoPriceInfo.cabin + ")");
            } else if (customPriceDetailInfo.customDocGoPriceInfo.cabinType == 4) {
                binding.tvCabinType.setText("经济舱y舱(" + customPriceDetailInfo.customDocGoPriceInfo.cabin + ")");
            } else if (customPriceDetailInfo.customDocGoPriceInfo.cabinType == 5) {
                binding.tvCabinType.setText("超值头等舱(" + customPriceDetailInfo.customDocGoPriceInfo.cabin + ")");
            } else if (customPriceDetailInfo.customDocGoPriceInfo.cabinType == -1) {
                binding.tvCabinType.setText("未配置");
            }
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
            binding.tvGoInfo.setText(DateUtil.strToStr(interGo.flightSegments.get(0).depDate) + DateUtil.strToWeek("yyyy-MM-dd", interGo.flightSegments.get(0).depDate)
                    + interGo.flightSegments.get(0).depTime + interGo.flightSegments.get(0).depAirportName + interGo.flightSegments.get(0).depTerminal + "-"
                    + interGo.flightSegments.get(interGo.flightSegments.size() - 1).arrAirportName + interGo.flightSegments.get(interGo.flightSegments.size() - 1).arrTerminal);
            binding.price.setText("¥" + MathUtils.subZero(String.valueOf(customPriceDetailInfo.customInterPriceInfo.price)));
            binding.arfAndTof.setText("机建+燃油 ¥0");
            if ("economy".equals(customPriceDetailInfo.customInterPriceInfo.cabinLevel)) {
                binding.tvCabinType.setText("经济舱(" + customPriceDetailInfo.customInterPriceInfo.cabin + ")");
            } else if ("first".equals(customPriceDetailInfo.customInterPriceInfo.cabinLevel)) {
                binding.tvCabinType.setText("头等舱(" + customPriceDetailInfo.customInterPriceInfo.cabin + ")");
            } else if ("business".equals(customPriceDetailInfo.customInterPriceInfo.cabinLevel)) {
                binding.tvCabinType.setText("商务舱(" + customPriceDetailInfo.customInterPriceInfo.cabin + ")");
            } else {
                binding.tvCabinType.setText("未配置");
            }
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
                binding.tvGoInfo.setText(DateUtil.strToStr(customPriceDetailInfo.customFightCityInfo.goDate) + DateUtil.strToWeek("yyyy-MM-dd", customPriceDetailInfo.customFightCityInfo.goDate) + docGoFlight.depTime + docGoFlight.depAirport + docGoFlight.depTerminal + "-" + docGoFlight.arrAirport + docGoFlight.arrTerminal);
                binding.tvBackInfo.setText(DateUtil.strToStr(customPriceDetailInfo.customFightCityInfo.backDate) + DateUtil.strToWeek("yyyy-MM-dd", customPriceDetailInfo.customFightCityInfo.backDate) + docBackFlight.depTime + docBackFlight.depAirport + docBackFlight.depTerminal + "-" + docBackFlight.arrAirport + docBackFlight.arrTerminal);
                binding.price.setText("¥" + MathUtils.subZero(String.valueOf(customPriceDetailInfo.customDocGoBackPriceInfo.barePrice)));
                binding.arfAndTof.setText("机建+燃油 ¥" + MathUtils.subZero(String.valueOf(customPriceDetailInfo.customDocGoBackFlightInfo.tof + customPriceDetailInfo.customDocGoBackFlightInfo.arf)));
                binding.tvCabinType.setText(customPriceDetailInfo.customDocGoBackPriceInfo.cabinDesc);
            } else {
                priceDetailInfo.price = customPriceDetailInfo.customInterPriceInfo.price;
                priceDetailInfo.arf = 0;
                priceDetailInfo.tof = 0;
                priceDetailInfo.cPrice = customPriceDetailInfo.customInterPriceInfo.cPrice;
                GoBackTripInfo interGo = customPriceDetailInfo.customInterFlightInfo.goTrip;
                GoBackTripInfo interBack = customPriceDetailInfo.customInterFlightInfo.backTrip;
                binding.tvGoInfo.setText(DateUtil.strToStr(interGo.flightSegments.get(0).depDate) + DateUtil.strToWeek("yyyy-MM-dd", interGo.flightSegments.get(0).depDate)
                        + interGo.flightSegments.get(0).depTime + interGo.flightSegments.get(0).depAirportName + interGo.flightSegments.get(0).depTerminal + "-"
                        + interGo.flightSegments.get(interGo.flightSegments.size() - 1).arrAirportName + interGo.flightSegments.get(interGo.flightSegments.size() - 1).arrTerminal);
                binding.tvBackInfo.setText(DateUtil.strToStr(interBack.flightSegments.get(0).depDate) + DateUtil.strToWeek("yyyy-MM-dd", interBack.flightSegments.get(0).depDate)
                        + interBack.flightSegments.get(0).depTime + interBack.flightSegments.get(0).depAirportName + interBack.flightSegments.get(0).depTerminal + "-"
                        + interBack.flightSegments.get(interBack.flightSegments.size() - 1).arrAirportName + interBack.flightSegments.get(interBack.flightSegments.size() - 1).arrTerminal);
                binding.price.setText("¥" + MathUtils.subZero(String.valueOf(customPriceDetailInfo.customInterPriceInfo.price)));
                binding.arfAndTof.setText("机建+燃油 ¥0");


                if ("economy".equals(customPriceDetailInfo.customInterPriceInfo.cabinLevel.split("/")[0])) {
                    binding.tvCabinType.setText("经济舱(" + customPriceDetailInfo.customInterPriceInfo.cabin.split("/")[0] + ")");
                } else if ("first".equals(customPriceDetailInfo.customInterPriceInfo.cabinLevel.split("/")[0])) {
                    binding.tvCabinType.setText("头等舱(" + customPriceDetailInfo.customInterPriceInfo.cabin.split("/")[0] + ")");
                } else if ("business".equals(customPriceDetailInfo.customInterPriceInfo.cabinLevel.split("/")[0])) {
                    binding.tvCabinType.setText("商务舱(" + customPriceDetailInfo.customInterPriceInfo.cabin.split("/")[0] + ")");
                } else {
                    binding.tvCabinType.setText("未配置");
                }
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
        request.accessToken = userInfo.accessToken;
        request.userId = userInfo.userId;
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

    @SuppressLint("SetTextI18n")
    private void setSpannableString(String str3, TextView view) {
        String str1 = "¥";
        view.setText("");
        SpannableString span1 = new SpannableString(str1);
        SpannableString span3 = new SpannableString(str3);
        ForegroundColorSpan colorSpan1 = new ForegroundColorSpan(Color.parseColor("#FF8300"));
        span1.setSpan(new AbsoluteSizeSpan(15, true), 0, str1.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        span1.setSpan(colorSpan1, 0, str1.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        ForegroundColorSpan colorSpan3 = new ForegroundColorSpan(Color.parseColor("#FF8300"));
        span3.setSpan(new AbsoluteSizeSpan(21, true), 0, str3.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        span3.setSpan(new StyleSpan(Typeface.BOLD), 0, str3.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        span3.setSpan(colorSpan3, 0, str3.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        view.append(span1);
        view.append(span3);

    }
}
