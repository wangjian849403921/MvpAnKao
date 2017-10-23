package com.mvpankao.model;

import android.content.Context;

import com.mvpankao.model.bean.MyOrder;
import com.tsy.sdk.myokhttp.MyOkHttp;

import java.util.List;

/**
 * Description: 我的订单接口
 * Author:wangjian
 * Date: 2017-03-07
 * Time: 11:39
 */
public interface OrderModel {

    /**订单列表
     * @param page
     * @param OnOrderListener
     * @param context
     */
    void initOrder(int page, OnOrderListener OnOrderListener, Context context, MyOkHttp myOkHttp, List<MyOrder.ObjectBean.ListBean> list, String userid, String statue);

    /**
     * 删除订单
     * @param OnOrderListener
     * @param context
     * @param myOkHttp
     * @param orderid
     * @param position
     */
    void deleteOrder(OnOrderListener OnOrderListener, Context context, MyOkHttp myOkHttp,String orderid,int position);
}
