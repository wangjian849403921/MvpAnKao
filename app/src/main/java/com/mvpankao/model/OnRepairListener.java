package com.mvpankao.model;

import com.mvpankao.model.bean.RepairDetailBean;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/10 17:21
 */
public interface OnRepairListener {
    //请求数据错误
    void onError();

    //请求成功
    void onSuccess();
    //请求成功
    void onSuccess(RepairDetailBean repairDetailBean);
    //请求失败
    void onFailure();

    //获取数据的总条数
    void getItemSize(int size);
}
