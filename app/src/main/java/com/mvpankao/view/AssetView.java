package com.mvpankao.view;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/14 10:52
 */
public interface AssetView {
    String getUserId();
    void showToast(String msg);

    void onSuccess();

    void onFailure();

    void onError();
}
