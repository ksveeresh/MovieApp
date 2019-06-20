package com.example.moviesapp.mvp;

import android.content.Context;

import com.example.moviesapp.pojo.MovieSavedData;

import com.example.moviesapp.pojo.Search;
import com.example.moviesapp.pojo.SearchMovies;
import com.example.moviesapp.pojo.SessionManager;
import com.example.moviesapp.retrofit.APIInterface;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MovieActivityPresenterImpl implements MovieActivityContract.Presenter  {
    APIInterface apiInterface;
    MovieActivityContract.View mView;


    @Inject
    public MovieActivityPresenterImpl(APIInterface apiInterface, MovieActivityContract.View mView) {
        this.apiInterface = apiInterface;
        this.mView = mView;
    }

    @Override
    public void loadData(String val, final Context context) {
        mView.showProgress();

        apiInterface.getData(val,"9026ba79").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SearchMovies>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SearchMovies value) {
                        if(value.getResponse().equals("False")){
                            Search search = new Search();
                            search.setTitle("No Movies Found");
                            ArrayList<Search> searches=new ArrayList<>();
                            searches.add(search);
                            mView.showData(searches);

                        }else{
                            SessionManager session = new SessionManager(context);
                            ArrayList<Search> list=new ArrayList<>();
                            for(Search apidata:value.getSearch()){
                                int i=0;
                                if(session.getfavorite()!=null) {
                                    for (Search SavedData : session.getfavorite()) {
                                        if (apidata.getImdbID().equals(SavedData.getImdbID())) {
                                            apidata.setFlag(1);
                                            list.add(apidata);
                                            i++;
                                            break;
                                        }
                                    }
                                }
                                if(i==0) {
                                    apidata.setFlag(0);
                                    list.add(apidata);
                                }
                            }
                            mView.showData(list);

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
