package com.mvpankao.model.impl;

import android.content.Context;
import android.graphics.Color;

import com.apkfuns.logutils.LogUtils;
import com.mvpankao.http.NetUrl;
import com.mvpankao.model.OnWorkOrderListener;
import com.mvpankao.model.WorkOrderModel;
import com.mvpankao.model.bean.ChildEntity;
import com.mvpankao.model.bean.CreateOrderAssert;
import com.mvpankao.model.bean.ParentEntity;
import com.mvpankao.model.bean.WorkOrderChildrenItem;
import com.mvpankao.model.bean.WorkOrderDeatil;
import com.mvpankao.model.bean.WorkOrderGroupItem;
import com.mvpankao.model.bean.WorkOrderLogBean;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;
import com.tsy.sdk.myokhttp.response.JsonResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description：工单Model实现，这里主要是网络请求的操作。
 *
 * @auth wangjian
 * @time 2017/3/13 17:03
 */
public class WorkOrderModelImpl implements WorkOrderModel {

    @Override
    public void initData(final OnWorkOrderListener OnWorkOrderListener, Context context, MyOkHttp myOkHttp, final List<WorkOrderGroupItem> list, String userId) {
        String url = NetUrl.URLHeader + NetUrl.WorkOrder.WorkOrderList;
        Map<String, String> map = new HashMap<>();
        map.put("createUserId", userId);
        map.put("page", "1");

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
                            WorkOrderGroupItem group = new WorkOrderGroupItem(groupname);
                            if (childrenList.length() != 0) {

                                for (int j = 0; j < childrenList.length(); j++) {

                                    JSONObject childObj = (JSONObject) childrenList.get(j);
                                    String id = childObj.getString("engineeringId");
                                    String childname = "";
                                    if (childObj.has("engineering_asset_position")){
                                       childname= childObj.getString("engineering_asset_position");
                                    }

                                    String starttime = "";
                                    if (childObj.has("executorPlanBeginDate")){
                                        starttime= childObj.getString("executorPlanBeginDate");
                                    }
                                    String endtime = "";
                                    if (childObj.has("executorPlanEndDate")){
                                        endtime= childObj.getString("executorPlanEndDate");
                                    }
                                    String content = childObj.getString("engineeringNote");
                                    String overdue = childObj.getString("isOverdue");
                                    WorkOrderChildrenItem child = new WorkOrderChildrenItem(id, childname, starttime, endtime, content, overdue);

                                    group.addChildrenItem(child);

                                }

                                list.add(group);
                            }
                        }
                        OnWorkOrderListener.onWorkOrderSuccess();

                    } else {
                        OnWorkOrderListener.onError();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                OnWorkOrderListener.onFailure();
            }
        });
    }

    @Override
    public void initSearchData(final OnWorkOrderListener OnWorkOrderListener, Context context, MyOkHttp myOkHttp, final List<WorkOrderGroupItem> list, Map<String, String> map) {
        String url = NetUrl.URLHeader + NetUrl.WorkOrder.WorkOrderList;
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
                            WorkOrderGroupItem group = new WorkOrderGroupItem(groupname);
                            if (childrenList.length() != 0) {

                                for (int j = 0; j < childrenList.length(); j++) {

                                    JSONObject childObj = (JSONObject) childrenList.get(j);
                                    String id = childObj.getString("engineeringId");
                                    String childname = "";
                                    if (childObj.has("engineering_asset_position")){
                                        childname= childObj.getString("engineering_asset_position");
                                    }

                                    String starttime = "";
                                    if (childObj.has("executorPlanBeginDate")){
                                        starttime= childObj.getString("executorPlanBeginDate");
                                    }
                                    String endtime = "";
                                    if (childObj.has("executorPlanEndDate")){
                                        endtime= childObj.getString("executorPlanEndDate");
                                    }
                                    String content = childObj.getString("engineeringNote");
                                    String overdue = childObj.getString("isOverdue");
                                    WorkOrderChildrenItem child = new WorkOrderChildrenItem(id, childname, starttime, endtime, content, overdue);

                                    group.addChildrenItem(child);

                                }

                                list.add(group);
                            }
                        }
                        OnWorkOrderListener.onWorkOrderSuccess();

                    } else {
                        OnWorkOrderListener.onError();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                OnWorkOrderListener.onFailure();
            }
        });
    }

    @Override
    public void initWorkOrderTypeList(final OnWorkOrderListener OnWorkOrderListener, Context context, MyOkHttp MyOkHttp) {
        String url = NetUrl.URLHeader + NetUrl.WorkOrder.WORKORDERTYPE;
        Map<String, String> map = new HashMap<>();

        map.put("", "");
        MyOkHttp.post().url(url).params(map).tag(context).enqueue(new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) {
                try {
                    if (response.getString("code").equals("200")) {

                        JSONArray obj = response.getJSONArray("object");
                        OnWorkOrderListener.setJsonArray(obj);

                        OnWorkOrderListener.onWorkOrderSuccess();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                OnWorkOrderListener.onFailure();
            }
        });

    }

    @Override
    public void initWarningTypeList(final OnWorkOrderListener OnWorkOrderListener, Context context, MyOkHttp MyOkHttp) {
        String url = NetUrl.URLHeader + NetUrl.WorkOrder.WARNINGTYPE;
        Map<String, String> map = new HashMap<>();

        map.put("", "");
        MyOkHttp.post().url(url).params(map).tag(context).enqueue(new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) {
                try {
                    if (response.getString("code").equals("200")) {

                        JSONArray obj = response.getJSONArray("object");
                        OnWorkOrderListener.setJsonArray(obj);

                        OnWorkOrderListener.onWarningSuccess();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                OnWorkOrderListener.onFailure();
            }
        });
    }

    @Override
    public void initDetailData(final OnWorkOrderListener OnWorkOrderListener, Context Context, MyOkHttp MyOkHttp, String id) {
        String url = NetUrl.URLHeader + NetUrl.WorkOrder.WorkOrderDetail;
        Map<String, String> map = new HashMap<>();
        map.put("engineeringId", id);

        MyOkHttp.post().url(url).params(map).tag(Context).enqueue(new GsonResponseHandler<WorkOrderDeatil>() {
            @Override
            public void onFailure(int statusCode, String error_msg) {
                OnWorkOrderListener.onFailure();

            }

            @Override
            public void onSuccess(int statusCode, WorkOrderDeatil response) {
                if (response.getCode() == 200) {

                    OnWorkOrderListener.onSuccess(response);
                } else {
                    OnWorkOrderListener.onError();
                }

            }
        });
    }

    @Override
    public void createWorkOrder(final OnWorkOrderListener OnWorkOrderListener, Context Context, MyOkHttp mMyOkHttp, String url, Map<String, String> map) {
        mMyOkHttp.post().url(url).params(map).tag(Context).enqueue(new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) {
                try {
                    if (response.getString("code").equals("200")) {
                        OnWorkOrderListener.onEditWorkOrderSuccess();
                    } else {
                        OnWorkOrderListener.onEditError();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                OnWorkOrderListener.onEditFailure();
            }
        });
    }

    @Override
    public void initWorkOrderAssert(final OnWorkOrderListener onWorkOrderListener, Context Context, MyOkHttp MyOkHttp, final List<CreateOrderAssert.ObjectBean> list, String userId) {
        String url = NetUrl.URLHeader + NetUrl.WorkOrder.AssertList;
        Map<String, String> map = new HashMap<>();
        map.put("userid", userId);
        MyOkHttp.post().url(url).params(map).tag(Context).enqueue(new GsonResponseHandler<CreateOrderAssert>() {
            @Override
            public void onFailure(int statusCode, String error_msg) {
                onWorkOrderListener.onFailure();

            }

            @Override
            public void onSuccess(int statusCode, CreateOrderAssert response) {
                LogUtils.d(response.getCode());
                if (response.getCode() == 200) {

                    for (int i = 0; i < response.getObject().size(); i++) {
                        list.add(response.getObject().get(i));
                    }
                    onWorkOrderListener.onWorkOrderSuccess();
                } else {
                    onWorkOrderListener.onError();
                }
            }
        });
    }

    @Override
    public void initWorkOrderLog(final OnWorkOrderListener OnWorkOrderListener, Context Context, MyOkHttp MyOkHttp, final List<WorkOrderLogBean.ObjectBean> list, String id) {
        String url = NetUrl.URLHeader + NetUrl.WorkOrder.WorkLogList;
        Map<String, String> map = new HashMap<>();
        map.put("engineeringId", id);
        LogUtils.d(map);
        LogUtils.d(url);
        MyOkHttp.post().url(url).params(map).tag(Context).enqueue(new GsonResponseHandler<WorkOrderLogBean>() {
            @Override
            public void onFailure(int statusCode, String error_msg) {
                OnWorkOrderListener.onFailure();

            }

            @Override
            public void onSuccess(int statusCode, WorkOrderLogBean response) {

                if (response.getCode() == 200) {

                    if (response.getObject() != null) {
                        for (int i = 0; i < response.getObject().size(); i++) {
                            list.add(response.getObject().get(i));


                        }
                        OnWorkOrderListener.onWorkOrderSuccess();
                    }
                } else {
                    OnWorkOrderListener.onError();
                }
            }
        });
    }

    @Override
    public void initAssertArea(final OnWorkOrderListener OnWorkOrderListener, final Context Context, MyOkHttp MyOkHttp, final ArrayList<ParentEntity> parents, String id) {
        String url = NetUrl.URLHeader + NetUrl.WorkOrder.AssertDetail;
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        MyOkHttp.post().url(url).params(map).tag(Context).enqueue(new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) {
                try {
                    if (response.getString("code").equals("200")) {
                        JSONArray parentarr = response.getJSONArray("object");
                        for (int i = 0; i < parentarr.length(); i++) {
                            String parentName = parentarr.getJSONObject(i).getString("assetOneName");
                            String parentid = parentarr.getJSONObject(i).getString("idOne");
                            String level = parentarr.getJSONObject(i).getString("assetOneLevel");
                            String icon = parentarr.getJSONObject(i).getString("assetOneIcon");

                            ParentEntity parent = new ParentEntity();
                            parent.setGroupName(parentName);
                            parent.setGroupId(parentid);
                            parent.setLevel(level);
                            parent.setIcon(icon);
                            parent.setGroupColor(Context.getResources().getColor(
                                    android.R.color.holo_red_light));

                            JSONObject groupObj = (JSONObject) parentarr.get(i);
                            JSONArray arry2 = groupObj.getJSONArray("assetTwoList");
                            ArrayList<ChildEntity> childs = new ArrayList<ChildEntity>();

                            for (int j = 0; j < arry2.length(); j++) {
                                String groupName = arry2.getJSONObject(j).getString("assetTwoName");
                                String groupId = arry2.getJSONObject(j).getString("idTwo");
                                String level2 = arry2.getJSONObject(j).getString("assetTwoLevel");
                                String groupicon = arry2.getJSONObject(j).getString("assetTwoIcon");

                                ChildEntity child = new ChildEntity();
                                child.setGroupName(groupName);
                                child.setGroupId(groupId);
                                child.setGroupLevel(level2);
                                child.setGroupIcon(groupicon);
                                child.setGroupColor(Color.parseColor("#ff00ff"));

                                JSONObject childObj = (JSONObject) arry2.get(j);
                                JSONArray arry3 = childObj.getJSONArray("assetThreeList");

                                ArrayList<String> childNames = new ArrayList<String>();
                                ArrayList<String> childids = new ArrayList<String>();
                                ArrayList<String> childLevels = new ArrayList<String>();
                                ArrayList<String> childIcons = new ArrayList<String>();

                                ArrayList<Integer> childColors = new ArrayList<Integer>();

                                for (int k = 0; k < arry3.length(); k++) {
                                    String childName = "";
                                    String childid = "";
                                    String childlevel = "";
                                    String childicon = "";
                                    if (arry3.getJSONObject(k).has("assetThreeName")) {
                                        childName = arry3.getJSONObject(k).getString("assetThreeName");
                                    }
                                    if (arry3.getJSONObject(k).has("assetThreeLevel")) {
                                        childlevel = arry3.getJSONObject(k).getString("assetThreeLevel");
                                    }
                                    if (arry3.getJSONObject(k).has("idThree")) {
                                        childid = arry3.getJSONObject(k).getString("idThree");
                                    }
                                    if (arry3.getJSONObject(k).has("assetThreeIcon")) {
                                        childicon = arry3.getJSONObject(k).getString("assetThreeIcon");
                                    }


                                    childNames.add(childName);
                                    childids.add(childid);
                                    childLevels.add(childlevel);
                                    childIcons.add(childicon);
                                    childColors.add(Color.parseColor("#ff00ff"));


                                }
                                child.setChildNames(childNames);
                                child.setChildIds(childids);
                                child.setChildLevels(childLevels);
                                child.setChildIcons(childIcons);
                                childs.add(child);
                            }

                            parent.setChilds(childs);

                            parents.add(parent);
                            OnWorkOrderListener.onWorkOrderSuccess();

                        }
                    } else {
                        OnWorkOrderListener.onError();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                OnWorkOrderListener.onFailure();
            }
        });
    }
}
