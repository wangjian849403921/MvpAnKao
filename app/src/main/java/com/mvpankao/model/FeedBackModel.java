package com.mvpankao.model;

import android.content.Context;

import com.tsy.sdk.myokhttp.MyOkHttp;

/**
 * Description: 反馈接口
 * Author:wangjian
 * Date: 2017-03-07
 * Time: 11:39
 */
public interface FeedBackModel {

    /**
     * 反馈接口
     *
     * @param OnFeedBackListener
     * @param context
     * @param myOkHttp
     * @param title
     * @param content
     * @param username
     * @param phone
     */
    void feedBack(OnFeedBackListener OnFeedBackListener, Context context, MyOkHttp myOkHttp, String title, String content, String username, String phone);
}
