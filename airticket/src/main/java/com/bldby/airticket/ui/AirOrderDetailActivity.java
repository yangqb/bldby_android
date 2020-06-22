package com.bldby.airticket.ui;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.airticket.R;
import com.bldby.airticket.adapter.AirOrderPassengerAdapter;
import com.bldby.airticket.databinding.ActivityAirOrderDetailBinding;
import com.bldby.airticket.model.AirOrderInfo;
import com.bldby.airticket.model.AirOrderStatus;
import com.bldby.airticket.model.CustomAirPriceDetailInfo;
import com.bldby.airticket.model.DocOrderDetailPassengerTypesInfo;
import com.bldby.airticket.model.DocOrderDetailPassengersInfo;
import com.bldby.airticket.model.DomesticOrderDetailInfo;
import com.bldby.airticket.model.InterOrderDetailInfo;
import com.bldby.airticket.model.PayModel;
import com.bldby.airticket.model.PriceDetailInfo;
import com.bldby.airticket.request.AirDomesticGoBackOrderDetailRequest;
import com.bldby.airticket.request.AirDomesticOrderDetailRequest;
import com.bldby.airticket.request.AirInternationalOrderDetailRequest;
import com.bldby.airticket.request.AirValidateRequest;
import com.bldby.airticket.request.PaySignRequest;
import com.bldby.airticket.view.CustomPayView;
import com.bldby.airticket.view.CustomTotalPriceInfoView;
import com.bldby.baselibrary.constants.RouteAirConstants;
import com.bldby.baselibrary.core.network.ApiLifeCallBack;
import com.bldby.baselibrary.core.pay.PayHelper;
import com.bldby.baselibrary.core.ui.baseactivity.BaseUiActivity;
import com.bldby.baselibrary.core.util.DateUtil;
import com.bldby.baselibrary.core.util.MathUtils;
import com.bldby.baselibrary.core.util.ToastUtil;
import com.bldby.loginlibrary.AccountManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lxj.xpopup.XPopup;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import java.util.ArrayList;
import java.util.List;

/**
 * package name: com.bldby.airticket.ui
 * user: yangqinbo
 * date: 2020/6/22
 * time: 16:09
 * email: 694125155@qq.com
 */
@Route(path = RouteAirConstants.AIRORDERDETAIL)
public class AirOrderDetailActivity extends BaseUiActivity {
    private ActivityAirOrderDetailBinding binding;
    private AirOrderPassengerAdapter mAdapter;
    private DomesticOrderDetailInfo docOrderDetailInfo;
    private DocOrderDetailPassengerTypesInfo adultPassengerType;
    private DocOrderDetailPassengerTypesInfo childPassengerType;
    private PriceDetailInfo priceDetailInfo = new PriceDetailInfo();
    private List<DocOrderDetailPassengersInfo> passengers = new ArrayList<>();
    private long time;
    @Autowired
    public AirOrderInfo.AirOrderModel airOrderModel;

    @Override
    protected View initContentView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        binding = ActivityAirOrderDetailBinding.inflate(layoutInflater, viewGroup, false);
        binding.setViewModel(this);
        return binding.getRoot();
    }

    @Override
    public void initView() {
        binding.price.setText("¥" + MathUtils.subZero(String.valueOf(airOrderModel.noPayAmount)));
        if (airOrderModel.status == AirOrderStatus.BOOK_OK) {
            binding.tvStatus.setText("等待付款");
        } else if (airOrderModel.status == AirOrderStatus.PAY_OK || airOrderModel.status == AirOrderStatus.TICKET_LOCK) {
            binding.tvStatus.setText("等待出票");
        } else if (airOrderModel.status == AirOrderStatus.TICKET_OK) {
            binding.tvStatus.setText("出票完成");
        } else if (airOrderModel.status == AirOrderStatus.CANCEL_OK) {
            binding.tvStatus.setText("订单取消");
        } else if (airOrderModel.status == AirOrderStatus.WAIT_REFUNDMENT || airOrderModel.status == AirOrderStatus.APPLY_RETURN_PAY || airOrderModel.status == AirOrderStatus.APPLY_REFUNDMENT) {
            binding.tvStatus.setText("等待退款");
        } else if (airOrderModel.status == AirOrderStatus.REFUND_OK) {
            binding.tvStatus.setText("退款完成");
        } else if (airOrderModel.status == AirOrderStatus.APPLY_CHANGE) {
            binding.tvStatus.setText("改签中");
        } else if (airOrderModel.status == AirOrderStatus.CHANGE_OK) {
            binding.tvStatus.setText("改签完成");
        }

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new AirOrderPassengerAdapter(passengers);
        binding.recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        binding.goDepAirportName.setText(airOrderModel.dptAirport + airOrderModel.dptTerminal);
        binding.goArrAirportName.setText(airOrderModel.arrAirport + airOrderModel.arrTerminal);
        binding.goFlightNum.setText(airOrderModel.flightNum);
        if (airOrderModel.type == 1 || airOrderModel.type == 3) {
            binding.llGoBg.setVisibility(View.GONE);
            binding.tvGoTag.setVisibility(View.GONE);
            binding.llGoTitle.setVisibility(View.VISIBLE);
            binding.llComeDetail.setVisibility(View.GONE);
            if (airOrderModel.status == AirOrderStatus.BOOK_OK || airOrderModel.status == AirOrderStatus.CANCEL_OK) {
                binding.rlRefundChange.setVisibility(View.GONE);
            } else {
                binding.rlRefundChange.setVisibility(View.VISIBLE);
            }
           /* if (orderModel.status == PlaneOrderStatus.CHANGE_OK || orderModel.status == PlaneOrderStatus.APPLY_CHANGE) {
                rlLuggageChangeNotice.setVisibility(View.VISIBLE);
            } else {
                rlLuggageChangeNotice.setVisibility(View.GONE);
            }*/

            if (airOrderModel.status == AirOrderStatus.BOOK_OK) {
                binding.rlBottom.setVisibility(View.VISIBLE);
            } else {
                binding.rlBottom.setVisibility(View.GONE);
            }
        } else {
            binding.llGoBg.setVisibility(View.VISIBLE);
            binding.tvGoTag.setVisibility(View.VISIBLE);
            binding.llGoTitle.setVisibility(View.GONE);
            binding.llComeDetail.setVisibility(View.VISIBLE);
            if (airOrderModel.status == AirOrderStatus.BOOK_OK || airOrderModel.status == AirOrderStatus.CANCEL_OK) {
                binding.rlRefundChange.setVisibility(View.GONE);
            } else {
                binding.rlRefundChange.setVisibility(View.VISIBLE);
            }
            /*if (orderModel.status == PlaneOrderStatus.CHANGE_OK || orderModel.status == PlaneOrderStatus.APPLY_CHANGE) {
                rlLuggageChangeNotice.setVisibility(View.VISIBLE);
            } else {
                rlLuggageChangeNotice.setVisibility(View.GONE);
            }*/

            if (airOrderModel.status == AirOrderStatus.BOOK_OK) {
                binding.rlBottom.setVisibility(View.VISIBLE);
            } else {
                binding.rlBottom.setVisibility(View.GONE);
            }
            binding.backDepAirportName.setText(airOrderModel.backDptAirport + airOrderModel.backDptTerminal);
            binding.backArrAirportName.setText(airOrderModel.backArrAirport + airOrderModel.backArrTerminal);
            binding.backFlightNum.setText(airOrderModel.backFlightNum);
        }
    }

    @Override
    public void loadData() {
        if (airOrderModel.type == 3 || airOrderModel.type == 4) {
            //国际单程和往返
            AirInternationalOrderDetailRequest request = new AirInternationalOrderDetailRequest();
            request.accessToken = AccountManager.getInstance().getToken();
            request.userId = AccountManager.getInstance().getUserId();
            request.orderNo = airOrderModel.sourceOrderNo;
            request.call(new ApiLifeCallBack<InterOrderDetailInfo>() {
                @Override
                public void onStart() {

                }

                @Override
                public void onFinsh() {

                }

                @Override
                public void onAPIResponse(InterOrderDetailInfo response) {
                    String json = new Gson().toJson(response);
                           /* if (response.body().code == 0) {
                                InterOrderDetailInfo interOrderDetailInfo = response.body().result;

                                goDepDate.setText(DateUtils.strToStr(docOrderDetailInfo.flightInfo.get(0).deptTime) + DateUtils.strToDate2(DateUtils.strToStr2(docOrderDetailInfo.flightInfo.get(0).deptTime)));
                                goCityName.setText(docOrderDetailInfo.flightInfo.get(0).dptCity + "-" + docOrderDetailInfo.flightInfo.get(0).arrCity);
                                backDepDate.setText(DateUtils.strToStr(docOrderDetailInfo.flightInfo.get(1).deptTime) + DateUtils.strToDate2(DateUtils.strToStr2(docOrderDetailInfo.flightInfo.get(1).deptTime)));
                                backCityName.setText(docOrderDetailInfo.flightInfo.get(1).dptCity + "-" + docOrderDetailInfo.flightInfo.get(1).arrCity);
                                goDepTime.setText(docOrderDetailInfo.flightInfo.get(0).deptTime.split("-")[3]);
                                goArrTime.setText(docOrderDetailInfo.flightInfo.get(0).deptTime.split("-")[4]);
                                backDepTime.setText(docOrderDetailInfo.flightInfo.get(1).deptTime.split("-")[3]);
                                backArrTime.setText(docOrderDetailInfo.flightInfo.get(1).deptTime.split("-")[4]);
                                contactName.setText(docOrderDetailInfo.contacterInfo.name);
                                contactPhone.setText(docOrderDetailInfo.contacterInfo.mobile);

                                mAdapter.setNewData(docOrderDetailInfo.passengers);
                                mAdapter.notifyDataSetChanged();

                            }*/
                }

                @Override
                public void onAPIError(int errorCode, String errorMsg) {

                }
            });
        } else if (airOrderModel.type == 1) {
            //国内单程
            AirDomesticOrderDetailRequest request = new AirDomesticOrderDetailRequest();
            request.accessToken = AccountManager.getInstance().getToken();
            request.userId = AccountManager.getInstance().getUserId();
            request.orderNo = airOrderModel.sourceOrderNo;
            request.isShowLoading = true;
            request.call(new ApiLifeCallBack<DomesticOrderDetailInfo>() {
                @Override
                public void onStart() {

                }

                @Override
                public void onFinsh() {

                }

                @Override
                public void onAPIResponse(DomesticOrderDetailInfo response) {
                    docOrderDetailInfo = response;
                    time = (docOrderDetailInfo.expiresDate - docOrderDetailInfo.nowTimeStamp) / 1000;
                    if (airOrderModel.status == AirOrderStatus.BOOK_OK) {
                        countDownTimer();
                    }
                    if (airOrderModel.status == AirOrderStatus.TICKET_OK) {
                        binding.tvCountdown.setText("奖励¥" + MathUtils.subZero(String.valueOf(MathUtils.multiply(docOrderDetailInfo.discount, airOrderModel.noPayAmount))));
                        binding.tvCountdown.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                    }
                    binding.goDate.setText(DateUtil.strToStr(docOrderDetailInfo.flightInfo.get(0).deptTime) + DateUtil.strToWeek("yyyy-MM-dd", DateUtil.strToStr2(docOrderDetailInfo.flightInfo.get(0).deptTime)));
                    binding.goCity.setText(docOrderDetailInfo.flightInfo.get(0).dptCity + "-" + docOrderDetailInfo.flightInfo.get(0).arrCity);
                    binding.goDepTime.setText(docOrderDetailInfo.flightInfo.get(0).deptTime.split("-")[3]);
                    binding.goArrTime.setText(docOrderDetailInfo.flightInfo.get(0).deptTime.split("-")[4]);
                    binding.contactName.setText(docOrderDetailInfo.contacterInfo.name);
                    binding.contactPhone.setText(docOrderDetailInfo.contacterInfo.mobile.substring(0, 3) + "****" + docOrderDetailInfo.contacterInfo.mobile.substring(8, 11));
                    passengers = docOrderDetailInfo.passengers;
                    mAdapter.setNewData(passengers);
                    mAdapter.notifyDataSetChanged();

                    if (docOrderDetailInfo.passengerTypes.size() > 0) {
                        if ("成人".equals(docOrderDetailInfo.passengerTypes.get(0).ageType)) {
                            adultPassengerType = docOrderDetailInfo.passengerTypes.get(0);
                        } else {
                            childPassengerType = docOrderDetailInfo.passengerTypes.get(0);
                        }
                    }

                    if (docOrderDetailInfo.passengerTypes.size() > 1) {
                        if ("成人".equals(docOrderDetailInfo.passengerTypes.get(1).ageType)) {
                            adultPassengerType = docOrderDetailInfo.passengerTypes.get(1);
                        } else {
                            childPassengerType = docOrderDetailInfo.passengerTypes.get(1);
                        }
                    }
                    if (adultPassengerType != null) {
                        priceDetailInfo.price = adultPassengerType.realPrice;
                        priceDetailInfo.arf = adultPassengerType.constructionFee; //机建加燃油
                        priceDetailInfo.num = adultPassengerType.Count;
                    } else {
                        priceDetailInfo.price = 0;
                        priceDetailInfo.arf = 0;
                        priceDetailInfo.num = 0;

                    }
                    if (childPassengerType != null) {
                        priceDetailInfo.cPrice = childPassengerType.realPrice;
                        priceDetailInfo.cnum = childPassengerType.Count;
                    } else {
                        priceDetailInfo.cPrice = 0;
                        priceDetailInfo.cnum = 0;
                    }
                    priceDetailInfo.tof = 0;
                    if (!TextUtils.isEmpty(docOrderDetailInfo.xcd.sjr)) {
                        priceDetailInfo.postage = 20;
                    }
                }

                @Override
                public void onAPIError(int errorCode, String errorMsg) {

                }
            });
        } else {
            //国内往返
            AirDomesticGoBackOrderDetailRequest request = new AirDomesticGoBackOrderDetailRequest();
            request.accessToken = AccountManager.getInstance().getToken();
            request.userId = AccountManager.getInstance().getUserId();
            request.orderNo = airOrderModel.sourceOrderNo;
            request.isShowLoading = true;
            request.call(new ApiLifeCallBack<DomesticOrderDetailInfo>() {
                @Override
                public void onStart() {

                }

                @Override
                public void onFinsh() {

                }

                @Override
                public void onAPIResponse(DomesticOrderDetailInfo response) {
                    docOrderDetailInfo = response;
                    time = (docOrderDetailInfo.expiresDate - docOrderDetailInfo.nowTimeStamp) / 1000;
                    if (airOrderModel.status == AirOrderStatus.BOOK_OK) {
                        countDownTimer();
                    }
                    if (airOrderModel.status == AirOrderStatus.TICKET_OK) {
                        binding.tvCountdown.setText("奖励¥" + MathUtils.subZero(String.valueOf(MathUtils.multiply(docOrderDetailInfo.discount, airOrderModel.noPayAmount))));
                        binding.tvCountdown.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                    }
                    binding.goDepDate.setText(DateUtil.strToStr(docOrderDetailInfo.flightInfo.get(0).deptTime) + DateUtil.strToWeek("yyyy-MM-dd", DateUtil.strToStr2(docOrderDetailInfo.flightInfo.get(0).deptTime)));
                    binding.goCityName.setText(docOrderDetailInfo.flightInfo.get(0).dptCity + "-" + docOrderDetailInfo.flightInfo.get(0).arrCity);
                    binding.backDepDate.setText(DateUtil.strToStr(docOrderDetailInfo.flightInfo.get(1).deptTime) + DateUtil.strToWeek("yyyy-MM-dd", DateUtil.strToStr2(docOrderDetailInfo.flightInfo.get(1).deptTime)));
                    binding.backCityName.setText(docOrderDetailInfo.flightInfo.get(1).dptCity + "-" + docOrderDetailInfo.flightInfo.get(1).arrCity);
                    binding.goDepTime.setText(docOrderDetailInfo.flightInfo.get(0).deptTime.split("-")[3]);
                    binding.goArrTime.setText(docOrderDetailInfo.flightInfo.get(0).deptTime.split("-")[4]);
                    binding.backDepTime.setText(docOrderDetailInfo.flightInfo.get(1).deptTime.split("-")[3]);
                    binding.backArrTime.setText(docOrderDetailInfo.flightInfo.get(1).deptTime.split("-")[4]);
                    binding.contactName.setText(docOrderDetailInfo.contacterInfo.name);
                    binding.contactPhone.setText(docOrderDetailInfo.contacterInfo.mobile.substring(0, 3) + "****" + docOrderDetailInfo.contacterInfo.mobile.substring(8, 11));
                    passengers = docOrderDetailInfo.passengers;
                    mAdapter.setNewData(passengers);
                    mAdapter.notifyDataSetChanged();

                    if (docOrderDetailInfo.passengerTypes.size() > 0) {
                        if ("成人".equals(docOrderDetailInfo.passengerTypes.get(0).ageType)) {
                            adultPassengerType = docOrderDetailInfo.passengerTypes.get(0);
                        } else {
                            childPassengerType = docOrderDetailInfo.passengerTypes.get(0);
                        }
                    }

                    if (docOrderDetailInfo.passengerTypes.size() > 1) {
                        if ("成人".equals(docOrderDetailInfo.passengerTypes.get(1).ageType)) {
                            adultPassengerType = docOrderDetailInfo.passengerTypes.get(1);
                        } else {
                            childPassengerType = docOrderDetailInfo.passengerTypes.get(1);
                        }
                    }
                    if (adultPassengerType != null) {
                        priceDetailInfo.price = adultPassengerType.realPrice;
                        priceDetailInfo.arf = adultPassengerType.constructionFee; //机建加燃油
                        priceDetailInfo.num = adultPassengerType.Count;
                    } else {
                        priceDetailInfo.price = 0;
                        priceDetailInfo.arf = 0;
                        priceDetailInfo.num = 0;

                    }
                    if (childPassengerType != null) {
                        priceDetailInfo.cPrice = childPassengerType.realPrice;
                        priceDetailInfo.cnum = childPassengerType.Count;
                    } else {
                        priceDetailInfo.cPrice = 0;
                        priceDetailInfo.cnum = 0;
                    }
                    priceDetailInfo.tof = 0;
                    priceDetailInfo.postage = 0;
                              /*  if (!TextUtils.isEmpty(docOrderDetailInfo.xcd.sjr)) {
                                    priceDetailInfo.postage = 20;
                                }*/
                }

                @Override
                public void onAPIError(int errorCode, String errorMsg) {

                }
            });
        }
    }

    public void onClickBtn(View view) {
        if (view.getId() == R.id.btn_reimbursement) {
            if (airOrderModel.status == AirOrderStatus.REFUND_OK || airOrderModel.status == AirOrderStatus.WAIT_REFUNDMENT
                    || airOrderModel.status == AirOrderStatus.APPLY_RETURN_PAY || airOrderModel.status == AirOrderStatus.APPLY_REFUNDMENT) {
                startWith(RouteAirConstants.AIRREIMBURSEMENT).withInt("orderStatus", airOrderModel.status).withSerializable("docOrderDetailInfo", docOrderDetailInfo).navigation();
            } else {
                ToastUtil.show("当前订单状态报销请联系客服");
            }
        } else if (view.getId() == R.id.btn_change) {
            startWith(RouteAirConstants.AIRCHANGE).withSerializable("docOrderDetailInfo", docOrderDetailInfo).navigation();
        } else if (view.getId() == R.id.btn_refund) {
            if (airOrderModel.status == AirOrderStatus.TICKET_OK ||
                    airOrderModel.status == AirOrderStatus.CHANGE_OK) {
                startWith(RouteAirConstants.AIRREFUND).withSerializable("docOrderDetailInfo", docOrderDetailInfo).navigation();
            } else {
                ToastUtil.show("当前订单状态不可退票");
            }
        } else if (view.getId() == R.id.price) {
            new XPopup.Builder(this)
                    .enableDrag(false)
                    .asCustom(new CustomTotalPriceInfoView(AirOrderDetailActivity.this).setData(priceDetailInfo)).show();
        } else if (view.getId() == R.id.pay) {
            payValidate();
        }
    }

    public void payValidate() {
        AirValidateRequest request = new AirValidateRequest();
        request.accessToken = AccountManager.getInstance().getToken();
        request.userId = AccountManager.getInstance().getUserId();
        request.orderNo = airOrderModel.sourceOrderNo;
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
                new XPopup.Builder(AirOrderDetailActivity.this)
                        .enableDrag(false)
                        .asCustom(new CustomPayView(AirOrderDetailActivity.this)
                                .setData(MathUtils.subZero(String.valueOf(airOrderModel.noPayAmount)))
                                .setOnConfirmClickListener(new CustomPayView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(String payChannel) {
                                        paySign(airOrderModel.sourceOrderNo, MathUtils.subZero(String.valueOf(airOrderModel.noPayAmount)));
                                    }
                                })).show();
            }

            @Override
            public void onAPIError(int errorCode, String errorMsg) {

            }
        });
    }

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
        PayHelper.aliPay(AirOrderDetailActivity.this, payProof, new PayHelper.OnPayListener() {
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
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                //获取剪贴版
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                //创建ClipData对象
                //第一个参数只是一个标记，随便传入。
                //第二个参数是要复制到剪贴版的内容
                ClipData clip = ClipData.newPlainText("simple text", passengers.get(position).ticketNo);
                //传入clipdata对象.
                clipboard.setPrimaryClip(clip);
                ToastUtil.show("已复制");
            }
        });
    }

    private CountDownTimer countDownTimer;

    public void countDownTimer() {
        countDownTimer = new CountDownTimer(time * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (!AirOrderDetailActivity.this.isFinishing()) {
                    time = millisUntilFinished / 1000;
                    String formatLongToTimeStr = DateUtil.formatTime2(millisUntilFinished);
                    binding.tvCountdown.setText("剩" + formatLongToTimeStr + "自动取消订单");
                }
            }

            /**
             *倒计时结束后调用的
             */
            @Override
            public void onFinish() {
                ToastUtil.show("订单已关闭，请重新预定机票");
            }

        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }
}
