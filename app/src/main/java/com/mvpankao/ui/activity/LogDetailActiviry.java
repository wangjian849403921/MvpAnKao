package com.mvpankao.ui.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

public class LogDetailActiviry extends BaseActivity {
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



    @Override
    protected void initView() {
        mTitle.setText("日志详情");
        mContactCustomerService.setVisibility(View.GONE);
        mContent.setText(getIntent().getStringExtra("content"));

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
        return R.layout.ac_logdetail;
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
