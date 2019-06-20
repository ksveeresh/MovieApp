package com.example.moviesapp.mvp;

import android.content.Context;

import com.example.moviesapp.pojo.Search;

import java.util.ArrayList;

public interface FavoriteActivityContract {
    interface View {
        void showData(ArrayList<Search> data);
        void showError(String message);
        void showComplete();
        void showProgress();
        void hideProgress();
    }

    interface Presenter {
        void loadData(Context context);
    }
}
