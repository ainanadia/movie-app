package com.example.navigationdrawerfragments.activity.updatemovie;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.navigationdrawerfragments.R;
import com.example.navigationdrawerfragments.model.TrendingMoviesModel;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UpdateMovieActivity extends AppCompatActivity {


    private static final int PICK_IMAGE = 1;
    private static final int STORAGE_PERMISSION_CODE = 123;
    private static final String TAG = "test add data" ;

    List<TrendingMoviesModel> itemList;
    private ImageView image;
    private int movieid;
    private Bitmap bitmap;
    private Uri filePath;
    private String fileName;
    EditText editTextMovieTitle;
    EditText editTextMovieYear;
    EditText editTextMovieClass;
    EditText editTextMovieSynopsis;
    String imageMovie;
    private String loadedimage;
    private Context Context;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_update_movie);

        editTextMovieTitle = findViewById(R.id.editTextMovieTitle);
        editTextMovieYear = findViewById(R.id.editTextMovieYear);
        editTextMovieClass = findViewById(R.id.editTextMovieClass);
        editTextMovieSynopsis = findViewById(R.id.editTextMovieSynopsis);
        image = findViewById(R.id.imageViewUpload);

        Button btnBackUpdateMovie = findViewById(R.id.btnbackUpdateMovie);
        Button btnUpload = findViewById(R.id.buttonUpload);
        Button btnUpdate = findViewById(R.id.buttonUpdateMovie);

        Intent intent=getIntent();
        movieid =intent.getIntExtra("itID", 12);
        System.out.println("id IT kat update berapa" + movieid);

        load_data_from_server(movieid);

        btnBackUpdateMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectImageFromGallery();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String movietitle = editTextMovieTitle.getText().toString();
                final String movieyear = editTextMovieYear.getText().toString();
                final String movieclass = editTextMovieClass.getText().toString();
                final String moviesynopsis = editTextMovieSynopsis.getText().toString();
                final String movieimage = imageMovie;

                Log.d(TAG, "check before update: " + movieid);
                Log.d(TAG, "check before update: " + movietitle);
                Log.d(TAG, "check before update: " + movieyear);
                Log.d(TAG, "check before update: " + movieclass);
                Log.d(TAG, "check before update: " + movieimage);
                Log.d(TAG, "check before update: " + moviesynopsis);

                save_data_to_server(movieid, movietitle, movieyear, movieclass, movieimage, moviesynopsis);

                Toast.makeText(UpdateMovieActivity.this, "Movie Successfully Updated", Toast.LENGTH_SHORT).show();

                finish();
            }
        });

        requestStoragePermission();
    }

    private void selectImageFromGallery() {

        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            fileName = getFileNameByUri(this,filePath);
            try {
                System.out.println(fileName);
                bitmap = MediaStore.Images.Media .getBitmap(this.getContentResolver(), filePath);
                image.setImageBitmap(bitmap);

            } catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    //Requesting permission
    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously code will come to this block
            //Explain here why this permission needed
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }

    public static String getFileNameByUri(Context context, Uri uri){
        String fileName="unknown";//default fileName
        Uri filePathUri = uri;
        if (uri.getScheme().toString().compareTo("content")==0){
            Cursor cursor = context.getContentResolver().query(uri,null,null,
                    null, null);
            if (cursor.moveToFirst()){
                int column_index =
                        cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                filePathUri = Uri.parse(cursor.getString(column_index));
                if(filePathUri == null){
                    fileName = "xxx.png";//load a default Image from server
                }else{
                    fileName = filePathUri.getLastPathSegment().toString();
                }
            }
        }else
        if (uri.getScheme().compareTo("file")==0){
            fileName = filePathUri.getLastPathSegment().toString();
        }else{
            fileName = fileName+"_"+filePathUri.getLastPathSegment();
        }
        return fileName;
    }

    private void save_data_to_server(final int movieid, final String movietitle, final String movieyear, final String movieclass, final String movieimage, final String moviesynopsis) {

        AsyncTask<String, Void, Void> task = new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... params) {

                HashMap<String, String> nameValuePairs = new HashMap<>();
                nameValuePairs.put("movieid", String.valueOf(movieid));
                nameValuePairs.put("movietitle", movietitle);
                nameValuePairs.put("movieyear", movieyear);
                nameValuePairs.put("movieclass", movieclass);
                nameValuePairs.put("movieimage", movieimage);
                nameValuePairs.put("moviesynopsis", moviesynopsis);

                Log.d(TAG, "cek valuepairs: " + nameValuePairs);


                JSONObject jsonObject = new JSONObject(nameValuePairs);
                final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                RequestBody body = RequestBody.create(JSON, String.valueOf(jsonObject));

                Log.d(TAG, "check body" + body);

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .header("Content-Type", "application/json; charset=utf-8")
                        .url("http://192.168.0.190:8080/movie-restful/rest/MovieService/updatemovie")
                        .put(body)
                        .build();

                Log.d(TAG, "check body 2: " + request);
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

        task.execute(String.valueOf(movieid), movietitle, movieyear, movieyear, movieimage, moviesynopsis);
    }

    private void load_data_from_server(final int moviesid) {

        final AsyncTask<String,Void,Void> task = new AsyncTask<String,Void,Void>(){

            protected Void doInBackground(String... params) {

                HashMap<String, Integer> nameValuePairs = new HashMap<>();
                nameValuePairs.put("movieid", moviesid);
                Log.d(TAG, "doInBackground: BERAPA ID MOVIE"+ moviesid);
                JSONObject jsonObject = new JSONObject(nameValuePairs);

                Log.d(TAG, "doInBackground: CHECK JSON OBJ"+ jsonObject);
                final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                RequestBody body = RequestBody.create(JSON, jsonObject.toString());
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .header("Content-Type", "application/json; charset=utf-8")
                        .url("http://192.168.0.190:8080/movie-restful/rest/MovieService/getmoviebyid")
                        .post(body)
                        .build();

                Log.d(TAG, "doInBackground: request COMPLETE");
                try {
                    Response response = client.newCall(request).execute();
                    Log.d(TAG, "doInBackground: response");

                    String accept = response.body().string();
                    JSONObject jsnobject = new JSONObject(accept);

                    setLayout(jsnobject);

                    Log.d(TAG, "doInBackground: response succeed" + jsnobject);


                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {

                //itemTrendingMoviesAdapter.notifyDataSetChanged();

            }
        };

        task.execute(String.valueOf(movieid));
    }

    private void setLayout(JSONObject jsnobject) throws JSONException {


        if (jsnobject.has("movietitle")) {
            String movietitle = jsnobject.getString("movietitle");
            editTextMovieTitle.setText(movietitle);
        }

        if (jsnobject.has("movieyear")) {
            int movieyear = jsnobject.getInt("movieyear");
            editTextMovieYear.setText(String.valueOf(movieyear));
        }
        if (jsnobject.has("movieclass")) {
            String movieclass = jsnobject.getString("movieclass");
            editTextMovieClass.setText(movieclass);
        }

        if (jsnobject.has("movieimage")) {
            imageMovie = jsnobject.getString("movieimage");
            Handler uiHandler = new Handler(Looper.getMainLooper());
            uiHandler.post(new Runnable(){
                @Override
                public void run() {
                    Picasso.get()
                            .load(imageMovie)
                            .into(image);
                }
            });
        }
        if (jsnobject.has("moviesynopsis")) {
            String moviesynopsis = jsnobject.getString("moviesynopsis");
            editTextMovieSynopsis.setText(moviesynopsis);
        }

    }
}

