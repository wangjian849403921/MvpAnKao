package com.mvpankao.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.blankj.utilcode.utils.SPUtils;
import com.mvpankao.R;
import com.mvpankao.dialog.AlertDialog;
import com.mvpankao.mvp.login.LoginActivity;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/9 14:16
 */

public class HasLoginUtils {

    public static void checkLogin(final Context mContext, final Intent intent) {
        SPUtils mSPUtils = new SPUtils(mContext, "USER_INFO");

        if (mSPUtils.getBoolean("hasLogin")) {
            mContext.startActivity(intent);
            ((Activity) mContext).overridePendingTransition(R.anim.anim_in_right_left, R.anim.anim_out_right_left);
        } else {
            new AlertDialog(mContext).builder().setTitle("提示")
                    .setMsg("您尚未登录不能进行相关操作")
                    .setPositiveButtonColor("#FD4A2E")
                    .setPositiveButton("去登录", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            mContext.startActivity(new Intent(mContext, LoginActivity.class));
                            ((Activity) mContext).overridePendingTransition(R.anim.anim_in_right_left, R.anim.anim_out_right_left);

                        }
                    }).setNegativeButton("取消", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            }).show();
        }
    }
}
