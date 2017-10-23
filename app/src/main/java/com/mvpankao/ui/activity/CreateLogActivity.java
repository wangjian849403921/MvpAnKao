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
import com.mvpankao.http.NetUrl;
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
 * Created by wangjian
 * On  2017/2/17
 */

public class CreateLogActivity extends BaseActivity implements UpLoadView {
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.title)
    TextView mTitle;

    @BindView(R.id.Content)
    EditText mContent;
    @BindView(R.id.righttext)
    TextView mRighttext;
    @BindView(R.id.rl_edit)
    RelativeLayout mRlEdit;
    GridImageAdapter adapter;
    @BindView(R.id.recycler)
    RecyclerView mRecycler;
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
        return R.layout.ac_createlog;
    }

    @Override
    protected void initView() {
        mTitle.setText("工单日志");
        String statue = getIntent().getStringExtra("statue");
        switch (statue) {
            case "1":
                mRlEdit.setVisibility(View.VISIBLE);
                mRighttext.setText("完成");
                break;
            case "2":
                mRlEdit.setVisibility(View.GONE);
                break;
        }
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

                    new ActionSheetDialog(mContext)
                            .builder()
                            .setCancelable(false)
                            .setCanceledOnTouchOutside(false)
                            .addSheetItem("拍照", ActionSheetDialog.SheetItemColor.Blue,
                                    new ActionSheetDialog.OnSheetItemClickListener() {
                                        @Override
                                        public void onClick(int which) {
                                            // 调用相机
                                            PictureSelectorUtils.openCamera(mContext);
                                        }
                                    })
                            .addSheetItem("从相册中选取", ActionSheetDialog.SheetItemColor.Blue,
                                    new ActionSheetDialog.OnSheetItemClickListener() {
                                        @Override
                                        public void onClick(int which) {
                                            // 调用相册
                                            PictureSelectorUtils.selectPicture(mContext, selectMedia, maxSelectNum);

//                                            PictureSelectorUtils.selectPicture(CreateLogActivity.this, selectMedia, maxSelectNum, resultCallback);

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
//        switch (requestCode) {
//            case TAKE_PICTURE:
//                if (list.size() < 5
//                        && resultCode == -1 && !TextUtils.isEmpty(paths)) {
//                    LocalMedia item = new LocalMedia();
//                    item.setPath(paths);
//                    list.add(item);
//
//                    mRecycler.setAdapter(adapter);
//                    adapter.notifyDataSetChanged();
//                }
//                break;
//        }
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
//
//    /**
//     * 图片回调方法
//     */
//    private PictureConfig.OnSelectResultCallback resultCallback = new PictureConfig.OnSelectResultCallback()   {
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
    protected void initEventAndData() {

    }
//
//    private static final int TAKE_PICTURE = 0x000675;
//    private String path = "";
//
//    @PermissionSuccess(requestCode = 100)
//    public void takePhotos() {
//        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//
//        File vFile = new File(Environment.getExternalStorageDirectory()
//                + "/myimage/", String.valueOf(System.currentTimeMillis())
//                + ".jpg");
//        if (!vFile.exists()) {
//            File vDirPath = vFile.getParentFile();
//            vDirPath.mkdirs();
//        } else {
//            if (vFile.exists()) {
//                vFile.delete();
//            }
//        }
//        path = vFile.getPath();
//        Uri cameraUri = Uri.fromFile(vFile);
//        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, cameraUri);
//        startActivityForResult(openCameraIntent, TAKE_PICTURE);
//    }
//
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        switch (requestCode) {
//            case TAKE_PICTURE:
//                if (selectMedia.size() < maxSelectNum
//                        && resultCode == -1 && !TextUtils.isEmpty(path)) {
//                    LocalMedia image = new LocalMedia();
//                    image.setPath(path);
//                    selectMedia.add(image);
//                    if (selectMedia != null) {
//                        adapter.setList(selectMedia);
//                        adapter.notifyDataSetChanged();
//                    }
//                }
//                break;
//        }
//
//    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.rl_back, R.id.rl_edit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_edit:
                String content = mContent.getText().toString();
                if (selectMedia.size() != 0 & !StringUtils.isEmpty(content)) {
                    CommitWithFile();
                } else {
                    new AlertDialog(mContext).builder().setTitle("提示")
                            .setMsg("请填写内容")
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
        String url = NetUrl.URLHeader + NetUrl.WorkOrder.AddWorkLog;
        Map<String, String> map = new HashMap<String, String>();
        map.put("engineeringIdfk", getIntent().getStringExtra("id"));
        map.put("logComment", mContent.getText().toString());
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

        mUpLoadPresenter.upLoad(mContext, mMyOkhttp, "2", map, mapfile);

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

        EventBus.getDefault().post(new MyEvent(Constants.LogLISTUPDATE_CODE));
        EventBus.getDefault().post(new MyEvent(Constants.WorkOrderDetailUPDATE_CODE));
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

    }

    @Override
    public void onError() {
        LemonBubble.showError(mContext, "上传失败", 1000);

    }
}
