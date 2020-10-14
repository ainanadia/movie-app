package com.example.navigationdrawerfragments.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.navigationdrawerfragments.R;
import com.example.navigationdrawerfragments.model.MoviesModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemMoviesAdapter extends RecyclerView.Adapter<ItemMoviesAdapter.ViewHolder> implements Filterable{

    List<MoviesModel> itemList;
    List<MoviesModel> itemListFull;


    public ItemMoviesAdapter(List<MoviesModel> itemList){

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
    public void onBindViewHolder(@NonNull ItemMoviesAdapter.ViewHolder holder, int position) {

        holder.itemImage.setImageResource(itemList.get(position).getImage());
        holder.itemText.setText(itemList.get(position).getMovieName());
        holder.itemLecturerText.setText(itemList.get(position).getMovieYear());

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
                    if (item.getMovieName().toLowerCase().contains(filterPattern)) {
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

        ImageView itemImage;
        TextView itemText;
        TextView itemLecturerText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.itemImage);
            itemText = itemView.findViewById(R.id.movieName);
            itemLecturerText = itemView.findViewById(R.id.movieYear);

        }
    }
}
