package com.example.moviesapp.module;

import android.content.Context;

import com.example.moviesapp.qualifier.ApplicationContext;
import com.example.moviesapp.scopes.ApplicationScope;


import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationScope
    @ApplicationContext
    public Context provideContext() {
        return context;
    }
}
