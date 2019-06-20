package com.example.moviesapp.pojo;

public class MovieSavedData {
    String MoiveId;

    public String getMoiveId() {
        return MoiveId;
    }

    public void setMoiveId(String moiveId) {
        MoiveId = moiveId;
    }

    public MovieSavedData(String moiveId) {
        MoiveId = moiveId;
    }

    @Override
    public String toString() {
        return "MovieSavedData{" +
                "MoiveId='" + MoiveId + '\'' +
                '}';
    }
}
