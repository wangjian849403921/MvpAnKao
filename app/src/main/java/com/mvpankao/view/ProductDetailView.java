package com.mvpankao.view;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/8 16:39
 */
public interface ProductDetailView {

    void showToast(String msg);
    //获取Id
    String getProductsId();
    //成功
    void onSuccess();

    //失败
    void onFailure();

    //错误
    void onError();
}
