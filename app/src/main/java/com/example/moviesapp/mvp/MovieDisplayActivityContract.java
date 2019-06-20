package com.example.moviesapp.mvp;

import android.content.Context;

import com.example.moviesapp.pojo.MovieData;

public interface MovieDisplayActivityContract {
    interface View {
        void showData(MovieData data);
        void showError(String message);
        void showComplete();
        void showProgress();
        void hideProgress();

    }

    interface Presenter {
        void loadData(String s);

    }
}
