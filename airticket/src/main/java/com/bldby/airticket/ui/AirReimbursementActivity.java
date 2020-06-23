package com.bldby.airticket.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.airticket.databinding.ActivityAirReimbursementBinding;
import com.bldby.airticket.model.DomesticOrderDetailInfo;
import com.bldby.airticket.model.RefundServiceInfo;
import com.bldby.airticket.request.AirRefundXcdRequest;
import com.bldby.baselibrary.constants.RouteAirConstants;
import com.bldby.baselibrary.core.network.ApiLifeCallBack;
import com.bldby.baselibrary.core.ui.baseactivity.BaseUiActivity;
import com.bldby.loginlibrary.AccountManager;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

/**
 * package name: com.bldby.airticket.ui
 * user: yangqinbo
 * date: 2020/6/22
 * time: 17:48
 * email: 694125155@qq.com
 */

/*
 * 机票报销页面
 * */
@Route(path = RouteAirConstants.AIRREIMBURSEMENT)
public class AirReimbursementActivity extends BaseUiActivity {
    private ActivityAirReimbursementBinding binding;
    private int reimbursementPosition = -1;
    private int invoicePosition = -1;
    @Autowired
    public DomesticOrderDetailInfo docOrderDetailInfo;
    @Autowired
    public int orderStatus;

    @Override
    protected View initContentView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        binding = ActivityAirReimbursementBinding.inflate(layoutInflater, viewGroup, false);
        binding.setViewModel(this);
        return binding.getRoot();
    }

    @Override
    public void initView() {
        initTitle("索要报销凭证");
        refundXcd();
        reimbursementPosition = 2;
        binding.tvReimbursement.setText("差额发票");
        //reimbursementType.setVisibility(View.GONE);
    }

    @Override
    public void loadData() {

    }

    /*
     * 报销查询
     * */
    public void refundXcd() {
        AirRefundXcdRequest request = new AirRefundXcdRequest();
        request.accessToken = AccountManager.getInstance().getToken();
        request.userId = AccountManager.getInstance().getUserId();
        request.orderNo = docOrderDetailInfo.detail.orderNo;
        request.isShowLoading = true;
        request.call(new ApiLifeCallBack<RefundServiceInfo>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onFinsh() {

            }

            @Override
            public void onAPIResponse(RefundServiceInfo response) {
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
