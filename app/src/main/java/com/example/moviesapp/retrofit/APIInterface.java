package com.example.moviesapp.retrofit;

import com.example.moviesapp.pojo.MovieData;
import com.example.moviesapp.pojo.SearchMovies;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {
//
//    @GET("ticker/?")
//    Observable<List<CryptoData>> getData(@Query("limit") String limit);


    @GET("?")
    Observable<SearchMovies> getData(@Query("s") String SerchString, @Query("apikey") String apikey);

    @GET("?")
    Observable<MovieData> getIdData(@Query("i") String SerchString, @Query("apikey") String apikey);
}
