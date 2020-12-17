package com.example.navigationdrawerfragments.activity.movies;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.navigationdrawerfragments.R;
import com.example.navigationdrawerfragments.activity.playtrailer.PlayTrailerActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DevilAllTheTimeActivity extends AppCompatActivity {

    private static final String TAG = "test delete devil: " ;
    Button backBtn;
    Button playTrailer;
    FloatingActionButton floatingEditButton, floatingDelete, floatingEdit ;
    boolean isFABOpen = false;
    int movieid;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devil);

        backBtn = findViewById(R.id.btnBackDevil);
        playTrailer = findViewById(R.id.btnPlayDevil);
        floatingEditButton = findViewById(R.id.floatingEditButton2);
        floatingEdit = findViewById(R.id.floatingEdit2);
        floatingDelete = findViewById(R.id.floatingDelete2);

        Intent intent=getIntent();
        movieid =intent.getIntExtra("devilallthetime", 11);


        playTrailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(DevilAllTheTimeActivity.this, PlayTrailerActivity.class);
                intent.putExtra("devil", 2);
                startActivity(intent);
                return;
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        floatingEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFABOpen){
                    showFABMenu();
                }else{
                    closeFABMenu();
                }
            }
        });

    }
    private void showFABMenu(){
        isFABOpen=true;
        floatingEdit.animate().translationY(-getResources().getDimension(R.dimen.standard_65));
        floatingDelete.animate().translationY(-getResources().getDimension(R.dimen.standard_125));
    }

    private void closeFABMenu(){
        isFABOpen=false;
        floatingEdit.animate().translationY(0);
        floatingDelete.animate().translationY(0);
    }

    public void openDeleteDialog (View v){

        AlertDialog.Builder dialog=new AlertDialog.Builder(this, R.style.MyDialogTheme);
        dialog.setTitle("Confirm Delete");
        dialog.setMessage("Do you want to delete this movie? ");
        dialog.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//            Database_TT db = new Database_TT(DeleteTT.this);

            public void onClick(DialogInterface dialog, int which) {
                // continue with delete
                delete_data_from_server(movieid);
                System.out.println("TENGOK ID BERAPA" + movieid);
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

    private void delete_data_from_server(final int itemid) {

        AsyncTask<String, Void, Void> task = new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... params) {

                HashMap<String, Integer> nameValuePairs = new HashMap<>();
                nameValuePairs.put("movieid", itemid);

                JSONObject jsonObject = new JSONObject(nameValuePairs);
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
}
