package com.mvpankao.model.impl;


import android.content.Context;

import com.mvpankao.http.NetUrl;
import com.mvpankao.model.NewsModel;
import com.mvpankao.model.OnNewsListener;
import com.mvpankao.model.bean.NewsBean;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: 新闻资讯Model实现，这里主要是网络请求的操作。
 * Author:wangjian
 * Date: 2017-03-07
 * Time: 10:42
 */
public class NewsModelImpl implements NewsModel {



    @Override
    public void initNews(int page, final OnNewsListener onNewsListener, Context context, MyOkHttp myOkHttp, final List<NewsBean.ObjectBean.ListBean> mInfo) {
        String url = NetUrl.URLHeader + NetUrl.NEWS.NEWSLIST;
        Map<String, String> map = new HashMap<>();
        map.put("page", page + "");

        myOkHttp.post().url(url).params(map).tag(context).enqueue(new GsonResponseHandler<NewsBean>() {
            @Override
            public void onFailure(int statusCode, String error_msg) {

                onNewsListener.onFailure();
            }

            @Override
            public void onSuccess(int statusCode, NewsBean response) {

                if (response.getCode() == 200) {
                    onNewsListener.getItemSize(response.getObject().getTotal());
                    for (int i = 0; i < response.getObject().getList().size(); i++) {
                        mInfo.add(response.getObject().getList().get(i));
                    }
                    onNewsListener.onSuccess();
                } else {
                   onNewsListener.onError();
                }
            }
        });
    }
}
