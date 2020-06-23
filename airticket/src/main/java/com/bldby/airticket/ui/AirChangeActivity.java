package com.bldby.airticket.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.LinearLayoutManager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.airticket.AirConstants;
import com.bldby.airticket.R;
import com.bldby.airticket.adapter.AirChangeServiceAdapter;
import com.bldby.airticket.adapter.RefundTicketPassengerAdapter;
import com.bldby.airticket.databinding.ActivityAirChangeBinding;
import com.bldby.airticket.model.DocOrderDetailPassengersInfo;
import com.bldby.airticket.model.DomesticOrderDetailInfo;
import com.bldby.airticket.model.NationalPassengerInfo;
import com.bldby.airticket.model.PayModel;
import com.bldby.airticket.model.TimePointChargsInfo;
import com.bldby.airticket.request.AirChangeApplyRequest;
import com.bldby.airticket.request.AirChangeQueryRequest;
import com.bldby.airticket.request.PaySign2Request;
import com.bldby.airticket.request.PaySignRequest;
import com.bldby.airticket.view.CustomListDialog;
import com.bldby.baselibrary.constants.RouteAirConstants;
import com.bldby.baselibrary.core.network.ApiLifeCallBack;
import com.bldby.baselibrary.core.pay.PayHelper;
import com.bldby.baselibrary.core.ui.baseactivity.BaseUiActivity;
import com.bldby.baselibrary.core.util.MathUtils;
import com.bldby.baselibrary.core.util.ToastUtil;
import com.bldby.loginlibrary.AccountManager;
import com.google.gson.Gson;
import com.lxj.xpopup.XPopup;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * package name: com.bldby.airticket.ui
 * user: yangqinbo
 * date: 2020/6/22
 * time: 17:49
 * email: 694125155@qq.com
 */

/*
 * 机票改签页面
 * */
@Route(path = RouteAirConstants.AIRCHANGE)
public class AirChangeActivity extends BaseUiActivity {
    private ActivityAirChangeBinding binding;
    private RefundTicketPassengerAdapter mAdapter;
    private AirChangeServiceAdapter serviceAdapter;
    private List<String> reasonList = new ArrayList<>();
    private List<TimePointChargsInfo> timePointChargesList = new ArrayList<>();
    private String changeDate;
    private int index = -1;
    private NationalPassengerInfo passengerInfo;
    private String changePrice = "0";

    @Autowired
    public DomesticOrderDetailInfo docOrderDetailInfo;

    @Override
    protected View initContentView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        binding = ActivityAirChangeBinding.inflate(layoutInflater, viewGroup, false);
        binding.setViewModel(this);
        return binding.getRoot();
    }

    @Override
    public void initView() {
        initTitle("申请改签");
        String[] strings = docOrderDetailInfo.flightInfo.get(0).deptTime.split("-");
        changeDate = strings[0] + "-" + strings[1] + "-" + strings[2];
        List<DocOrderDetailPassengersInfo> passengerTypes = docOrderDetailInfo.passengers;
        mAdapter = new RefundTicketPassengerAdapter(passengerTypes);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        serviceAdapter = new AirChangeServiceAdapter(timePointChargesList);
        binding.serviceRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.serviceRecyclerView.setAdapter(serviceAdapter);
        serviceAdapter.notifyDataSetChanged();

        binding.recyclerView.setNestedScrollingEnabled(false);
        binding.serviceRecyclerView.setNestedScrollingEnabled(false);

        setSpannableString(changePrice, binding.price);
    }

    @Override
    public void loadData() {

    }


    public void onClickBtn(View view) {
        if (view.getId() == R.id.btn_bottom) {
            apply();
        } else if (view.getId() == R.id.rl_shippingSpace) {
            if (TextUtils.isEmpty(binding.tvDate.getText().toString())) {
                ToastUtil.show("请先选择要改签的日期");
            } else {
                if (reasonList != null && reasonList.size() > 0) {
                    selectShoppingSpaceDialog();
                }
            }
        } else if (view.getId() == R.id.select_date) {
            startWith(RouteAirConstants.SELECTDATE)
                    .withString("goDate", changeDate)
                    .withInt("searchType", 0)
                    .navigation(AirChangeActivity.this, AirConstants.REQUEST_DATE__CODE);
        }
    }

    public void apply() {
        if (TextUtils.isEmpty(binding.tvDate.getText().toString())) {
            ToastUtil.show("请选择改签日期");
            return;
        }
        if (TextUtils.isEmpty(binding.tvShippingSpace.getText().toString())) {
            ToastUtil.show("请选择改签原因");
            return;
        }
        if (!passengerInfo.changeSearchResult.canChange) {
            ToastUtil.show(passengerInfo.changeSearchResult.reason);
            return;
        }

        AirChangeApplyRequest request = new AirChangeApplyRequest();
        request.isShowLoading = true;
        request.applyRemarks = passengerInfo.changeSearchResult.tgqReasons.get(index).msg;
        request.cabinCode = passengerInfo.changeSearchResult.tgqReasons.get(index).changeFlightSegmentList.get(0).cabinCode;
        request.callbackUrl = "";
        request.changeCauseId = passengerInfo.changeSearchResult.tgqReasons.get(index).code + "";
        request.childExtraPrice = MathUtils.subZero(String.valueOf(passengerInfo.changeSearchResult.tgqReasons.get(index).changeFlightSegmentList.get(0).childUpgradeFee));
        request.childUseFee = MathUtils.subZero(String.valueOf(passengerInfo.changeSearchResult.tgqReasons.get(index).changeFlightSegmentList.get(0).childGqFee));
        request.endTime = passengerInfo.changeSearchResult.tgqReasons.get(index).changeFlightSegmentList.get(0).endTime;
        request.flightNo = passengerInfo.changeSearchResult.tgqReasons.get(index).changeFlightSegmentList.get(0).flightNo;
        request.gqFee = MathUtils.subZero(String.valueOf(passengerInfo.changeSearchResult.tgqReasons.get(index).changeFlightSegmentList.get(0).gqFee));
        request.orderNo = docOrderDetailInfo.detail.orderNo;
        request.passengerIds = passengerInfo.id + "";
        request.startDate = changeDate;
        request.startTime = passengerInfo.changeSearchResult.tgqReasons.get(index).changeFlightSegmentList.get(0).startTime;
        request.uniqKey = passengerInfo.changeSearchResult.tgqReasons.get(index).changeFlightSegmentList.get(0).uniqKey;
        request.upgradeFee = MathUtils.subZero(String.valueOf(passengerInfo.changeSearchResult.tgqReasons.get(index).changeFlightSegmentList.get(0).upgradeFee));
        request.call(new ApiLifeCallBack<List<NationalPassengerInfo>>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onFinsh() {

            }

            @Override
            public void onAPIResponse(List<NationalPassengerInfo> response) {
                if (response != null && response.get(0).changeApplyResult.success) {
                    if (passengerInfo.changeSearchResult.tgqReasons.get(index).changeFlightSegmentList.get(0).allFee == 0) {
                        ToastUtil.show("申请成功");
                    } else {
                        paySign(docOrderDetailInfo.detail.orderNo, MathUtils.subZero(String.valueOf(passengerInfo.changeSearchResult.tgqReasons.get(index).changeFlightSegmentList.get(0).allFee)), response.get(0).changeApplyResult.gqId);
                    }
                }
            }

            @Override
            public void onAPIError(int errorCode, String errorMsg) {

            }
        });
    }

    public void paySign(String orderNo, String noPayAmount, String gpId) {

        PaySign2Request request = new PaySign2Request();
        request.isShowLoading = true;
        request.appId = "";
        request.accessToken = AccountManager.getInstance().getToken();
        request.userId = AccountManager.getInstance().getUserId();
        request.orderNo = orderNo;
        request.channel = "alipay";
        request.amount = noPayAmount;
        request.type = "2";
        request.gqId = gpId;
        request.passengerIds = passengerInfo.id + "";
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

        PayHelper.aliPay(AirChangeActivity.this, payProof, new PayHelper.OnPayListener() {
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

    }

    public void selectShoppingSpaceDialog() {
        new XPopup.Builder(this)
                .asCustom(new CustomListDialog(AirChangeActivity.this)
                        .setData(reasonList)
                        .setOnItemClickListener(new CustomListDialog.OnItemClickListener() {
                            @Override
                            public void onItemClick(int position) {
                                binding.tvShippingSpace.setText(reasonList.get(position));
                                index = position;
                                changePrice = MathUtils.subZero(String.valueOf(passengerInfo.changeSearchResult.tgqReasons.get(index).changeFlightSegmentList.get(0).allFee));
                                setSpannableString(changePrice, binding.price);
                            }
                        }))
                .show();
    }

    /*
     * 改签查询
     * */
    public void checkChange() {
        AirChangeQueryRequest request = new AirChangeQueryRequest();
        request.isShowLoading = true;
        request.changeDate = changeDate;
        request.orderNo = docOrderDetailInfo.detail.orderNo;
        request.call(new ApiLifeCallBack<List<NationalPassengerInfo>>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onFinsh() {

            }

            @Override
            public void onAPIResponse(List<NationalPassengerInfo> response) {
                if (response != null) {
                    if (response.get(0).changeSearchResult.canChange && response.get(0).changeSearchResult.tgqReasons.get(0).changeFlightSegmentList != null) {
                        if (response.get(0).changeSearchResult.changeRuleInfo.timePointChargesList != null) {
                            timePointChargesList = response.get(0).changeSearchResult.changeRuleInfo.timePointChargesList;
                            passengerInfo = response.get(0);
                            serviceAdapter.setNewData(timePointChargesList);
                            serviceAdapter.notifyDataSetChanged();
                            reasonList.clear();
                            binding.tvShippingSpace.setText("");
                            if (passengerInfo.changeSearchResult.tgqReasons != null) {
                                for (int i = 0; i < passengerInfo.changeSearchResult.tgqReasons.size(); i++) {
                                    reasonList.add(passengerInfo.changeSearchResult.tgqReasons.get(i).msg);
                                }
                            }
                        }
                    } else {
                        ToastUtil.show(response.get(0).changeSearchResult.reason == null ? "当前日期没有可改签的航班" : response.get(0).changeSearchResult.reason);
                    }
                }
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
            if (requestCode == AirConstants.REQUEST_DATE__CODE) {
                changeDate = data.getStringExtra(AirConstants.SELECT_DATE).split("=")[0];
                binding.tvDate.setText(changeDate);
                checkChange();
            }
        }
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
