package com.mvpankao.mvp.login;

import android.content.Context;

import com.mvpankao.mvp.base.BasePresenter;
import com.mvpankao.mvp.base.BaseView;
import com.tsy.sdk.myokhttp.MyOkHttp;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LoginContract {
    interface View extends BaseView {
        void showLogin();//显示登录loading
        void showToast(String msg);//toast
    }

    interface  Presenter extends BasePresenter<View> {
        void login(String username,String password,Context context, MyOkHttp myOkHttp);
    }
}
