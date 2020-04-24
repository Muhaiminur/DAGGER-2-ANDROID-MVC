package com.abir.dagger_abir.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.abir.dagger_abir.HTTP.API_LINK;
import com.abir.dagger_abir.MODEL;
import com.abir.dagger_abir.R;
import com.abir.dagger_abir.databinding.FragmentHomeBinding;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeFragment extends DaggerFragment {

    private HomeViewModel homeViewModel;
    FragmentHomeBinding fragmentHomeBinding;

    @Inject
    API_LINK api_link;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        fragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                fragmentHomeBinding.homefragTest.setText(s);
            }
        });

        if (api_link == null) {
            Log.d("DAGGER", "API LINK  NULL");
        } else {
            Log.d("DAGGER", "API LINK NOT NULL");
            api_link.Dagger_USer(1)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(/*showProgressBar(true)*/disposable -> {
                        showProgressBar(true);
                    })
                    .doOnTerminate(() -> {
                        showProgressBar(false);
                    })
                    .subscribe(new io.reactivex.rxjava3.core.Observer<MODEL>() {
                        @Override
                        public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@io.reactivex.rxjava3.annotations.NonNull MODEL model) {
                            Log.d("RXJAVA NEXT", model.toString());
                            fragmentHomeBinding.homefragTest.setText(model.getEmail());
                        }

                        @Override
                        public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
        return fragmentHomeBinding.getRoot();
    }

    private void showProgressBar(boolean isVisible) {
        if (isVisible) {
            fragmentHomeBinding.homefragProgress.setVisibility(View.VISIBLE);
        } else {
            fragmentHomeBinding.homefragProgress.setVisibility(View.GONE);
        }
    }
}
