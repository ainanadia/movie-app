package com.example.navigationdrawerfragments.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.navigationdrawerfragments.R;
import com.example.navigationdrawerfragments.adapter.ItemMoviesAdapter;
import com.example.navigationdrawerfragments.adapter.ItemPhotosAdapter;
import com.example.navigationdrawerfragments.model.MoviesModel;
import com.example.navigationdrawerfragments.model.PhotosModel;

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

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PhotosFragment() {
        // Required empty public constructor
    }

    RecyclerView recyclerView;
    private StaggeredGridLayoutManager _sGridLayoutManager;
    List<PhotosModel> itemList;

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
        recyclerView.setLayoutManager(_sGridLayoutManager);

        //initData();

        recyclerView.setAdapter(new ItemPhotosAdapter(initData()));

        return view;
    }

    private List<PhotosModel> initData() {

        itemList = new ArrayList<>();
        itemList.add(new PhotosModel(R.drawable.thering));
        itemList.add(new PhotosModel(R.drawable.devilallthetimee));
        itemList.add(new PhotosModel(R.drawable.ghostship));
        itemList.add(new PhotosModel(R.drawable.kissingbooth));
        itemList.add(new PhotosModel(R.drawable.jumanjitwo));
        itemList.add(new PhotosModel(R.drawable.oldguard));
        itemList.add(new PhotosModel(R.drawable.frozen));
        itemList.add(new PhotosModel(R.drawable.birdbox));
        itemList.add(new PhotosModel(R.drawable.badgenius));
        itemList.add(new PhotosModel(R.drawable.alice));
        itemList.add(new PhotosModel(R.drawable.animal));
        itemList.add(new PhotosModel(R.drawable.aquaman));
        itemList.add(new PhotosModel(R.drawable.greta));
        itemList.add(new PhotosModel(R.drawable.roma));
        itemList.add(new PhotosModel(R.drawable.beauty));
        itemList.add(new PhotosModel(R.drawable.blackpanther));
        itemList.add(new PhotosModel(R.drawable.blade));
        itemList.add(new PhotosModel(R.drawable.bohemian));



        return itemList;
    }
}