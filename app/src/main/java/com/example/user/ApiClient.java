package com.example.user;

import android.os.Build;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static ApiClient mInstance;
    private Retrofit retrofit;
    private String url;


    private ApiClient() {

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(40, TimeUnit.SECONDS)
                .build();


        url = "http://192.168.1.104/narm_kochakian/";
        retrofit = new Builder().client(okHttpClient).baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static synchronized ApiClient getInstance() {
        if (mInstance == null) {
            mInstance = new ApiClient();
        }
        return mInstance;
    }

    public ApiInterface getApi() {
        return retrofit.create(ApiInterface.class);
    }

}
