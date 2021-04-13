package com.example.madsqlite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    List<ProductModel> products;

    MyRecyclerAdapter() {

    }

    MyRecyclerAdapter(List<ProductModel> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvPrice.setText(products.get(position).productPrice);
        holder.tvName.setText(products.get(position).productName);
        holder.tvMRP.setText(products.get(position).productMRP);
        holder.tvID.setText(products.get(position).productID);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvID, tvPrice, tvMRP;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvNameView);
            tvID = itemView.findViewById(R.id.tvIdView);
            tvMRP = itemView.findViewById(R.id.tvMRPView);
            tvPrice = itemView.findViewById(R.id.tvPriceView);
        }

    }
}