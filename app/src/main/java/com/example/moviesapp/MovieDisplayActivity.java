package com.example.moviesapp;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moviesapp.component.ApplicationComponent;
import com.example.moviesapp.component.DaggerMovieDisplayActivityComponent;
import com.example.moviesapp.component.MovieDisplayActivityComponent;
import com.example.moviesapp.module.MovieDisplayActivityContextModule;
import com.example.moviesapp.module.MovieDisplayActivityMvpModule;
import com.example.moviesapp.mvp.MovieDisplayActivityContract;
import com.example.moviesapp.mvp.MovieDisplayActivityPresenterImpl;
import com.example.moviesapp.pojo.MovieData;
import com.example.moviesapp.pojo.MovieSavedData;
import com.example.moviesapp.pojo.Ratings;
import com.example.moviesapp.pojo.SessionManager;
import com.example.moviesapp.qualifier.ActivityContext;
import com.example.moviesapp.qualifier.ApplicationContext;

import java.util.ArrayList;

import javax.inject.Inject;

public class MovieDisplayActivity extends AppCompatActivity implements MovieDisplayActivityContract.View {
    @Inject
    @ApplicationContext
    public Context mContext;

    @Inject
    @ActivityContext
    public Context activityContext;

    @Inject
    MovieDisplayActivityPresenterImpl presenter;


    private MovieDisplayActivityComponent mainActivityComponent;
    private ProgressDialog dialog;
    private ImageView mHeader;
    private Toolbar toolbar;
    private TextView mYear,mDirector,mWriter,mDescrpition;
    private TextView mRated;
    private TextView mSource;
    private TextView mSource1;
    private TextView mSource2;
    private LinearLayout mLinerlayout;
    private String mMovie_id;
    private Menu menus;
    ArrayList<MovieSavedData> movieSavedData;
    private MenuItem nav_favorite;
    private LinearLayout mRatingslayout;
    private MovieData mSelectedData;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_display);
        movieSavedData=new ArrayList<>();

        Intent intent = getIntent();
        mMovie_id=intent.getExtras().getString("Movie_id");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mYear=(TextView)findViewById(R.id.textYearView);
        mDirector=(TextView)findViewById(R.id.textdirectorView);
        mWriter=(TextView)findViewById(R.id.textwriterView);
        mDescrpition=(TextView)findViewById(R.id.textDescrpitionView);
        mRated=(TextView)findViewById(R.id.textratedrView);
        mHeader=(ImageView)findViewById(R.id.header);
        mLinerlayout=(LinearLayout)findViewById(R.id.mlayout);
        mRatingslayout=(LinearLayout)findViewById(R.id.Ratings);
        dialog = new ProgressDialog(this);
        dialog.setMessage("please wait..");
        getSupportActionBar().setTitle(intent.getExtras().getString("Movie_name"));
        ApplicationComponent applicationComponent = MyApplication.get(this).getApplicationComponent();
        mainActivityComponent= DaggerMovieDisplayActivityComponent.builder()
                .movieDisplayActivityContextModule(new MovieDisplayActivityContextModule(this))
                .movieDisplayActivityMvpModule(new MovieDisplayActivityMvpModule(this))
                .applicationComponent(applicationComponent)
                .build();


        mainActivityComponent.injectMovieViewActivity(this);
        presenter.loadData(mMovie_id);
    }


    @SuppressLint("ResourceType")
    @Override
    public void showData(MovieData data) {
        mSelectedData=data;
        Glide.with(this).load(data.getPoster()).placeholder(R.drawable.collapsed_image_background).into(mHeader);
        mYear.setText(data.getReleased());
        mDirector.setText(data.getDirector());
        mWriter.setText(data.getWriter());
        mDescrpition.setText(data.getPlot());
        mRated.setText(data.getRated());
        Ratings[] mRateing = data.getRatings();
        for(Ratings val:mRateing){

            SpannableStringBuilder builder = new SpannableStringBuilder();

            SpannableString redSpannable = new SpannableString(val.getSource());
            StyleSpan bss = new StyleSpan(Typeface.BOLD);
            redSpannable.setSpan(new ForegroundColorSpan(Color.WHITE), 0, redSpannable.length(), 0);
            redSpannable.setSpan(bss, 0, redSpannable.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            redSpannable.setSpan(new RelativeSizeSpan(  1.3f), 0,redSpannable.length(), 0);
            builder.append(redSpannable);

            SpannableString Typevalue = new SpannableString("  ");
            builder.append(Typevalue);

            SpannableString redSpannable1 = new SpannableString(val.getValue());
            StyleSpan bss1 = new StyleSpan(Typeface.BOLD);
            redSpannable1.setSpan(new ForegroundColorSpan(Color.WHITE), 0, redSpannable1.length(), 0);
            redSpannable1.setSpan(bss1, 0, redSpannable1.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            builder.append(redSpannable1);


            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    DrawerLayout.LayoutParams.WRAP_CONTENT, DrawerLayout.LayoutParams.WRAP_CONTENT );
            final TextView rowTextView = new TextView(this);
            rowTextView.setLayoutParams(params);
            rowTextView.setText(builder);
            rowTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_rating, 0);
            rowTextView.setTextColor(Color.WHITE);
            mRatingslayout.addView(rowTextView);
        }
        String[] genre = data.getGenre().split(",");
        for(String val:genre){
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    DrawerLayout.LayoutParams.WRAP_CONTENT, 100);
            params.setMargins(10, 2, 10, 0);
            final TextView rowTextView = new TextView(this);
            rowTextView.setPadding(30, 30, 30, 30);
            rowTextView.setLayoutParams(params);
            rowTextView.setTextSize(10);
            rowTextView.setText(val);
            rowTextView.setTextColor(Color.WHITE);
            rowTextView.setBackgroundResource(R.drawable.round);
            mLinerlayout.addView(rowTextView);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        final MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.favorites_menu, menu);
        nav_favorite = menu.findItem(R.id.nav_favorite);

        nav_favorite.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {


            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                String shareBody = mSelectedData.getTitle()+"\n"+mSelectedData.getYear()+"\n"+mSelectedData.getWebsite();
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Movie Details");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.share_using)));
                return false;
            }
        });
        return true;
    }
    @Override
    public void showError(String message) {

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

