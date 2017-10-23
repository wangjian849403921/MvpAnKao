package com.mvpankao.model.impl;

import android.content.Context;

import com.mvpankao.http.NetUrl;
import com.mvpankao.model.OnOrderListener;
import com.mvpankao.model.OrderModel;
import com.mvpankao.model.bean.MyOrder;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;
import com.tsy.sdk.myokhttp.response.JsonResponseHandler;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description：订单Model实现，这里主要是网络请求的操作。
 *
 * @auth wangjian
 * @time 2017/3/9 18:02
 */
public class OrderModelImpl implements OrderModel {
    @Override
    public void initOrder(int page, final OnOrderListener OnOrderListener, Context context, MyOkHttp myOkHttp, final List<MyOrder.ObjectBean.ListBean> list, String userid, String statue) {
        String url = NetUrl.URLHeader + NetUrl.MyOrder.MyOrder;
        Map<String, String> map = new HashMap<>();
        map.put("userId", userid);
        map.put("page", page + "");
        map.put("status", statue);
        map.put("rows", "20");

        myOkHttp.post().url(url).params(map).tag(context).enqueue(new GsonResponseHandler<MyOrder>() {
            @Override
            public void onFailure(int statusCode, String error_msg) {
                OnOrderListener.onFailure();
            }

            @Override
            public void onSuccess(int statusCode, MyOrder response) {
                if (response.getCode() == 200) {

                    OnOrderListener.getItemSize(response.getObject().getTotal());
                    for (int i = 0; i < response.getObject().getList().size(); i++) {
                        list.add(response.getObject().getList().get(i));
                    }
                    OnOrderListener.onSuccess();
                } else {
                    OnOrderListener.onError();
                }
            }
        });

    }

    @Override
    public void deleteOrder(final OnOrderListener OnOrderListener, final Context context, MyOkHttp myOkHttp, String orderid, final int position) {
        String url = NetUrl.URLHeader + NetUrl.MyOrder.MyOrderDELETE;
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", orderid);
        myOkHttp.post().url(url).params(map).tag(context).enqueue(new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) {
                try {
                    if (response.getString("code").equals("200")) {
                        OnOrderListener.ondeleteSuccess(position);
                    } else {
                        OnOrderListener.ondeleteError();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
               OnOrderListener.ondeleteFailure();

            }
        });
    }
}

