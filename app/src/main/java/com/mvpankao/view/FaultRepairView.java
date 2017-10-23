package com.mvpankao.view;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/13 15:53
 */
public interface FaultRepairView {
    String getType();
    String getUserId();
    String getUserName();
    String getPhone();
    String getRepairId();
    String getLineName();
    String getArea();
    String getDetailAddress();
    String getCompany();
    String getStatue();
    String getRemark();
    void showToast(String msg);

    //成功
    void onSuccess();

    //失败
    void onFailure();

    //错误
    void onError();
}
