package com.bldby.airticket.callback;


import com.bldby.airticket.model.CityModel;

/**
 * @date: on 2019-10-30
 * @author: a112233
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public interface OnCitySelectListener {

    void onCitySelect(CityModel cityModel);

    void onSelectCancel();

}
