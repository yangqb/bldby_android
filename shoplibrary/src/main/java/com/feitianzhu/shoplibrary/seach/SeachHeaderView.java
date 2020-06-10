package com.feitianzhu.shoplibrary.seach;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.configure.PickerOptions;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.feitianzhu.baselibrary.app.GlobalUtil;
import com.feitianzhu.baselibrary.core.addresspick.AddressPickerUtil;
import com.feitianzhu.shoplibrary.databinding.ViewSeachHeaderBinding;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

public class SeachHeaderView {

    private ViewSeachHeaderBinding headerBinding;
    Activity context;
    public AddressPickerUtil.OnOptionsTextSelectListener onOptionsSelectListener;

    public View getView(LayoutInflater view) {
        headerBinding = ViewSeachHeaderBinding.inflate(view);
        headerBinding.setViewModel(this);
        return headerBinding.getRoot();
    }

    public SeachHeaderView(Activity context) {
        this.context = context;
    }

    public void onClickLocaltioned(View view) {

        AddressPickerUtil addressPickerUtil = new AddressPickerUtil();
        if (onOptionsSelectListener != null) {
            addressPickerUtil.getOptionsPickerView(context, false, onOptionsSelectListener).show();
        }

    }


}
