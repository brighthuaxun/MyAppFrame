package com.example.bowen.myappframe.protocol.network;

import rx.Subscriber;

/**
 * Created by dongbowen on 2016/6/26.
 */
public class ZaSubscriber<T> extends Subscriber<T> {

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCompleted() { }

    @Override
    public void onNext(T t) { }

    @Override
    public void onError(Throwable e) { }

}
