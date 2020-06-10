package com.feitianzhu.shoplibrary.seach;

import android.view.LayoutInflater;
import android.view.View;

import com.feitianzhu.shoplibrary.databinding.ViewSeachHeaderBinding;

import org.jetbrains.annotations.NotNull;

public class SeachHeaderView {

    private ViewSeachHeaderBinding headerBinding;

    public View getView(LayoutInflater view) {
        headerBinding = ViewSeachHeaderBinding.inflate(view);
        headerBinding.setViewModel(this);
        return headerBinding.getRoot();
    }

    public void onClickLocaltioned(View view) {
//        ProvinceDialog2 branchDialog = ProvinceDialog2.newInstance();
//        branchDialog.setCityLevel(ProvinceDialog2.PROVINCE_CITY);
//        branchDialog.setAddress("北京市", "东城区", "东华门街道");
//        branchDialog.setSelectOnListener(this);
//        branchDialog.show(getChildFragmentManager());
    }
}
