package com.mvpankao.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mvpankao.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by wangjian
 * On  2017/1/20
 */

public class DownFile {
    private Context mContext;
    private Dialog noticeDialog;
    private Dialog downloadDialog;
    private static final String savePath = "/sdcard/downfile/";
    private int progress;
    private ProgressBar mProgress;
    private static final int DOWN_UPDATE = 1;
    private static final int DOWN_OVER = 2;
    private Thread downLoadThread;
    private boolean interceptFlag = false;
//    private static String fileUrl = "http://222.185.56.218:8090/fileResource/docment/e3c371c6a0344b4a851e90bf3cb8b839.docx";

    private static String fileUrl = "";

    //    private static String fileUrl = "http://www.baidu.com/img/shouye_b5486898c692066bd2cbaeda86d74448.gif";
    private static String saveFileName = "";

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DOWN_UPDATE:
                    mProgress.setProgress(progress);
                    break;
                case DOWN_OVER:
                    downloadDialog.dismiss();
                    openFile();
                    break;
                default:
                    break;
            }
        }


    };

    public DownFile(Context context, String path) {
        this.mContext = context;
        this.fileUrl = path;
        saveFileName = savePath + fileUrl.split("/")[fileUrl.split("/").length - 1];
    }

    public void checkUpdateInfo() {
        File path = new File(saveFileName);
        if (path.exists()) {
            openFile();
            return;
        }
        showNoticeDialog();

    }

    private void showNoticeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("提示");
        builder.setMessage("发现新资源,是否下载？");
        builder.setPositiveButton("马上下载", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                showDownloadDialog();

            }
        });
        builder.setNegativeButton("以后再说", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        noticeDialog = builder.create();
        noticeDialog.show();
    }

    private void showDownloadDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("正在下载中...");

        final LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.progress, null);
        mProgress = (ProgressBar) v.findViewById(R.id.progress);

        builder.setView(v);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                interceptFlag = true;
            }
        });
        downloadDialog = builder.create();
        downloadDialog.show();

        downloadfile();
    }

    private Runnable mdownfileRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                URL url = new URL(fileUrl);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.connect();
                int length = conn.getContentLength();
                InputStream is = conn.getInputStream();

                File file = new File(savePath);
                if (!file.exists()) {
                    file.mkdir();
                }
                String apkFile = saveFileName;
                File ApkFile = new File(apkFile);
                FileOutputStream fos = new FileOutputStream(ApkFile);

                int count = 0;
                byte buf[] = new byte[1024];

                do {
                    int numread = is.read(buf);
                    count += numread;
                    progress = (int) (((float) count / length) * 100);
// 更新进度
                    mHandler.sendEmptyMessage(DOWN_UPDATE);
                    if (numread <= 0) {
// 下载完成通知安装
                        mHandler.sendEmptyMessage(DOWN_OVER);
                        break;
                    }
                    fos.write(buf, 0, numread);
                } while (!interceptFlag);// 点击取消就停止下载.

                fos.close();
                is.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    };

    private void downloadfile() {
        downLoadThread = new Thread(mdownfileRunnable);
        downLoadThread.start();
    }

    private void openFile() {
        File apkfile = new File(saveFileName);
        if (!apkfile.exists()) {
            return;
        }

        try {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(apkfile), getMIMEType(apkfile));
            mContext.startActivity(intent);
        } catch (Exception e) {
// TODO: handle exception
            Toast.makeText(mContext, "请安装相关插件", Toast.LENGTH_LONG).show();
        }

    }

    private static String getMIMEType(File f) {
        String type = "";
        String fName = f.getName();
        String end = fName.substring(fName.lastIndexOf(".") + 1, fName.length()).toLowerCase();
        if (end.equals("m4a") || end.equals("mp3") || end.equals("mid") || end.equals("xmf") || end.equals("ogg") || end.equals("wav")) {
            type = "audio";
        } else if (end.equals("3gp") || end.equals("mp4")) {
            type = "video";
        } else if (end.equals("jpg") || end.equals("gif") || end.equals("png") || end.equals("jpeg") || end.equals("bmp")) {
            type = "image/*";
        } else if (end.equals("doc") || end.equals("docx")) {
            type = "application/msword";
        } else if (end.equals("ppt") || end.equals("pot") || end.equals("pps")) {
            type = "application/vnd.ms-powerpoint";
        } else if (end.equals("xla") || end.equals("xlc") || end.equals("xlm") || end.equals("xls") || end.equals("xlt") || end.equals("xlw") || end.equalsIgnoreCase("xlsx")) {
            type = "application/vnd.ms-excel";
        } else if (end.equals("xll")) {
            type = "application/x-excel";
        } else if (end.equals("pdf")) {
            type = "application/pdf";
        } else if (end.equals("zip")) {
            type = "application/zip";
        } else if (end.equals("rar")) {
            type = "application/x-rar-compressed";
        } else if (end.equals("apk")) {
            type = "application/vnd.android.package-archive";
        } else {
            type = "/*";
        }
        return type;
    }
}
