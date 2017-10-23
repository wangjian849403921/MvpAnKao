package com.mvpankao.utils;

import android.app.Activity;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.compress.Luban;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.mvpankao.R;

import java.util.List;

import static com.luck.picture.lib.config.PictureConfig.LUBAN_COMPRESS_MODE;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/10 14:58
 */

public class PictureSelectorUtils {
    /**
     * @param mContext
     * @param list         图片、视频list
     * @param maxSelectNum 最多选取多少张
     */
    public static void selectPicture(Activity mContext, List<LocalMedia> list, int maxSelectNum) {
        PictureSelector.create(mContext)
                .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
                .theme(R.style.picture_QQ_style)//主题样式(不设置为默认样式) 也可参考demo values/styles下 例如：R.style.picture.white.style
                .maxSelectNum(maxSelectNum)// 最大图片选择数量 int
//                .minSelectNum()// 最小选择数量 int
                .imageSpanCount(4)// 每行显示个数 int
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片 true or false
                .previewVideo(false)// 是否可预览视频 true or false
                .enablePreviewAudio(true) // 是否可播放音频 true or false
                .compressGrade(Luban.THIRD_GEAR)// luban压缩档次，默认3档 Luban.THIRD_GEAR、Luban.FIRST_GEAR、Luban.CUSTOM_GEAR
                .isCamera(true)// 是否显示拍照按钮 true or false
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .setOutputCameraPath("/CustomPath")// 自定义拍照保存路径,可不填
                .enableCrop(false)// 是否裁剪 true or false
                .compress(true)// 是否压缩 true or false
                .compressMode(LUBAN_COMPRESS_MODE)//系统自带 or 鲁班压缩 PictureConfig.SYSTEM_COMPRESS_MODE or LUBAN_COMPRESS_MODE
                .glideOverride(120, 120)// int glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .withAspectRatio(16, 9)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示 true or false
                .isGif(true)// 是否显示gif图片 true or false
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                .circleDimmedLayer(true)// 是否圆形裁剪 true or false
                .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                .openClickSound(true)// 是否开启点击声音 true or false
                .selectionMedia(list)// 是否传入已选图片 List<LocalMedia> list
                .previewEggs(true)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
//                .cropCompressQuality()// 裁剪压缩质量 默认90 int
                .compressMaxKB(50)//压缩最大值kb compressGrade()为Luban.CUSTOM_GEAR有效 int
//                .compressWH() // 压缩宽高比 compressGrade()为Luban.CUSTOM_GEAR有效  int
//                .cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效 int
                .rotateEnabled(true) // 裁剪是否可旋转图片 true or false
                .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
//                .videoQuality()// 视频录制质量 0 or 1 int
//                .videoSecond()// 显示多少秒以内的视频or音频也可适用 int
//                .recordVideoSecond()//视频秒数录制 默认60s int
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }

    /**
     * 拍照
     * @param mContext
     */
    public static void openCamera(Activity mContext) {
        PictureSelector.create(mContext)
                .openCamera(PictureMimeType.ofImage())
                .enableCrop(true)// 是否裁剪 true or false
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                .circleDimmedLayer(true)// 是否圆形裁剪 true or false
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }
//    public static boolean isCheckNumMode = false;
//    public static boolean theme = false;
//    public static int selectType = FunctionConfig.TYPE_IMAGE;
//    public static int copyMode = FunctionConfig.CROP_MODEL_DEFAULT;
//    private static boolean selectImageType = false;
//
//    private static boolean isCompress = true;
//
//    public static void selectPicture(Activity mContext, List<LocalMedia> selectMedia, int maxSelectNum, PictureConfig.OnSelectResultCallback resultCallback) {
//
//        /**
//         * type --> 1图片 or 2视频
//         * copyMode -->裁剪比例，默认、1:1、3:4、3:2、16:9
//         * maxSelectNum --> 可选择图片的数量
//         * selectMode         --> 单选 or 多选
//         * isShow       --> 是否显示拍照选项 这里自动根据type 启动拍照或录视频
//         * isPreview    --> 是否打开预览选项
//         * isCrop       --> 是否打开剪切选项
//         * isPreviewVideo -->是否预览视频(播放) mode or 多选有效
//         * ThemeStyle -->主题颜色
//         * CheckedBoxDrawable -->图片勾选样式
//         * cropW-->裁剪宽度 值不能小于100  如果值大于图片原始宽高 将返回原图大小
//         * cropH-->裁剪高度 值不能小于100
//         * isCompress -->是否压缩图片
//         * setEnablePixelCompress 是否启用像素压缩
//         * setEnableQualityCompress 是否启用质量压缩
//         * setRecordVideoSecond 录视频的秒数，默认不限制
//         * setRecordVideoDefinition 视频清晰度  Constants.HIGH 清晰  Constants.ORDINARY 低质量
//         * setImageSpanCount -->每行显示个数
//         * setCheckNumMode 是否显示QQ选择风格(带数字效果)
//         * setPreviewColor 预览文字颜色
//         * setCompleteColor 完成文字颜色
//         * setPreviewBottomBgColor 预览界面底部背景色
//         * setBottomBgColor 选择图片页面底部背景色
//         * setCompressQuality 设置裁剪质量，默认无损裁剪
//         * setSelectMedia 已选择的图片
//         * setCompressFlag 1为系统自带压缩  2为第三方luban压缩
//         * 注意-->type为2时 设置isPreview or isCrop 无效
//         * 注意：Options可以为空，默认标准模式
//         */
//
//
//        int selector = R.drawable.checkbox_selector;
//        FunctionOptions options = new FunctionOptions.Builder()
//                .setType(selectType)
//                .setCropMode(copyMode)
//                .setCompress(isCompress)
//                .setEnablePixelCompress(true)
//                .setEnableQualityCompress(true)
//                .setMaxSelectNum(maxSelectNum)
//                .setSelectMode(FunctionConfig.MODE_MULTIPLE)
//                .setShowCamera(true)
//                .setEnablePreview(true)
//                .setEnableCrop(false)
////              .setCropW(cropW)
////              .setCropH(cropH)
//                .setCheckNumMode(true)
//                .setCompressQuality(100)
//                .setImageSpanCount(4)
//                .setSelectMedia(selectMedia)
//                .setCompressFlag(1)
//                .setCompressW(100)
//                .setCompressH(100).create();
//        if (theme) {
//            options.setThemeStyle(ContextCompat.getColor(mContext, R.color.blue));
//            // 可以自定义底部 预览 完成 文字的颜色和背景色
//            if (!isCheckNumMode) {
//                // QQ 风格模式下 这里自己搭配颜色，使用蓝色可能会不好看
//                options.setPreviewColor(ContextCompat.getColor(mContext, R.color.white));
//                options.setCompleteColor(ContextCompat.getColor(mContext, R.color.white));
//                options.setPreviewBottomBgColor(ContextCompat.getColor(mContext, R.color.blue));
//                options.setBottomBgColor(ContextCompat.getColor(mContext, R.color.blue));
//            }
//        }
//        if (selectImageType) {
//            options.setCheckedBoxDrawable(selector);
//        }
//
//        // 先初始化参数配置，在启动相册
//        PictureConfig.getInstance().init(options).openPhoto(mContext, resultCallback);
//    }
}
