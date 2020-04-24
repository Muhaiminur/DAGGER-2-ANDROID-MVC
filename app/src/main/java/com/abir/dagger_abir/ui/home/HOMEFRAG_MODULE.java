package com.abir.dagger_abir.ui.home;

import com.abir.dagger_abir.HTTP.API_LINK;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class HOMEFRAG_MODULE {
    @Provides
    static API_LINK user_api(Retrofit retrofit) {
        return retrofit.create(API_LINK.class);
    }
}
