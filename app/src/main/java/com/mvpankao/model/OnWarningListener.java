package com.mvpankao.model;

import com.mvpankao.model.bean.WarningDetailBean;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/14 10:15
 */
public interface OnWarningListener {
    //请求数据错误
    void onError();

    //请求成功
    void onSuccess();
    void onSuccess(WarningDetailBean response);
    //请求失败
    void onFailure();

    //获取数据的总条数
    void getItemSize(int size);
}
