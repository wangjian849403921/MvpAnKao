package com.mvpankao.view;

/**
 * description：登录view
 *
 * @auth wangjian
 * @time 2017/3/7 16:17
 */
public interface LoginView {
    void showLogin();
    void dismissLogin();
    //获取用户名
    String getUserName();
    //获取密码
    String getPassWord();
    void showToast(String msg);
    void Finish();
    void upDate();
}
