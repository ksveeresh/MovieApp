package com.example.moviesapp.mvp;

import android.content.Context;

import com.example.moviesapp.pojo.Search;
import com.example.moviesapp.pojo.SessionManager;

import java.util.ArrayList;

public class FavoritActivityPresenterImpl implements FavoriteActivityContract.Presenter{
    FavoriteActivityContract.View view;

    public FavoritActivityPresenterImpl(FavoriteActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void loadData(Context context) {
        SessionManager session = new SessionManager(context);
        if(session.getfavorite()!=null) {
            view.showData(session.getfavorite());
        }
    }
}
