package com.feitianzhu.shoplibrary.seach;

import android.view.View;

import com.feitianzhu.shoplibrary.databinding.ViewSeachHeaderBinding;

import org.jetbrains.annotations.NotNull;

public class SeachHeaderView {

    private ViewSeachHeaderBinding headerBinding;

    private View getView(View view) {
        headerBinding = ViewSeachHeaderBinding.bind(view);
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
