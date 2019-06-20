package com.example.moviesapp.pojo;

import java.util.ArrayList;

public class SearchMovies {
    private String Response;

    private String totalResults;

    private ArrayList<Search> Search;

    public String getResponse ()
    {
        return Response;
    }

    public void setResponse (String Response)
    {
        this.Response = Response;
    }

    public String getTotalResults ()
    {
        return totalResults;
    }

    public void setTotalResults (String totalResults)
    {
        this.totalResults = totalResults;
    }

    public ArrayList<com.example.moviesapp.pojo.Search> getSearch() {
        return Search;
    }

    public void setSearch(ArrayList<com.example.moviesapp.pojo.Search> search) {
        Search = search;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Response = "+Response+", totalResults = "+totalResults+", Search = "+Search+"]";
    }
}
