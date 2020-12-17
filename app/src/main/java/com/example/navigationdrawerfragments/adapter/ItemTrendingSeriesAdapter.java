package com.example.navigationdrawerfragments.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.navigationdrawerfragments.R;
import com.example.navigationdrawerfragments.model.TrendingSeriesModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemTrendingSeriesAdapter extends RecyclerView.Adapter<ItemTrendingSeriesAdapter.ViewHolder> {

    private Context context;
    List<TrendingSeriesModel> itemList;

    public ItemTrendingSeriesAdapter(Context context, List<TrendingSeriesModel> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemTrendingSeriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trendingseries, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemTrendingSeriesAdapter.ViewHolder holder, final int position) {

        Glide.with(context).load(itemList.get(position).getImage()).into(holder.itemImage);

//        holder.itemImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (position == 0) {
//                    Intent intent = new Intent(view.getContext(), PeakyActivity.class);
//                    view.getContext().startActivity(intent);
//                } else if (position == 1) {
//                    Intent intent = new Intent(view.getContext(), StrangerThingsActivity.class);
//                    view.getContext().startActivity(intent);
//                }
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView itemImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.itemTrendingSeries);

        }
    }
}
