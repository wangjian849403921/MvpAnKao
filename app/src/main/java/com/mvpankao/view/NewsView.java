package com.mvpankao.view;

/**
 * description：新闻资讯view
 *
 * @auth wangjian
 * @time 2017/3/7 17:35
 */
public interface NewsView {
    //获取页码
    int getPage();

    void getItemSize(int size);

    void showToast(String msg);

    void onSuccess();

    void onFailure();

    void onError();



}
