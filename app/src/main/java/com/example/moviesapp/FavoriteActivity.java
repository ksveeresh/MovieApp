package com.example.moviesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.moviesapp.module.FavoritRecyclerViewAdapter;
import com.example.moviesapp.module.RecyclerViewAdapter;
import com.example.moviesapp.mvp.FavoritActivityPresenterImpl;
import com.example.moviesapp.mvp.FavoriteActivityContract;
import com.example.moviesapp.pojo.Search;

import java.util.ArrayList;

import javax.inject.Inject;

public class FavoriteActivity extends AppCompatActivity implements FavoriteActivityContract.View {

    FavoritActivityPresenterImpl presenter;

    private RecyclerView recyclerView;

    private ProgressBar progressBar;
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Favorite");
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        progressBar = findViewById(R.id.progressBar);
        presenter=new FavoritActivityPresenterImpl(this);
        presenter.loadData(this);
    }

    @Override
    public void showData(ArrayList<Search> data) {
        FavoritRecyclerViewAdapter favoritRecyclerViewAdapter = new FavoritRecyclerViewAdapter(this, data);
        recyclerView.setAdapter(favoritRecyclerViewAdapter);
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showComplete() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
