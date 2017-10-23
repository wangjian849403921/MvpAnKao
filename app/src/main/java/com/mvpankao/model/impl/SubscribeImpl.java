package com.mvpankao.model.impl;

import android.content.Context;

import com.mvpankao.http.NetUrl;
import com.mvpankao.model.OnSubscribeListener;
import com.mvpankao.model.SubscribeModel;
import com.mvpankao.model.bean.SubscribeChildrenItem;
import com.mvpankao.model.bean.SubscribeGroupItem;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.JsonResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description：我的预约
 *
 * @auth wangjian
 * @time 2017/3/13 9:57
 */
public class SubscribeImpl implements SubscribeModel {
    @Override
    public void initData(final OnSubscribeListener OnSubscribeListener, Context context, MyOkHttp myOkHttp, final List<SubscribeGroupItem> list, String userId, String date) {
        String url = NetUrl.URLHeader + NetUrl.MySubscribe.SubscribeList;
        Map<String, String> map = new HashMap<>();
        map.put("userid", userId);
        map.put("date", date);

        myOkHttp.post().url(url).params(map).tag(context).enqueue( new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) {
                try {

                    String code = response.getString("code");
                    if (code.equals("200")) {

                        JSONArray groupList = response.getJSONArray("object");
                        for (int i = 0; i < groupList.length(); i++) {
                            JSONObject groupObj = (JSONObject) groupList.get(i);

                            String groupname = groupObj.getString("month");
                            JSONArray childrenList = groupObj.getJSONArray("data");
                            SubscribeGroupItem group = new SubscribeGroupItem(groupname);

                            if (childrenList.length() != 0) {
                                for (int j = 0; j < childrenList.length(); j++) {

                                    JSONObject childObj = (JSONObject) childrenList.get(j);
                                    String id = childObj.getString("id");
                                    String childname = childObj.getString("experimentNote");
                                    String starttime = childObj.getString("experimentBeginDate");
                                    String endtime = childObj.getString("experimentEndDate");

                                    String statue = childObj.getString("experimentStatus");
                                    SubscribeChildrenItem child = new SubscribeChildrenItem(id, starttime, endtime, childname, statue);

                                    group.addChildrenItem(child);

                                }
                                list.add(group);
                            }


                        }
                       OnSubscribeListener.onSuccess();

                    } else {
                     OnSubscribeListener.onError();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
             OnSubscribeListener.onFailure();
            }
        });
    }
}
