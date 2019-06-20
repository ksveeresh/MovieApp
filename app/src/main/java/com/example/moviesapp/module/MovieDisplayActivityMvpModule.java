package com.example.moviesapp.module;

import com.example.moviesapp.mvp.MovieDisplayActivityContract;
import com.example.moviesapp.scopes.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieDisplayActivityMvpModule {
    public final MovieDisplayActivityContract.View mView;
    public MovieDisplayActivityMvpModule(MovieDisplayActivityContract.View mView) {
        this.mView = mView;
    }

    @Provides
    @ActivityScope
    MovieDisplayActivityContract.View provideView() {
        return mView;
    }
}
