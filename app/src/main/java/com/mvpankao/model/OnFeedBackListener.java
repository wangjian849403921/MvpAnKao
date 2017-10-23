package com.mvpankao.model;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/9 16:19
 */
public interface OnFeedBackListener {

    //请求数据错误
    void onError();

    //请求成功
    void onSuccess();

    //请求失败
    void onFailure();
}
