package com.mvpankao.model;

import android.content.Context;

import com.mvpankao.model.bean.WarningBean;
import com.mvpankao.model.bean.WarningDetailBean;
import com.tsy.sdk.myokhttp.MyOkHttp;

import java.util.List;
import java.util.Map;

/**
 * Description: 警报接口
 * Author:wangjian
 * Date: 2017-03-07
 * Time: 11:39
 */
public interface WarningModel {

    /**
     * 警报列表
     * @param page
     * @param OnWarningListener
     * @param context
     * @param myOkHttp
     * @param list
     */
    void initData(int page, OnWarningListener OnWarningListener, Context context, MyOkHttp myOkHttp, List<WarningBean.ObjectBean.ListBean> list);

    /**
     * 警报筛选结果
     * @param page
     * @param OnWarningListener
     * @param context
     * @param myOkHttp
     * @param list
     * @param map
     */
    void initSearchData(int page, OnWarningListener OnWarningListener, Context context, MyOkHttp myOkHttp, List<WarningBean.ObjectBean.ListBean> list,Map<String,String> map);

    /**
     * 警报详情
     * @param OnWarningListener
     * @param Context
     * @param MyOkHttp
     * @param id
     */
    void initDetailData(OnWarningListener OnWarningListener, Context Context, MyOkHttp MyOkHttp, List<WarningDetailBean.ObjectBean.TimePointBean> list, String id);

}
