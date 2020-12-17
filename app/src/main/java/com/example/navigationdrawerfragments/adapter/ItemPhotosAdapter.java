package com.example.navigationdrawerfragments.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.navigationdrawerfragments.R;
import com.example.navigationdrawerfragments.model.PhotosModel;
import com.example.navigationdrawerfragments.model.TrendingSeriesModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemPhotosAdapter extends RecyclerView.Adapter<ItemPhotosAdapter.ViewHolder> {

    private Context context;
    List<PhotosModel> itemList;

    public ItemPhotosAdapter(Context context, List<PhotosModel> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemPhotosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photos, parent, false);
        ViewHolder  viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemPhotosAdapter.ViewHolder holder, int position) {

        Glide.with(context).load(itemList.get(position).getPhotos()).into(holder.itemPhotos);

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView itemPhotos;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemPhotos = itemView.findViewById(R.id.itemPhotos);
        }
    }
}
