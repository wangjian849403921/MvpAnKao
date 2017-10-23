package com.mvpankao.ui.activity;

import android.graphics.Bitmap;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
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
 * 论坛
 * Created by wangjian
 * On  2017/1/16
 */

public class KnowledgeForuActivity extends BaseActivity {
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.Pbar)
    ProgressBar bar;
    @BindView(R.id.webView)
    WebView mWebView;
    @BindView(R.id.contact_Customer_service)
    RelativeLayout mContactCustomerService;

    @Override
    protected int getLayout() {
        return R.layout.ac_knowloedge;
    }

    @Override
    protected void initView() {
        mTitle.setText("瓦特论坛");
        WebSettings webSettings = mWebView.getSettings();


        // web_myorder.addJavascriptInterface(new Contact(), "Android");

        // 设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);

        // 设置可以访问文件
        webSettings.setAllowFileAccess(true);

        // 设置支持缩放
        webSettings.setBuiltInZoomControls(false);

        // 设置可以支持缩放
        webSettings.setSupportZoom(false);

        // 扩大比例的缩放
        webSettings.setUseWideViewPort(false);

        // 自适应屏幕
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        webSettings.setLoadWithOverviewMode(true);


        String html = "http://139.196.180.249:8080/forum/web/login.xhtml";

        mWebView.loadUrl(html);
//        mWebView.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);

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

    @OnClick({R.id.contact_Customer_service,R.id.rl_back})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.contact_Customer_service:
                CallPhoneUtils.callphone(mContext);

                break;
            case R.id.rl_back:
                finish();
                break;
        }

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
}
