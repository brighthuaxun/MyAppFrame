package com.example.bowen.myappframe.util;

import android.view.View;

import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import rx.functions.Action1;

/**
 * Created by dongbowen on 2016/6/26.
 */
public class RxBindUtil {

    //防手抖
    public static void click(final View view, final RxBinding rxBinding) {
        RxView.clicks(view)
                .throttleFirst(600, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        rxBinding.onClickView(view.getId());
                    }
                });

    }


    public interface RxBinding {
        void onClickView(int resId);
    }

}
