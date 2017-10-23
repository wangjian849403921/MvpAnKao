package com.mvpankao.mvp.news;

import android.content.Context;

import com.mvpankao.http.NetUrl;
import com.mvpankao.model.bean.NewsBean;
import com.mvpankao.mvp.base.BasePresenterImpl;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class NewsPresenter extends BasePresenterImpl<NewsContract.View> implements NewsContract.Presenter{

    @Override
    public void initData(Context context, MyOkHttp myOkHttp, int page) {
        String url = NetUrl.URLHeader + NetUrl.NEWS.NEWSLIST;
        Map<String, String> map = new HashMap<>();
        map.put("page", page + "");

        myOkHttp.post().url(url).params(map).tag(context).enqueue(new GsonResponseHandler<NewsBean>() {
            @Override
            public void onFailure(int statusCode, String error_msg) {

                mView.onRequestFailed();
            }

            @Override
            public void onSuccess(int statusCode, NewsBean response) {

                if (response.getCode() == 200) {
                    mView.onSuccess(response);

                } else {
                   mView.onFailure();
                }
            }
        });
    }
}
