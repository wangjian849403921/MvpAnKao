package com.mvpankao.model;

import android.content.Context;

import com.mvpankao.model.bean.CreateOrderAssert;
import com.mvpankao.model.bean.ParentEntity;
import com.mvpankao.model.bean.WorkOrderGroupItem;
import com.mvpankao.model.bean.WorkOrderLogBean;
import com.tsy.sdk.myokhttp.MyOkHttp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description: 工单接口
 * Author:wangjian
 * Date: 2017-03-07
 * Time: 11:39
 */
public interface WorkOrderModel {
    /**
     * 工单列表
     *
     * @param OnWorkOrderListener
     * @param context
     * @param myOkHttp
     * @param list
     * @param userId
     */

    void initData(OnWorkOrderListener OnWorkOrderListener, Context context, MyOkHttp myOkHttp, List<WorkOrderGroupItem> list, String userId);

    /**
     * 筛选结果
     * @param OnWorkOrderListener
     * @param context
     * @param myOkHttp
     * @param list
     * @param map
     */
    void initSearchData(OnWorkOrderListener OnWorkOrderListener, Context context, MyOkHttp myOkHttp, List<WorkOrderGroupItem> list, Map<String,String> map);

    /**
     * 工单筛选类型
     *
     * @param OnWorkOrderListener
     * @param context
     * @param MyOkHttp
     */
    void initWorkOrderTypeList(OnWorkOrderListener OnWorkOrderListener, Context context, MyOkHttp MyOkHttp);
    /**
     * 警报筛选类型
     *
     * @param OnWorkOrderListener
     * @param context
     * @param MyOkHttp
     */
    void initWarningTypeList(OnWorkOrderListener OnWorkOrderListener, Context context, MyOkHttp MyOkHttp);
    /**
     * 工单详情
     *
     * @param OnWorkOrderListener
     * @param Context
     * @param MyOkHttp
     * @param id
     */
    void initDetailData(OnWorkOrderListener OnWorkOrderListener, Context Context, MyOkHttp MyOkHttp, String id);

    /**
     * 创建执行完成工单
     *
     * @param OnWorkOrderListener
     * @param Context
     * @param mMyOkHttp
     * @param map
     */
    void createWorkOrder(OnWorkOrderListener OnWorkOrderListener, Context Context, MyOkHttp mMyOkHttp, String url, Map<String, String> map);

    /**
     * 创建工单时选择客户
     *
     * @param onWorkOrderListener
     * @param Context
     * @param MyOkHttp
     * @param list
     * @param userId
     */
    void initWorkOrderAssert(OnWorkOrderListener onWorkOrderListener, Context Context, MyOkHttp MyOkHttp, List<CreateOrderAssert.ObjectBean> list, String userId);

    /**
     * 工单日志列表
     *
     * @param OnWorkOrderListener
     * @param Context
     * @param MyOkHttp
     * @param list
     * @param id
     */
    void initWorkOrderLog(OnWorkOrderListener OnWorkOrderListener, Context Context, MyOkHttp MyOkHttp, List<WorkOrderLogBean.ObjectBean> list, String id);

    /**
     * 工单资产范围
     * @param OnWorkOrderListener
     * @param Context
     * @param MyOkHttp
     * @param parents
     * @param id
     */
    void initAssertArea(OnWorkOrderListener OnWorkOrderListener, Context Context, MyOkHttp MyOkHttp, ArrayList<ParentEntity> parents, String id);
}
