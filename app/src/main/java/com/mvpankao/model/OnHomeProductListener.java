package com.mvpankao.model;

/**
 * description：监听器用来处理Model实现后的操作
 *
 * @auth wangjian
 * @time 2017/3/8 11:21
 */
public interface OnHomeProductListener {

    //请求数据错误
    void onError();

    //请求成功
    void onSuccess();

    //请求失败
    void onFailure();
}
