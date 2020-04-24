package com.abir.dagger_abir;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.abir.dagger_abir.HTTP.API_LINK;
import com.abir.dagger_abir.HTTP.AuthResource;
import com.abir.dagger_abir.databinding.ActivityMainBinding;
import com.bumptech.glide.RequestManager;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends DaggerAppCompatActivity {

    @Inject
    String justcheck;

    @Inject
    boolean getapp;

    @Inject
    RequestManager home_logo;

    ActivityMainBinding activityMainBinding;

    @Inject
    API_LINK api_link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.homePageTest.setText(justcheck + getapp);
        home_logo.load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSJcKCm_TRdUArpbPphu1ZAVlFvp4YtZteO7OR55wrHU7GFyI39wQ&s").into(activityMainBinding.homePageImage);

        if (api_link == null) {
            Log.d("DAGGER", "API LINK  NULL");
        } else {
            Log.d("DAGGER", "API LINK NOT NULL");
            /*api_link.Dagger_USer(1).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread().(new Observer<MODEL>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {
                    Log.d("RXJAVA SUBSCRIBE",d.toString());
                }

                @Override
                public void onNext(@NonNull MODEL model) {
                    Log.d("RXJAVA NEXT",model.toString());
                    activityMainBinding.homePageTest.setText("BAIR");
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    Log.d("RXJAVA ERROR",e.toString());
                }

                @Override
                public void onComplete() {
                    Log.d("RXJAVA COMPLETE","COMPLETE");
                }
            });*/
            api_link.Dagger_USer(1)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(/*showProgressBar(true)*/disposable -> {
                        showProgressBar(true);
                    })
                    .doOnTerminate(() -> {
                        showProgressBar(false);
                    })
                    .subscribe(new Observer<MODEL>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull MODEL model) {
                            Log.d("RXJAVA NEXT", model.toString());
                            activityMainBinding.homePageTest.setText(model.getName());
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
        activityMainBinding.homePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
            }
        });
    }

    /*private void subscribeObservers() {
        api_link.Dagger_USer(1).observe(this, new Observer<AuthResource<MODEL>>() {
            @Override
            public void onChanged(AuthResource<MODEL> userAuthResource) {
                if (userAuthResource != null) {
                    switch (userAuthResource.status) {
                        case LOADING: {
                            showProgressBar(true);
                            break;
                        }

                        case AUTHENTICATED: {
                            showProgressBar(false);
                            Log.d(TAG, "onChanged: LOGIN SUCCESS: " + userAuthResource.data.getEmail());
                            break;
                        }

                        case ERROR: {
                            Log.e(TAG, "onChanged: " + userAuthResource.message);
                            showProgressBar(false);
                            Toast.makeText(MainActivity.this,
                                    userAuthResource.message + "\nDid you enter a number between 0 and 10?",
                                    Toast.LENGTH_SHORT)
                                    .show();
                            break;
                        }

                        case NOT_AUTHENTICATED: {
                            showProgressBar(false);
                            break;
                        }
                    }
                }
            }
        });
    }*/

    private void showProgressBar(boolean isVisible) {
        if (isVisible) {
            activityMainBinding.progressBar.setVisibility(View.VISIBLE);
        } else {
            activityMainBinding.progressBar.setVisibility(View.GONE);
        }
    }
}
