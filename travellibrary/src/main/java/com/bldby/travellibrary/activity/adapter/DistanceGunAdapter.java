package com.bldby.travellibrary.activity.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.bldby.travellibrary.R;
import com.bldby.travellibrary.activity.model.OilStationsDetailBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class DistanceGunAdapter  extends BaseQuickAdapter<OilStationsDetailBean.OilInfoBean.GunNosBean, BaseViewHolder> {
    private int posion = -1;

    public DistanceGunAdapter(@Nullable List<OilStationsDetailBean.OilInfoBean.GunNosBean> data) {
        super(R.layout.popup_item_oil1, data);

    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, OilStationsDetailBean.OilInfoBean.GunNosBean item) {
        helper.setText(R.id.oilname, item.getGunNo());
        if (posion == helper.getAdapterPosition()) {
            TextView view = helper.getView(R.id.oilname);
            view.setEnabled(true);
        } else {
            TextView view = helper.getView(R.id.oilname);
            view.setEnabled(false);
        }

    }

    public void chengtextcolor(int posion) {
        this.posion = posion;
    }
}
