package com.bldby.shoplibrary.goods;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.chip.Chip;
import android.support.design.chip.ChipGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.baselibrary.constants.RouteShopConstants;
import com.bldby.baselibrary.core.ui.basefragment.Basefragment;
import com.bldby.baselibrary.core.util.ToastUtil;
import com.bldby.shoplibrary.R;
import com.bldby.shoplibrary.databinding.FragmentSkuGoodsBinding;
import com.bldby.shoplibrary.goods.model.GoodsDetailModel;
import com.bldby.shoplibrary.goods.model.ShopDetailModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

@Route(path = RouteShopConstants.SHOPGOODSSKU)
public class GoodsSkuFragment extends Basefragment {


    private FragmentSkuGoodsBinding goodsBinding;
    private ShopDetailModel detailModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        goodsBinding = FragmentSkuGoodsBinding.inflate(inflater, container, false);
        goodsBinding.setViewModel(this);
        detailModel = ViewModelProviders.of(getActivity()).get(ShopDetailModel.class);
        return goodsBinding.getRoot();

    }

    @Override
    public void initView() {

    }

    @Override
    public void loadData() {
        Chip childAt = (Chip) goodsBinding.chipGroup.getChildAt(0);
        childAt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    childAt.setChipBackgroundColor(getResources().getColorStateList(R.color.FCB432));
                } else {
                    childAt.setChipBackgroundColor(getResources().getColorStateList(R.color.gray_dddddd));
                }
            }
        });

    }

    @Override
    public void initListener() {
        goodsBinding.chipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup chipGroup, int i) {

//                childAt.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                });
                ToastUtil.show(i + "");
            }
        });
    }
}
