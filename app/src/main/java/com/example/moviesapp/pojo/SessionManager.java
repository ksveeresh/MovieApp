package com.example.moviesapp.pojo;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SessionManager {
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "AndroidHivePref";



    // Constructor
    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);

    }

    public void createfavorite(ArrayList<Search> movieSavedData) {
        editor = pref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(movieSavedData);
        editor.putString("favorite", json);
        editor.commit();
    }
    public ArrayList<Search>  getfavorite(){
        Gson gson = new Gson();
        String json = pref.getString("favorite", "");
        Type listType = new TypeToken<ArrayList<Search>>(){}.getType();
        return gson.fromJson(json, listType);
    }

}
