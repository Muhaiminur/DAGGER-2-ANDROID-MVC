package com.abir.dagger_abir.ui.slideshow;

import com.abir.dagger_abir.ADAPTER.PostRecyclerAdapter;
import com.abir.dagger_abir.HTTP.POST_LINK;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class SLIDEFRAG_MODULE {
    @Provides
    static POST_LINK user_api(Retrofit retrofit) {
        return retrofit.create(POST_LINK.class);
    }

    @Provides
    static PostRecyclerAdapter recyclerAdapter_post() {
        return new PostRecyclerAdapter();
    }
}
