package com.mvpankao.model;

/**
 * Description: 监听器用来处理Model实现后的操作
 * Author:wangjian
 * Date: 2017-03-07
 */
public interface OnNewsListener {

    //请求数据错误
    void onError();

    //请求成功
    void onSuccess();

    //请求失败
    void onFailure();

    //获取数据的总条数
    void getItemSize(int size);
}
