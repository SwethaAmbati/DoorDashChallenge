package com.doordash.rest;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by SwethaAmbati on 5/24/17.
 */

public class ApiClient {
    //root api
    private static final String BASE_URL = "https://api.doordash.com/v2/";

    public static ApiInterface getApi() {
        OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(new HeaderInterceptor()).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ApiInterface.class);
    }


}
