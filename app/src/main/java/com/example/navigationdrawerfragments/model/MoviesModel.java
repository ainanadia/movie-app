package com.example.navigationdrawerfragments.model;

public class MoviesModel {

    int image;
    String movieName;
    String movieYear;

    public MoviesModel(int image, String movieName, String movieYear) {
        this.image = image;
        this.movieName = movieName;
        this.movieYear = movieYear;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieYear() {
        return movieYear;
    }

    public void setMovieYear(String movieYear) {
        this.movieYear = movieYear;
    }
}
