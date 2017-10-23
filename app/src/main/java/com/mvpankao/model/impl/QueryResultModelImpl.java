package com.mvpankao.model.impl;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.blankj.utilcode.utils.StringUtils;
import com.mvpankao.http.NetUrl;
import com.mvpankao.model.OnQueryListener;
import com.mvpankao.model.QueryResultModel;
import com.mvpankao.model.bean.ReportBean;
import com.mvpankao.model.bean.TechnologyBean;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/13 11:10
 */
public class QueryResultModelImpl implements QueryResultModel {

    @Override
    public void initReport(int page, final OnQueryListener onQueryListener, final Context context, MyOkHttp myOkHttp, final List<ReportBean.ObjectBean.ListBean> list, String userId, String role, final String content) {
        String url = NetUrl.URLHeader + NetUrl.Query.ReportQuery;
        if (!StringUtils.isEmpty(content)) {
            Map<String, String> map = new HashMap<>();
            map.put("userid", userId);
            map.put("type", role);
            map.put("name", content);
            map.put("page", page + "");
            myOkHttp.post().url(url).params(map).tag(context).enqueue(new GsonResponseHandler<ReportBean>() {
                @Override
                public void onFailure(int statusCode, String error_msg) {
                    onQueryListener.onFailure();
                }

                @Override
                public void onSuccess(int statusCode, ReportBean response) {
                    if (response.getCode() == 200) {
                        //查询到结果先将列表清空
                        list.clear();
                        onQueryListener.getItemSize(response.getObject().getTotal());
                        if (response.getObject().getList().size() > 0) {
                            for (int i = 0; i < response.getObject().getList().size(); i++) {
                                list.add(response.getObject().getList().get(i));
                            }
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    onQueryListener.onSuccess();
                                }
                            }, 500);
                        } else {
                            onQueryListener.onError();
                            Toast.makeText(context, "没查询到相关内容", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        onQueryListener.onError();

                    }
                }


            });
        } else {
            Map<String, String> map = new HashMap<>();
            map.put("userid", userId);
            map.put("type", role);
            map.put("page", page + "");
            myOkHttp.post().url(url).params(map).tag(context).enqueue(new GsonResponseHandler<ReportBean>() {
                @Override
                public void onFailure(int statusCode, String error_msg) {
                    onQueryListener.onFailure();
                }

                @Override
                public void onSuccess(int statusCode, ReportBean response) {
                    if (response.getCode() == 200) {

                        onQueryListener.getItemSize(response.getObject().getTotal());
                        if (response.getObject().getTotal()> 0) {
                            for (int i = 0; i < response.getObject().getList().size(); i++) {
                                list.add(response.getObject().getList().get(i));
                            }
                            onQueryListener.onSuccess();
                        }else{
                            onQueryListener.onError();

                        }
                    } else {
                        onQueryListener.onError();
                    }
                }


            });
        }
    }

    @Override
    public void initTechnology(int page, final OnQueryListener onQueryListener, final Context context, MyOkHttp myOkHttp, final List<TechnologyBean.ObjectBean.ListBean> list, String userId, String role, String content) {

        String url = NetUrl.URLHeader + NetUrl.Query.TechnologyQuery;
        if (!StringUtils.isEmpty(content)) {
            Map<String, String> map = new HashMap<>();
            map.put("doctitle", content);
            map.put("page", page + "");
            myOkHttp.post().url(url).params(map).tag(context).enqueue(new GsonResponseHandler<TechnologyBean>() {
                @Override
                public void onFailure(int statusCode, String error_msg) {
                    onQueryListener.onFailure();
                }

                @Override
                public void onSuccess(int statusCode, TechnologyBean response) {
                    if (response.getCode() == 200) {
                        //查询到结果先将列表清空
                        list.clear();
                        onQueryListener.getItemSize(response.getObject().getTotal());
                        if (response.getObject().getList().size() > 0) {
                            for (int i = 0; i < response.getObject().getList().size(); i++) {
                                list.add(response.getObject().getList().get(i));
                            }
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    onQueryListener.onSuccess();
                                }
                            }, 500);
                        } else {
                            onQueryListener.onError();
                            Toast.makeText(context, "没查询到相关内容", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        onQueryListener.onError();

                    }
                }


            });
        } else
        {
            Map<String, String> map = new HashMap<>();

            map.put("page", page + "");
            myOkHttp.post().url(url).params(map).tag(context).enqueue(new GsonResponseHandler<TechnologyBean>() {
                @Override
                public void onFailure(int statusCode, String error_msg) {
                    onQueryListener.onFailure();
                }

                @Override
                public void onSuccess(int statusCode, TechnologyBean response) {
                    if (response.getCode() == 200) {
                        onQueryListener.getItemSize(response.getObject().getTotal());
                        if (response.getObject().getList().size() > 0) {
                            for (int i = 0; i < response.getObject().getList().size(); i++) {
                                list.add(response.getObject().getList().get(i));
                            }
                            onQueryListener.onSuccess();
                        }else{
                            onQueryListener.onError();
                        }

                    } else {
                        onQueryListener.onError();
                    }
                }
            });
        }
    }
}
