package com.mvpankao.presenter;

import android.content.Context;

import com.blankj.utilcode.utils.NetworkUtils;
import com.blankj.utilcode.utils.RegexUtils;
import com.blankj.utilcode.utils.StringUtils;
import com.mvpankao.model.FeedBackModel;
import com.mvpankao.model.OnFeedBackListener;
import com.mvpankao.model.impl.FeedBackImpl;
import com.mvpankao.view.FeedBackView;
import com.tsy.sdk.myokhttp.MyOkHttp;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/7 16:20
 */

public class FeedBackPresenter implements OnFeedBackListener {
    private FeedBackModel Model;
    private FeedBackView View;

    public FeedBackPresenter(FeedBackView View) {
        this.View = View;
        Model = new FeedBackImpl();
    }


    public void feedback(Context context, MyOkHttp myOkHttp) {
        String title = View.getfeedbackTitle();
        String username=View.getUserName();
        String phone=View.getPhone();
        String content=View.getContent();
        if (NetworkUtils.isConnected(context)) {
            if (!StringUtils.isEmpty(content)) {
                if (RegexUtils.isMobileExact(phone)) {
                    View.showDialog();
                    Model.feedBack(this, context, myOkHttp, title, content, username, phone);
                } else {
                    View.showToast("请输入正确手机号");
                }
            }else{
                View.showToast("反馈内容不能为空");
            }
        } else {
            View.showToast("网络异常");
        }


    }

    @Override
    public void onError() {
        View.showToast("反馈失败");
        View.onError();
    }


    @Override
    public void onSuccess() {
        View.onSuccess();

    }


    @Override
    public void onFailure() {
        View.onFailure();
    }


}
