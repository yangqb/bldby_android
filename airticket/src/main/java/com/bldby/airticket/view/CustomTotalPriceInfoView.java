package com.bldby.airticket.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bldby.airticket.R;
import com.bldby.airticket.model.PriceDetailInfo;
import com.bldby.baselibrary.core.util.MathUtils;
import com.lxj.xpopup.core.BottomPopupView;

/**
 * package name: com.bldby.airticket.view
 * user: yangqinbo
 * date: 2020/6/20
 * time: 13:08
 * email: 694125155@qq.com
 */
public class CustomTotalPriceInfoView extends BottomPopupView {
    private PriceDetailInfo priceDetailInfo;

    public CustomTotalPriceInfoView(@NonNull Context context) {
        super(context);
    }

    public CustomTotalPriceInfoView setData(PriceDetailInfo priceDetailInfo) {
        this.priceDetailInfo = priceDetailInfo;
        return this;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.dialog_air_total_price;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        TextView goPrice = findViewById(R.id.go_price);
        goPrice.setText("¥" + MathUtils.subZero(String.valueOf(priceDetailInfo.price)) + "x" + priceDetailInfo.num);
        TextView goArfTof = findViewById(R.id.go_arfTof);
        goArfTof.setText("¥" + MathUtils.subZero(String.valueOf(priceDetailInfo.arf + priceDetailInfo.tof)) + "x" + priceDetailInfo.num);
        TextView goCprice = findViewById(R.id.go_cprice);
        TextView goCArfTof = findViewById(R.id.go_cArfTof);
        goCprice.setText("¥" + MathUtils.subZero(String.valueOf(priceDetailInfo.cPrice)) + "x" + priceDetailInfo.cnum);
        goCArfTof.setText("¥0" + "x" + priceDetailInfo.cnum);
        RelativeLayout rlChildPrice = findViewById(R.id.rl_child_price);
        RelativeLayout rlPostage = findViewById(R.id.rl_postage);
        if (priceDetailInfo.cPrice == 0 || priceDetailInfo.cnum == 0) {
            rlChildPrice.setVisibility(GONE);
        } else {
            rlChildPrice.setVisibility(VISIBLE);
        }

        if (priceDetailInfo.postage == 0) {
            rlPostage.setVisibility(GONE);
        } else {
            rlPostage.setVisibility(VISIBLE);
        }
    }
}
