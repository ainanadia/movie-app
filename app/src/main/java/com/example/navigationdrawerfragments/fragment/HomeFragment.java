package com.example.navigationdrawerfragments.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;

import com.example.navigationdrawerfragments.R;
import com.example.navigationdrawerfragments.activity.trendingmovies.TrendingMoviesActivity;
import com.example.navigationdrawerfragments.adapter.ItemTrendingMoviesAdapter;
import com.example.navigationdrawerfragments.adapter.ItemTrendingSeriesAdapter;
import com.example.navigationdrawerfragments.model.TrendingMoviesModel;
import com.example.navigationdrawerfragments.model.TrendingSeriesModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "test trending movies" ;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    RecyclerView recyclerViewTrendingSeries;
    RecyclerView recyclerViewTrendingMovies;
    RecyclerView recyclerViewPopularMovies;
    SwipeRefreshLayout swipeRefreshLayout;
    SearchView searchView;
    Button buttonLoad;

    List<TrendingSeriesModel> itemListTrendingSeries;
    List<TrendingMoviesModel> itemListTrendingMovies;

    ItemTrendingMoviesAdapter itemTrendingMoviesAdapter;
    ItemTrendingSeriesAdapter itemTrendingSeriesAdapter;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerViewTrendingMovies = view.findViewById(R.id.recyclerViewTrendingMovies);
        buttonLoad = view.findViewById(R.id.buttonLoad);
        swipeRefreshLayout = view.findViewById(R.id.refreshLayout);
        recyclerViewTrendingMovies.setHasFixedSize(true);
        recyclerViewTrendingMovies.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        buttonLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), TrendingMoviesActivity.class);
                startActivity(intent);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                itemListTrendingMovies.clear();
                itemListTrendingSeries.clear();
                load_data_from_server(0);
                load_series_from_server(0);

            }
        });

        recyclerViewTrendingSeries = view.findViewById(R.id.recyclerViewTrendingSeries);
        recyclerViewTrendingSeries.setHasFixedSize(true);
        recyclerViewTrendingSeries.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        recyclerViewPopularMovies = view.findViewById(R.id.recyclerViewPopularSeries);
        recyclerViewPopularMovies.setHasFixedSize(true);
        recyclerViewPopularMovies.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        //initData();

        itemListTrendingMovies  = new ArrayList<>();
        load_data_from_server(0);

        itemTrendingMoviesAdapter = new ItemTrendingMoviesAdapter(getContext(),itemListTrendingMovies );
        recyclerViewTrendingMovies.setAdapter(itemTrendingMoviesAdapter);

        itemListTrendingSeries  = new ArrayList<>();
        load_series_from_server(0);

        itemTrendingSeriesAdapter = new ItemTrendingSeriesAdapter(getContext(),itemListTrendingSeries );
        recyclerViewTrendingSeries.setAdapter(itemTrendingSeriesAdapter);

        recyclerViewPopularMovies.setAdapter(itemTrendingMoviesAdapter);

        return view;
    }

    private void load_data_from_server(int id) {

        AsyncTask<Integer,Void,Void> task = new AsyncTask<Integer,Void,Void>(){

            protected Void doInBackground(Integer... integers) {

                JSONObject jsonObject = new JSONObject();
                final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                RequestBody body = RequestBody.create(JSON, jsonObject.toString());
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .header("Content-Type", "application/json; charset=utf-8")
                        .url("http://192.168.0.190:8080/movie-restful/rest/MovieService/getmovieimage")
                        .post(body)
                        .build();

                Log.d(TAG, "doInBackground: request");
                try {
                    Response response = client.newCall(request).execute();
                    Log.d(TAG, "doInBackground: response");

                    //Log.d(TAG, "response: " + response.body().string());
                    String accept = response.body().string();
                    JSONObject jsnobject = new JSONObject(accept);
                    JSONArray array = jsnobject.getJSONArray("allMovies");

                    Log.d(TAG, "doInBackground: sampai sini");
                    for (int i=0; i<array.length(); i++){

                        JSONObject object = array.getJSONObject(i);
                        Log.d(TAG, "doInBackground: get json");
                        TrendingMoviesModel moviesModel = new TrendingMoviesModel(object.getInt("movieid"), object.getString("movietitle"),
                                object.getInt("movieyear"), object.getString("movieclass"), object.getString("movieimage"), object.getString("moviesynopsis"));


                        itemListTrendingMovies.add(moviesModel);
                        Log.d(TAG, "doInBackground: get json habis");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                itemTrendingMoviesAdapter.notifyDataSetChanged();
            }
        };

        task.execute(id);
    }

    private void load_series_from_server (int id) {

        AsyncTask<Integer,Void,Void> task = new AsyncTask<Integer,Void,Void>(){

            protected Void doInBackground(Integer... integers) {

                JSONObject jsonObject = new JSONObject();
                final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                RequestBody body = RequestBody.create(JSON, jsonObject.toString());
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .header("Content-Type", "application/json; charset=utf-8")
                        .url("http://192.168.0.190:8080/movie-restful/rest/SeriesService/getseriesimage")
                        .post(body)
                        .build();

                Log.d(TAG, "doInBackground: request");
                try {
                    Response response = client.newCall(request).execute();
                    Log.d(TAG, "doInBackground: response");

                    //Log.d(TAG, "response: " + response.body().string());
                    String accept = response.body().string();
                    JSONObject jsnobject = new JSONObject(accept);
                    JSONArray array = jsnobject.getJSONArray("allMovies");

                    Log.d(TAG, "doInBackground: sampai sini");
                    for (int i=0; i<array.length(); i++){

                        JSONObject object = array.getJSONObject(i);
                        Log.d(TAG, "doInBackground: get json");
                        TrendingSeriesModel seriesModel = new TrendingSeriesModel(object.getString("seriesimage"));

                        itemListTrendingSeries.add(seriesModel);
                        Log.d(TAG, "doInBackground: get json habis");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                itemTrendingSeriesAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);

            }
        };

        task.execute(id);
    }

//    private List<TrendingMoviesModel> initDataTrendingMovies(){
//
//        itemListTrendingMovies = new ArrayList<>();
//        itemListTrendingMovies.add(new TrendingMoviesModel(R.drawable.thering));
//        itemListTrendingMovies.add(new TrendingMoviesModel(R.drawable.devilallthetimee));
//        itemListTrendingMovies.add(new TrendingMoviesModel(R.drawable.ghostship));
//        itemListTrendingMovies.add(new TrendingMoviesModel(R.drawable.kissingbooth));
//        itemListTrendingMovies.add(new TrendingMoviesModel(R.drawable.jumanjitwo));
//        itemListTrendingMovies.add(new TrendingMoviesModel(R.drawable.oldguard));
//        itemListTrendingMovies.add(new TrendingMoviesModel(R.drawable.frozen));
//        itemListTrendingMovies.add(new TrendingMoviesModel(R.drawable.birdbox));
//        itemListTrendingMovies.add(new TrendingMoviesModel(R.drawable.badgenius));
//
//        return itemListTrendingMovies;
//    }

//    private List<TrendingSeriesModel> initDataTrendingSeries(){
//
//        itemListTrendingSeries = new ArrayList<>();
//        itemListTrendingSeries.add(new TrendingSeriesModel(R.drawable.peaky));
//        itemListTrendingSeries.add(new TrendingSeriesModel(R.drawable.strangertthing));
//        itemListTrendingSeries.add(new TrendingSeriesModel(R.drawable.cuckoo));
//        itemListTrendingSeries.add(new TrendingSeriesModel(R.drawable.you));
//        itemListTrendingSeries.add(new TrendingSeriesModel(R.drawable.frozen));
//        itemListTrendingSeries.add(new TrendingSeriesModel(R.drawable.birdbox));
//        itemListTrendingSeries.add(new TrendingSeriesModel(R.drawable.badgenius));
//        itemListTrendingSeries.add(new TrendingSeriesModel(R.drawable.thering));
//        itemListTrendingSeries.add(new TrendingSeriesModel(R.drawable.devilallthetimee));
//
//        return itemListTrendingSeries;
//    }






}