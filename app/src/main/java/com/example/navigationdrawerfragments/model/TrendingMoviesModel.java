package com.example.navigationdrawerfragments.model;

public class TrendingMoviesModel {

    int movieId;
    String movieTitle;
    int movieYear;
    String movieClass;
    String movieImage;
    String movieSynopsis;


    public TrendingMoviesModel(int movieId, String movieTitle, int movieYear, String movieClass, String movieImage, String movieSynopsis) {
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.movieYear = movieYear;
        this.movieClass = movieClass;
        this.movieImage = movieImage;
        this.movieSynopsis = movieSynopsis;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public int getMovieYear() {
        return movieYear;
    }

    public void setMovieYear(int movieYear) {
        this.movieYear = movieYear;
    }

    public String getMovieClass() {
        return movieClass;
    }

    public void setMovieClass(String movieClass) {
        this.movieClass = movieClass;
    }

    public String getMovieImage() {
        return movieImage;
    }

    public void setMovieImage(String movieImage) {
        this.movieImage = movieImage;
    }

    public String getMovieSynopsis() {
        return movieSynopsis;
    }

    public void setMovieSynopsis(String movieSynopsis) {
        this.movieSynopsis = movieSynopsis;
    }
}
