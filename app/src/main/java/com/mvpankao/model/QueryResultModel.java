package com.mvpankao.model;

import android.content.Context;

import com.mvpankao.model.bean.ReportBean;
import com.mvpankao.model.bean.TechnologyBean;
import com.tsy.sdk.myokhttp.MyOkHttp;

import java.util.List;

/**
 * Description: 查询接口
 * Author:wangjian
 * Date: 2017-03-07
 * Time: 11:39
 */
public interface QueryResultModel {

    /**
     * 报告查询
     * @param page
     * @param onQueryListener
     * @param context
     * @param myOkHttp
     * @param list
     */
    void initReport(int page, OnQueryListener onQueryListener, Context context, MyOkHttp myOkHttp, List<ReportBean.ObjectBean.ListBean> list,String userId,String role,String content);

    /**
     * 工艺查询
     * @param page
     * @param onQueryListener
     * @param context
     * @param myOkHttp
     * @param list
     * @param content
     */
    void initTechnology(int page, OnQueryListener onQueryListener, Context context, MyOkHttp myOkHttp, List<TechnologyBean.ObjectBean.ListBean> list,String userId,String role, String content);

}
