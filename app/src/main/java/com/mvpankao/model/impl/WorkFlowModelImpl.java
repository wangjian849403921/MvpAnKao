package com.mvpankao.model.impl;

import android.content.Context;

import com.mvpankao.http.NetUrl;
import com.mvpankao.model.OnWorkFlowListener;
import com.mvpankao.model.WorkFlowModel;
import com.mvpankao.model.bean.WorkFlowBean;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/10 11:49
 */
public class WorkFlowModelImpl implements WorkFlowModel {
    @Override
    public void initData(int page, final OnWorkFlowListener OnWorkFlowListener, Context mContext, MyOkHttp mMyOkHttp, final List<WorkFlowBean.ObjectBean.ListBean> list, String userId, String role, String statue) {
        String url = NetUrl.URLHeader + NetUrl.WorkFlow.WorkFlowList;
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        map.put("usertype", role);
        map.put("status", statue);
        map.put("page", page + "");

        mMyOkHttp.post().url(url).params(map).tag(mContext).enqueue(new GsonResponseHandler<WorkFlowBean>() {
            @Override
            public void onFailure(int statusCode, String error_msg) {
                OnWorkFlowListener.onFailure();
            }

            @Override
            public void onSuccess(int statusCode, WorkFlowBean response) {

                if (response.getCode() == 200) {

                    OnWorkFlowListener.getItemSize(response.getObject().getTotal());
                    for (int i = 0; i < response.getObject().getList().size(); i++) {
                        list.add(response.getObject().getList().get(i));
                    }
                    OnWorkFlowListener.onSuccess();

                } else {
                    OnWorkFlowListener.onError();
                }
            }
        });
    }
}
