package com.mvpankao.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.mvpankao.R;
import com.mvpankao.base.BaseActivity;
import com.mvpankao.http.NetUrl;
import com.mvpankao.model.bean.ParamGroupItem;
import com.mvpankao.presenter.AssetPresenter;
import com.mvpankao.presenter.UpLoadPresenter;
import com.mvpankao.ui.adapter.ParamAdapter;
import com.mvpankao.utils.Constants;
import com.mvpankao.utils.MyEvent;
import com.mvpankao.utils.PictureSelectorUtils;
import com.mvpankao.view.AssetParamView;
import com.mvpankao.view.UpLoadView;
import com.mvpankao.widget.CircleProgressView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 第一级参数
 * Created by wangjian
 * On  2017/1/20
 */

public class ParentActivity extends BaseActivity implements UpLoadView, AssetParamView {
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.contact_Customer_service)
    RelativeLayout mContactCustomerService;
    @BindView(R.id.circle_progress)
    CircleProgressView mCircleProgressView;
    @BindView(R.id.topimage)
    ImageView mTopimage;
    @BindView(R.id.explistview)
    ExpandableListView mExpandableListView;
    //    @BindView(R.id.scrollView)
//    MyScrollView mScrollView;
    @BindView(R.id.Layout)
    LinearLayout mLinearLayout;
    private static final int REQUEST_CODE = 0;
    private List<ParamGroupItem> dataList = new ArrayList<>();
    private int expandFlag = -1;//控制列表的展开
    ParamAdapter mParamAdapter;
    private int maxSelectNum = 1;// 图片最大可选数量
    private List<LocalMedia> selectMedia = new ArrayList<>();
    private UpLoadPresenter mUpLoadPresenter = new UpLoadPresenter(this);
    private AssetPresenter mAssetPresenter = new AssetPresenter(this);
    String path = "";
    RequestOptions options = new RequestOptions();

    @Override

    protected void initView() {
        options.error(R.drawable.icon_error);
        showProgress();
        mTitle.setText(getIntent().getStringExtra("name"));
        LogUtils.d(NetUrl.DOCHeader + getIntent().getStringExtra("image"));

        Glide.with(mContext).load(NetUrl.DOCHeader + getIntent().getStringExtra("image")).apply(options).into(mTopimage);
        mContactCustomerService.setVisibility(View.GONE);
    }


    @Override
    protected int getLayout() {
        return R.layout.parentactivity;
    }

    @Override
    protected void initEventAndData() {

        initData();
    }

    private void initData() {
        if(mContext.isDestroyed()){
            return;
        }
        mAssetPresenter.initParamData(mContext, mMyOkhttp, dataList);

    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void MyEvent(MyEvent event) {
        switch (event.getMsg()) {
            case Constants.Param_CODE:
                mParamAdapter = new ParamAdapter(mContext, dataList);
                mExpandableListView.setAdapter(mParamAdapter);
                int groupCount = mExpandableListView.getCount();

                for (int i = 0; i < groupCount; i++) {

                    mExpandableListView.expandGroup(i);

                }
                mExpandableListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                });
                mExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

                    @Override
                    public boolean onGroupClick(ExpandableListView parent, View v,
                                                int groupPosition, long id) {
                        // TODO Auto-generated method stub
                        return true;
                    }
                });
                break;
        }
    }

    @OnClick({R.id.rl_back, R.id.topimage})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.topimage:
//                PictureSelectorUtils.selectPicture(mContext, selectMedia, maxSelectNum, resultCallback);
                PictureSelectorUtils.selectPicture(mContext, selectMedia, maxSelectNum);

                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectMedia = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    path = selectMedia.get(0).getPath();
                    uploadImage(path);

                    break;
            }
        }
    }
//    /**
//     * 图片回调方法
//     */
//    private PictureConfig.OnSelectResultCallback resultCallback = new PictureConfig.OnSelectResultCallback() {
//        @Override
//        public void onSelectSuccess(List<LocalMedia> resultList) {
//            selectMedia = resultList;
//            path = selectMedia.get(0).getPath();
//            uploadImage(path);
//
//        }
//
//        @Override
//        public void onSelectSuccess(LocalMedia media) {
//            // 单选回调
//            selectMedia.add(media);
//        }
//    };

    private void showProgress() {

        mCircleProgressView.setVisibility(View.VISIBLE);
        mCircleProgressView.spin();
        mLinearLayout.setVisibility(View.GONE);
    }

    public void hideProgress() {

        mCircleProgressView.setVisibility(View.GONE);
        if (mCircleProgressView.isSpinning()) {
            mCircleProgressView.stopSpinning();
        }
        mLinearLayout.setVisibility(View.VISIBLE);
    }


    private void uploadImage(final String path) {
        Map<String, String> map = new HashMap<String, String>();

        map.put("id", getIntent().getStringExtra("id"));
        map.put("level", getIntent().getStringExtra("level"));

        Map<String, File> mapfile = new HashMap<>();
        mapfile.put("icon", new File(path));
        LogUtils.i("Commit: " + map);
        LogUtils.i("file:" + mapfile);

        mUpLoadPresenter.upLoad(mContext, mMyOkhttp, "3", map, mapfile);


    }

    @Override
    public void showDialog() {

    }

    @Override
    public void dismissDialog() {

    }

    @Override
    public String getAssetId() {
        return getIntent().getStringExtra("id");
    }

    @Override
    public String getAssetLevel() {
        return getIntent().getStringExtra("level");
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onParamSuccess() {
        hideProgress();
        EventBus.getDefault().post(new MyEvent(Constants.Param_CODE));
    }

    @Override
    public void onParamFailure() {
        hideProgress();
//        mLinearLayout.setVisibility(View.GONE);
    }

    @Override
    public void onParamError() {
        hideProgress();
//        mLinearLayout.setVisibility(View.GONE);
    }

    @Override
    public void onSuccess() {
        Glide.with(mContext).load(path).into(mTopimage);
        EventBus.getDefault().post(new MyEvent(Constants.ASSERTLISTUPDATE_CODE));
    }

    @Override
    public void onFailure() {
        Glide.with(mContext).load(NetUrl.DOCHeader + getIntent().getStringExtra("image")).apply(options).into(mTopimage);

    }

    @Override
    public void onError() {
        Glide.with(mContext).load(NetUrl.DOCHeader + getIntent().getStringExtra("image")).apply(options).into(mTopimage);

    }
}
