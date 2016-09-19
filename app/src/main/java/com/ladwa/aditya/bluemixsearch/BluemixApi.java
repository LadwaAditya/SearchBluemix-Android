package com.ladwa.aditya.bluemixsearch;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * A class that makes requests to our remote cloudant database
 * Created by Aditya on 18-Sep-16.
 */

public class BluemixApi {
    private static final String BASE_URL = "http://searchbluemix.mybluemix.net/";

    interface BlueMixService {

        @GET("api/search/{name}")
        Observable<Model> getUsers(@Path("name") String name);

    }

    public Observable<Model> getUsers(String name) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(new OkHttpClient.Builder().addInterceptor(logging).build())
                .build().create(BlueMixService.class).getUsers(name);

    }
}
