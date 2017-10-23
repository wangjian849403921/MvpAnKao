package com.mvpankao.model;

import android.content.Context;

import com.tsy.sdk.myokhttp.MyOkHttp;

import java.io.File;
import java.util.Map;

/**
 * Description: 上传图片文件接口
 * Author:wangjian
 * Date: 2017-03-07
 * Time: 11:39
 */
public interface UploadModel {

    /**
     *
     * @param onUpLoadListener
     * @param context
     * @param myOkHttp
     * @param map
     * @param filemap
     */
    void upLoad(OnUpLoadListener onUpLoadListener, Context context, MyOkHttp myOkHttp, String type,Map<String, String> map, Map<String, File> filemap);

}
