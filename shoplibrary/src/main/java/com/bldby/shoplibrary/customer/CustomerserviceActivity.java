package com.bldby.shoplibrary.customer;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.baselibrary.constants.RouteShopConstants;
import com.bldby.baselibrary.core.network.ApiCallBack;
import com.bldby.baselibrary.core.ui.baseactivity.BaseActivity;
import com.bldby.baselibrary.core.ui.baseactivity.BaseUiActivity;
import com.bldby.shoplibrary.R;
import com.bldby.shoplibrary.customer.bean.ConverzServiceListBean;
import com.bldby.shoplibrary.customer.request.ConverServiceUrlRequest;
import com.bldby.shoplibrary.databinding.ActivityCustomerserviceBinding;

import java.util.List;

@Route(path = RouteShopConstants.SHOPGOODSCUST)
public class CustomerserviceActivity extends BaseActivity {
    ActivityCustomerserviceBinding binding;
    private List<ConverzServiceListBean> response;
    @Override
    public void bindIngView() {
        binding = DataBindingUtil.setContentView(CustomerserviceActivity.this, R.layout.activity_customerservice);
        binding.setViewModel(this);
    }

    @Override
    public void initView() {
        binding.titleName.setText("在线客服");
        binding.leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        initdata();
        binding.shopissues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerserviceActivity.this, ImActivity.class);
                //username为对方的环信id
//                    intent.putExtra("name",response.get(0).getNick());
//                    intent.putExtra("icon",response.get(0).getIcon());
//                    intent.putExtra(EaseConstant.EXTRA_USER_ID, "688577"+"-dev");
                intent.putExtra("userId", response.get(0).getUserId() + IMContent.IMTAG);
                startActivity(intent);
            }
        });

    }

    private void initdata() {
        ConverServiceUrlRequest converServiceRequest = new ConverServiceUrlRequest();
        converServiceRequest.isShowLoading = true;
        converServiceRequest.call(new ApiCallBack<List<ConverzServiceListBean>>() {
            @Override
            public void onAPIResponse(List<ConverzServiceListBean> response) {
                CustomerserviceActivity.this.response = response;
            }

            @Override
            public void onAPIError(int errorCode, String errorMsg) {

            }
        });
    }

    @Override
    public void loadData() {

    }

    @Override
    public void initListener() {

    }
}