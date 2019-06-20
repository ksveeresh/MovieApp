package com.example.moviesapp.module;

import android.content.Context;

import com.example.moviesapp.MovieDisplayActivity;
import com.example.moviesapp.qualifier.ActivityContext;
import com.example.moviesapp.scopes.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieDisplayActivityContextModule {
    MovieDisplayActivity movieViewActivity;
    public Context context;

    public MovieDisplayActivityContextModule(MovieDisplayActivity movieViewActivity) {
        this.movieViewActivity = movieViewActivity;
        this.context = movieViewActivity;
    }
    @Provides
    @ActivityScope
    public MovieDisplayActivity providesMainActivity() {
        return movieViewActivity;
    }

    @Provides
    @ActivityScope
    @ActivityContext
    public Context provideContext() {
        return context;
    }
}
