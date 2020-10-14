package com.example.navigationdrawerfragments.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.navigationdrawerfragments.R;
import com.example.navigationdrawerfragments.activity.DevilAllTheTimeActivity;
import com.example.navigationdrawerfragments.activity.GhostShipActivity;
import com.example.navigationdrawerfragments.activity.TheRingActivity;
import com.example.navigationdrawerfragments.model.TrendingMoviesModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemTrendingMoviesAdapter extends RecyclerView.Adapter<ItemTrendingMoviesAdapter.ViewHolder> {

    List<TrendingMoviesModel> itemList;

    public ItemTrendingMoviesAdapter(List<TrendingMoviesModel> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemTrendingMoviesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trendingmovies, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemTrendingMoviesAdapter.ViewHolder holder, final int position) {
        holder.itemImage.setImageResource(itemList.get(position).getImage());

        holder.itemImage.setOnClickListener(new View.OnClickListener() {
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

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView itemImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.itemTrendingMovies);
        }
    }
}
