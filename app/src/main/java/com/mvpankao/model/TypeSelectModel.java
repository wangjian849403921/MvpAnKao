package com.mvpankao.model;

import android.content.Context;

import com.mvpankao.model.bean.TypeBean;
import com.tsy.sdk.myokhttp.MyOkHttp;

import java.util.List;

/**
 * Description: 类型选择接口
 * Author:wangjian
 * Date: 2017-03-07
 * Time: 11:39
 */
public interface TypeSelectModel {

    /**
     *
     * @param OnTyeSelectListener
     * @param context
     * @param myOkHttp
     * @param list
     */
    void initTypeList(OnTyeSelectListener OnTyeSelectListener, Context context, MyOkHttp myOkHttp, List<TypeBean.ObjectBean.RepairTypeListBean> list);
}
