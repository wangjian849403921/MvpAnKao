package com.mvpankao.view;

import com.mvpankao.model.bean.ProductsBean;

import org.json.JSONArray;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/9 11:49
 */
public interface ProductAdapterView {
    //获取地址ID
    String getAddressId();
    //获取地址
    String getAddress();
    //获取联系人
    String getReciver();
    //获取联系方式
    String getPhone();
    void showToast(String msg);
    //成功
    void onSuccess(ProductsBean.ObjectBean.ListBean mProductOrder);

    //失败
    void onFailure();

    //错误
    void onError();
    void setJsonArray(JSONArray jsonArray);
}
