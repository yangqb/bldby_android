package com.bldby.centerlibrary.address.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.bldby.baselibrary.core.util.ToastUtil;
import com.bldby.centerlibrary.R;
import com.bldby.centerlibrary.model.AddressInfo;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.guanaj.easyswipemenulibrary.EasySwipeMenuLayout;

import java.util.List;

/**
 * package name: com.bldby.centerlibrary.address.adapter
 * user: yangqinbo
 * date: 2020/6/24
 * time: 16:22
 * email: 694125155@qq.com
 */
public class AddressListAdapter extends BaseQuickAdapter<AddressInfo.ShopAddressListBean, BaseViewHolder> {

    public OnDeleteListener onDeleteListener;

    public interface OnDeleteListener {
        void delete(int position);
    }

    public void setOnDeleteListener(OnDeleteListener listener) {
        this.onDeleteListener = listener;
    }

    public AddressListAdapter(@Nullable List<AddressInfo.ShopAddressListBean> data) {
        super(R.layout.item_layout_address, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, AddressInfo.ShopAddressListBean item) {
        helper.setText(R.id.name, item.getUserName());
        helper.setText(R.id.phone, item.getPhone());
        helper.setText(R.id.address, item.getProvinceName() + item.getCityName() + item.getAreaName() + item.getDetailAddress());
        if (item.getIsDefalt() == 1) {
            helper.setVisible(R.id.isDefault, true);
        } else {
            helper.setVisible(R.id.isDefault, false);
        }
        helper.getView(R.id.right_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EasySwipeMenuLayout swipeMenuLayout = helper.getView(R.id.swipeLayout);
                swipeMenuLayout.resetStatus();
                onDeleteListener.delete(helper.getAdapterPosition());
                ToastUtil.show("删除了哦");
            }
        });

        helper.getView(R.id.right_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.show("选择了哦");
            }
        });

    }
}