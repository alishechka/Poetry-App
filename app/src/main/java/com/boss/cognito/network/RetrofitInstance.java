package com.boss.cognito.network;

import com.boss.cognito.App;
import com.readystatesoftware.chuck.ChuckInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.boss.cognito.common.Constants.BASE_URL;

public class RetrofitInstance {
    private static OkHttpClient okHttpClient = new OkHttpClient.Builder().
            addInterceptor(new ChuckInterceptor(App.getAppContext()))
            .build();

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    public static PoemClient service = retrofit.create(PoemClient.class);

}