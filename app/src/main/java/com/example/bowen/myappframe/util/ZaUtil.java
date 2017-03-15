package com.example.bowen.myappframe.util;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by dongbowen on 2016/6/26.
 */
public class ZaUtil {

    /**
     * 获得文件存储的绝对路径
     * @param context
     * @param dirName       可传 images,voices,errorLogs
     * @return
     */
    public static String getSavePath(Context context, String dirName){
        String path;
        if (android.os.Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            //有SD卡
            path = Environment.getExternalStorageDirectory()
                    + File.separator
                    + "Android"
                    + File.separator
                    + "Data"
                    + File.separator
                    + context.getPackageName()
                    + File.separator
                    + dirName
                    + File.separator;
        } else {
            //无SD卡
            path = context.getFilesDir().getAbsolutePath() + File.separator + dirName + File.separator;
        }
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        return path;
    }

}
