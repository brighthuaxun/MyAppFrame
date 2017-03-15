package com.example.bowen.myappframe.util;

import android.preference.PreferenceManager;

import com.example.bowen.myappframe.application.BaseApplication;


/**
 * Created by dongbowen on 2016/6/26.
 */
public class SpUtil {

    public static void putString(String key, String value) {
        PreferenceManager.getDefaultSharedPreferences(BaseApplication.getInstance())
                .edit().putString(key, value).apply();
    }

    public static void putBoolean(String key, boolean value) {
        PreferenceManager.getDefaultSharedPreferences(BaseApplication.getInstance())
                .edit().putBoolean(key, value).apply();
    }

    public static void putInt(String key, int value) {
        PreferenceManager.getDefaultSharedPreferences(BaseApplication.getInstance())
                .edit().putInt(key, value).apply();
    }

    public static void putFloat(String key, float value) {
        PreferenceManager.getDefaultSharedPreferences(BaseApplication.getInstance())
                .edit().putFloat(key, value).apply();
    }

    public static void putLong(String key, long value) {
        PreferenceManager.getDefaultSharedPreferences(BaseApplication.getInstance())
                .edit().putLong(key, value).apply();
    }

    public static String getString(String key) {
        return PreferenceManager.getDefaultSharedPreferences(BaseApplication.getInstance())
                .getString(key, null);
    }

    public static String getString(String key, String defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(BaseApplication.getInstance())
                .getString(key, defaultValue);
    }

    public static Boolean getBoolean(String key) {
        return PreferenceManager.getDefaultSharedPreferences(BaseApplication.getInstance())
                .getBoolean(key, false);
    }

    public static Boolean getBoolean(String key, boolean defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(BaseApplication.getInstance())
                .getBoolean(key, defaultValue);
    }

    public static Integer getInt(String key) {
        return PreferenceManager.getDefaultSharedPreferences(BaseApplication.getInstance())
                .getInt(key, -1);
    }

    public static Integer getInt(String key, int defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(BaseApplication.getInstance())
                .getInt(key, defaultValue);
    }

    public static Float getFloat(String key) {
        return PreferenceManager.getDefaultSharedPreferences(BaseApplication.getInstance())
                .getFloat(key, -1F);
    }

    public static Float getFloat(String key, float defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(BaseApplication.getInstance())
                .getFloat(key, defaultValue);
    }

    public static Long getLong(String key) {
        return PreferenceManager.getDefaultSharedPreferences(BaseApplication.getInstance())
                .getLong(key, -1L);
    }

    public static Long getLong(String key, long defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(BaseApplication.getInstance())
                .getLong(key, defaultValue);
    }

    public static void remove(String key) {
        PreferenceManager.getDefaultSharedPreferences(BaseApplication.getInstance())
                .edit().remove(key).apply();
    }

    public static void removeAll() {
        PreferenceManager.getDefaultSharedPreferences(BaseApplication.getInstance())
                .edit().clear().apply();
    }

}
