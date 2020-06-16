package com.bldby.travellibrary.activity.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.bldby.travellibrary.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class Distance2Adapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private int posion = -1;
    private String posion1 = "";

    public Distance2Adapter(@Nullable List<String> data) {
        super(R.layout.item_popup_oil1, data);

    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        helper.setText(R.id.oilname, item);
        if (posion == helper.getAdapterPosition()) {
            TextView view = helper.getView(R.id.oilname);
            view.setEnabled(true);
        } else {
            TextView view = helper.getView(R.id.oilname);
            view.setEnabled(false);
        }
        if (posion1.equals(item)) {
            TextView view = helper.getView(R.id.oilname);
            view.setEnabled(true);
        }

    }

    public void chengtextcolor1(String posion) {
        this.posion1 = posion;
    }

    public void chengtextcolor(int posion) {
        this.posion = posion;
    }
}
