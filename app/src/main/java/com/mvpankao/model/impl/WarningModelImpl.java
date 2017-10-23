package com.mvpankao.model.impl;

import android.content.Context;

import com.mvpankao.http.NetUrl;
import com.mvpankao.model.OnWarningListener;
import com.mvpankao.model.WarningModel;
import com.mvpankao.model.bean.WarningBean;
import com.mvpankao.model.bean.WarningDetailBean;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description：警报Model实现，这里主要是网络请求的操作。
 *
 * @auth wangjian
 * @time 2017/3/14 10:16
 */
public class WarningModelImpl implements WarningModel {
    @Override
    public void initData(int page, final OnWarningListener OnWarningListener, Context context, MyOkHttp myOkHttp, final List<WarningBean.ObjectBean.ListBean> list) {
        String url = NetUrl.URLHeader + NetUrl.WorkOrder.WarningList;
        Map<String, String> map = new HashMap<>();
        map.put("page", page + "");
        myOkHttp.post().url(url).params(map).tag(context).enqueue(new GsonResponseHandler<WarningBean>() {
            @Override
            public void onFailure(int statusCode, String error_msg) {

                OnWarningListener.onFailure();

            }

            @Override
            public void onSuccess(int statusCode, WarningBean response) {
                if (response.getCode() == 200) {

                    OnWarningListener.getItemSize(response.getObject().getTotal());
                    for (int i = 0; i < response.getObject().getList().size(); i++) {
                        list.add(response.getObject().getList().get(i));
                    }
                    OnWarningListener.onSuccess();

                } else {
                    OnWarningListener.onError();
                }
            }
        });
    }

    @Override
    public void initSearchData(int page, final OnWarningListener OnWarningListener, Context context, MyOkHttp myOkHttp, final List<WarningBean.ObjectBean.ListBean> list, Map<String, String> map) {
        String url = NetUrl.URLHeader + NetUrl.WorkOrder.WarningList;
        myOkHttp.post().url(url).params(map).tag(context).enqueue(new GsonResponseHandler<WarningBean>() {
            @Override
            public void onFailure(int statusCode, String error_msg) {

                OnWarningListener.onFailure();

            }

            @Override
            public void onSuccess(int statusCode, WarningBean response) {
                if (response.getCode() == 200) {

                    OnWarningListener.getItemSize(response.getObject().getTotal());
                    for (int i = 0; i < response.getObject().getList().size(); i++) {
                        list.add(response.getObject().getList().get(i));
                    }
                    OnWarningListener.onSuccess();

                } else {
                    OnWarningListener.onError();
                }
            }
        });
    }

    @Override
    public void initDetailData(final OnWarningListener OnWarningListener, Context Context, MyOkHttp MyOkHttp, final List<WarningDetailBean.ObjectBean.TimePointBean> list, String id) {
        String url = NetUrl.URLHeader + NetUrl.WorkOrder.WarningDetail;
        Map<String, String> map = new HashMap<>();
        map.put("AlarmId", id);

        MyOkHttp.post().url(url).params(map).tag(Context).enqueue(new GsonResponseHandler<WarningDetailBean>() {
            @Override
            public void onFailure(int statusCode, String error_msg) {
                OnWarningListener.onFailure();
            }

            @Override
            public void onSuccess(int statusCode, WarningDetailBean response) {

                if (response.getCode() == 200) {

                    if (response.getObject() != null) {

                        for (int i = 0; i < response.getObject().getTimePoint().size(); i++) {
                            list.add(response.getObject().getTimePoint().get(i));
                        }
                        OnWarningListener.onSuccess(response);

                    }
                } else {
                    OnWarningListener.onError();
                }
            }
        });
    }
}
