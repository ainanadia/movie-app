package com.example.navigationdrawerfragments.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.example.navigationdrawerfragments.R;
import com.example.navigationdrawerfragments.adapter.ItemMoviesAdapter;
import com.example.navigationdrawerfragments.adapter.ItemTrendingMoviesAdapter;
import com.example.navigationdrawerfragments.adapter.ItemTrendingSeriesAdapter;
import com.example.navigationdrawerfragments.model.MoviesModel;
import com.example.navigationdrawerfragments.model.TrendingMoviesModel;
import com.example.navigationdrawerfragments.model.TrendingSeriesModel;

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

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    RecyclerView recyclerViewTrendingSeries;
    RecyclerView recyclerViewTrendingMovies;
    RecyclerView recyclerViewPopularMovies;
    SearchView searchView;

    List<TrendingSeriesModel> itemListTrendingSeries;
    List<TrendingMoviesModel> itemListTrendingMovies;

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

        recyclerViewTrendingSeries = view.findViewById(R.id.recyclerViewTrendingSeries);
        recyclerViewTrendingSeries.setHasFixedSize(true);
        recyclerViewTrendingSeries.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        recyclerViewTrendingMovies = view.findViewById(R.id.recyclerViewTrendingMovies);
        recyclerViewTrendingMovies.setHasFixedSize(true);
        recyclerViewTrendingMovies.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        recyclerViewPopularMovies = view.findViewById(R.id.recyclerViewPopularSeries);
        recyclerViewPopularMovies.setHasFixedSize(true);
        recyclerViewPopularMovies.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        //initData();

        recyclerViewTrendingMovies.setAdapter(new ItemTrendingMoviesAdapter(initDataTrendingMovies()));
        recyclerViewTrendingSeries.setAdapter(new ItemTrendingSeriesAdapter(initDataTrendingSeries()));
        recyclerViewPopularMovies.setAdapter(new ItemTrendingMoviesAdapter(initDataTrendingMovies()));
        return view;
    }

    private List<TrendingMoviesModel> initDataTrendingMovies(){

        itemListTrendingMovies = new ArrayList<>();
        itemListTrendingMovies.add(new TrendingMoviesModel(R.drawable.thering));
        itemListTrendingMovies.add(new TrendingMoviesModel(R.drawable.devilallthetimee));
        itemListTrendingMovies.add(new TrendingMoviesModel(R.drawable.ghostship));
        itemListTrendingMovies.add(new TrendingMoviesModel(R.drawable.kissingbooth));
        itemListTrendingMovies.add(new TrendingMoviesModel(R.drawable.jumanjitwo));
        itemListTrendingMovies.add(new TrendingMoviesModel(R.drawable.oldguard));
        itemListTrendingMovies.add(new TrendingMoviesModel(R.drawable.frozen));
        itemListTrendingMovies.add(new TrendingMoviesModel(R.drawable.birdbox));
        itemListTrendingMovies.add(new TrendingMoviesModel(R.drawable.badgenius));


        return itemListTrendingMovies;
    }

    private List<TrendingSeriesModel> initDataTrendingSeries(){

        itemListTrendingSeries = new ArrayList<>();
        itemListTrendingSeries.add(new TrendingSeriesModel(R.drawable.peaky));
        itemListTrendingSeries.add(new TrendingSeriesModel(R.drawable.strangertthing));
        itemListTrendingSeries.add(new TrendingSeriesModel(R.drawable.cuckoo));
        itemListTrendingSeries.add(new TrendingSeriesModel(R.drawable.you));
        itemListTrendingSeries.add(new TrendingSeriesModel(R.drawable.frozen));
        itemListTrendingSeries.add(new TrendingSeriesModel(R.drawable.birdbox));
        itemListTrendingSeries.add(new TrendingSeriesModel(R.drawable.badgenius));
        itemListTrendingSeries.add(new TrendingSeriesModel(R.drawable.thering));
        itemListTrendingSeries.add(new TrendingSeriesModel(R.drawable.devilallthetimee));



        return itemListTrendingSeries;
    }






}