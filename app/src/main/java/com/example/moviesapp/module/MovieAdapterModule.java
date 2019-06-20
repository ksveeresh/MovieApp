package com.example.moviesapp.module;

import com.example.moviesapp.MovieActivity;
import com.example.moviesapp.scopes.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module(includes = {MovieActivityContextModule.class})
public class MovieAdapterModule {
    @Provides
    @ActivityScope
    public RecyclerViewAdapter getCoinList(RecyclerViewAdapter.ClickListener clickListener, MovieActivity movieActivity) {
        return new RecyclerViewAdapter(clickListener,movieActivity);
    }

    @Provides
    @ActivityScope
    public RecyclerViewAdapter.ClickListener getClickListener(MovieActivity movieActivity) {
        return movieActivity;
    }
}
