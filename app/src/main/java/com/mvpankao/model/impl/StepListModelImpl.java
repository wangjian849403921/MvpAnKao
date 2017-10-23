package com.mvpankao.model.impl;

import android.content.Context;

import com.mvpankao.http.NetUrl;
import com.mvpankao.model.OnStepListListener;
import com.mvpankao.model.StepListModel;
import com.mvpankao.model.bean.StepBean;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description：评论步骤列表Model实现，这里主要是网络请求的操作。
 *
 * @auth wangjian
 * @time 2017/3/10 17:11
 */
public class StepListModelImpl implements StepListModel {
    @Override
    public void initStepData(final OnStepListListener onStepListListener, Context context, MyOkHttp myOkHttp, final List<StepBean.ObjectBean> list, String id) {
        String url = NetUrl.URLHeader + NetUrl.WorkFlow.WorkFlowStepList;
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        myOkHttp.post().url(url).params(map).tag(context).enqueue(new GsonResponseHandler<StepBean>() {
            @Override
            public void onFailure(int statusCode, String error_msg) {
                onStepListListener.onFailure();

            }

            @Override
            public void onSuccess(int statusCode, StepBean response) {

                if (response.getCode() == 200) {
                    for (int i = 0; i < response.getObject().size(); i++) {
                        list.add(response.getObject().get(i));
                    }
                    onStepListListener.onSuccess();

                } else {
                    onStepListListener.onError();
                }
            }
        });
    }
}
