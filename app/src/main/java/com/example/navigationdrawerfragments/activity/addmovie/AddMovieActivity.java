package com.example.navigationdrawerfragments.activity.addmovie;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.navigationdrawerfragments.R;
import com.example.navigationdrawerfragments.model.TrendingMoviesModel;

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

public class AddMovieActivity extends AppCompatActivity {

    private static final int PICK_IMAGE = 1;
    private static final int STORAGE_PERMISSION_CODE = 123;
    private static final String TAG = "test add data" ;

    List<TrendingMoviesModel> itemList;
    private ImageView image;
    private Bitmap bitmap;
    private Uri filePath;
    private String fileName;

    public AddMovieActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_movie);

         final EditText editTextMovieTitle = findViewById(R.id.editTextMovieTitle);
         final EditText editTextMovieYear = findViewById(R.id.editTextMovieYear);
         final EditText editTextMovieClass = findViewById(R.id.editTextMovieClass);
         final EditText editTextMovieSynopsis = findViewById(R.id.editTextMovieSynopsis);


        image = findViewById(R.id.imageViewUpload);

        Button btnBackAddMovie = findViewById(R.id.btnbackAddmovie);
        Button btnUpload = findViewById(R.id.buttonUpload);
        Button btnSave = findViewById(R.id.buttonSaveMovie);


        btnBackAddMovie.setOnClickListener(new View.OnClickListener() {
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
        
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final String movietitle = editTextMovieTitle.getText().toString();
                final String movieyear = editTextMovieYear.getText().toString();
                final String movieclass = editTextMovieClass.getText().toString();
                final String moviesynopsis = editTextMovieSynopsis.getText().toString();
                final String movieimage = filePath.toString();

                save_data_to_server(movietitle, movieyear, movieclass, movieimage, moviesynopsis);

                Toast.makeText(AddMovieActivity.this, "Movie Successfully Added", Toast.LENGTH_SHORT).show();

                finish();
            }
        });

        requestStoragePermission();


    }

    private void save_data_to_server(final String movietitle, final String movieyear, final String movieclass, final String movieimage, final String moviesynopsis) {

        AsyncTask<String, Void, Void> task = new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... params) {

                HashMap<String, String> nameValuePairs = new HashMap<>();
                nameValuePairs.put("movietitle", movietitle);
                nameValuePairs.put("movieyear", movieyear);
                nameValuePairs.put("movieclass", movieclass);
                nameValuePairs.put("movieimage", movieimage);
                nameValuePairs.put("moviesynopsis", moviesynopsis);

                JSONObject jsonObject = new JSONObject(nameValuePairs);
                final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                RequestBody body = RequestBody.create(JSON, String.valueOf(jsonObject));

                Log.d(TAG, "doInBackground: request" + jsonObject);

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .header("Content-Type", "application/json; charset=utf-8")
                        .url("http://192.168.0.190:8080/movie-restful/rest/MovieService/newmovie")
                        .post(body)
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

        task.execute(movietitle, movieyear, movieyear, movieimage, moviesynopsis);
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

//        public byte[] getBytesImage(Bitmap bmp){
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bmp.compress(Bitmap.CompressFormat.PNG,100, baos);
//        byte[] imageBytes = baos.toByteArray();
//        return imageBytes;
//    }


//    public static String ConvertBitmapToString(Bitmap bitmap) {
//        String encodedImage = "";
//
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
//        try {
//            encodedImage = URLEncoder.encode(Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT), "UTF-8");
//
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//
//        return encodedImage;
//    }


//    public Uri getImageUri(Context inContext, Bitmap inImage) {
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
//        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
//        return Uri.parse(path);
//    }
//
//    public String getRealPathFromURI(Uri uri) {
//        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
//        cursor.moveToFirst();
//        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
//        return cursor.getString(idx);
//    }

}
