package com.abir.dagger_abir.ui.slideshow;

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
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.abir.dagger_abir.ADAPTER.PostRecyclerAdapter;
import com.abir.dagger_abir.HTTP.POST_LINK;
import com.abir.dagger_abir.MODEL;
import com.abir.dagger_abir.POST_MODEL;
import com.abir.dagger_abir.R;
import com.abir.dagger_abir.databinding.FragmentSlideshowBinding;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SlideshowFragment extends DaggerFragment {

    private SlideshowViewModel slideshowViewModel;
    FragmentSlideshowBinding fragmentSlideshowBinding;

    @Inject
    PostRecyclerAdapter postRecyclerAdapter;


    @Inject
    POST_LINK post_link;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel = ViewModelProviders.of(this).get(SlideshowViewModel.class);
        fragmentSlideshowBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_slideshow, container, false);
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        reccylerview();
        return fragmentSlideshowBinding.getRoot();
    }

    public void reccylerview() {
        /*fragmentSlideshowBinding.postRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        fragmentSlideshowBinding.postRecycler.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        fragmentSlideshowBinding.postRecycler.setAdapter(postRecyclerAdapter);*/
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        fragmentSlideshowBinding.postRecycler.setLayoutManager(mLayoutManager);
        // adding inbuilt divider line
        fragmentSlideshowBinding.postRecycler.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        // adding custom divider line with padding 16dp
        // recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
        fragmentSlideshowBinding.postRecycler.setItemAnimator(new DefaultItemAnimator());
        fragmentSlideshowBinding.postRecycler.setAdapter(postRecyclerAdapter);
        postfrom_network();
    }

    public void postfrom_network() {
        if (post_link == null) {
            Log.d("DAGGER", "Post LINK  NULL");
        } else {
            Log.d("DAGGER", "post LINK NOT NULL");
            if (postRecyclerAdapter == null) {
                Log.d("DAGGER", "ADAPTER NULL");
            } else {
                Log.d("DAGGER", "ADAPTER NOT NULL");
            }
            post_link.Dagger_POST()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(/*showProgressBar(true)*/disposable -> {
                        showProgressBar(true);
                    })
                    .doOnTerminate(() -> {
                        showProgressBar(false);
                    })
                    .subscribe(new io.reactivex.rxjava3.core.Observer<List<POST_MODEL>>() {
                        @Override
                        public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@io.reactivex.rxjava3.annotations.NonNull List<POST_MODEL> post_models) {
                            Log.d("RXJAVA NEXT", post_models.size() + "");
                            postRecyclerAdapter.setPosts(post_models);
                        }

                        @Override
                        public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }

    private void showProgressBar(boolean isVisible) {
        if (isVisible) {
            fragmentSlideshowBinding.slideProgress.setVisibility(View.VISIBLE);
        } else {
            fragmentSlideshowBinding.slideProgress.setVisibility(View.GONE);
        }
    }
}
