package com.mvpankao.model.impl;

import android.content.Context;

import com.apkfuns.logutils.LogUtils;
import com.mvpankao.http.NetUrl;
import com.mvpankao.model.FeedBackModel;
import com.mvpankao.model.OnFeedBackListener;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.JsonResponseHandler;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/9 16:20
 */

public class FeedBackImpl implements FeedBackModel {
    @Override
    public void feedBack(final OnFeedBackListener OnFeedBackListener, Context context, MyOkHttp myOkHttp, String title, String content, String username, String phone) {
        String url = NetUrl.URLHeader + NetUrl.FeedBack.FeedBack;
        Map<String, String> map = new HashMap<>();
        map.put("feedbackTitle", title);
        map.put("feedbackContent", content);
        map.put("feedbackPeople", username);
        map.put("feedbackPhone", phone);


        myOkHttp.post().url(url).params(map).tag(content)
                .enqueue(new JsonResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, JSONObject response) {
                        LogUtils.d(response);
                        try {

                            String code = response.getString("code");
                            if (code.equals("200")) {
                                OnFeedBackListener.onSuccess();
                            } else {

                                OnFeedBackListener.onError();


                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        OnFeedBackListener.onFailure();

                    }
                });
    }
}
