package com.example.moviesapp.component;

import android.content.Context;

import com.example.moviesapp.MovieDisplayActivity;
import com.example.moviesapp.module.MovieDisplayActivityMvpModule;
import com.example.moviesapp.module.MovieDisplayAdapterModule;
import com.example.moviesapp.qualifier.ActivityContext;
import com.example.moviesapp.scopes.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = {MovieDisplayAdapterModule.class, MovieDisplayActivityMvpModule.class}, dependencies = ApplicationComponent.class)

public interface MovieDisplayActivityComponent {
    @ActivityContext
    Context getContext();
    void injectMovieViewActivity(MovieDisplayActivity movieDisplayActivity);
}
