package com.example.movieapp;

import android.util.Log;

import com.example.movieapp.database.entaties.Movie;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Test {

    List<Movie> movieList = new ArrayList<>();
    public void sortListByOrder(){
        Collections.sort(movieList, new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    long date1 = formatter.parse(o1.getRelease_date()).getTime();
                    long date2 = formatter.parse(o2.getRelease_date()).getTime();
                    return date1 > date2 ? 1 : date1 < date2 ? -1 : 0;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return 1;
            }
        });

    }
}
