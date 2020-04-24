package com.abir.dagger_abir.HTTP;

import java.util.concurrent.TimeUnit;
public class ApiClient {

    //public static final String BASE_URL = "http://192.168.123.230:9090/";
    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    /*private static Retrofit retrofit = null;


    public static Retrofit getBaseClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10000, TimeUnit.SECONDS)
                .readTimeout(10000, TimeUnit.SECONDS).build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                //.addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }*/

}
