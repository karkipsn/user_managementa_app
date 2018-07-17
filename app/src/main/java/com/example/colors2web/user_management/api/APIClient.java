package com.example.colors2web.user_management.api;

import android.util.JsonReader;

import com.example.colors2web.user_management.POJO.LoginResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.StringReader;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class APIClient {

    private static Retrofit retrofit = null;

    public  static Retrofit getClient() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        OkHttpClient.Builder builder = new OkHttpClient.Builder().addInterceptor(interceptor);
        builder.connectTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES);

        OkHttpClient client = builder.build();


        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.104:8088/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        return retrofit;
    }

}
