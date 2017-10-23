package com.mvpankao.utils;

import android.content.Context;
import android.os.Handler;
import android.widget.ImageView;

import com.kaopiz.kprogresshud.KProgressHUD;


/**
 * 自定义dialog
 * 带有progressbar 消息提示
 * Created by wangjian
 * On  2016/9/14
 */
public class DialogUtils {
    private static KProgressHUD mKProgressHUD;
    private Context mContext;
    private static Handler handler = new Handler();
    public DialogUtils(Context context) {
        this.mContext = context;
        mKProgressHUD = new KProgressHUD(context);
    }

    public static void Loading(Context context) {
        mKProgressHUD = new KProgressHUD(context);
        mKProgressHUD.setLabel("   加载中...")
                .setCancellable(false)
                .show();
        new Thread() {
            public void run() {
                try {
                    sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // 卸载所创建的myDialog对象。
                    mKProgressHUD.dismiss();

                }
            }
        }.start();

    }

    public static void Login(Context context) {
        mKProgressHUD = new KProgressHUD(context);
        mKProgressHUD.setLabel("   登录中...")
                .setCancellable(false)
                .show();

        new Thread() {
            public void run() {
                try {
                    sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // 卸载所创建的myDialog对象。
                    mKProgressHUD.dismiss();

                }
            }
        }.start();
    }
    public static void Progress(Context context,String string) {
        mKProgressHUD = new KProgressHUD(context);
        mKProgressHUD.setLabel(string)
                .setCancellable(false)
                .show();
        new Thread() {
            public void run() {
                try {
                    sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // 卸载所创建的myDialog对象。
                    mKProgressHUD.dismiss();

                }
            }
        }.start();

    }
    public static void dismiss() {
        if (mKProgressHUD!=null) {
            mKProgressHUD.dismiss();
        }
    }

    public static void setMessage(Context context, String message) {
        dismiss();
        ImageView imageView = new ImageView(context);
        mKProgressHUD = new KProgressHUD(context);
        mKProgressHUD.setLabel(message)
                .setCustomView(imageView)
                .setCancellable(false)
                .show();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dismiss();
            }
        }, 1500);
    }

    public static void timeOut(Context context){
        dismiss();
        mKProgressHUD=new KProgressHUD(context);
        ImageView imageView = new ImageView(context);

        mKProgressHUD.setCustomView(imageView)
                .setLabel("连接服务器超时")
                .setCancellable(false)
                .show();

        new Thread() {
            public void run() {
                try {
                    sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // 卸载所创建的myDialog对象。
                    mKProgressHUD.dismiss();

                }
            }
        }.start();
    }
}
