package com.example.navigationdrawerfragments.activity.trendingmovies;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.navigationdrawerfragments.MethodCaller;
import com.example.navigationdrawerfragments.R;
import com.example.navigationdrawerfragments.activity.addmovie.AddMovieActivity;
import com.example.navigationdrawerfragments.activity.editmovie.EditMovieActivity;
import com.example.navigationdrawerfragments.adapter.ItemTrendingMoviesAdapter;
import com.example.navigationdrawerfragments.model.TrendingMoviesModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TrendingMoviesActivity extends AppCompatActivity implements MethodCaller {

    private static final String TAG = "trendingmovies";
    private StaggeredGridLayoutManager _sGridLayoutManager;
    ItemTrendingMoviesAdapter itemTrendingMoviesAdapter;
    TrendingMoviesModel trendingMoviesModel;
    List<TrendingMoviesModel> itemList;
    SwipeRefreshLayout swipeRefreshLayout;
    FloatingActionButton floatingAddButton;
    Button btnBackTM;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_trending_movies);

        btnBackTM = findViewById(R.id.buttonbackTM);
        floatingAddButton = findViewById(R.id.floatingAddButton);
        swipeRefreshLayout = findViewById(R.id.refreshTrendingMovies);

        btnBackTM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        floatingAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), AddMovieActivity.class);
                startActivity(intent);
            }
        });


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                itemList.clear();
                load_data_from_server();

            }
        });

        final RecyclerView recyclerView = findViewById(R.id.TrendingMoviesAll);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        //initData();
        itemList  = new ArrayList<>();
        load_data_from_server();

        itemTrendingMoviesAdapter = new ItemTrendingMoviesAdapter(getApplicationContext(),itemList, this);

        recyclerView.setAdapter(itemTrendingMoviesAdapter);

    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    public boolean onContextItemSelected(MenuItem item) {
        //find out which menu item was pressed
        switch (item.getItemId()) {
            case R.id.deleteOption:
                openDeleteDialog();
                return true;
            case R.id.editOption:
                Intent intent = new Intent(this, EditMovieActivity.class);
                startActivity(intent);
                return true;
            default:
                return false;
        }
    }


    private void load_data_from_server() {

        AsyncTask<String,Void,Void> task = new AsyncTask<String,Void,Void>(){

            protected Void doInBackground(String... params) {

                JSONObject jsonObject = new JSONObject();
                final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                RequestBody body = RequestBody.create(JSON, jsonObject.toString());
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .header("Content-Type", "application/json; charset=utf-8")
                        .url("http:/192.168.0.190:8080/movie-restful/rest/MovieService/allmovies")
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


                        itemList.add(moviesModel);
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
                swipeRefreshLayout.setRefreshing(false);
            }
        };

        task.execute();
    }

    private void delete_data_from_server(int id) {

        AsyncTask<String, Void, Void> task = new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... params) {

                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("movieid", params[0]);

                JSONObject jsonObject = new JSONObject();
                final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                RequestBody body = RequestBody.create(JSON, String.valueOf(jsonObject));

                Log.d(TAG, "doInBackground: request" + jsonObject);

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .header("Content-Type", "application/json; charset=utf-8")
                        .url("http://192.168.0.190:8080/movie-restful/rest/MovieService/deletemovie")
                        .delete(body)
                        .build();

                Log.d(TAG, "doInBackground: request");
                try {
                    Response response = client.newCall(request).execute();
                    Log.d(TAG, "doInBackground: response");

                    Log.d(TAG, "response: " + response.body().string());
                    //String accept = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;

            }
        };

        task.execute();
    }


    @Override
    public void openDeleteDialog() {

            AlertDialog.Builder dialog=new AlertDialog.Builder(this, R.style.MyDialogTheme);
            dialog.setTitle("Confirm Delete");
            dialog.setMessage("Do you want to delete this movie? ");
            dialog.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//            Database_TT db = new Database_TT(DeleteTT.this);

                public void onClick(DialogInterface dialog, int which) {
                    // continue with delete
                    delete_data_from_server(0);
                }
            });
            dialog.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // close dialog
                    dialog.cancel();
                }
            });
            AlertDialog alertDialog = dialog.create();
            alertDialog.show();
        }
}
