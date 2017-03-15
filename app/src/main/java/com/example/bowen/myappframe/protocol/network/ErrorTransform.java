package com.example.bowen.myappframe.protocol.network;

import android.util.Log;
import android.widget.Toast;

import com.example.bowen.myappframe.application.BaseApplication;

import java.util.ServiceConfigurationError;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by dongbowen on 2016/6/26.
 * 更优雅的转换Observable去统一处理错误
 */
public class ErrorTransform<T> implements Observable.Transformer<T, T>{

    private static final String TAG = "ErrorTransform";

    @Override
    public Observable<T> call(Observable<T> tObservable) {
        return tObservable.onErrorResumeNext(new Func1<Throwable, Observable<? extends T>>() {
            @Override
            public Observable<? extends T> call(Throwable throwable) {
                //判断异常是什么类型
                Log.i(TAG, throwable.getClass().getName() + " " + throwable.getLocalizedMessage() + " " + throwable.getMessage());
                String errorMessage = "";
                //通过状态码判断错误
                if (throwable instanceof HttpException) {
                    HttpException response = (HttpException) throwable;
                    switch (response.code()){
                        case 404:
                            errorMessage = "token无效"+response.message();
                            break;
                        case 402:
                            errorMessage = "数据库连接错误"+response.message();
                            break;
                        case 403:
                            errorMessage = "无记录"+response.message();
                            break;
                        case 400:
                            errorMessage = "参数为空"+response.message();
                            break;
                        default:
                            errorMessage = "未知错误"+response.message();
                            break;
                    }
                }else if (throwable instanceof ServiceConfigurationError){
                    errorMessage = "服务器错误";
                } else {
                    errorMessage = "网络错误";
                }
                Toast.makeText(BaseApplication.getInstance(), errorMessage, Toast.LENGTH_SHORT).show();
                return Observable.empty();
            }
        });
    }
}
