package com.mvpankao.model.impl;

import android.content.Context;

import com.mvpankao.http.NetUrl;
import com.mvpankao.model.OnTyeSelectListener;
import com.mvpankao.model.bean.TypeBean;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/13 14:05
 */
public class TypeSelectModelImpl implements com.mvpankao.model.TypeSelectModel {
    @Override
    public void initTypeList(OnTyeSelectListener OnTyeSelectListener, Context context, MyOkHttp myOkHttp, final List<TypeBean.ObjectBean.RepairTypeListBean> list) {
        String url = NetUrl.URLHeader + NetUrl.Repair.RepairType;
        Map<String, String> map = new HashMap<>();
        map.put("", "");
        myOkHttp.post().url(url).params(map).tag(context).enqueue( new GsonResponseHandler<TypeBean>() {
            @Override
            public void onFailure(int statusCode, String error_msg) {

            }

            @Override
            public void onSuccess(int statusCode, TypeBean response) {
                if (response.getCode() == 1) {


                    for (int i = 0; i < response.getObject().getRepairTypeList().size(); i++) {
                        list.add(response.getObject().getRepairTypeList().get(i));
                    }

                } else {
                }
            }
        });
    }
}
