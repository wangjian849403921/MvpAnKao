package com.mvpankao.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mvpankao.R;
import com.mvpankao.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by codeest on 16/8/20.
 */

public class PictureDetailActivity extends BaseActivity {


    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.contact_Customer_service)
    RelativeLayout mContactCustomerService;
    @BindView(R.id.iv_girl_detail)
    ImageView mImage;
    String url;
    Bitmap bitmap;
    PhotoViewAttacher mAttacher;

    @Override
    protected void initView() {
        mTitle.setText("图片预览");
        mContactCustomerService.setVisibility(View.GONE);
        Intent intent = getIntent();
        url = intent.getExtras().getString("url");


//        if (url != null) {
//            Glide.with(mContext).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
//                @Override
//                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                    bitmap = resource;
//                    mImage.setImageBitmap(resource);
//                    mAttacher = new PhotoViewAttacher(mImage);
//                }
//            });
//        }
    }

    @Override
    protected int getLayout() {
        return R.layout.ac_picture_detail;
    }

    @Override
    protected void initEventAndData() {

    }


    @OnClick(R.id.rl_back)
    public void onClick() {
        finish();
    }
}
