package com.mvpankao.model.impl;


import android.content.Context;

import com.mvpankao.http.NetUrl;
import com.mvpankao.model.CommitOrderModel;
import com.mvpankao.model.OnCommitListener;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.JsonResponseHandler;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 提交订单Model实现，这里主要是网络请求的操作。
 * Author:wangjian
 * Date: 2017-03-07
 * Time: 10:42
 */
public class CommitOrderModelImpl implements CommitOrderModel {


    @Override
    public void commitShopCarOrder(final OnCommitListener onCommitListener, Context context, MyOkHttp myOkHttp, String userId, String stockid, String productId, String number, String shopcarid, String receiver, String phone, String address, String require) {
        String url = NetUrl.URLHeader + NetUrl.Product.CommitShopCarOrder;
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        map.put("productStockDetailIdfk", stockid);
        map.put("productIdfk", productId);
        map.put("productOrderNum", number);
        map.put("shopCarId", shopcarid);
        map.put("receiverName", receiver);
        map.put("receiverPhone", phone);
        map.put("receiverAddress", address);
        map.put("customerRequirement", require);
        myOkHttp.post().url(url).params(map).tag(context).enqueue(new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) {
                try {
                    if (response.getString("code").equals("200")) {
                        onCommitListener.onSuccess();


                    } else {
                        onCommitListener.onError();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                onCommitListener.onFailure();

            }
        });

    }

    @Override
    public void commitOrder(final OnCommitListener onCommitListener, Context context, MyOkHttp myOkHttp, String userId, String stockId, String productId, String number, String receiver, String phone, String address, String require) {
        String url = NetUrl.URLHeader + NetUrl.Product.CommitOrder;
        final Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        map.put("productStockDetailIdfk", stockId);
        map.put("productIdfk", productId);
        map.put("productOrderNum", number);

        map.put("receiverName", receiver);
        map.put("receiverPhone", phone);
        map.put("receiverAddress", address);
        map.put("customerRequirement", require);

        myOkHttp.post().url(url).params(map).tag(context).enqueue(new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) {
                try {
                    if (response.getString("code").equals("200")) {
                        onCommitListener.onSuccess();

                    } else {
                        onCommitListener.onError();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                onCommitListener.onFailure();
            }
        });
    }
}
