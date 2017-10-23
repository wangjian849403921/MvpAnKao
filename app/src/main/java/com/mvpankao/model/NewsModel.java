package com.mvpankao.model;

import android.content.Context;

import com.mvpankao.model.bean.NewsBean;
import com.tsy.sdk.myokhttp.MyOkHttp;

import java.util.List;

/**
 * Description: 新闻资讯接口
 * Author:wangjian
 * Date: 2017-03-07
 * Time: 11:39
 */
public interface NewsModel {

    /**
     * @param page
     * @param onNewsListener
     * @param context
     */
    void initNews(int page, OnNewsListener onNewsListener, Context context, MyOkHttp myOkHttp, List<NewsBean.ObjectBean.ListBean> list);
}
