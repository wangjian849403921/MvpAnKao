package com.mvpankao.view;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/13 13:59
 */
public interface TypeView {

    void showToast(String msg);

    //成功
    void onSuccess();

    //失败
    void onFailure();

    //错误
    void onError();
}
