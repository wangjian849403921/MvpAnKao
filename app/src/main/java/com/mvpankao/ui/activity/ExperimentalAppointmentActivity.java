package com.mvpankao.ui.activity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mvpankao.R;
import com.mvpankao.base.BaseActivity;
import com.mvpankao.utils.CallPhoneUtils;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 实验预约
 * Created by wangjian
 * On  2017/1/13
 */
@SuppressLint({"JavascriptInterface", "SetJavaScriptEnabled"})
public class ExperimentalAppointmentActivity extends BaseActivity {
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.contact_Customer_service)
    RelativeLayout mContactCustomerService;
    @BindView(R.id.Pbar)
    ProgressBar bar;
    @BindView(R.id.webView)
    WebView mWebView;
    String result = "";

    @Override
    protected int getLayout() {
        return R.layout.ac_experiment;
    }

    @Override
    protected void initView() {
        mTitle.setText("实验预约");

        // 必须要设置支持JS
        mWebView.getSettings().setJavaScriptEnabled(true);

        // 设置编码，防止中文问题
        mWebView.getSettings().setDefaultTextEncodingName("utf-8");

//        mWebView.addJavascriptInterface(new JSHook(),"");

        // 加载本地assets下面的index.html文件
        mWebView.loadUrl("file:///android_asset/calendar.html?userId="+mSPUtils.getString("userid"));
        // 设置这个之后，js就可以这样 window.jsObj.setResult(""); 调用如下的 setResult方法
//        mWebView.addJavascriptInterface();
//        mWebView.addJavascriptInterface(new Object() {
//
//            @SuppressWarnings("unused")
//            public void setResult(String param) {
//                result = param;
//
//                Toast.makeText(mContext, "结果是：" + result,
//                        Toast.LENGTH_LONG).show();
//            }
////            @SuppressWarnings("unused")
////            public String getResult() {
//////                result = param;
////
//////                Toast.makeText(mContext, "结果是：" + result,
//////                        Toast.LENGTH_LONG).show();
////                return "OK";
////            }
//        }, "jsObj");

//        mWebView.loadUrl("http://blog.csdn.net/beyond0525/article/details/9374301");
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                String p = "xxx我是传入js的内容xxx";
//                mWebView.loadUrl("javascript:getUserId('+" + p + "')");
//            }
//        });
        // 这个是为了，判断页面是否加载完成。加载完成才能调用js方法
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                // 加载完成，调用js方法。runOnUiThread这样做是为了运行在主线程
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        String p = "xxx我是传入js的内容xxx";
//                        mWebView.loadUrl("javascript:getUserId('+" + p + "')");
//                    }
//                });
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

            }
        });

        mWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    bar.setVisibility(View.INVISIBLE);
                } else {
                    if (View.INVISIBLE == bar.getVisibility()) {
                        bar.setVisibility(View.VISIBLE);
                    }
                    bar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }

        });
        mWebView.setWebViewClient(new webViewClient());
    }
    public class JSHook{
        @JavascriptInterface
        public void javaMethod(String p){
            Log.d("", "JSHook.JavaMethod() called! + "+p);
        }
        @JavascriptInterface
        public void showAndroid(){
            String info = "来自手机内的内容！！！";
            mWebView.loadUrl("javascript:show('"+info+"')");
        }

        public String getInfo(){
            return "获取手机内的信息！！";
        }
    }
    // 设置回退
    // 覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack(); // goBack()表示返回WebView的上一页面
            return true;
        }
        finish();
        return false;
    }

    // Web视图
    private class webViewClient extends WebViewClient {
        // 网页加载时的连接的网址
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        // 网页开始加载
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {// 网页页面开始加载的时候
            mWebView.setEnabled(false);
            // if (dialog == null) {
            // dialog = ProgressDialog.show(Personal_MyOrderActivity.this, "",
            // "加载中…", true, true);
            // web_myorder.setEnabled(false);// 当加载网页的时候将网页进行隐藏
            // }
            super.onPageStarted(view, url, favicon);
        }

        // 网页加载结束
        @Override
        public void onPageFinished(WebView view, String url) {
            mWebView.setEnabled(true);
            // if (dialog != null && dialog.isShowing()) {
            // dialog.dismiss();
            // dialog = null;
            // web_myorder.setEnabled(true);
            // }
        }
    }


    @Override
    protected void initEventAndData() {

    }

    @OnClick({R.id.rl_back, R.id.contact_Customer_service})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.contact_Customer_service:
                CallPhoneUtils.callphone(mContext);

                break;
        }
    }
}
