package com.abir.dagger_abir.HTTP;

import com.abir.dagger_abir.POST_MODEL;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface POST_LINK {
    @GET("posts")
    Observable<List<POST_MODEL>> Dagger_POST();
}
