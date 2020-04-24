package com.abir.dagger_abir.DI_ABIR;


import com.abir.dagger_abir.AuthModule;
import com.abir.dagger_abir.FRAGMENT_INJECTOR.ALLFRAGMENTMODULE;
import com.abir.dagger_abir.HTTP.API_LINK;
import com.abir.dagger_abir.HomeActivity;
import com.abir.dagger_abir.MainActivity;
import com.abir.dagger_abir.ui.home.HOMEFRAG_MODULE;
import com.abir.dagger_abir.ui.slideshow.SLIDEFRAG_MODULE;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {
    @ContributesAndroidInjector(modules = {AuthModule.class})
    abstract MainActivity contributeMainactivity();

    @ContributesAndroidInjector(modules = {ALLFRAGMENTMODULE.class, HOMEFRAG_MODULE.class, SLIDEFRAG_MODULE.class})
    abstract HomeActivity contributeHomeActivity();

    /*@Provides
    static String justTest(){
        return "This is from dagger";
    }*/
}
