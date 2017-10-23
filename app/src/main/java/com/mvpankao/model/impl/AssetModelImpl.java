package com.mvpankao.model.impl;

import android.content.Context;

import com.mvpankao.http.NetUrl;
import com.mvpankao.model.AssetModel;
import com.mvpankao.model.OnAssetListener;
import com.mvpankao.model.bean.AssetBean;
import com.mvpankao.model.bean.ParamChildrenItem;
import com.mvpankao.model.bean.ParamGroupItem;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;
import com.tsy.sdk.myokhttp.response.JsonResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/14 11:00
 */
public class AssetModelImpl implements AssetModel {
    @Override
    public void initData(final OnAssetListener OnAssetListener, Context context, MyOkHttp myOkHttp, final List<AssetBean.ObjectBean> list, String userId) {
        String url = NetUrl.URLHeader + NetUrl.WorkOrder.AssertList;
        Map<String, String> map = new HashMap<>();

        map.put("userid", userId);
        myOkHttp.post().url(url).params(map).tag(context).enqueue(new GsonResponseHandler<AssetBean>() {
            @Override
            public void onFailure(int statusCode, String error_msg) {
                OnAssetListener.onFailure();
            }

            @Override
            public void onSuccess(int statusCode, AssetBean response) {

                if (response.getCode() == 200) {


                    for (int i = 0; i < response.getObject().size(); i++) {
                        list.add(response.getObject().get(i));
                    }

                    OnAssetListener.onSuccess();

                } else {
                    OnAssetListener.onError();
                }
            }
        });
    }

    @Override
    public void initAssetParam(final OnAssetListener OnAssetListener, Context context, MyOkHttp myOkHttp, final List<ParamGroupItem> list, String id, String level) {
        String url = NetUrl.URLHeader + NetUrl.WorkOrder.ParamInfo;
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("level", level);
        myOkHttp.post().url(url).params(map).tag(context).enqueue(new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) {
                try {
                    String code = response.getString("code");
                    if (code.equals("200")) {


                        JSONArray groupList = response.getJSONArray("object");
                        for (int i = 0; i < groupList.length(); i++) {
                            JSONObject groupObj = (JSONObject) groupList.get(i);

                            String groupname = groupObj.getString("key");
                            JSONArray childrenList = groupObj.getJSONArray("data");
                            ParamGroupItem group = new ParamGroupItem(groupname);


                            for (int j = 0; j < childrenList.length(); j++) {

                                JSONObject childObj = (JSONObject) childrenList.get(j);

                                String childname = childObj.getString("assetParamName");
                                String data = childObj.getString("assetParamData");

                                ParamChildrenItem child = new ParamChildrenItem(childname, data);

                                group.addChildrenItem(child);

                            }

                            list.add(group);

                        }
                        OnAssetListener.onSuccess();
                    } else {
                        OnAssetListener.onError();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                OnAssetListener.onFailure();
            }
        });

    }
}
