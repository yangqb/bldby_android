package com.bldby.airticket.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.airticket.AirConstants;
import com.bldby.airticket.R;
import com.bldby.airticket.databinding.ActivityAirReimbursementBinding;
import com.bldby.airticket.model.AskForResultInfo;
import com.bldby.airticket.model.DomesticOrderDetailInfo;
import com.bldby.airticket.model.PayModel;
import com.bldby.airticket.model.RefundServiceInfo;
import com.bldby.airticket.request.AirRefundAskForRequest;
import com.bldby.airticket.request.AirRefundXcdRequest;
import com.bldby.airticket.request.ItineraryAskForRequest;
import com.bldby.airticket.request.ItineraryQueryRequest;
import com.bldby.airticket.request.PaySignRequest;
import com.bldby.airticket.view.CustomDialog;
import com.bldby.airticket.view.CustomListDialog;
import com.bldby.baselibrary.constants.RouteAirConstants;
import com.bldby.baselibrary.core.network.ApiLifeCallBack;
import com.bldby.baselibrary.core.pay.PayHelper;
import com.bldby.baselibrary.core.ui.baseactivity.BaseUiActivity;
import com.bldby.baselibrary.core.util.ToastUtil;
import com.bldby.centerlibrary.model.AddressInfo;
import com.bldby.centerlibrary.request.AddressListRequest;
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
    private boolean canApply;
    private String invoiceStringType;
    private int expressFee;
    private AddressInfo.ShopAddressListBean addressBean;
    private List<AddressInfo.ShopAddressListBean> addressInfos = new ArrayList<>();
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
        AddressListRequest request = new AddressListRequest();
        request.accessToken = AccountManager.getInstance().getToken();
        request.userId = AccountManager.getInstance().getUserId();
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

    public void onClickBtn(View view) {
        if (view.getId() == R.id.invoiceType) {
            String[] strings1 = new String[]{"个人", "企业", "政府机关行政单位"};
            new XPopup.Builder(this)
                    .asCustom(new CustomListDialog(AirReimbursementActivity.this)
                            .setData(Arrays.asList(strings1))
                            .setOnItemClickListener(new CustomListDialog.OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {
                                    binding.tvInvoice.setText(strings1[position]);
                                    invoicePosition = position + 2;
                                    if (invoicePosition == 3) {

                                    } else {

                                    }
                                }
                            }))
                    .show();
        } else if (view.getId() == R.id.rl_address) {
            /*Intent intent = new Intent(PlaneReimbursementActivity.this, AddressManagementActivity.class);
            intent.putExtra(AddressManagementActivity.IS_SELECT, true);
            startActivityForResult(intent, REQUEST_CODE);*/
        } else if (view.getId() == R.id.identification_num_explain) {
            String content = "纳税人识别号是企业税务登记证上唯一识别企业的税号，由15/18或20位数码组成。根据国家税务局规定，自2017年7月1日起，开具增值税普通发票必须有纳税人识别号或统一社会信用代码，否则该发票为不符合规定的发票，不得作为税收凭证。纳税人识别号可登录纳税人信息查询网www.yibannashuiren.com 查询，或向公司财务人员咨询。";

            new XPopup.Builder(AirReimbursementActivity.this)
                    .enableDrag(false)
                    .asCustom(new CustomDialog(this
                    ).setContent(content)).show();
        } else if (view.getId() == R.id.reimbursementType) {
/* String[] strings2 = new String[]{"全额发票", "行程单", "行程单和差额发票"};
                new XPopup.Builder(this)
                        .asCustom(new CustomRefundView(PlaneReimbursementActivity.this)
                                .setData(Arrays.asList(strings2))
                                .setOnItemClickListener(new CustomRefundView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(int position) {
                                        tvReimbursement.setText(strings2[position]);
                                        reimbursementPosition = position;
                                    }
                                }))
                        .show();*/
        } else if (view.getId() == R.id.submit) {
            onSubmit();
        }

    }


    public void onSubmit() {
       /* if (TextUtils.isEmpty(tvReimbursement.getText().toString())) {
            ToastUtils.show("请选择凭证类型");
            return;
        }*/
        if (TextUtils.isEmpty(binding.tvReimbursement.getText().toString())) {
            ToastUtil.show("请选择发票类型");
            return;
        }

        if ((reimbursementPosition == 0 || reimbursementPosition == 2) && TextUtils.isEmpty(binding.editInvoiceTitle.getText().toString().trim())) {
            ToastUtil.show("请填写发票抬头");
            return;
        }
        if (invoicePosition == 3 && TextUtils.isEmpty(binding.editNum.getText().toString().trim())) {
            ToastUtil.show("请填写纳税人识别号");
            return;
        }
        if (addressBean == null) {
            ToastUtil.show("请选择收货地址");
            return;
        }
        if (canApply && invoiceStringType != null) {
            refundAskFor();
        } else {
            ToastUtil.show("当前订单不可报销");
        }
    }

    /*
     * 索要退票手续费
     * */
    public void refundAskFor() {
        AirRefundAskForRequest request = new AirRefundAskForRequest();
        request.isShowLoading = true;
        request.userId = AccountManager.getInstance().getUserId();
        request.orderNo = docOrderDetailInfo.detail.orderNo;
        request.receiptTitle = binding.editInvoiceTitle.getText().toString().trim();
        request.receiptTitleTypeCode = invoicePosition + "";
        request.receiverCity = addressBean.getCityName();
        request.receiverDistrict = addressBean.getAreaName();
        request.receiverName = addressBean.getUserName();
        request.receiverPhone = addressBean.getPhone();
        request.receiverProvince = addressBean.getProvinceName();
        request.receiverStreet = addressBean.getDetailAddress();
        request.reimburseType = reimbursementPosition + "";
        request.taxpayerId = binding.editNum.getText().toString();
        request.call(new ApiLifeCallBack<AskForResultInfo>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onFinsh() {

            }

            @Override
            public void onAPIResponse(AskForResultInfo response) {
                if (response.needPay) {
                    paySign(docOrderDetailInfo.detail.orderNo, String.valueOf(expressFee));
                }
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
        request.type = "4";  ////3行程单4退票
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
        PayHelper.aliPay(AirReimbursementActivity.this, payProof, new PayHelper.OnPayListener() {
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

    /*
     * 退票手续费查询
     * */
    public void refundXcd() {
        AirRefundXcdRequest request = new AirRefundXcdRequest();
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
                canApply = response.canApply;
                invoiceStringType = response.invoiceType;
                if (response.canApply && response.invoiceType != null) {
                    binding.postagePrice.setText("¥" + response.expressFee);
                    expressFee = response.expressFee;
                } else {
                    ToastUtil.show("当前订单不可报销");
                }
            }

            @Override
            public void onAPIError(int errorCode, String errorMsg) {

            }
        });

    }

    /*
     * 查询行程单
     * */
    public void itinerarySearch() {
        ItineraryQueryRequest request = new ItineraryQueryRequest();
        request.orderNo = docOrderDetailInfo.detail.orderNo;
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
     * 二次索要行程单手续费
     * */
    public void itineraryAskFor() {

        ItineraryAskForRequest request = new ItineraryAskForRequest();
        request.call(new ApiLifeCallBack<AskForResultInfo>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onFinsh() {

            }

            @Override
            public void onAPIResponse(AskForResultInfo response) {

            }

            @Override
            public void onAPIError(int errorCode, String errorMsg) {

            }
        });
    }

    @Override
    public void initListener() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == AirConstants.REQUEST_ADDRESS_CODE) {
                addressBean = (AddressInfo.ShopAddressListBean) data.getSerializableExtra(AirConstants.SELECT_ADDRESS);
                if (addressBean != null) {
                    binding.name.setText(addressBean.getUserName());
                    binding.address.setText(addressBean.getProvinceName() + addressBean.getCityName() + addressBean.getAreaName() + addressBean.getDetailAddress());
                    binding.phone.setText(addressBean.getPhone());
                }
            }
        }
    }
}
