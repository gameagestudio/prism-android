
package com.shopex.android.prism.utils;

import java.util.Locale;

import com.shopex.android.prism.domain.DeviceInfo;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * 系统工具类，主要是针对手机硬件条件的判断
 * 
 * @author bluestome
 */
public class SystemUtils {

    private static final String TAG = SystemUtils.class.getSimpleName();

    /**
     * 检测网络连接
     * 
     * @param context
     * @return
     */
    public static boolean checkConnection(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null) {
            return networkInfo.isAvailable();
        }
        return false;
    }

    /**
     * 检查wifi是否可用
     * 
     * @param mContext
     * @return
     */
    public static boolean isWifi(Context mContext) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null && activeNetInfo.getTypeName().equals("WIFI")) {
            return true;
        }
        return false;
    }

    /**
     * 检查SD卡是否可用
     * 
     * @param mContext
     * @return
     */
    public static boolean checkSDCard(Context mContext) {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }

    /**
     * 初始化设备信息
     */
    public static DeviceInfo initDeviceInfo(Context ctx) {
        DeviceInfo mDeviceInfo = new DeviceInfo();
        mDeviceInfo.product = android.os.Build.MANUFACTURER;
        if (StringUtil.isBlank(mDeviceInfo.product)) {
            mDeviceInfo.product = "unknow";
        }
        mDeviceInfo.modle = android.os.Build.MODEL;
        if (StringUtil.isBlank(mDeviceInfo.modle)) {
            mDeviceInfo.modle = "unknow";
        }
        mDeviceInfo.sdks = android.os.Build.BRAND;
        mDeviceInfo.sdk = (short) android.os.Build.VERSION.SDK_INT;
        TelephonyManager tm = (TelephonyManager) ctx
                .getSystemService(Context.TELEPHONY_SERVICE);
        mDeviceInfo.osVersion = "Android" + android.os.Build.VERSION.RELEASE;
        mDeviceInfo.imsi = tm.getSubscriberId();// "810260000000007";
        if (StringUtil.isBlank(mDeviceInfo.imsi)) {
            mDeviceInfo.imsi = "111111111111111";
        } else if (mDeviceInfo.imsi.length() < 15) {
            int len = mDeviceInfo.imsi.length();
            int s = 15 - len;
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < s; i++) {
                sb.append("0");
            }
            mDeviceInfo.imsi += sb.toString();
        } else if (mDeviceInfo.imsi.length() > 15) {
            mDeviceInfo.imsi = mDeviceInfo.imsi.substring(0, 15);
        }

        mDeviceInfo.imei = tm.getDeviceId();
        if (StringUtil.isBlank(mDeviceInfo.imei)) {
            mDeviceInfo.imei = "222222222222222";
        } else if (mDeviceInfo.imei.length() < 15) {
            int len = mDeviceInfo.imei.length();
            int s = 15 - len;
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < s; i++) {
                sb.append("0");
            }
            mDeviceInfo.imei += sb.toString();
        } else if (mDeviceInfo.imei.length() > 15) {
            mDeviceInfo.imei = mDeviceInfo.imei.substring(0, 15);
        }
        // 应用版本号
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), 0);
            mDeviceInfo.version = String.valueOf(pi.versionCode);
            mDeviceInfo.versionName = pi.versionName;
        } catch (Exception e) {
            mDeviceInfo.version = "1";
            mDeviceInfo.versionName = "1.0.e";
        }
        if (StringUtil.isBlank(mDeviceInfo.version)) {
            mDeviceInfo.version = "1";
            mDeviceInfo.versionName = "1.0.e";
        }
        Log.d(TAG, "厂商:" + mDeviceInfo.product);
        Log.d(TAG, "机型:" + mDeviceInfo.modle);
        Log.d(TAG, "IMEI:" + mDeviceInfo.imei);
        Log.d(TAG, "IMSI:" + mDeviceInfo.imsi);
        Log.d(TAG, "SDK:" + mDeviceInfo.sdk);
        Log.d(TAG, "OS VERSION:" + mDeviceInfo.osVersion);
        Log.d(TAG, "应用版本号:" + mDeviceInfo.version);
        Log.d(TAG, "应用版本号(Name):" + mDeviceInfo.versionName);
        return mDeviceInfo;
    }

    /**
     * 获取系统的UA
     * 
     * @param ctx
     * @return
     */
    public static String getUA(Context ctx) {
        String ua = null;
        WebView webview = new WebView(ctx);
        WebSettings settings = webview.getSettings();
        ua = settings.getUserAgentString();
        return ua;
    }

    /**
     * 获取本地语言
     * 
     * @return
     */
    public static String getLocales() {
        Locale locales = Locale.getDefault();
        // 格式化语言类型
        String format = locales.getLanguage() + "-" + locales.getCountry();
        return format;
    }
}
