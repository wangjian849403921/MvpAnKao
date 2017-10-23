package com.mvpankao.model;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/10 18:07
 */
public interface OnSubscribeListener {
    //请求数据错误
    void onError();

    //请求成功
    void onSuccess();

    //请求失败
    void onFailure();
}
