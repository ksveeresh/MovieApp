package com.example.moviesapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.moviesapp.component.ApplicationComponent;
import com.example.moviesapp.component.DaggerMovieActivityComponent;
import com.example.moviesapp.component.MovieActivityComponent;
import com.example.moviesapp.module.MovieActivityContextModule;
import com.example.moviesapp.module.MovieActivityMvpModule;
import com.example.moviesapp.module.RecyclerViewAdapter;
import com.example.moviesapp.mvp.MovieActivityContract;
import com.example.moviesapp.mvp.MovieActivityPresenterImpl;
import com.example.moviesapp.pojo.MovieSavedData;
import com.example.moviesapp.pojo.Search;
import com.example.moviesapp.pojo.SessionManager;
import com.example.moviesapp.qualifier.ActivityContext;
import com.example.moviesapp.qualifier.ApplicationContext;

import java.util.ArrayList;

import javax.inject.Inject;

public class MovieActivity extends AppCompatActivity  implements MovieActivityContract.View, RecyclerViewAdapter.ClickListener {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private String serchString="";
    MovieActivityComponent mainActivityComponent;

    @Inject
    public RecyclerViewAdapter recyclerViewAdapter;

    @Inject
    @ApplicationContext
    public Context mContext;

    @Inject
    @ActivityContext
    public Context activityContext;

    @Inject
    MovieActivityPresenterImpl presenter;
    private ProgressDialog dialog;
    private ArrayList<Search> movieSavedData;

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadData(serchString,this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        serchString="avengers";

        dialog = new ProgressDialog(this);
        dialog.setMessage("please wait..");

        ApplicationComponent applicationComponent = MyApplication.get(this).getApplicationComponent();
        mainActivityComponent = DaggerMovieActivityComponent.builder()
                .movieActivityContextModule(new MovieActivityContextModule(this))
                .movieActivityMvpModule(new MovieActivityMvpModule(this))
                .applicationComponent(applicationComponent)
                .build();

        mainActivityComponent.injectMainActivity(this);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(activityContext));
        recyclerView.setAdapter(recyclerViewAdapter);
        progressBar = findViewById(R.id.progressBar);
        presenter.loadData(serchString,this);
        movieSavedData =new ArrayList<Search>();
        int i = 0;
        SessionManager session = new SessionManager(this);
        if(session.getfavorite()!=null) {
            movieSavedData=session.getfavorite();
        }

    }
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        final MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView mSearchView = (SearchView) searchItem.getActionView();
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(final String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(final String newText) {
                if(newText.isEmpty()) {
                    Log.i("Typing", "empty");
                }else{
                    serchString=newText;
                    presenter.loadData(serchString,MovieActivity.this);
                }

                return false;
            }
        });
        final MenuItem favoriteItem = menu.findItem(R.id.action_favorite);
        favoriteItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent i = new Intent(MovieActivity.this, FavoriteActivity.class);
                startActivity(i);
                return false;
            }
        });
        return true;
    }
    @Override
    public void launchIntent(Search  search) {
        Intent i = new Intent(this, MovieDisplayActivity.class);
        i.putExtra("Movie_id",search.getImdbID());
        i.putExtra("Movie_name",search.getTitle());
        startActivity(i);
    }

    @Override
    public void ClickFavorite(Search search) {
       SessionManager session = new SessionManager(this);
            int i=0;
            if(movieSavedData != null) {
                for (Search Data : movieSavedData) {
                    if (Data.getImdbID().equals(search.getImdbID())) {
                        movieSavedData.remove(Data);
                        i++;
                        break;
                    }
                }
            }
        if(i==0){
            movieSavedData.add(search);
            session.createfavorite(movieSavedData);
        }else{
            session.createfavorite(movieSavedData);

        }

    }


    @Override
    public void showData(ArrayList<Search> data) {
        recyclerViewAdapter.setData(data);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showComplete() {

    }

    @Override
    public void showProgress() {
        dialog.show();

    }

    @Override
    public void hideProgress() {
        dialog.dismiss();
    }
}