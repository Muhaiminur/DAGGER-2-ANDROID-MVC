package com.abir.dagger_abir.BASEAPPLICATION;

import com.abir.dagger_abir.DI_ABIR.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class DAGGER_ABIR extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().Application(this).build();
    }
}
