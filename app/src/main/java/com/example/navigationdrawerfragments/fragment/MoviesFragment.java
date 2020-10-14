package com.example.navigationdrawerfragments.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import com.example.navigationdrawerfragments.adapter.ItemMoviesAdapter;
import com.example.navigationdrawerfragments.model.MoviesModel;
import com.example.navigationdrawerfragments.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MoviesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoviesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MoviesFragment() {
        // Required empty public constructor
    }

    RecyclerView recyclerView;
    List<MoviesModel> itemList;
    SearchView searchView;
    ItemMoviesAdapter mAdapter;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MoviesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MoviesFragment newInstance(String param1, String param2) {
        MoviesFragment fragment = new MoviesFragment();
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
        View view = inflater.inflate(R.layout.fragment_movies, container, false);

        setHasOptionsMenu(true);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //initData();
        mAdapter = new ItemMoviesAdapter(initData());
        recyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setIconifiedByDefault(false);
        searchView.getBackground();
        searchView.setQueryHint("Search..");
        searchView.setImeOptions(EditorInfo.IME_ACTION_SEARCH);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchView.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                mAdapter.getFilter().filter(s);
                return true;
            }
        });
    }

    private List<MoviesModel> initData(){

        itemList = new ArrayList<>();
        itemList.add(new MoviesModel(R.drawable.thering, "The Ring", "2020"));
        itemList.add(new MoviesModel(R.drawable.devilallthetimee, "Devil All The Time", "2020"));
        itemList.add(new MoviesModel(R.drawable.ghostship, "Ghost Ship", "2019"));
        itemList.add(new MoviesModel(R.drawable.kissingbooth, "The Kissing Booth 2", "2020"));
        itemList.add(new MoviesModel(R.drawable.jumanjitwo, "Jumanji ", "2019"));
        itemList.add(new MoviesModel(R.drawable.oldguard, "Old Guard", "2020"));
        itemList.add(new MoviesModel(R.drawable.frozen, "Frozen 2", "2019"));
        itemList.add(new MoviesModel(R.drawable.birdbox, "Bird Box", "2019"));
        itemList.add(new MoviesModel(R.drawable.badgenius, "Bad Genius", "2019"));
        itemList.add(new MoviesModel(R.drawable.cuckoo, "Cuckoo", "2020"));
        itemList.add(new MoviesModel(R.drawable.insideout, "Inside Out", "2017"));
        itemList.add(new MoviesModel(R.drawable.inception, "Inception", "2017"));
        itemList.add(new MoviesModel(R.drawable.alice, "Alice in the Wonderland", "2018"));
        itemList.add(new MoviesModel(R.drawable.rango, "Rango ", "2019"));
        itemList.add(new MoviesModel(R.drawable.roma, "Roma", "2020"));
        itemList.add(new MoviesModel(R.drawable.blackpanther, "Black Panther", "2019"));
        itemList.add(new MoviesModel(R.drawable.bohemian, "Bohemian Rhapsody", "2019"));
        itemList.add(new MoviesModel(R.drawable.hellboy, "Hell Boy", "2018"));
        itemList.add(new MoviesModel(R.drawable.green, "Green Book", "2019"));
        itemList.add(new MoviesModel(R.drawable.you, "You", "2019"));
        itemList.add(new MoviesModel(R.drawable.pirates, "Pirates of the Carribean", "2018"));
        itemList.add(new MoviesModel(R.drawable.up, "Up", "2018"));
        itemList.add(new MoviesModel(R.drawable.thor, "Thor", "2019"));
        itemList.add(new MoviesModel(R.drawable.peaky, "Peaky Blinders", "2020"));
        itemList.add(new MoviesModel(R.drawable.strangertthing, "Stranger Things ", "2016"));
        itemList.add(new MoviesModel(R.drawable.kungfu, "Kungfu Panda", "2018"));
        itemList.add(new MoviesModel(R.drawable.star, "A Star is Born", "2019"));
        itemList.add(new MoviesModel(R.drawable.ironman, "Iron Man 2", "2015"));
        itemList.add(new MoviesModel(R.drawable.dunkirk, "Dunkirk", "2018"));
        itemList.add(new MoviesModel(R.drawable.gold, "Gold", "2019"));
        itemList.add(new MoviesModel(R.drawable.blade, "Blade", "2015"));
        itemList.add(new MoviesModel(R.drawable.beauty, "Beauty and The Beast", "2017"));
        itemList.add(new MoviesModel(R.drawable.sitter, "The Sitter", "2020"));
        itemList.add(new MoviesModel(R.drawable.aquaman, "Aquaman ", "2019"));
        itemList.add(new MoviesModel(R.drawable.bfg, "The BFG", "2020"));
        itemList.add(new MoviesModel(R.drawable.consumption, "Consumption", "2019"));
        itemList.add(new MoviesModel(R.drawable.deadpool, "Deadpool", "2019"));
        itemList.add(new MoviesModel(R.drawable.fantastice, "Fantastic Beast", "2019"));
        itemList.add(new MoviesModel(R.drawable.flash, "Flash", "2018"));
        itemList.add(new MoviesModel(R.drawable.giant, "I Kill Giants", "2019"));
        itemList.add(new MoviesModel(R.drawable.gid, "God of Egypts", "2020"));
        itemList.add(new MoviesModel(R.drawable.greta, "Greta", "2020"));
        itemList.add(new MoviesModel(R.drawable.hellboy, "Hellboy", "2019"));
        itemList.add(new MoviesModel(R.drawable.inception, "Inception", "2016"));
        itemList.add(new MoviesModel(R.drawable.lala, "Lala Land ", "2017"));
        itemList.add(new MoviesModel(R.drawable.lion, "Lion", "2016"));
        itemList.add(new MoviesModel(R.drawable.lucky, "Lucky", "2018"));
        itemList.add(new MoviesModel(R.drawable.mazer, "Maze Runner - Death Cure", "2019"));
        itemList.add(new MoviesModel(R.drawable.meg, "The Meg", "2018"));
        itemList.add(new MoviesModel(R.drawable.moana, "Moana", "2019"));
        itemList.add(new MoviesModel(R.drawable.neighbor, "Neighbour", "2020"));
        itemList.add(new MoviesModel(R.drawable.passengers, "Passengers", "2017"));
        itemList.add(new MoviesModel(R.drawable.pink, "Pink", "2020"));
        itemList.add(new MoviesModel(R.drawable.rogue, "Rogue One ", "2019"));
        itemList.add(new MoviesModel(R.drawable.secret, "The Secret She Hide", "2020"));
        itemList.add(new MoviesModel(R.drawable.sing, "Sing", "2018"));
        itemList.add(new MoviesModel(R.drawable.sultan, "Sultan", "2017"));
        itemList.add(new MoviesModel(R.drawable.tarzan, "Tarzan", "2018"));
        itemList.add(new MoviesModel(R.drawable.thegirl, "The Girl on The Train", "2019"));
        itemList.add(new MoviesModel(R.drawable.thetickets, "The Ticket", "2019"));
        itemList.add(new MoviesModel(R.drawable.thewild, "The Wild Life", "2020"));
        itemList.add(new MoviesModel(R.drawable.thor, "Thor", "2020"));
        itemList.add(new MoviesModel(R.drawable.tombraider, "Tomb Raider", "2019"));
        itemList.add(new MoviesModel(R.drawable.train, "The Girl on The Train", "2020"));
        itemList.add(new MoviesModel(R.drawable.venom, "Venom ", "2017"));
        itemList.add(new MoviesModel(R.drawable.wrinke, "Wrinkle", "2020"));
        itemList.add(new MoviesModel(R.drawable.xmen, "X-Men", "2019"));
        itemList.add(new MoviesModel(R.drawable.birdbox, "Bird Box", "2019"));
        itemList.add(new MoviesModel(R.drawable.badgenius, "Bad Genius", "2019"));
        itemList.add(new MoviesModel(R.drawable.thering, "The Ring", "2020"));
        itemList.add(new MoviesModel(R.drawable.devilallthetimee, "Devil All The Time", "2020"));
        itemList.add(new MoviesModel(R.drawable.ghostship, "Ghost Ship", "2019"));
        itemList.add(new MoviesModel(R.drawable.kissingbooth, "The Kissing Booth 2", "2020"));
        itemList.add(new MoviesModel(R.drawable.jumanjitwo, "Jumanji ", "2019"));
        itemList.add(new MoviesModel(R.drawable.oldguard, "Old Guard", "2020"));
        itemList.add(new MoviesModel(R.drawable.frozen, "Frozen 2", "2019"));
        itemList.add(new MoviesModel(R.drawable.birdbox, "Bird Box", "2019"));
        itemList.add(new MoviesModel(R.drawable.badgenius, "Bad Genius", "2019"));
        itemList.add(new MoviesModel(R.drawable.badgenius, "Bad Genius", "2019"));
        itemList.add(new MoviesModel(R.drawable.badgenius, "Bad Genius", "2019"));
        itemList.add(new MoviesModel(R.drawable.thering, "The Ring", "2020"));
        itemList.add(new MoviesModel(R.drawable.devilallthetimee, "Devil All The Time", "2020"));
        itemList.add(new MoviesModel(R.drawable.ghostship, "Ghost Ship", "2019"));
        itemList.add(new MoviesModel(R.drawable.kissingbooth, "The Kissing Booth 2", "2020"));
        itemList.add(new MoviesModel(R.drawable.jumanjitwo, "Jumanji ", "2019"));
        itemList.add(new MoviesModel(R.drawable.oldguard, "Old Guard", "2020"));
        itemList.add(new MoviesModel(R.drawable.frozen, "Frozen 2", "2019"));
        itemList.add(new MoviesModel(R.drawable.birdbox, "Bird Box", "2019"));
        itemList.add(new MoviesModel(R.drawable.badgenius, "Bad Genius", "2019"));
        itemList.add(new MoviesModel(R.drawable.thering, "The Ring", "2020"));
        itemList.add(new MoviesModel(R.drawable.devilallthetimee, "Devil All The Time", "2020"));
        itemList.add(new MoviesModel(R.drawable.ghostship, "Ghost Ship", "2019"));
        itemList.add(new MoviesModel(R.drawable.kissingbooth, "The Kissing Booth 2", "2020"));
        itemList.add(new MoviesModel(R.drawable.jumanjitwo, "Jumanji ", "2019"));
        itemList.add(new MoviesModel(R.drawable.oldguard, "Old Guard", "2020"));
        itemList.add(new MoviesModel(R.drawable.frozen, "Frozen 2", "2019"));
        itemList.add(new MoviesModel(R.drawable.birdbox, "Bird Box", "2019"));
        itemList.add(new MoviesModel(R.drawable.badgenius, "Bad Genius", "2019"));
        itemList.add(new MoviesModel(R.drawable.badgenius, "Bad Genius", "2019"));
        itemList.add(new MoviesModel(R.drawable.badgenius, "Bad Genius", "2019"));



        return itemList;
    }
}