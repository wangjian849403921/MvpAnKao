package com.mvpankao.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mvpankao.R;
import com.mvpankao.base.BaseActivity;
import com.mvpankao.presenter.LoginPresenter;
import com.mvpankao.utils.Constants;
import com.mvpankao.utils.DialogUtils;
import com.mvpankao.utils.MyEvent;
import com.mvpankao.view.LoginView;
import com.mvpankao.widget.ClearEditText;

import net.lemonsoft.lemonbubble.LemonBubble;
import net.lemonsoft.lemonbubble.enums.LemonBubbleLayoutStyle;
import net.lemonsoft.lemonbubble.enums.LemonBubbleLocationStyle;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wangjian
 * On  2016/12/27
 */

public class LoginActivity extends BaseActivity implements LoginView {
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

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginPresenter = new LoginPresenter(this);
    }

    @Override
    protected void initView() {


    }

    @Override
    protected int getLayout() {
        return R.layout.ac_login;
    }

    @Override
    protected void initEventAndData() {

    }


    @OnClick({R.id.login, R.id.forgetpassword})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                loginPresenter.login(mContext,mMyOkhttp);
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
    public void dismissLogin() {
        DialogUtils.dismiss();
    }

    @Override
    public String getUserName() {
        return mLoginName.getText().toString();
    }

    @Override
    public String getPassWord() {
        return mLoginPassWord.getText().toString();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();

    }

    @Override
    public void Finish() {
        EventBus.getDefault().post(new MyEvent(Constants.UserName_CODE));

        finish();

    }

    @Override
    public void upDate() {
        EventBus.getDefault().post(new MyEvent(Constants.Address_CODE));
    }


}
