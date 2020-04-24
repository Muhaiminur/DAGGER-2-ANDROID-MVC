package com.abir.dagger_abir.HTTP;

import com.abir.dagger_abir.MODEL;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface API_LINK {
    @GET("users/{id}")
    Observable<MODEL> Dagger_USer(@Path("id") int i);
}
