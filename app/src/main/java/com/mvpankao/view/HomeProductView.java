package com.mvpankao.view;

/**
 * description：电缆输电
 *
 * @auth wangjian
 * @time 2017/3/8 11:12
 */
public interface HomeProductView {
    //toast
    void showToast(String msg);

    //成功
    void onSuccess();

    //失败
    void onFailure();

    //错误
    void onError();
}
