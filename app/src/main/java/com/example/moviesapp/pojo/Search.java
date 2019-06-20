package com.example.moviesapp.pojo;

public class Search {
    private String Type;

    private String Year;

    private String imdbID;

    private String Poster;

    private String Title;
    private int flag;
    public String getType ()
    {
        return Type;
    }

    public void setType (String Type)
    {
        this.Type = Type;
    }

    public String getYear ()
    {
        return Year;
    }

    public void setYear (String Year)
    {
        this.Year = Year;
    }

    public String getImdbID ()
    {
        return imdbID;
    }

    public void setImdbID (String imdbID)
    {
        this.imdbID = imdbID;
    }

    public String getPoster ()
    {
        return Poster;
    }

    public void setPoster (String Poster)
    {
        this.Poster = Poster;
    }

    public String getTitle ()
    {
        return Title;
    }

    public void setTitle (String Title)
    {
        this.Title = Title;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Type = "+Type+", Year = "+Year+", imdbID = "+imdbID+", Poster = "+Poster+", Title = "+Title+"]";
    }
}
