package com.mvpankao.widget.refresh.footerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mvpankao.R;


/**
 * @author deadline
 * @time 2016/10/22
 */
public class SimpleFooterView extends BaseFooterView {

    private TextView mText;
    private LinearLayout mRl_Footer;
    private ProgressBar progressBar;

    public SimpleFooterView(Context context) {
        this(context, null);
    }

    public SimpleFooterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setLayoutParams(new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_footer_view, this);
        mRl_Footer = (LinearLayout) view.findViewById(R.id.rl_footer_view_progressbar);
        progressBar = (ProgressBar) view.findViewById(R.id.footer_view_progressbar);
        mText = (TextView) view.findViewById(R.id.footer_view_tv);
//        ((AnimationDrawable) progressBar.getDrawable()).start();
    }


    @Override
    public void onLoadingMore() {
//        ((AnimationDrawable) progressBar.getDrawable()).start();
        mRl_Footer.setVisibility(VISIBLE);
        mText.setVisibility(GONE);
    }

    public void showText() {
        mRl_Footer.setVisibility(GONE);
        mText.setVisibility(VISIBLE);
    }

    /**************
     * 文字自行修改或根据传入的参数动态修改
     ****************/

    @Override
    public void onNoMore(CharSequence message) {
        showText();
        mText.setText(message);
    }

    @Override
    public void onError(CharSequence message) {
        showText();
        mText.setText(message);
    }

    @Override
    public void onNetChange(boolean isAvailable) {
        showText();
        mText.setText("网络连接不通畅!");
    }
}
