package com.mvpankao.model.impl;

import android.content.Context;

import com.mvpankao.http.NetUrl;
import com.mvpankao.model.OnRepairListener;
import com.mvpankao.model.RepairModel;
import com.mvpankao.model.bean.RepairBean;
import com.mvpankao.model.bean.RepairDetailBean;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;
import com.tsy.sdk.myokhttp.response.JsonResponseHandler;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description：我的报修Model实现，这里主要是网络请求的操作。
 *
 * @auth wangjian
 * @time 2017/3/10 17:25
 */
public class RepairModelImpl implements RepairModel {
    @Override
    public void initData(int page, final OnRepairListener onRepairListener, Context context, MyOkHttp myOkHttp, final List<RepairBean.ObjectBean.DataBean.ListBean> list, String userId, String role) {
        String url = NetUrl.URLHeader + NetUrl.Repair.RepairList;
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        map.put("type", role);
        map.put("rows", "10");
        map.put("page", page + "");
        myOkHttp.post().url(url).params(map).tag(context).enqueue(new GsonResponseHandler<RepairBean>() {
            @Override
            public void onFailure(int statusCode, String error_msg) {
                onRepairListener.onFailure();
            }

            @Override
            public void onSuccess(int statusCode, RepairBean response) {

                if (response.getCode() == 200) {

                    onRepairListener.getItemSize(response.getObject().getData().getTotal());
                    for (int i = 0; i < response.getObject().getData().getList().size(); i++) {
                        list.add(response.getObject().getData().getList().get(i));
                    }
                    onRepairListener.onSuccess();
                } else {
                    onRepairListener.onError();
                }
            }
        });
    }

    /**
     * 报修详情
     *
     * @param OnRepairListener
     * @param mContext
     * @param MyOkHttp
     * @param list
     * @param repairId
     */
    @Override
    public void initDetailData(final OnRepairListener OnRepairListener, Context mContext, MyOkHttp MyOkHttp, final List<RepairDetailBean.ObjectBean.RepailogListBean> list, String repairId) {
        String url = NetUrl.URLHeader + NetUrl.Repair.RepairDetail;
        Map<String, String> map = new HashMap<String, String>();
        map.put("repairId", repairId);

        MyOkHttp.post().url(url).params(map).tag(mContext).enqueue(new GsonResponseHandler<RepairDetailBean>() {
            @Override
            public void onFailure(int statusCode, String error_msg) {
                OnRepairListener.onFailure();
            }

            @Override
            public void onSuccess(int statusCode, RepairDetailBean response) {
//                LogUtils.d(response.getObject().getRepailogList().get(0));
                if (response.getCode() == 200) {

                    for (int i = 0; i < response.getObject().getRepailogList().size(); i++) {
                        list.add(response.getObject().getRepailogList().get(i));
                    }
                    OnRepairListener.onSuccess(response);

                } else {
                    OnRepairListener.onError();
                }
            }
        });
    }

    @Override
    public void commit(final OnRepairListener OnRepairListener, Context Context, MyOkHttp MyOkHttp, String userId, String repairid, String type, String phone, String username, String area, String detailaddress, String company, String linename, String statue, String remark) {
        String url = NetUrl.URLHeader + NetUrl.Repair.Repair;
        Map<String, String> map = new HashMap<>();
        map.put("repairTypeIdfk", repairid);
        map.put("repairCircuit", linename);
        map.put("repairNote", remark);
        map.put("repairGpsAddress", area);
        map.put("repairAddress", detailaddress);
        map.put("repairCompany", company);
        map.put("repairUser", userId);
        map.put("repairRequestUserId", userId);
        map.put("repairPhone", phone);
        map.put("repairStatus", statue);
        MyOkHttp.post().url(url).params(map).tag(Context).enqueue(new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) {
                try {
                    String code = response.getString("code");
                    if (code.equals("1")) {
                        OnRepairListener.onSuccess();
                    } else {
                        OnRepairListener.onError();
                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                OnRepairListener.onFailure();
            }
        });

    }
}
