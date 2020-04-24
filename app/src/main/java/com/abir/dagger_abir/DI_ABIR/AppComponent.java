package com.abir.dagger_abir.DI_ABIR;

import android.app.Application;

import com.abir.dagger_abir.BASEAPPLICATION.DAGGER_ABIR;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                ActivityModule.class,
                FIXED_MODULE.class
        }
)
public interface AppComponent extends AndroidInjector<DAGGER_ABIR> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder Application(Application application);

        AppComponent build();
    }
}
