package com.mvpankao.view;

/**
 * description：查询View
 *
 * @auth wangjian
 * @time 2017/3/13 10:23
 */
public interface QueryResultView {
    String getUserId();
    String getRole();
    //获取搜索框内容
    String getQueryContent();
    //获取页码
    int getPage();

    void getItemSize(int size);

    void showToast(String msg);

    void onSuccess();

    void onFailure();

    void onError();
}
