package com.mvpankao.model;

import android.content.Context;

import com.mvpankao.model.bean.WorkFlowBean;
import com.tsy.sdk.myokhttp.MyOkHttp;

import java.util.List;

/**
 * Description: 工作流接口
 * Author:wangjian
 * Date: 2017-03-07
 * Time: 11:39
 */
public interface WorkFlowModel {

    /**
     * @param page
     * @param OnWorkFlowListener
     * @param mContext
     * @param mMyOkHttp
     * @param list
     * @param userId
     * @param role
     * @param statue
     */
    void initData(int page, OnWorkFlowListener OnWorkFlowListener, Context mContext, MyOkHttp mMyOkHttp, List<WorkFlowBean.ObjectBean.ListBean> list, String userId, String role, String statue);
}
