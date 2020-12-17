package com.example.navigationdrawerfragments.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.navigationdrawerfragments.R;
import com.example.navigationdrawerfragments.activity.movies.DevilAllTheTimeActivity;
import com.example.navigationdrawerfragments.activity.movies.GhostShipActivity;
import com.example.navigationdrawerfragments.activity.movies.TheRingActivity;
import com.example.navigationdrawerfragments.model.MoviesModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemMoviesAdapter extends RecyclerView.Adapter<ItemMoviesAdapter.ViewHolder> implements Filterable {

    private Context context;
    List<MoviesModel> itemList;
    List<MoviesModel> itemListFull;


    public ItemMoviesAdapter(Context context, List<MoviesModel> itemList){
        this.context = context;
        this.itemList = itemList;
        itemListFull = new ArrayList<>(itemList);

    }


    @NonNull
    @Override
    public ItemMoviesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movies, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemMoviesAdapter.ViewHolder holder, final int position) {

        Glide.with(context).load(itemList.get(position).getMovieImage()).into(holder.movieImage);
        holder.movieTitle.setText(itemList.get(position).getMovieTitle());
        holder.movieYear.setText(String.valueOf(itemList.get(position).getMovieYear()));
        holder.movieClass.setText(itemList.get(position).getMovieClass());
        holder.movieSynopsis.setText(itemList.get(position).getMovieSynopsis());

        holder.movieImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position == 0) {
                    Intent intent = new Intent(view.getContext(), TheRingActivity.class);
                    view.getContext().startActivity(intent);
                } else if (position == 1) {
                    Intent intent = new Intent(view.getContext(), DevilAllTheTimeActivity.class);
                    view.getContext().startActivity(intent);
                }else if (position == 2) {
                    Intent intent = new Intent(view.getContext(), GhostShipActivity.class);
                    view.getContext().startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public Filter getFilter() {
        return doFilter;
    }

    private Filter doFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<MoviesModel> filteredList = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(itemListFull);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for (MoviesModel item : itemListFull) {
                    if (item.getMovieTitle().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

                FilterResults results = new FilterResults();
                results.values = filteredList;

                return results;
             }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            itemList.clear();
            itemList.addAll((ArrayList) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView movieImage;
        TextView movieTitle;
        TextView movieYear;
        TextView movieClass;
        TextView movieSynopsis;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            movieImage = itemView.findViewById(R.id.itemImage);
            movieTitle = itemView.findViewById(R.id.movieName);
            movieYear = itemView.findViewById(R.id.movieYear);
            movieClass = itemView.findViewById(R.id.movieClass);
            movieSynopsis = itemView.findViewById(R.id.movieSynopsis);
        }
    }
}
