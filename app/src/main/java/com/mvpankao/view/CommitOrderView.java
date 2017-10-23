package com.mvpankao.view;

/**
 * description：提交订单view
 *
 * @auth wangjian
 * @time 2017/3/9 15:08
 */
public interface CommitOrderView {
    void showDialog();

    void showToast(String msg);

    //成功
    void onSuccess();

    //失败
    void onFailure();

    //错误
    void onError();
}
