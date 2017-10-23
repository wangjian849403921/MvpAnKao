package com.mvpankao.model;

import android.content.Context;

import com.mvpankao.model.bean.RepairBean;
import com.mvpankao.model.bean.RepairDetailBean;
import com.tsy.sdk.myokhttp.MyOkHttp;

import java.util.List;

/**
 * Description: 报修接口
 * Author:wangjian
 * Date: 2017-03-07
 * Time: 11:39
 */
public interface RepairModel {

    /**报修列表
     * @param page
     * @param onRepairListener
     * @param context
     * @param myOkHttp
     * @param list
     * @param userId
     * @param role
     */
    void initData(int page, OnRepairListener onRepairListener, Context context, MyOkHttp myOkHttp, List<RepairBean.ObjectBean.DataBean.ListBean> list, String userId, String role);

    /**
     * 报修详情接口
     * @param OnRepairListener
     * @param mContext
     * @param MyOkHttp
     * @param list
     * @param repairId
     */
    void initDetailData(OnRepairListener OnRepairListener, Context mContext, MyOkHttp MyOkHttp, List<RepairDetailBean.ObjectBean.RepailogListBean> list, String repairId);

    /**
     * 报修提交
     * @param OnRepairListener
     * @param Context
     * @param MyOkHttp
     * @param userId
     * @param repairid
     * @param phone
     * @param username
     * @param area
     * @param detailaddress
     * @param company
     * @param linename
     * @param statue
     * @param remark
     */
    void commit(OnRepairListener OnRepairListener,Context Context,MyOkHttp MyOkHttp,String userId,String repairid,String type,String phone,String username,String area,String detailaddress,String company,String linename,String statue,String remark);
}
