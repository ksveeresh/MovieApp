package com.example.moviesapp;

import android.app.Activity;
import android.app.Application;

import com.example.moviesapp.component.ApplicationComponent;
import com.example.moviesapp.component.DaggerApplicationComponent;
import com.example.moviesapp.module.ContextModule;


public class MyApplication extends Application {

    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder().contextModule(new ContextModule(this)).build();
        applicationComponent.injectApplication(this);

    }

    public static MyApplication get(Activity activity){
        return (MyApplication) activity.getApplication();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}