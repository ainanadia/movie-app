package com.example.navigationdrawerfragments.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.navigationdrawerfragments.MethodCaller;
import com.example.navigationdrawerfragments.R;
import com.example.navigationdrawerfragments.activity.movies.AliveActivity;
import com.example.navigationdrawerfragments.activity.movies.DevilAllTheTimeActivity;
import com.example.navigationdrawerfragments.activity.movies.ITActivity;
import com.example.navigationdrawerfragments.activity.movies.TheMegActivity;
import com.example.navigationdrawerfragments.activity.movies.TheRingActivity;
import com.example.navigationdrawerfragments.model.TrendingMoviesModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemTrendingMoviesAdapter extends RecyclerView.Adapter<ItemTrendingMoviesAdapter.ViewHolder> {

    private Context context;
    List<TrendingMoviesModel> itemList;
    public MethodCaller methodCaller;

    public ItemTrendingMoviesAdapter(Context context, List<TrendingMoviesModel> itemList, MethodCaller methodCaller) {
        this.context = context;
        this.itemList = itemList;
        this.methodCaller = methodCaller;
    }

    public ItemTrendingMoviesAdapter(Context context, List<TrendingMoviesModel> itemList) {
        this.context = context;
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
        //holder.itemImage.setImageResource(itemList.get(position).getImage());
        Glide.with(context).load(itemList.get(position).getMovieImage()).into(holder.itemImage);


        holder.itemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (position == 0) {
                    Intent intent = new Intent(view.getContext(), TheRingActivity.class);
                    intent.putExtra("thering", itemList.get(position).getMovieId());
                    view.getContext().startActivity(intent);
                    Log.e("checksendingdata","check id "+ itemList.get(position).getMovieId());

                    view.getContext().startActivity(intent);
                } else if (position == 1) {
                    Intent intent = new Intent(view.getContext(), AliveActivity.class);
                    intent.putExtra("alive", itemList.get(position).getMovieId());
                    view.getContext().startActivity(intent);
                    Log.e("checksendingdata","check id "+ itemList.get(position).getMovieId());
                }else if (position == 2) {
                    Intent intent = new Intent(view.getContext(), TheMegActivity.class);
                    intent.putExtra("themeg", itemList.get(position).getMovieId());
                    view.getContext().startActivity(intent);
                } else if (position == 11) {
                Intent intent = new Intent(view.getContext(), DevilAllTheTimeActivity.class);
                intent.putExtra("devilallthetime", itemList.get(position).getMovieId());
                view.getContext().startActivity(intent);
                Log.e("checksendingdata","check id "+ itemList.get(position).getMovieId());
                }else if (position == 12) {
                    Intent intent = new Intent(view.getContext(), ITActivity.class);
                    intent.putExtra("it", itemList.get(position).getMovieId());
                    view.getContext().startActivity(intent);
                    Log.e("checksendingdata","check id "+ itemList.get(position).getMovieId());
                }
            }
        });

    }

    @Override
    public long getItemId(int position) {
        return itemList.get(position).getMovieId();
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        ImageView itemImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.itemTrendingMovies);
            itemImage.setOnCreateContextMenuListener(this);

        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {

            MenuItem edit = contextMenu.add(Menu.NONE, 1, 1, "Edit Movie");
            MenuItem delete = contextMenu.add(Menu.NONE, 2, 2, "Delete Movie");

            edit.setOnMenuItemClickListener(onEditMenu);
            delete.setOnMenuItemClickListener(onEditMenu);
        }

        private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case 1:
                        Toast.makeText(context,"Edit Movie",Toast.LENGTH_LONG).show();
                        return true;
                    case 2:
                        Toast.makeText(context,"Delete Movie",Toast.LENGTH_LONG).show();
                        methodCaller.openDeleteDialog();
                        return true;
                }
                return false;
            }
        };
    }
}
