package com.mvpankao.model;

import android.content.Context;

import com.mvpankao.model.bean.HomePageProduct;
import com.tsy.sdk.myokhttp.MyOkHttp;

import java.util.List;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/8 11:18
 */

public interface HomeProductModel {

    void initData(OnHomeProductListener onHomeProductListener, Context context, MyOkHttp myOkHttp, List<HomePageProduct.ObjectBean> list, String type);
}
