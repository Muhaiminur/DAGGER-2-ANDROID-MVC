package com.abir.dagger_abir.FRAGMENT_INJECTOR;

import com.abir.dagger_abir.ui.home.HomeFragment;
import com.abir.dagger_abir.ui.slideshow.SlideshowFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ALLFRAGMENTMODULE {
    @ContributesAndroidInjector
    abstract HomeFragment contributeHomefragment();

    @ContributesAndroidInjector
    abstract SlideshowFragment contributeSlideshowFragment();
}
