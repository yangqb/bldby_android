package com.bldby.travellibrary.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.baselibrary.app.login.model.UserInfo;
import com.bldby.baselibrary.constants.RouteLoginConstants;
import com.bldby.baselibrary.constants.RouteTravelConstants;
import com.bldby.baselibrary.core.network.ApiCallBack;
import com.bldby.baselibrary.core.ui.baseactivity.BaseActivity;
import com.bldby.baselibrary.core.ui.baseactivity.BaseUiActivity;
import com.bldby.travellibrary.R;
import com.bldby.travellibrary.activity.adapter.TraveFormAdapter;
import com.bldby.travellibrary.activity.model.OilOrederBean;
import com.bldby.travellibrary.activity.request.OilOrderUrlRequest;
import com.bldby.travellibrary.databinding.ActivityOilOrderBinding;

import java.util.List;

@Route(path = RouteTravelConstants.TRAVELORDER)
//, extras = RouteLoginConstants.SHOWCHECKLOGIN
public class OilOrderActivity extends BaseActivity {

    private ActivityOilOrderBinding oilOrderBinding;

    @Override
    public void bindIngView() {
        oilOrderBinding = DataBindingUtil.setContentView(this, R.layout.activity_oil_order);
        oilOrderBinding.setViewmodel(this);
    }

    @Override
    public void initView() {
        View mEmptyView = View.inflate(this, R.layout.view_common_nodata, null);
        ImageView img_empty = (ImageView) mEmptyView.findViewById(R.id.img_empty);
        img_empty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        TraveFormAdapter traveFormAdapter = new TraveFormAdapter(null);
        traveFormAdapter.setEmptyView(mEmptyView);
        oilOrderBinding.recyclerView.setAdapter(traveFormAdapter);
        traveFormAdapter.notifyDataSetChanged();

        UserInfo userInfo = new UserInfo();
        String phone = userInfo.phone;
        oilOrderBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        OilOrderUrlRequest oilOrderRequest = new OilOrderUrlRequest(50, 1, phone);
        oilOrderRequest.userId = userInfo.userId;
        oilOrderRequest.accessToken = userInfo.accessToken;
        oilOrderRequest.call(new ApiCallBack<List<OilOrederBean>>() {
            @Override
            public void onAPIResponse(List<OilOrederBean> response) {
                if (response != null && response.size() > 0) {
                    TraveFormAdapter traveFormAdapter = new TraveFormAdapter(response);
                    oilOrderBinding.recyclerView.setAdapter(traveFormAdapter);
                }
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

  /*  @Override
    protected View initContentView(LayoutInflater layoutInflater, ViewGroup viewGroup) {

        return oilOrderBinding.getRoot();
    }*/
}