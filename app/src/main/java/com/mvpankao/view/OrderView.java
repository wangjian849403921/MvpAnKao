package com.mvpankao.view;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/9 17:52
 */
public interface OrderView {
    String getUserId();

    String getStatue();
    //获取页码
    int getPage();

    void getItemSize(int size);

    void showToast(String msg);

    void onSuccess();
    void ondeleteSuccess(int position);
    void onFailure();
    void ondeleteFailure();
    void onError();
    void ondeleteError();
}
