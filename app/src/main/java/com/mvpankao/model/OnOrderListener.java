package com.mvpankao.model;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/9 18:00
 */
public interface OnOrderListener {

    //请求数据错误
    void onError();
    void ondeleteError();
    //请求成功
    void onSuccess();
    void ondeleteSuccess(int position);

    //请求失败
    void onFailure();
    void ondeleteFailure();
    //获取数据的总条数
    void getItemSize(int size);
}
