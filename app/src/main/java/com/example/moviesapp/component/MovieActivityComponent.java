package com.example.moviesapp.component;

import android.content.Context;

import com.example.moviesapp.MovieActivity;
import com.example.moviesapp.module.MovieActivityMvpModule;
import com.example.moviesapp.module.MovieAdapterModule;
import com.example.moviesapp.qualifier.ActivityContext;
import com.example.moviesapp.scopes.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = {MovieAdapterModule.class, MovieActivityMvpModule.class}, dependencies = ApplicationComponent.class)

public interface MovieActivityComponent {
    @ActivityContext
    Context getContext();
    void injectMainActivity(MovieActivity  movieActivity);
}
