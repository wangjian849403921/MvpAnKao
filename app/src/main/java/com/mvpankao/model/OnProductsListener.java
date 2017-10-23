package com.mvpankao.model;

import com.mvpankao.model.bean.ProductsBean;

import org.json.JSONArray;

/**
 * description：监听器用来处理Model实现后的操作
 *
 * @auth wangjian
 * @time 2017/3/8 13:48
 */

public interface OnProductsListener {
    //请求数据错误
    void onError();

    //请求成功
    void onSuccess();
    //成功
    void onSuccess(ProductsBean.ObjectBean.ListBean mProductOrder);
    //请求失败
    void onFailure();

    //获取数据的总条数
    void getItemSize(int size);

    void setJsonArray(JSONArray JSONArray);
}
