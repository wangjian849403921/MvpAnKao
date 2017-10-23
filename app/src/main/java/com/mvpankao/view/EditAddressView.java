package com.mvpankao.view;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/8 17:44
 */
public interface EditAddressView {
    String getUserId();
    String getAddressId();
    String getReceiver();
    String getPhone();
    String getProvince();
    String getCity();
    String getArea();
    String getDetailAddress();
    String getIsDefault();
    void showDialog();
    void dismissDialog();
    void showToast(String msg);

    //成功
    void onSuccess();

    //失败
    void onFailure();

    //错误
    void onError();
}
