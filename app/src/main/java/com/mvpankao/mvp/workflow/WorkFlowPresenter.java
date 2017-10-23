package com.mvpankao.mvp.workflow;

import android.content.Context;

import com.mvpankao.http.NetUrl;
import com.mvpankao.model.bean.WorkFlowBean;
import com.mvpankao.model.bean.WorkFlowDetailBean2;
import com.mvpankao.mvp.base.BasePresenterImpl;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import java.util.HashMap;
import java.util.Map;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class WorkFlowPresenter extends BasePresenterImpl<WorkFlowContract.View> implements WorkFlowContract.Presenter{

    @Override
    public void initData(Context mContext, MyOkHttp mMyOkHttp, int page, String userId, String role, String statue) {
        String url = NetUrl.URLHeader + NetUrl.WorkFlow.WorkFlowList;
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        map.put("usertype", role);
        map.put("status", statue);
        map.put("page", page + "");

        mMyOkHttp.post().url(url).params(map).tag(mContext).enqueue(new GsonResponseHandler<WorkFlowBean>() {
            @Override
            public void onFailure(int statusCode, String error_msg) {
                mView.onRequestFailed();
            }

            @Override
            public void onSuccess(int statusCode, WorkFlowBean response) {

                if (response.getCode() == 200) {


                    mView.onSuccess(response);

                } else {
                    mView.onFailure();
                }
            }
        });
    }

    @Override
    public void initData(Context mContext, MyOkHttp mMyOkhttp, String id) {
        String url = NetUrl.URLHeader + NetUrl.WorkFlow.WorkFlowDetail;
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        mMyOkhttp.post().url(url).params(map).tag(mContext).enqueue(new GsonResponseHandler<WorkFlowDetailBean2>() {
            @Override
            public void onFailure(int statusCode, String error_msg) {
              mView.onRequestFailed();
            }

            @Override
            public void onSuccess(int statusCode, WorkFlowDetailBean2 response) {
                if (response.getCode() == 200) {


                   mView.onSuccess(response);
                } else {
                 mView.onFailure();
                }
            }
        });
    }
}
