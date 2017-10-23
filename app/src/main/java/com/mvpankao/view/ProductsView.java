package com.mvpankao.view;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/8 11:57
 */
public interface ProductsView {
    //获取页码
    int getPage();

    void getItemSize(int size);

    void showToast(String msg);

    void onSuccess();

    void onFailure();

    void onError();
}
