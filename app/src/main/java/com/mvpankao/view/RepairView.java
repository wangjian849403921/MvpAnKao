package com.mvpankao.view;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/10 17:18
 */
public interface RepairView {
    String getUserId();
    String getRole();
    //获取页码
    int getPage();

    void getItemSize(int size);

    void showToast(String msg);

    void onSuccess();

    void onFailure();

    void onError();

}
