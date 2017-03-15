package com.example.bowen.myappframe.protocol.network;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.example.bowen.myappframe.application.BaseApplication;
import com.example.bowen.myappframe.util.SpUtil;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dongbowen on 2016/6/26.
 */
public class HeaderInterceptor implements Interceptor {

    public static String Android_Type = "3";
    public static String App_Version = getAppVersion(BaseApplication.getInstance());
    public static String Device_Id = getDeviceId(BaseApplication.getInstance());
    public static String OS_Version = getSdkVersion();
    public static String Device_Type = getDeviceName();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();
        Headers.Builder headerBuilder = oldRequest.headers()
                .newBuilder()
                .add("Client-Version", App_Version)
                .add("Client-Type", Android_Type)
                .add("Device-ID", Device_Id)
                .add("OS-Version", OS_Version)
                .add("Device-Type", Device_Type)
                .add("Token", SpUtil.getString("token", ""))
                .add("Accept", "application/json");
        Request newRequest = oldRequest.newBuilder()
                .url(oldRequest.url())
                .method(oldRequest.method(), oldRequest.body())
                .headers(headerBuilder.build())
                .build();

        //添加固定参数
//        HttpUrl.Builder builder = oldRequest.url()
//                .newBuilder()
//                .scheme(oldRequest.url().scheme())
//                .host(oldRequest.url().host())
//                .addQueryParameter(APP, APP_VALUE)
//                .addQueryParameter(PLATFORM, PLATFORM_VALUE)
//                .addQueryParameter(SYSTEM_VER, Build.VERSION.RELEASE)
//                .addQueryParameter(VERSION, BuildConfig.VERSION_NAME)
//                .addQueryParameter(APP_VER, BuildConfig.VERSION_NAME)
//                .addQueryParameter(IMEI, getImei())
//                .addQueryParameter(DEVICE_ID, getDeviceId())
//                .addQueryParameter(MAC, getMac())
//                .addQueryParameter(SECURE_ID, getSecureId())
//                .addQueryParameter(INSTALL_ID, getInstallId())
//                .addQueryParameter(PHONE_TYPE, getDeviceInfo())
//                .addQueryParameter(VENDOR, "ziyou")
//                .addQueryParameter(U, "NA");
//
//        Request newRequest = oldRequest.newBuilder()
//                .method(oldRequest.method(), oldRequest.body())
//                .url(builder.build())
//                .build();

        return chain.proceed(newRequest);
    }

    /**
     * 软件版本名称
     * @return
     */
    public static String getAppVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String sVersion = String.valueOf(info.versionName);
            return sVersion;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "1.0";
    }

    public static String getDeviceId(Context context) {
        String IMEI = getIMEI(context);
        String MAC = getMacAddress(context);
        String SDCARD = getSimSerialNumber(context);
        if (!TextUtils.isEmpty(IMEI)) {
            return IMEI;
        } else {
            if (!TextUtils.isEmpty(MAC)) {
                return MAC;
            } else {
                return SDCARD;
            }
        }
    }

    /**
     * 获取IMEI
     * @return
     */
    public static String getIMEI(Context context) {
        Context sContext = context;
        TelephonyManager telephonyManager = (TelephonyManager) sContext.getSystemService(Context.TELEPHONY_SERVICE);
        String imei = telephonyManager.getDeviceId();
        return imei;
    }

    /**
     * 获取MAC
     * @return
     */
    public static String getMacAddress(Context context) {
        try {
            Context sContext = context;
            WifiManager wifi = (WifiManager) sContext.getSystemService(Context.WIFI_SERVICE);
            String macAddress = wifi.getConnectionInfo().getMacAddress();
            macAddress = macAddress.replace(":", "");
            return macAddress;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取SIM卡序列号
     *
     * @return
     */
    public static String getSimSerialNumber(Context context) {
        Context sContext = context;
        TelephonyManager telephonyManager = (TelephonyManager) sContext.getSystemService(Context.TELEPHONY_SERVICE);
        String simSerialNumber = telephonyManager.getSimSerialNumber();
        return simSerialNumber;
    }

    /**
     * 系统版本号
     * @return
     */
    public static String getSdkVersion() {
        return "" + android.os.Build.VERSION.SDK_INT;
    }

    /**
     * 设备型号
     * @return
     */
    public static String getDeviceName() {
        return android.os.Build.MODEL;
    }

}
