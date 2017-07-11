package com.mingren.lib.baselibrary.utils.premission;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import com.mingren.lib.baselibrary.basefragment.BaseFragment;

/**
 *  申请权限工具类
 */

public class PermissionUtil {

    public static final int WRITE_EXTERNAL_STORAGE = 10010;  // 读写权限回调码
    public static final int CAMERA = 10011; // 相机权限回调码

    public static  String[] writePermission = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
    public static String[] cameraPermission = {Manifest.permission.CAMERA};
    /**
     *  判断系统大于等于23
     */
    public static boolean buildVersion(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return  true;
        }else {
            return  false;
        }
    }


    /**
     *   申请权限
     */
    public static void permission(final BaseFragment fragment, String message, final String[] permission, final int code){
        // 检查该权限是否已经获取
                     int i = ContextCompat.checkSelfPermission(fragment.getMyActivity(), permission[0]);
                     // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
                     if (i != PackageManager.PERMISSION_GRANTED) {
                             // 如果没有授予该权限，就去提示用户请求
                         showDialogTipUserRequestPermission(fragment.getMyActivity(),message, new DialogInterface.OnClickListener() {
                             @Override
                             public void onClick(DialogInterface dialog, int which) {
                                 startRequestPermission(fragment.getMyActivity(),permission,code);
                             }
                         });
                     }else{
                         ((PermissionLisenter)fragment).permissionRunnable(code);
                     }
    }

    // 提示用户该请求权限的弹出框
      public static void showDialogTipUserRequestPermission(final Context context,  String message,DialogInterface.OnClickListener onClickListener) {
                 new AlertDialog.Builder(context)
                         .setMessage(message)
                         .setPositiveButton("立即开启", onClickListener
                  )
                         .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                                     }
                  }).setCancelable(false).show();
             }


    // 开始提交请求权限
      public static void startRequestPermission(Activity context,String[] permission,int code) {
                 ActivityCompat.requestPermissions( context, permission, code);
      }

}
