package com.example.navigationdrawerfragments.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.navigationdrawerfragments.R;
import com.example.navigationdrawerfragments.model.PhotosModel;
import com.example.navigationdrawerfragments.model.PromotionModel;

import org.w3c.dom.Text;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemPromotionAdapter extends RecyclerView.Adapter<ItemPromotionAdapter.ViewHolder> {

    private Context context;
    List<PromotionModel> promoList;

    public ItemPromotionAdapter(Context context, List<PromotionModel> promoList) {
        this.context = context;
        this.promoList = promoList;
    }

    @NonNull
    @Override
    public ItemPromotionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_promotion, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemPromotionAdapter.ViewHolder holder, int position) {

        Glide.with(context).load(promoList.get(position).getPromoImage()).into(holder.promoImage);
        holder.promoTitle.setText(promoList.get(position).getPromoTitle());
        holder.promoDescription.setText(promoList.get(position).getPromoDescription());

    }

    @Override
    public int getItemCount() {
        return promoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView promoImage;
        TextView promoTitle;
        TextView promoDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            promoImage = itemView.findViewById(R.id.promoImage);
            promoTitle = itemView.findViewById(R.id.promoTitle);
            promoDescription = itemView.findViewById(R.id.promoDescription);
        }
    }
}
