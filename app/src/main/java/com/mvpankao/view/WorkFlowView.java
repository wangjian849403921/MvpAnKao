package com.mvpankao.view;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/10 11:40
 */
public interface WorkFlowView {
    String getUserId();

    String getRole();

    String getStatue();

    //获取页码
    int getPage();

    void getItemSize(int size);

    void showToast(String msg);

    void onSuccess();

    void onFailure();

    void onError();
}
