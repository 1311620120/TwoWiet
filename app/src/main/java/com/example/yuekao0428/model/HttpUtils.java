package com.example.yuekao0428.model;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpUtils<T> {

    private final OkHttpClient okHttpClient;
    public final Api api;


    private HttpUtils(){
        okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new logginter())
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("http://172.17.8.100/")
                .build();
        api = retrofit.create(Api.class);

    }
       private     static  class  HttpUtilsgetIntants{
           private  static  HttpUtils httpUtils =new HttpUtils();
       }
       public    static  HttpUtils getIntants(){
           return  HttpUtilsgetIntants.httpUtils;
       }
       public class logginter implements Interceptor{

           @Override
           public Response intercept(Chain chain) throws IOException {
               Request request = chain.request();
               Request request1 = request.newBuilder()
                       .addHeader("usereId", "15565934174092498")
                       .addHeader("sesstion", "2498")
                       .build();
               Response proceed = chain.proceed(request1);
               return proceed;
           }
       }

 }
