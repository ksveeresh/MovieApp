package com.example.moviesapp.component;

import android.content.Context;

import com.example.moviesapp.MyApplication;
import com.example.moviesapp.module.ContextModule;
import com.example.moviesapp.module.RetrofitModule;
import com.example.moviesapp.qualifier.ApplicationContext;
import com.example.moviesapp.retrofit.APIInterface;
import com.example.moviesapp.scopes.ApplicationScope;

import dagger.Component;

@ApplicationScope
@Component(modules = {ContextModule.class, RetrofitModule.class})
public interface ApplicationComponent {
    APIInterface getApiInterface();

    @ApplicationContext
    Context getContext();

    void injectApplication(MyApplication myApplication);
}
