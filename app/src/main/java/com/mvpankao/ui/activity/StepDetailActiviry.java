package com.mvpankao.ui.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.StringUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.mvpankao.R;
import com.mvpankao.base.BaseActivity;
import com.mvpankao.ui.adapter.PictrueAdapter;
import com.mvpankao.widget.MyGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wangjian
 * On  2017/2/8
 */

public class StepDetailActiviry extends BaseActivity {
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.contact_Customer_service)
    RelativeLayout mContactCustomerService;
    @BindView(R.id.content)
    TextView mContent;
    @BindView(R.id.gridview)
    MyGridView mGridView;
    List<LocalMedia> list = new ArrayList<>();
    PictrueAdapter mAdapter;
    @BindView(R.id.reason)
    TextView mReason;


    @Override
    protected void initView() {
        mTitle.setText("详情");
        mContactCustomerService.setVisibility(View.GONE);
        mContent.setText("备注："+getIntent().getStringExtra("content"));
        if (!StringUtils.isEmpty(getIntent().getStringExtra("reason"))){
            mReason.setText("审核未通过："+getIntent().getStringExtra("reason"));
        }else{
            mReason.setText("");
        }
        if (getIntent().getStringExtra("have").equals("1")) {
            list = (ArrayList<LocalMedia>) getIntent().getSerializableExtra("imagelist");
        }
        mGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PictureSelector.create(mContext).externalPicturePreview(position, list);

//                PictureConfig.getInstance().externalPicturePreview(mContext, position, list);
            }
        });

    }

    @Override
    protected int getLayout() {
        return R.layout.ac_stepdetail;
    }

    @Override
    protected void initEventAndData() {
        getData();
    }

    private void getData() {


        mAdapter = new PictrueAdapter(mContext, list);
        mGridView.setAdapter(mAdapter);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.rl_back)
    public void onClick() {
        finish();
    }
}
