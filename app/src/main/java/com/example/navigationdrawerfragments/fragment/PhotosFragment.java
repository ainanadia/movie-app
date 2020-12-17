package com.example.navigationdrawerfragments.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.navigationdrawerfragments.R;
import com.example.navigationdrawerfragments.adapter.ItemMoviesAdapter;
import com.example.navigationdrawerfragments.adapter.ItemPhotosAdapter;
import com.example.navigationdrawerfragments.model.MoviesModel;
import com.example.navigationdrawerfragments.model.PhotosModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PhotosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PhotosFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "test photos";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PhotosFragment() {
        // Required empty public constructor
    }

    RecyclerView recyclerView;
    private StaggeredGridLayoutManager _sGridLayoutManager;
    List<PhotosModel> itemList;
    ItemPhotosAdapter itemPhotosAdapter;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PhotosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PhotosFragment newInstance(String param1, String param2) {
        PhotosFragment fragment = new PhotosFragment();
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
        View view = inflater.inflate(R.layout.fragment_photos, container, false);

        ImageView imageView = view.findViewById(R.id.itemPhotos);

        recyclerView = view.findViewById(R.id.recyclerViewPhoto);
        recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        _sGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        _sGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(_sGridLayoutManager);

        //initData();
        itemList  = new ArrayList<>();
        load_data_from_server(0);

        itemPhotosAdapter = new ItemPhotosAdapter(getContext(),itemList );
        recyclerView.setAdapter(itemPhotosAdapter);

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
                        PhotosModel moviesModel = new PhotosModel( object.getString("movieimage"));

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
                itemPhotosAdapter.notifyDataSetChanged();
            }
        };

        task.execute(id);
    }
}