package com.mvpankao.utils;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;

import com.mvpankao.dialog.ActionSheetDialog;


/**
 * Created by wangjian
 * On  2017/2/10
 */

public class CallPhoneUtils {

    public static void  callphone(final Context mContext){
        new ActionSheetDialog(mContext)
                .builder()
                .setTitle("呼叫客服" + Constants.Phone + "?")
                .setCancelable(false)
                .setCanceledOnTouchOutside(false)
                .addSheetItem("呼叫", ActionSheetDialog.SheetItemColor.Red,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + Constants.Phone));
                                if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                    // TODO: Consider calling
                                    //    ActivityCompat#requestPermissions
                                    // here to request the missing permissions, and then overriding
                                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                    //                                          int[] grantResults)
                                    // to handle the case where the user grants the permission. See the documentation
                                    // for ActivityCompat#requestPermissions for more details.
                                    return;
                                }
                                mContext.startActivity(intent);
                            }
                        }).show();
    }
}
