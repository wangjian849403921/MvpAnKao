package com.mvpankao.model;

import android.content.Context;

import com.tsy.sdk.myokhttp.MyOkHttp;

/**
 * Description: 提交订单接口
 * Author:wangjian
 * Date: 2017-03-07
 * Time: 11:39
 */
public interface CommitOrderModel {


    /**
     * 购物车订单的提交
     * @param onCommitListener
     * @param context
     * @param myOkHttp
     * @param userId
     * @param stockid
     * @param productId
     * @param number
     * @param shopcarid
     * @param receiver
     * @param phone
     * @param address
     * @param require
     */
    void commitShopCarOrder(OnCommitListener onCommitListener, Context context, MyOkHttp myOkHttp,String userId,String stockid,String productId,String number,String shopcarid,String receiver,String phone,String address,String require);

    /**
     * 非购物车提交订单
     * @param onCommitListener
     * @param context
     * @param myOkHttp
     * @param userId
     * @param stockId
     * @param productId
     * @param number
     * @param receiver
     * @param phone
     * @param address
     * @param require
     */
    void commitOrder(OnCommitListener onCommitListener, Context context, MyOkHttp myOkHttp,String userId,String stockId,String productId,String number,String receiver,String phone,String address,String require);
}
