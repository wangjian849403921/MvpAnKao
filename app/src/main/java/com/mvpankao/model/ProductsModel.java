package com.mvpankao.model;

import android.content.Context;

import com.mvpankao.model.bean.ProductsBean;
import com.tsy.sdk.myokhttp.MyOkHttp;

import java.util.List;

/**
 * description：产品接口
 *
 * @auth wangjian
 * @time 2017/3/8 13:51
 */

public interface ProductsModel {
    //产品列表
    void initData(int page, OnProductsListener onProductsListener, Context context, MyOkHttp myOkHttp, List<ProductsBean.ObjectBean.ListBean> list, String type, String typeid);

    //产品详情
    void initData(OnProductsListener onProductsListener, Context context, MyOkHttp myOkHttp, ProductsBean.ObjectBean.ListBean mProducts, String id, List<String> listimage);

    void initSelectData(OnProductsListener onProductsListener, Context context, MyOkHttp myOkHttp);

    void initAdapterData(OnProductsListener onProductsListener, Context context, MyOkHttp myOkHttp,String productId,ProductsBean.ObjectBean.ListBean mProductOrder,String addressid,String address, String reciver,String phone);

}
