package com.abir.dagger_abir;

import com.abir.dagger_abir.HTTP.API_LINK;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class AuthModule {

    @Provides
    static API_LINK apiLink(Retrofit retrofit){
        return retrofit.create(API_LINK.class);
    }
}
