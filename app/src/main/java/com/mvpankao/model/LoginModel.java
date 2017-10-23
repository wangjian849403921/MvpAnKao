package com.mvpankao.model;

import android.content.Context;

import com.tsy.sdk.myokhttp.MyOkHttp;

/**
 * Description: 登录接口
 * Author:wangjian
 * Date: 2017-03-07
 * Time: 11:39
 */
public interface LoginModel {

    void login(String name, String password, OnLoginListener onLoginListener, Context context, MyOkHttp myOkHttp);
}
