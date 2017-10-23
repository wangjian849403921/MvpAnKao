package com.mvpankao.mvp.base;

import android.content.Context;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public interface BaseView {
     Context getContext();
     void onFailure();
     void onRequestFailed();
     void onSuccess(Object responses);
}
