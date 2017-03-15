package com.example.bowen.myappframe.util;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by dongbowen on 2016/6/26.
 */
public class ZaLog {

	static boolean DEBUG = true;
	static final String TAG = "zaLog";

	public static void d(String msg){
		if (DEBUG){
			if(!TextUtils.isEmpty(msg)) {
				Log.d(TAG, msg);
			}
		}
	}

	public static void v(String msg){
		if (DEBUG){
			if(!TextUtils.isEmpty(msg)) {
				Log.d(TAG, msg);
			}
		}
	}

	public static void e(String msg){
		if(!TextUtils.isEmpty(msg)) {
			Log.d(TAG, msg);
		}
	}

	public static void i(String msg){
		if (DEBUG){
			if(!TextUtils.isEmpty(msg)) {
				Log.d(TAG, msg);
			}
		}
	}

	public static void d(String tag, String msg){
		if (DEBUG){
			if(!TextUtils.isEmpty(msg)) {
				Log.d(tag, msg);
			}
		}
	}

	public static void v(String tag, String msg){
		if (DEBUG){
			if(!TextUtils.isEmpty(msg)) {
				Log.d(tag, msg);
			}
		}
	}

	public static void e(String tag, String msg){
		if(!TextUtils.isEmpty(msg)) {
			Log.d(tag, msg);
		}
	}

	public static void i(String tag, String msg){
		if (DEBUG){
			if(!TextUtils.isEmpty(msg)) {
				Log.d(tag, msg);
			}
		}
	}

}
