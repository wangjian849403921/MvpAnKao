package com.mvpankao.ui.activity;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.blankj.utilcode.utils.StringUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.mvpankao.R;
import com.mvpankao.base.BaseActivity;
import com.mvpankao.dialog.ActionSheetDialog;
import com.mvpankao.dialog.AlertDialog;
import com.mvpankao.presenter.UpLoadPresenter;
import com.mvpankao.utils.Constants;
import com.mvpankao.utils.MyEvent;
import com.mvpankao.utils.PictureSelectorUtils;
import com.mvpankao.view.UpLoadView;
import com.mvpankao.widget.photopicker.FullyGridLayoutManager;
import com.mvpankao.widget.photopicker.GridImageAdapter;

import net.lemonsoft.lemonbubble.LemonBubble;
import net.lemonsoft.lemonbubble.enums.LemonBubbleLayoutStyle;
import net.lemonsoft.lemonbubble.enums.LemonBubbleLocationStyle;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 上传任务
 * Created by wangjian
 * On  2016/12/29
 */

public class UploadTaskActivity extends BaseActivity implements UpLoadView {
    @BindView(R.id.back)
    TextView mBack;
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.UpLoad)
    RelativeLayout mUpLoad;
    @BindView(R.id.Content)
    EditText mContent;
    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    GridImageAdapter adapter;
    // 创建一个以当前系统时间为名称的文件，防止重复
    private File tempFile;
    private int maxSelectNum = 5;// 图片最大可选数量

    private List<LocalMedia> selectMedia = new ArrayList<>();

    // 使用系统当前日期加以调整作为照片的名称
    private String getPhotoFileName(int i) {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("'PNG'_yyyyMMdd_HHmmss");
        return sdf.format(date) + i + ".png";
    }

    private UpLoadPresenter mUpLoadPresenter = new UpLoadPresenter(this);

    @Override
    protected int getLayout() {
        return R.layout.ac_uploadtask;
    }

    @Override
    protected void initView() {
        FullyGridLayoutManager manager = new FullyGridLayoutManager(mContext, maxSelectNum, GridLayoutManager.VERTICAL, false);
        mRecycler.setLayoutManager(manager);
        adapter = new GridImageAdapter(mContext, onAddPicClickListener);
        adapter.setSelectMax(maxSelectNum);
        mRecycler.setAdapter(adapter);
        adapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                // 这里可预览图片
                PictureSelector.create(mContext).externalPicturePreview(position, selectMedia);
//                PictureConfig.getInstance().externalPicturePreview(mContext, position, selectMedia);
            }
        });
    }

    /**
     * 删除图片回调接口
     */
    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick(int type, int position) {
            switch (type) {
                case 0:
//                    PictureSelectorUtils.selectPicture(mContext, selectMedia, maxSelectNum, resultCallback);

                    new ActionSheetDialog(UploadTaskActivity.this)
                            .builder()
                            .setCancelable(false)
                            .setCanceledOnTouchOutside(false)
                            .addSheetItem("拍照", ActionSheetDialog.SheetItemColor.Blue,
                                    new ActionSheetDialog.OnSheetItemClickListener() {
                                        @Override
                                        public void onClick(int which) {
                                            // 调用相机
                                            PictureSelectorUtils.openCamera(mContext);
//                                            PictureConfig.getInstance().startOpenCamera(mContext, resultCallback);
//                                            PermissionGen.needPermission(UploadTaskActivity.this, 900, new String[]{Manifest.permission.CAMERA});
                                        }
                                    })
                            .addSheetItem("从相册中选取", ActionSheetDialog.SheetItemColor.Blue,
                                    new ActionSheetDialog.OnSheetItemClickListener() {
                                        @Override
                                        public void onClick(int which) {
                                            // 调用相册
                                            PictureSelectorUtils.selectPicture(mContext, selectMedia, maxSelectNum);

//                                            PictureSelectorUtils.selectPicture(mContext, selectMedia, maxSelectNum, resultCallback);

                                        }
                                    }).show();

                    break;
                case 1:
                    // 删除图片
                    selectMedia.remove(position);
                    adapter.notifyItemRemoved(position);
                    break;
            }
        }
    };

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
                    if (selectMedia != null) {
                        adapter.setList(selectMedia);
                        adapter.notifyDataSetChanged();
                    }

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
//            Log.i("callBack_result", selectMedia.size() + "");
//            if (selectMedia != null) {
//                adapter.setList(selectMedia);
//                adapter.notifyDataSetChanged();
//            }
//        }
//        @Override
//        public void onSelectSuccess(LocalMedia media) {
//            // 单选回调
//            selectMedia.add(media);
//            if (selectMedia != null) {
//                adapter.setList(selectMedia);
//                adapter.notifyDataSetChanged();
//            }
//        }
//    };

    @Override
    protected void onResume() {
        super.onResume();

    }


    @Override
    protected void initEventAndData() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.rl_back, R.id.UpLoad})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.UpLoad:
                String content = mContent.getText().toString();
                if (selectMedia.size() != 0 & !StringUtils.isEmpty(content)) {
                    CommitWithFile();

                } else {
                    new AlertDialog(mContext).builder().setTitle("提示")
                            .setMsg("请上传图片和内容，我们将进行校检")
                            .setPositiveButton("确定", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            }).show();
                }
                break;
        }
    }

    /**
     * 包含图片的反馈提交
     */
    private void CommitWithFile() {
        if(mContext.isDestroyed()){
            return;
        }

        Map<String, String> map = new HashMap<String, String>();
        map.put("workflowStepIdFk", getIntent().getStringExtra("stepid"));
        map.put("workflowProIdFk", getIntent().getStringExtra("id"));
        map.put("workflowStepOrder", getIntent().getStringExtra("step"));
        map.put("workflowStepComment", mContent.getText().toString());
        map.put("workflowStepOperationName", mSPUtils.getString("username"));
        LogUtils.d(map);

        Map<String, File> mapfile = new HashMap<>();
        if (selectMedia.size() > 0) {
            for (int i = 0; i < selectMedia.size(); i++) {

                tempFile = new File(
                        Environment.getExternalStorageDirectory(),
                        getPhotoFileName(i));
                mapfile.put("file" + i + "\"; filename=\"" + tempFile.getName(), new File(selectMedia.get(i).getPath()
                        .toString()));

            }
        }
        LogUtils.i("Commit: " + map);
        LogUtils.i("file:" + mapfile);
        mUpLoadPresenter.upLoad(mContext, mMyOkhttp, "1", map, mapfile);
    }

    @Override
    public void showDialog() {
//        DialogUtils.Progress(mContext, "   上传中...");
        LemonBubble.getRoundProgressBubbleInfo()
                .setLocationStyle(LemonBubbleLocationStyle.CENTER)
                .setLayoutStyle(LemonBubbleLayoutStyle.ICON_LEFT_TITLE_RIGHT)
                .setBubbleSize(200, 50)
                .setProportionOfDeviation(0.1f)
                .setTitle("上传中...")
                .show(mContext);
    }

    @Override
    public void dismissDialog() {
//        DialogUtils.dismiss();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess() {

        EventBus.getDefault().post(new MyEvent(Constants.WorkFlowStep_CODE));
        EventBus.getDefault().post(new MyEvent(Constants.STEP_CODE2));
        Toast.makeText(mContext, "上传成功", Toast.LENGTH_SHORT).show();
        LemonBubble.hide();
        finish();


//        new AlertDialog(mContext).builder().setTitle("提示")
//                .setMsg("上传成功！")
//                .setPositiveButton("确定", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        selectMedia.clear();
//                        finish();
//
//                    }
//                }).show();

    }

    @Override
    public void onFailure() {
        LemonBubble.showError(mContext, "上传失败", 1000);

//        DialogUtils.dismiss();
    }

    @Override
    public void onError() {
//        DialogUtils.dismiss();
        LemonBubble.showError(mContext, "上传失败", 1000);

    }
}
