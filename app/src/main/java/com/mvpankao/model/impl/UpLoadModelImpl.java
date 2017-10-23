package com.mvpankao.model.impl;

import android.content.Context;

import com.mvpankao.http.NetUrl;
import com.mvpankao.model.OnUpLoadListener;
import com.mvpankao.model.UploadModel;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.JsonResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.Map;

/**
 * description：上传图片Model实现，这里主要是网络请求的操作。
 *
 * @auth wangjian
 * @time 2017/3/10 15:50
 */
public class UpLoadModelImpl implements UploadModel {
    String url = "";

    @Override
    public void upLoad(final OnUpLoadListener onUpLoadListener, final Context context, MyOkHttp myOkHttp, String type, Map<String, String> map, Map<String, File> filemap) {


        switch (type) {
            case "1":
                url = NetUrl.URLHeader + NetUrl.WorkFlow.UPLOADTask;
                break;
            case "2":
                url = NetUrl.URLHeader + NetUrl.WorkOrder.AddWorkLog;
                break;
            case "3":
                url = NetUrl.URLHeader + NetUrl.WorkOrder.ParamImageUpdate;
                break;
        }
        myOkHttp.upload()
                .url(url)
                .params(map)
                .files(filemap)
                .tag(this)
                .enqueue(new JsonResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, JSONObject response) {
                        try {
                            JSONObject json = new JSONObject(response.toString());

                            String code = json.getString("code");
                            if (code.equals("200")) {
                                onUpLoadListener.onSuccess();
                            } else {
                                onUpLoadListener.onError();

                            }
                        } catch (JSONException e) {

                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        onUpLoadListener.onFailure();


                    }
                });
    }
}
