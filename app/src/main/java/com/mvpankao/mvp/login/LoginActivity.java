package com.mvpankao.mvp.login;


import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mvpankao.R;
import com.mvpankao.model.bean.UserBeans;
import com.mvpankao.mvp.base.MVPBaseActivity;
import com.mvpankao.utils.Constants;
import com.mvpankao.utils.MyEvent;
import com.mvpankao.widget.ClearEditText;

import net.lemonsoft.lemonbubble.LemonBubble;
import net.lemonsoft.lemonbubble.enums.LemonBubbleLayoutStyle;
import net.lemonsoft.lemonbubble.enums.LemonBubbleLocationStyle;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * MVP
 *  LoginActivity
 */

public class LoginActivity extends MVPBaseActivity<LoginContract.View, LoginPresenter> implements LoginContract.View {
    @BindView(R.id.LoginName)
    ClearEditText mLoginName;
    @BindView(R.id.LoginPassWord)
    ClearEditText mLoginPassWord;
    @BindView(R.id.login)
    CardView mLogin;
    @BindView(R.id.forgetpasswordtext)
    TextView mForgetpasswordtext;
    @BindView(R.id.forgetpassword)
    RelativeLayout mForgetpassword;

    @Override
    protected int getLayout() {
        return R.layout.ac_login;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initEventAndData() {

    }
    @OnClick({R.id.login, R.id.forgetpassword})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                if(mContext.isDestroyed()){
                    return;
                }
                mPresenter.login(mLoginName.getText().toString(),mLoginPassWord.getText().toString(),mContext,mMyOkhttp);
                break;
            case R.id.forgetpassword:
                break;
        }
    }

    @Override
    public void showLogin() {

        LemonBubble.getRoundProgressBubbleInfo()
                .setLocationStyle(LemonBubbleLocationStyle.CENTER)
                .setLayoutStyle(LemonBubbleLayoutStyle.ICON_LEFT_TITLE_RIGHT)
                .setBubbleSize(200, 50)
                .setProportionOfDeviation(0.1f)
                .setTitle("正在登录...")
                .show(mContext);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccess(Object responses) {
        UserBeans response=(UserBeans)responses;
        mSPUtils.putString("role", response.getObject().getUser().getRole());
        mSPUtils.putString("username", response.getObject().getUser().getUsername());
        mSPUtils.putString("userid", response.getObject().getUser().getId());
        mSPUtils.putString("userimage", response.getObject().getUser().getIcon());
        mSPUtils.putBoolean("hasLogin", true);
        if (response.getObject().getReceiptAddress() != null) {
            String reciver = response.getObject().getReceiptAddress().get(0).getReceiveName();
            String phone = response.getObject().getReceiptAddress().get(0).getReceivePhone();
            mSPUtils.putString("reciver", response.getObject().getReceiptAddress().get(0).getReceiveName());
            mSPUtils.putString("phone", response.getObject().getReceiptAddress().get(0).getReceivePhone());

            mSPUtils.putString("addressid", response.getObject().getReceiptAddress().get(0).getId());
            mSPUtils.putString("defaultaddress", response.getObject().getReceiptAddress().get(0).getProvince() + response.getObject().getReceiptAddress().get(0).getCitys() + response.getObject().getReceiptAddress().get(0).getAreas() + response.getObject().getReceiptAddress().get(0).getReceiveAddress());
        }
        if (mSPUtils.contains("state")) {
            if (mSPUtils.getString("state").equals("1")) {
                EventBus.getDefault().post(new MyEvent(Constants.Address_CODE));
            }
        }
        EventBus.getDefault().post(new MyEvent(Constants.UserName_CODE));
        LemonBubble.hide();
        finish();
    }

    @Override
    public void onFailure() {
        LemonBubble.showError(mContext, "用户名或密码错误", 2000);

    }

    @Override
    public void onRequestFailed() {
        LemonBubble.showError(mContext, "请求失败", 2000);
    }

}
