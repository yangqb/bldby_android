package com.bldby.airticket.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.airticket.R;
import com.bldby.airticket.adapter.RefundTicketPassengerAdapter;
import com.bldby.airticket.databinding.ActivityRefundTicketBinding;
import com.bldby.airticket.model.DocOrderDetailPassengersInfo;
import com.bldby.airticket.model.DomesticOrderDetailInfo;
import com.bldby.airticket.model.NationalPassengerInfo;
import com.bldby.airticket.request.AirApplyRefundRequest;
import com.bldby.airticket.request.AirRefundQueryRequest;
import com.bldby.airticket.view.CustomDialog;
import com.bldby.airticket.view.CustomListDialog;
import com.bldby.baselibrary.constants.RouteAirConstants;
import com.bldby.baselibrary.core.network.ApiLifeCallBack;
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
import java.util.Arrays;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * package name: com.bldby.airticket.ui
 * user: yangqinbo
 * date: 2020/6/22
 * time: 17:51
 * email: 694125155@qq.com
 */

/*
 * 机票退票页面
 * */
@Route(path = RouteAirConstants.AIRREFUND)
public class AirRefundActivity extends BaseUiActivity {
    private ActivityRefundTicketBinding binding;
    private int index = -1;
    private RefundTicketPassengerAdapter mAdapter;
    private NationalPassengerInfo passengerInfo;
    private List<NationalPassengerInfo> passengerInfos;
    private List<String> reasonList = new ArrayList<>();
    @Autowired
    public DomesticOrderDetailInfo docOrderDetailInfo;

    @Override
    protected View initContentView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        binding = ActivityRefundTicketBinding.inflate(layoutInflater, viewGroup, false);
        binding.setViewModel(this);
        return binding.getRoot();
    }

    @Override
    public void initView() {
        List<DocOrderDetailPassengersInfo> passengerTypes = docOrderDetailInfo.passengers;
        mAdapter = new RefundTicketPassengerAdapter(passengerTypes);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadData() {

        AirRefundQueryRequest request = new AirRefundQueryRequest();
        request.accessToken = AccountManager.getInstance().getToken();
        request.accessToken = AccountManager.getInstance().getUserId();
        request.orderNo = docOrderDetailInfo.detail.orderNo;
        request.isShowLoading = true;
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
                    passengerInfos = response;
                    passengerInfo = response.get(0);
                    if (passengerInfo.refundSearchResult.canRefund) {
                        reasonList.clear();
                        if (passengerInfo.refundSearchResult.tgqReasons != null) {
                            for (int i = 0; i < passengerInfo.refundSearchResult.tgqReasons.size(); i++) {
                                if (passengerInfo.refundSearchResult.tgqReasons.get(i).code != 19) { //去掉身体原因且有二级甲等医院<含>以上的医院证明）
                                    reasonList.add(passengerInfo.refundSearchResult.tgqReasons.get(i).msg);
                                }
                            }
                        }
                    } else {
                        ToastUtil.show(passengerInfo.refundSearchResult.reason);
                    }
                }
            }

            @Override
            public void onAPIError(int errorCode, String errorMsg) {

            }
        });
    }

    public void onClickBtn(View view) {
        if (view.getId() == R.id.refund_reason) {
            if (reasonList != null && reasonList.size() > 0) {
                selectShoppingSpaceDialog();
            }
        } else if (view.getId() == R.id.invoiceType) {
            String[] strings1 = new String[]{"企业", "个人", "政府机关行政单位"};
            new XPopup.Builder(this)
                    .asCustom(new CustomListDialog(AirRefundActivity.this)
                            .setData(Arrays.asList(strings1))
                            .setOnItemClickListener(new CustomListDialog.OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {
                                }
                            }))
                    .show();
        } else if (view.getId() == R.id.identification_num_explain) {
            String content = "纳税人识别号是企业税务登记证上唯一识别企业的税号，由15/18或20位数码组成。根据国家税务局规定，自2017年7月1日起，开具增值税普通发票必须有纳税人识别号或统一社会信用代码，否则该发票为不符合规定的发票，不得作为税收凭证。纳税人识别号可登录纳税人信息查询网www.yibannashuiren.com 查询，或向公司财务人员咨询。";
            new XPopup.Builder(AirRefundActivity.this)
                    .enableDrag(false)
                    .asCustom(new CustomDialog(AirRefundActivity.this).setContent(content)).show();
        } else if (view.getId() == R.id.submit) {
            apply();
        }
    }

    private int totalServiceAmount;
    private int totalRefundAmount;

    public void selectShoppingSpaceDialog() {
        new XPopup.Builder(this)
                .asCustom(new CustomListDialog(AirRefundActivity.this)
                        .setData(reasonList)
                        .setOnItemClickListener(new CustomListDialog.OnItemClickListener() {
                            @Override
                            public void onItemClick(int position) {
                                binding.tvReason.setText(reasonList.get(position));
                                index = position;
                                for (int i = 0; i < passengerInfos.size(); i++) {
                                    totalRefundAmount += passengerInfos.get(i).refundSearchResult.tgqReasons.get(index).refundPassengerPriceInfoList.get(0).refundFeeInfo.returnRefundFee;
                                    totalServiceAmount += passengerInfos.get(i).refundSearchResult.tgqReasons.get(index).refundPassengerPriceInfoList.get(0).refundFeeInfo.refundFee;
                                }
                                binding.serviceAmount.setText("¥" + MathUtils.subZero(String.valueOf(totalServiceAmount)));
                                binding.refundAmount.setText("¥" + MathUtils.subZero(String.valueOf(totalRefundAmount)));
                                binding.totalAmount.setText("¥" + MathUtils.subZero(String.valueOf(docOrderDetailInfo.passengerTypes.get(0).allPrices)));
                            }
                        }))
                .show();
    }

    public void apply() {
        if (TextUtils.isEmpty(binding.tvReason.getText().toString())) {
            ToastUtil.show("请选择退票原因");
            return;
        }
        if (!passengerInfo.refundSearchResult.canRefund) {
            ToastUtil.show(passengerInfo.refundSearchResult.reason);
            return;
        }

        AirApplyRefundRequest request = new AirApplyRefundRequest();
        request.isShowLoading = true;
        request.callbackUrl = "";//后台写死
        request.orderNo = docOrderDetailInfo.detail.orderNo;
        request.passengerIds = passengerInfo.id + "";
        request.refundCause = passengerInfo.refundSearchResult.tgqReasons.get(index).msg;
        request.refundCauseId = passengerInfo.refundSearchResult.tgqReasons.get(index).code + "";
        request.amount = String.valueOf(totalRefundAmount);
        request.call(new ApiLifeCallBack<List<NationalPassengerInfo>>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onFinsh() {

            }

            @Override
            public void onAPIResponse(List<NationalPassengerInfo> response) {
                if (response != null && response.get(0).refundApplyResult.success) {
                    ToastUtil.show("申请成功");
                    finish();
                }
            }

            @Override
            public void onAPIError(int errorCode, String errorMsg) {

            }
        });
    }

    @Override
    public void initListener() {

    }
}
