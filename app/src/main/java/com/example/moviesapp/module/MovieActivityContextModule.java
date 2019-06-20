package com.example.moviesapp.module;

import android.content.Context;

import com.example.moviesapp.MovieActivity;
import com.example.moviesapp.qualifier.ActivityContext;
import com.example.moviesapp.scopes.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieActivityContextModule {
    private MovieActivity movieActivity;
    public Context context;

    public MovieActivityContextModule(MovieActivity movieActivity) {
        this.movieActivity = movieActivity;
        this.context = movieActivity;
    }
    @Provides
    @ActivityScope
    public MovieActivity providesMainActivity() {
        return movieActivity;
    }

    @Provides
    @ActivityScope
    @ActivityContext
    public Context provideContext() {
        return context;
    }
}
