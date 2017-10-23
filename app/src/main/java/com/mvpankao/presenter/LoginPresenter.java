package com.mvpankao.presenter;

import android.content.Context;

import com.blankj.utilcode.utils.NetworkUtils;
import com.blankj.utilcode.utils.StringUtils;
import com.mvpankao.model.LoginModel;
import com.mvpankao.model.OnLoginListener;
import com.mvpankao.model.impl.LoginModelImpl;
import com.mvpankao.view.LoginView;
import com.tsy.sdk.myokhttp.MyOkHttp;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/7 16:20
 */

public class LoginPresenter implements OnLoginListener {
    private LoginModel loginModel;
    private LoginView loginView;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
        loginModel = new LoginModelImpl();
    }


    /**
     * @param context
     */
    public void login(Context context, MyOkHttp myOkHttp) {
        String name = loginView.getUserName();
        String password = loginView.getPassWord();
        if (StringUtils.isEmpty(name)||StringUtils.isEmpty(password)){
            loginView.showToast("用户名或密码不能为空");
        }else{
            if(NetworkUtils.isConnected(context)){
                loginView.showLogin();
                loginModel.login(name, password, this,context,myOkHttp);
            }else{
                loginView.showToast("网络异常");
            }
        }

    }

    @Override
    public void onLoginError(String msg) {


        loginView.showToast(msg);
    }

    @Override
    public void onSuccess() {
        loginView.dismissLogin();
        loginView.Finish();
    }

    @Override
    public void onUpdate() {
        loginView.upDate();
    }

    @Override
    public void onFailure() {
        loginView.dismissLogin();
        loginView.showToast("请求失败");
    }
}
