package com.mvpankao.model.impl;

import android.content.Context;

import com.blankj.utilcode.utils.LogUtils;
import com.mvpankao.http.NetUrl;
import com.mvpankao.model.OnProductsListener;
import com.mvpankao.model.ProductsModel;
import com.mvpankao.model.bean.ProductDetailBean;
import com.mvpankao.model.bean.ProductsBean;
import com.smarttop.library.utils.LogUtil;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;
import com.tsy.sdk.myokhttp.response.JsonResponseHandler;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * description：产品Model实现，这里主要是网络请求的操作。
 *
 * @auth wangjian
 * @time 2017/3/8 13:59
 */

public class ProductsImpl implements ProductsModel {
    /**
     * 产品列表，筛选结果
     *
     * @param page
     * @param onProductsListener
     * @param context
     * @param myOkHttp
     * @param list
     * @param type
     * @param typeid
     */
    @Override
    public void initData(int page, final OnProductsListener onProductsListener, Context context, MyOkHttp myOkHttp, final List<ProductsBean.ObjectBean.ListBean> list, String type, String typeid) {
        String url = NetUrl.URLHeader + NetUrl.Product.ProductList;
        Map<String, String> map = new HashMap<>();
        map.put("page", page + "");
        map.put("categoryId", type);
        map.put("categoryIdTwo", typeid);
        myOkHttp.post().url(url).params(map).tag(context).enqueue(new GsonResponseHandler<ProductsBean>() {
            @Override
            public void onFailure(int statusCode, String error_msg) {
                onProductsListener.onFailure();
            }

            @Override
            public void onSuccess(int statusCode, ProductsBean response) {
                LogUtils.d(response.getCode());
                if (response.getCode() == 200) {

                    if (response.getObject() != null) {
                        onProductsListener.getItemSize(response.getObject().getTotal());
                        for (int i = 0; i < response.getObject().getList().size(); i++) {
                            list.add(response.getObject().getList().get(i));
                        }
                        onProductsListener.onSuccess();
                    } else {
                        onProductsListener.onError();

                    }
                } else {
                    onProductsListener.onError();
                }
            }
        });
    }


    /**
     * 产品详情
     *
     * @param onProductsListener
     * @param context
     * @param myOkHttp
     * @param mProducts
     * @param id
     * @param listimage
     */
    List<String> iconlist = null;

    @Override
    public void initData(final OnProductsListener onProductsListener, Context context, MyOkHttp myOkHttp, final ProductsBean.ObjectBean.ListBean mProducts, String id, final List<String> listimage) {
        String url = NetUrl.URLHeader + NetUrl.Product.ProductDetail;
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        myOkHttp.post().url(url).params(map).tag(context).enqueue(new GsonResponseHandler<ProductDetailBean>() {
            @Override
            public void onFailure(int statusCode, String error_msg) {
                onProductsListener.onFailure();
            }

            @Override
            public void onSuccess(int statusCode, ProductDetailBean response) {
                if (response.getCode() == 200) {


                    mProducts.setProductId(response.getObject().getProduct().getId());
                    mProducts.setProductName(response.getObject().getProduct().getProductName());
                    mProducts.setProductInventory(response.getObject().getProduct().getProductStockSize());
                    mProducts.setProductSubname(response.getObject().getProduct().getProductSubname());
                    iconlist = response.getObject().getIconlist();
                    for (int i = 0; i < iconlist.size(); i++) {
                        listimage.add(NetUrl.DOCHeader + iconlist.get(i));
                    }
                    onProductsListener.onSuccess();

                } else {
                    onProductsListener.onError();
                }
            }
        });
    }

    /**
     * 筛选菜单数据
     *
     * @param onProductsListener
     * @param context
     * @param mMyOkhttp
     */
    @Override
    public void initSelectData(final OnProductsListener onProductsListener, Context context, MyOkHttp mMyOkhttp) {
        String url = NetUrl.URLHeader + NetUrl.Product.TypeSelect;
        Map<String, String> map = new HashMap<>();

        map.put("", "");

        mMyOkhttp.post().params(map).url(url).tag(this).enqueue(new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) {
                try {
                    if (response.getString("code").equals("200")) {
                        LogUtil.d("", response.toString());

                        onProductsListener.setJsonArray(response.getJSONArray("object"));
                        onProductsListener.onSuccess();

                    } else {
                        onProductsListener.onError();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                onProductsListener.onFailure();

            }
        });

    }

    /**
     * Adapter弹窗
     *
     * @param onProductsListener
     * @param context
     * @param myOkHttp
     * @param productId
     * @param mProductOrder
     * @param addressid
     * @param address
     * @param reciver
     * @param phone
     */
    @Override
    public void initAdapterData(final OnProductsListener onProductsListener, Context context, MyOkHttp myOkHttp, String productId, final ProductsBean.ObjectBean.ListBean mProductOrder, final String addressid, final String address, final String reciver, final String phone) {

        String url = NetUrl.URLHeader + NetUrl.Product.TypeSelect2;
        Map<String, String> map = new HashMap<>();

        map.put("productId", productId);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        MyOkHttp mMyOkhttp = new MyOkHttp(okHttpClient);
        mMyOkhttp.post().url(url).params(map).tag(this).enqueue(new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) {
                try {
                    if (response.getString("code").equals("200")) {
                        onProductsListener.setJsonArray(response.getJSONArray("object"));
                        onProductsListener.onSuccess(mProductOrder);
                    } else {
                        onProductsListener.onError();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                onProductsListener.onFailure();
            }
        });
    }
}
