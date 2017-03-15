package com.example.bowen.myappframe.protocol;

import com.example.bowen.myappframe.protocol.bean.Contacts;
import com.example.bowen.myappframe.protocol.bean.Messages;
import com.example.bowen.myappframe.protocol.bean.User;
import com.example.bowen.myappframe.protocol.network.ApiFactory;
import com.example.bowen.myappframe.protocol.network.ErrorTransform;
import com.example.bowen.myappframe.protocol.network.SchedulerTransform;

import java.util.Map;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

/**
 * Created by dongbowen on 2016/6/26.
 * 使用rx进行异步处理
 */
public class NetRequest {

    private static ApiClient apiClient = ApiFactory.getApiClientInstance();

    public static void login(String mobile, String password, Subscriber<User> subscriber) {
        setSubscribe(apiClient.login(mobile, password), subscriber);
    }

    public static void register(Map<String, String> map, Subscriber<User> subscriber) {
        setSubscribe(apiClient.register(map), subscriber);
    }

    public static void getUserData(String userId, Subscriber<User> subscriber) {
        setSubscribe(apiClient.getUserData(userId), subscriber);
    }

    public static void getMessageList(String messageId, String pageSize, Subscriber<Messages> subscriber) {
        setSubscribe(apiClient.getMessageList(messageId, pageSize), subscriber);
    }

    public static void getContacts(Subscriber<Contacts> subscriber) {
        setSubscribe(apiClient.getContactList(), subscriber);
    }

    /**
     * 设置观察者
     * @param observable
     * @param observer
     * @param <T>
     */
    public static <T> void setSubscribe(Observable<T> observable, Observer<T> observer) {
        observable
//                .subscribeOn(Schedulers.io()) //子线程访问网络
//                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .compose(new SchedulerTransform<T>())
                .compose(new ErrorTransform<T>())
                .subscribe(observer);
    }

}
