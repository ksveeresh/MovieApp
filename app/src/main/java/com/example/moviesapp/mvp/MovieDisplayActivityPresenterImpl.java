package com.example.moviesapp.mvp;

import android.content.Context;

import com.example.moviesapp.pojo.MovieData;
import com.example.moviesapp.pojo.MovieSavedData;
import com.example.moviesapp.pojo.SessionManager;
import com.example.moviesapp.retrofit.APIInterface;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MovieDisplayActivityPresenterImpl implements MovieDisplayActivityContract.Presenter {
    APIInterface apiInterface;
    MovieDisplayActivityContract.View mView;
    @Inject
    public MovieDisplayActivityPresenterImpl(APIInterface apiInterface, MovieDisplayActivityContract.View mView) {
        this.apiInterface = apiInterface;
        this.mView = mView;
    }

    @Override
    public void loadData(String s) {
        mView.showProgress();
        apiInterface.getIdData(s,"9026ba79").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieData value) {
                        if(value.getResponse().equals("False")){
                            MovieData movieData = new MovieData();
                            movieData.setTitle("No Movies Found");
                            mView.showData(value);

                        }else{
                            mView.showData(value);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError("Error occurred");
                        mView.hideProgress();
                    }

                    @Override
                    public void onComplete() {
                        mView.showComplete();
                        mView.hideProgress();
                    }
                });
    }


}
