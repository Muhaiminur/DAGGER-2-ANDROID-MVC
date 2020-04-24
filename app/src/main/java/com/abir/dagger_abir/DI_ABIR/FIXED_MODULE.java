package com.abir.dagger_abir.DI_ABIR;

import android.app.Application;

import com.abir.dagger_abir.HTTP.ApiClient;
import com.abir.dagger_abir.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.abir.dagger_abir.HTTP.ApiClient.BASE_URL;

@Module
public abstract class FIXED_MODULE {

    @Singleton
    @Provides
    static String justTest() {
        return "This is from dagger";
    }

    @Singleton
    @Provides
    static boolean getapp(Application application) {
        return application == null;
    }

    @Singleton
    @Provides
    static RequestOptions Glide_Request(Application application) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.error(R.drawable.ic_centerlogo);
        return requestOptions;
    }

    @Singleton
    @Provides
    static RequestManager Glide_Image(Application application, RequestOptions requestOptions) {
        return Glide.with(application).setDefaultRequestOptions(requestOptions);
    }


    @Singleton
    @Provides
    static Retrofit getBaseClient() {

        Retrofit retrofit = null;
        /*OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10000, TimeUnit.SECONDS)
                .readTimeout(10000, TimeUnit.SECONDS).build();
        retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                //.addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();*/
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
