package com.mvpankao.model;

import android.content.Context;

import com.mvpankao.model.bean.AssetBean;
import com.mvpankao.model.bean.ParamGroupItem;
import com.tsy.sdk.myokhttp.MyOkHttp;

import java.util.List;

/**
 * Description: 资产接口
 * Author:wangjian
 * Date: 2017-03-07
 * Time: 11:39
 */
public interface AssetModel {

    /**
     * @param OnAssetListener
     * @param context
     */
    void initData(OnAssetListener OnAssetListener, Context context, MyOkHttp myOkHttp, List<AssetBean.ObjectBean> list,String userId);

    /**
     * 资产详情参数
     * @param OnAssetListener
     * @param context
     * @param myOkHttp
     * @param list
     * @param id
     * @param level
     */
    void initAssetParam(OnAssetListener OnAssetListener, Context context, MyOkHttp myOkHttp, List<ParamGroupItem>  list, String id, String level);


}
