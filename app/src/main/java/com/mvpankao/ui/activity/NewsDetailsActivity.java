package com.mvpankao.ui.activity;

import android.content.Intent;
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
import com.smarttop.library.utils.LogUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wangjian
 * On  2016/11/28
 */

public class NewsDetailsActivity extends BaseActivity {
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
        return R.layout.ac_newsdetails;
    }

    @Override
    protected void initView() {
        mTitle.setText("安靠资讯");
        Intent intent = getIntent();

        WebSettings webSettings = mWebView.getSettings();


        // web_myorder.addJavascriptInterface(new Contact(), "Android");

        // 设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);

        // 设置可以访问文件
        webSettings.setAllowFileAccess(true);

        // 设置支持缩放
        webSettings.setBuiltInZoomControls(true);

        // 设置可以支持缩放
        webSettings.setSupportZoom(true);

        // 扩大比例的缩放
        webSettings.setUseWideViewPort(true);

        // 自适应屏幕
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        webSettings.setLoadWithOverviewMode(true);
        String title = getIntent().getStringExtra("title");
        String context = getIntent().getStringExtra("url");
        LogUtil.d("url:", context);
        String infotitle = "<p style='font-family:above;color:#333;font-size:36px;text-align:center;font-weight:bold;padding-left=20px'>" + title + "</p>";
        context = context.replaceAll("<p>", "<p style='font-family:above;color:#333;font-size:30px'>");
        context = context.replaceAll("<section", "<section style='color:#333;font-size:25px;padding:0px 10px 0px 10px;letter-spacing:2px'");

        context = context.replaceAll("<img", "<img style='display:block;width:100%;padding:20px 0px 0px 0px;'");
        context = context.replaceAll("<p", "<p style='color:#333;font-size:30px;padding:0px 20px 0px 20px;letter-spacing:2px'");

        String html = infotitle + context;

        mWebView.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);

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


    @OnClick(R.id.contact_Customer_service)
    public void onClick() {
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


    @OnClick({R.id.contact_Customer_service, R.id.rl_back})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.contact_Customer_service:
                CallPhoneUtils.callphone(mContext);
                break;
            case R.id.rl_back:
                finish();
                break;
        }
    }
}
