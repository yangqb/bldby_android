package com.bldby.shoplibrary.seach;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.databinding.ObservableField;
import android.databinding.ObservableFloat;
import android.databinding.ObservableInt;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.configure.PickerOptions;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bldby.baselibrary.app.GlobalUtil;
import com.bldby.baselibrary.core.addresspick.AddressPickerUtil;
import com.bldby.shoplibrary.R;
import com.bldby.shoplibrary.databinding.ViewSeachHeaderBinding;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

public class SeachHeaderView {

    public ViewSeachHeaderBinding headerBinding;
    Activity context;
    public AddressPickerUtil.OnOptionsTextSelectListener onOptionsSelectListener;
    public ObservableField<ColorStateList> backButton = new ObservableField<ColorStateList>();
    public ObservableField<Drawable> backBackground = new ObservableField<Drawable>();
    public ObservableFloat backBackgroundAlpha = new ObservableFloat(0f);

    public View getView() {

        return headerBinding.getRoot();
    }

    public SeachHeaderView(Activity context, LayoutInflater view) {
        this.context = context;
        headerBinding = ViewSeachHeaderBinding.inflate(view);
        headerBinding.setViewModel(this);
        backBackground.set(context.getResources().getDrawable(R.color.transparent));
        backButton.set(context.getResources().getColorStateList(R.color.white));
        headerBinding.homeSeach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context,SeachActivity.class));
               // context. overridePendingTransition(R.anim.slid_left_in, R.anim.fade_outt);
            }
        });
    }

    public void onClickLocaltioned(View view) {

        AddressPickerUtil addressPickerUtil = new AddressPickerUtil();
        if (onOptionsSelectListener != null) {
            addressPickerUtil.getOptionsPickerView(context, false, onOptionsSelectListener).show();
        }

    }


}
