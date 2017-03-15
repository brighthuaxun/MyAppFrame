package com.example.bowen.myappframe.protocol;


import com.example.bowen.myappframe.protocol.bean.Contacts;
import com.example.bowen.myappframe.protocol.bean.Messages;
import com.example.bowen.myappframe.protocol.bean.User;

import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by dongbowen on 2016/6/26.
 */
public interface ApiClient {

    //上海测试环境
    public static final String HOST_HTTPS = "http://10.253.6.176:3080/za-papa/";
    public static final String HOST_HTTP = "http://10.253.6.176:3080/za-papa/";
    public static final String HOST_GROUP = "http://10.139.52.41:7080/";

    //POST请求
    @FormUrlEncoded
    @POST("users/login")
    Observable<User> login(@Field("mobile") String mobile, @Field("password") String password);

    //POST请求
    @FormUrlEncoded
    @POST("users/register")
    Observable<User> register(@FieldMap Map<String, String> map);

    // 获取个人信息，RESTFUL请求方式
    @GET("users/{userId}")
    Observable<User> getUserData(@Path("userId") String userId);

    // 获取消息列表
    @GET("users/getMessages")
    Observable<Messages> getMessageList(@Query("messageId") String messageId, @Query("pageSize") String pageSize);

    // 获取联系人列表
    @GET("users/getContacts/")
    Observable<Contacts> getContactList();

}
