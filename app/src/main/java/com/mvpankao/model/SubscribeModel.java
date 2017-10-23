package com.mvpankao.model;

import android.content.Context;

import com.mvpankao.model.bean.SubscribeGroupItem;
import com.tsy.sdk.myokhttp.MyOkHttp;

import java.util.List;

/**
 * Description: 我的预约接口
 * Author:wangjian
 * Date: 2017-03-07
 * Time: 11:39
 */
public interface SubscribeModel {

    /**
     *
     * @param OnSubscribeListener
     * @param context
     * @param myOkHttp
     * @param list
     * @param userId
     * @param date
     */
    void initData(OnSubscribeListener OnSubscribeListener, Context context, MyOkHttp myOkHttp, List<SubscribeGroupItem> list,String userId,String date);
}
