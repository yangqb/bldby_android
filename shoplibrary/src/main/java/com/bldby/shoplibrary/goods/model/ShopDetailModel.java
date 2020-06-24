package com.bldby.shoplibrary.goods.model;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;


public class ShopDetailModel extends ViewModel {
    private MutableLiveData<GoodsDetailModel> goodsDetailModel = new MutableLiveData<>();


    public ShopDetailModel() {

    }

    public MutableLiveData<GoodsDetailModel> getGoodsDetailModel() {
        return goodsDetailModel;
    }

}
