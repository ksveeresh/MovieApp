package com.example.moviesapp.module;

import com.example.moviesapp.mvp.MovieActivityContract;
import com.example.moviesapp.scopes.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieActivityMvpModule {
    private final MovieActivityContract.View mView;
    public MovieActivityMvpModule(MovieActivityContract.View mView) {
        this.mView = mView;
    }

    @Provides
    @ActivityScope
    MovieActivityContract.View provideView() {
        return mView;
    }
}
