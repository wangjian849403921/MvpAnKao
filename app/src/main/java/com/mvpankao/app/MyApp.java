package com.mvpankao.app;

import android.app.Application;

import com.apkfuns.logutils.LogUtils;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/7 14:03
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //打开log日志工具
        LogUtils.configAllowLog = true;
//        FunctionOptions options = new FunctionOptions.Builder()
//                .setType(FunctionConfig.TYPE_IMAGE)
//                .setCompress(true)
//                .setGrade(Luban.THIRD_GEAR)
//                .create();
//        PictureConfig.getInstance().init(options);
    }
}
