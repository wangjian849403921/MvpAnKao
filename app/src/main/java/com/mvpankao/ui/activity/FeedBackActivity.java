package com.mvpankao.ui.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mvpankao.R;
import com.mvpankao.base.BaseActivity;
import com.mvpankao.dialog.AlertDialog;
import com.mvpankao.presenter.FeedBackPresenter;
import com.mvpankao.utils.CallPhoneUtils;
import com.mvpankao.utils.DialogUtils;
import com.mvpankao.view.FeedBackView;
import com.mvpankao.widget.ClearEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 建议反馈
 * Created by wangjian
 * On  2016/11/23
 */

public class FeedBackActivity extends BaseActivity implements FeedBackView {
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.feedback_sub)
    ClearEditText mFeedbackSub;
    @BindView(R.id.feedbackcontent)
    EditText mFeedbackcontent;
    @BindView(R.id.UserName)
    ClearEditText mUserName;
    @BindView(R.id.PhoneNum)
    ClearEditText mPhoneNum;
    @BindView(R.id.commit)
    TextView mCommit;
    @BindView(R.id.contact_Customer_service)
    RelativeLayout mContactCustomerService;
    private FeedBackPresenter FeedBackPresenter = new FeedBackPresenter(this);

    @Override
    protected void initView() {
        mTitle.setText("建议与反馈");
    }

    @Override
    protected int getLayout() {
        return R.layout.ac_feedback;
    }

    @Override
    protected void initEventAndData() {
        mPhoneNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mPhoneNum.length() == 11) {
                    DialogUtils.setMessage(mContext, "手机号码最多11位");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @OnClick({R.id.rl_back, R.id.commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.contact_Customer_service:
                CallPhoneUtils.callphone(mContext);
                break;
            case R.id.rl_back:
                finish();
                break;
            case R.id.commit:
                if(mContext.isDestroyed()){
                    return;
                }
                FeedBackPresenter.feedback(mContext, mMyOkhttp);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void showDialog() {
        DialogUtils.Progress(mContext, "   提交中...");
    }

    @Override
    public String getUserName() {
        return mUserName.getText().toString();
    }

    @Override
    public String getPhone() {
        return mPhoneNum.getText().toString();
    }

    @Override
    public String getfeedbackTitle() {
        return mTitle.getText().toString();
    }

    @Override
    public String getContent() {
        return mFeedbackcontent.getText().toString();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess() {
        new AlertDialog(mContext).builder().setTitle("提示")
                .setMsg("恭喜，反馈成功！")
                .setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        finish();
                    }
                }).show();
    }

    @Override
    public void onFailure() {
        DialogUtils.dismiss();
    }

    @Override
    public void onError() {
        DialogUtils.dismiss();
    }
}
