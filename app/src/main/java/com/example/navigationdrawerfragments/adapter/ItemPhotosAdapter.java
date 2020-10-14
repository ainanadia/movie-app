package com.example.navigationdrawerfragments.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.navigationdrawerfragments.R;
import com.example.navigationdrawerfragments.model.PhotosModel;
import com.example.navigationdrawerfragments.model.TrendingSeriesModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemPhotosAdapter extends RecyclerView.Adapter<ItemPhotosAdapter.ViewHolder> {

    List<PhotosModel> itemList;

    public ItemPhotosAdapter(List<PhotosModel> itemList) {
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

        holder.itemPhotos.setImageResource(itemList.get(position).getPhotos());

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
