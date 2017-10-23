package com.mvpankao.model;

/**
 * Description: 监听器用来处理Model实现后的操作
 * Author:wangjian
 * Date: 2017-03-07
 */
public interface OnLoginListener {

    //登录错误
    void onLoginError(String msg);

    //登录成功
    void onSuccess();

    //更新数据
    void onUpdate();

    //请求失败
    void onFailure();
}
