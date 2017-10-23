package com.mvpankao.model;

import android.content.Context;

import com.mvpankao.model.bean.StepBean;
import com.tsy.sdk.myokhttp.MyOkHttp;

import java.util.List;

/**
 * Description: 新闻资讯接口
 * Author:wangjian
 * Date: 2017-03-07
 * Time: 11:39
 */
public interface StepListModel {

    /**
     *
     * @param onStepListListener
     * @param context
     * @param myOkHttp
     * @param list
     * @param id
     */
    void initStepData(OnStepListListener onStepListListener, Context context, MyOkHttp myOkHttp, List<StepBean.ObjectBean> list,String id);
}
