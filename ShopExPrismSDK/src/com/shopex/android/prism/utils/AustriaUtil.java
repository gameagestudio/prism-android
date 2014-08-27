/*
 * ========================================================
 * Copyright(c) 2014 杭州偶尔科技-版权所有
 * ========================================================
 * 本软件由杭州偶尔技所有, 未经书面许可, 任何单位和个人不得以
 * 任何形式复制代码的部分或全部, 并以任何形式传播。
 * 公司网址
 * 
 *          http://www.ouer.com/
 * 
 * ========================================================
 */

package com.shopex.android.prism.utils;

import java.io.File;
import java.lang.reflect.Method;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;




/**
 * @author : Zhenshui.Xia
 * @date : 2014年5月17日
 * @desc :
 */
public class AustriaUtil {
    // 消息通知ID
    private static final int MSG_NOTIFY_ID = 0;

    /**
     * 安装应用
     * 
     * @param context
     * @param filepath
     */
    public static void installApk(Context context, String filepath) {
        Uri uri = Uri.fromFile(new File(filepath));
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    /**
     * 保存裁剪之后的图片数据
     * 
     * @param picdata
     */
    public static void setPicToView(Intent picdata, ImageView mImageShow) {
        Bundle extras = picdata.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            Drawable drawable = new BitmapDrawable(photo);
            if (null != mImageShow) {
                mImageShow.setBackgroundDrawable(drawable);
            }
        }
    }

    /**
     * 裁剪图片方法实现
     * 
     * @param uri
     */
    public static void startPhotoZoom(Activity activity, Uri uri, int aspectX,
            int aspectY, int width, int height, Uri oUri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (null != uri) {
            intent.setDataAndType(uri, "image/*");
        }
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", aspectX);
        intent.putExtra("aspectY", aspectY);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("scale", true);
        intent.putExtra("outputX", width);
        intent.putExtra("outputY", height);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        // 是否保存为壁纸
        intent.putExtra("setWallpaper", false);
        if (null != oUri) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, oUri);
            intent.putExtra("return-data", false);
        } else {
            intent.putExtra("return-data", true);
        }

        activity.startActivityForResult(intent, 3);
    }

    /**
     * 裁剪图片方法实现
     * 
     * @param uri
     */
    public static void startPhotoZoom(Activity activity, Uri uri, int aspectX,
            int aspectY, int width, int height, Uri oUri, int resultCode) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (null != uri) {
            intent.setDataAndType(uri, "image/*");
        }
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", aspectX);
        intent.putExtra("aspectY", aspectY);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("scale", true);
        // 是否保存为壁纸
        intent.putExtra("setWallpaper", false);
        if (width > 0) {
            intent.putExtra("outputX", width);
        }
        if (height > 0) {
            intent.putExtra("outputY", height);
        }
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        if (null != oUri) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, oUri);
            intent.putExtra("return-data", false);
        } else {
            intent.putExtra("return-data", true);
        }

        activity.startActivityForResult(intent, resultCode);
    }



    /**
     * 显示Toast
     * 
     * @param context
     * @param text
     * @param duration
     */
    public static void toast(Context context, String text, int duration) {
        if (context == null || StringUtil.isBlank(text) || duration <= 0)
            return;
        Toast.makeText(context, text, duration).show();
    }

    /**
     * 网络是否连接
     * 
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager
                    .getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * WIFI是否可用
     * 
     * @param context
     * @return
     */
    public static boolean isWifiConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetInfo = mConnectivityManager.getActiveNetworkInfo();
            if (activeNetInfo != null
                    && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * 移动网络是否可用
     * 
     * @param context
     * @return
     */
    public static boolean isMobileConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobileNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mMobileNetworkInfo != null) {
                return mMobileNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 网络是否可用
     * 
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            Log.i("NetWorkState", "Unavailabel");
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        Log.i("NetWorkState", "Availabel");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 获取当前运行的进程名
     * 
     * @param context
     * @return
     */
    public static String getProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }

        return null;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获取当前运行界面的包名
     * 
     * @param context
     * @return
     */
    public static String getTopPackageName(Context context) {
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        ComponentName cn = activityManager.getRunningTasks(1).get(0).topActivity;
        return cn.getPackageName();
    }

    /**
     * 获取当前运行界面的activity名
     * 
     * @param context
     * @return
     */
    public static String getTopActivity(Context context) {
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        ComponentName cn = activityManager.getRunningTasks(1).get(0).topActivity;
        return cn.getClassName();
    }

   
    /**
     * 取消消息通知
     * 
     * @param context
     */
    public static void cancelMsg(Context context) {
        NotificationManager manager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancel(MSG_NOTIFY_ID);
    }

   

    /**
     * 判断是否是平板
     * 
     * @param context
     * @return
     */
    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
        & Configuration.SCREENLAYOUT_SIZE_MASK)
        >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    /**
     * 优化内存分配机制
     * 
     * @param ClassLoader
     * @return
     */

    public static void optimizeDalvikVM(ClassLoader loader) {
        try {
            Class<?> clazz = loader.loadClass("dalvik.system.VMRuntime");
            Method method = clazz.getMethod("getRuntime", new Class[] {});
            method.setAccessible(true);
            Object instance = method.invoke(clazz);
            method =
                    instance.getClass().getMethod("setTargetHeapUtilization", new
                            Class[] {
                                float.class
                            });
            method.setAccessible(true);
            method.invoke(instance, new Object[] {
                    0.75f
            });
            System.out.println("optimize Dalvik VM...");
        } catch (Exception e) {

        }
    }

}
