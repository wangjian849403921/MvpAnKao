package com.mvpankao.view;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/10 15:45
 */
public interface UpLoadView {

    void showDialog();
    void dismissDialog();

    void showToast(String msg);

    void onSuccess();

    void onFailure();

    void onError();
}
