package com.mvpankao.view;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/10 17:59
 */
public interface SubscribeView {

    //获取用户Id
    String getUserId();

    String getDate();

    void showToast(String msg);

    void onSuccess();

    void onFailure();

    void onError();
}
