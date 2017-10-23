package com.mvpankao.model.impl;

import android.content.Context;

import com.mvpankao.http.NetUrl;
import com.mvpankao.model.HomeProductModel;
import com.mvpankao.model.OnHomeProductListener;
import com.mvpankao.model.bean.HomePageProduct;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/8 11:22
 */

public class HomeProductImpl implements HomeProductModel {

    @Override
    public void initData(final OnHomeProductListener onHomeProductListener, Context context, MyOkHttp myOkHttp, final List<HomePageProduct.ObjectBean> list, String type) {

        String url = NetUrl.URLHeader + NetUrl.Product.HomePageProduct;
        Map<String, String> map = new HashMap<>();
        map.put("categorytype",type);

        myOkHttp.post().url(url).params(map).tag(context).enqueue(new GsonResponseHandler<HomePageProduct>() {
            @Override
            public void onFailure(int statusCode, String error_msg) {
                onHomeProductListener.onFailure();
            }

            @Override
            public void onSuccess(int statusCode, HomePageProduct response) {

                if (response.getCode() == 200) {

                    for (int i = 0; i < response.getObject().size(); i++) {
                        list.add(response.getObject().get(i));
                    }
                    onHomeProductListener.onSuccess();
                } else {

                    onHomeProductListener.onError();
                }
            }
        });
    }
}
