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
import com.bldby.airticket.model.TimePointChargsInfo;
import com.bldby.airticket.view.CustomListDialog;
import com.bldby.baselibrary.constants.RouteAirConstants;
import com.bldby.baselibrary.core.ui.baseactivity.BaseUiActivity;
import com.bldby.baselibrary.core.util.MathUtils;
import com.bldby.baselibrary.core.util.ToastUtil;
import com.lxj.xpopup.XPopup;

import java.util.ArrayList;
import java.util.List;

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

    public void checkChange() {

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
