package com.mvpankao.model;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/10 11:46
 */
public interface OnWorkFlowListener {
    //请求数据错误
    void onError();

    //请求成功
    void onSuccess();

    //请求失败
    void onFailure();

    //获取数据的总条数
    void getItemSize(int size);
}
