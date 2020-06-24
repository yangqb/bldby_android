package com.bldby.shoplibrary.customer;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bldby.baselibrary.app.RxCodeConstants;
import com.bldby.baselibrary.core.network.ApiCallBack;
import com.bldby.baselibrary.core.rxbus.RxBus;
import com.bldby.baselibrary.core.ui.baseactivity.BaseActivity;
import com.bldby.baselibrary.core.util.ToastUtil;
import com.bldby.loginlibrary.AccountManager;
import com.bldby.loginlibrary.model.AccountInfo;
import com.bldby.loginlibrary.model.UserInfo;
import com.bldby.loginlibrary.request.UserInfoRequest;
import com.bldby.shoplibrary.R;
import com.bldby.shoplibrary.databinding.ActivityImBinding;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.easeui.widget.chatrow.EaseChatRow;

public class ImActivity extends BaseActivity {
    ActivityImBinding binding;
    private EaseChatFragment chatFragment;

    @Override
    public void bindIngView() {
       binding = DataBindingUtil.setContentView(ImActivity.this, R.layout.activity_im);
       binding.setViewModel(this);

    }

    @Override
    public void initView() {
        String userId = AccountManager.getInstance().getUserId();
        String token = AccountManager.getInstance().getToken();
        String stringExtra = getIntent().getStringExtra("userId");
        UserInfoRequest request = new UserInfoRequest();
        request.userId = userId;
        request.accessToken = token;
        request.call(new ApiCallBack<AccountInfo>() {
            @Override
            public void onAPIResponse(AccountInfo response) {
                //设置聊天头像
                EaseChatRow.OtherIMG = response.headImg;
                //所有未读消息数清零
                EMClient.getInstance().chatManager().markAllConversationsAsRead();
                RxBus.getDefault().post(RxCodeConstants.IM_MESSAGE, false);

                chatFragment = new EaseChatFragment();
                chatFragment.setAvatar(response.headImg);
                chatFragment.setName(response.nickName);
                chatFragment.setTitle(response.nickName);
                //pass parameters to chat fragment
                chatFragment.setArguments(getIntent().getExtras());
                getSupportFragmentManager().beginTransaction().add(R.id.container, chatFragment).commit();
            }

            @Override
            public void onAPIError(int errorCode, String errorMsg) {

                ToastUtil.show(getString(com.bldby.loginlibrary.R.string.getuserinfofail));
//                finish();
            }
        });


    }

    @Override
    public void loadData() {

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (chatFragment != null && !chatFragment.isDetached()) {
            chatFragment.onRequestPermissionsResult(requestCode, permissions, grantResults);

        }
    }

    @Override
    public void initListener() {

    }
}