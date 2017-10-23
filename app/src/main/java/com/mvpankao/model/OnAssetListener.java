package com.mvpankao.model;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/14 10:57
 */
public interface OnAssetListener {
    //请求数据错误
    void onError();

    //请求成功
    void onSuccess();

    //请求失败
    void onFailure();

}
