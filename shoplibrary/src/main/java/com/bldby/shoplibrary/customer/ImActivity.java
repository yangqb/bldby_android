package com.bldby.shoplibrary.customer;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bldby.baselibrary.core.ui.baseactivity.BaseActivity;
import com.bldby.shoplibrary.R;
import com.bldby.shoplibrary.databinding.ActivityImBinding;

public class ImActivity extends BaseActivity {
    ActivityImBinding binding;
    @Override
    public void bindIngView() {
       binding = DataBindingUtil.setContentView(ImActivity.this, R.layout.activity_im);
       binding.setViewModel(this);

    }

    @Override
    public void initView() {
        String stringExtra = getIntent().getStringExtra("userId");
          /*      //设置聊天头像
                EaseChatRow.OtherIMG = response.getHeadImg();
                //所有未读消息数清零
                EMClient.getInstance().chatManager().markAllConversationsAsRead();
                RxBus.getDefault().post(RxCodeConstants.IM_MESSAGE, false);

                chatFragment = new EaseChatFragment();
                chatFragment.setAvatar(UserInfoUtils.getUserInfo(ImActivity.this).getHeadImg());
                chatFragment.setName(UserInfoUtils.getUserInfo(ImActivity.this).getNickName());
                chatFragment.setTitle(response.getNickName());
                //pass parameters to chat fragment
                chatFragment.setArguments(getIntent().getExtras());
                getSupportFragmentManager().beginTransaction().add(R.id.container, chatFragment).commit();*/
    }

    @Override
    public void loadData() {

    }

    @Override
    public void initListener() {

    }
}