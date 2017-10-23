package com.mvpankao.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mvpankao.R;
import com.mvpankao.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by wangjian
 * On  2017/1/20
 */

public class EditOrderContentActivity extends BaseActivity {
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.righttext)
    TextView mRighttext;
    @BindView(R.id.rl_edit)
    RelativeLayout mRlEdit;
    @BindView(R.id.value)
    EditText mValue;
    public static int resultCode = 0x006666;



    @Override
    protected void initView() {
        mTitle.setText("工单内容");
        mValue.setText(getIntent().getStringExtra("value"));
        mRighttext.setText("完成");
    }

    @Override
    protected int getLayout() {
        return R.layout.ac_editordercontent;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rl_back, R.id.rl_edit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;

            case R.id.rl_edit:

                Intent intent = getIntent();
                String data = intent.getDataString();

                intent.putExtra("value", mValue.getText().toString());
                setResult(resultCode, intent);

                finish();
                break;
        }
    }
}
