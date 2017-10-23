package com.mvpankao.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @author 汪健
 *
 * @date 2016-1-5
 *
 * @description 图片压缩
 *
 */

public class IMGUtils {

    /**
     * 质量压缩法
     * @param image
     * @return
     */
    private static Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while ( baos.toByteArray().length / 1024>100) { //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }


    /**
     * 图片按比例大小压缩方法（根据路径获取图片并压缩）
     * @param srcPath
     * @return
     */
    public static Bitmap getimage(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(srcPath,newOpts);//此时返回bm为空

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 800f;//这里设置高度为800f
        float ww = 480f;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 2;//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        Bitmap  bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return bitmap;//压缩好比例大小后再进行质量压缩
    }


    /**
     * 图片按比例大小压缩方法（根据Bitmap图片压缩）
     * @param image
     * @return
     */
    private static Bitmap comp(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        if( baos.toByteArray().length / 1024>1024) {//判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, 50, baos);//这里压缩50%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 800f;//这里设置高度为800f
        float ww = 480f;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        isBm = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        return compressImage(bitmap);//压缩好比例大小后再进行质量压缩
    }


    /**
     *
     * @param options
     * @param reqWidth
     * @return
     */
    public static int calculateInSampleSize(BitmapFactory.Options options,int reqWidth){
        //源图片的宽度
        final int width = options.outWidth;
        int inSampleSize =1;
        if(width>reqWidth){
            //计算出实际宽度和目标宽度的比率
            final int widthRatio = Math.round((float)width/(float)reqWidth);
            inSampleSize =widthRatio;
        }
        return inSampleSize;
    }

    /**
     *
     * @param pathName
     * @param reqWidth 图片宽度
     * @return
     */
    public static Bitmap decodeSampledBitmaoFromResource(String pathName,int reqWidth){
        //第一次解析将inJustDecodeBounds设置为true，来获取图片大小
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathName,options);
        //调用上面定义的方法计算inSampleSize值
        options.inSampleSize = calculateInSampleSize(options,reqWidth);
        //使用获取到inSampleSize值再次解析图片
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(pathName, options);
    }


//    public static List<File> getFileAll(Context context){
//        String filePath = FileOperateUtil.getFolderPath(context,null);
//        List<File> fileList = new ArrayList<File>();
//        File file = new File(filePath);
//        if(file.exists()){
//            File[] fileAll  = file.listFiles();
//            for(int i =0 ;i<fileAll.length;i++){
//                if(fileAll[i].isDirectory()){
//                    List<File> list = getRecursion(fileAll[i]);
//                    if(list!=null&&list.size()>0){
//                        fileList.addAll(list);
//                    }
//                }else if(fileAll[i].isFile()&&fileAll[i].getName().contains(".jpg")){
//                    fileList.add(fileAll[i]);
//                }
//            }
//        }
//        return fileList;
//    }

    private static List<File> getRecursion(File file){
        List<File> list = new ArrayList<File>();
        File[] files = file.listFiles();
        for(int i=0;i<files.length;i++){
            if(files[i].isFile()){
                list.add(files[i]);
            }
        }
        return list;
    }

    /**
     * 删除文件夹下面的所有文件
     * @param file
     */
    public static void getDeleFile(File file){
        File[] files = file.listFiles();
        for(File file1:files){
            if(file1.isDirectory()){
                getDeleFile2(file1);
            }else{
                file1.delete();
            }
        }
    }

    public static void getDeleFile2(File file){
        File[] files = file.listFiles();
        for(File file1:files){
            if(file1.isDirectory()){
                getDeleFile2(file1);
            }else{
                file1.delete();
            }
        }
        file.delete();
    }
}
