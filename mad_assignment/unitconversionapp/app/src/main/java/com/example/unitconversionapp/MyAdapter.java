package com.example.unitconversionapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    ArrayAdapter<CharSequence> a1;
    ArrayList<Double> a2;

    MyAdapter() {

    }

    MyAdapter(ArrayAdapter<CharSequence> a1, ArrayList<Double> a2) {
        this.a1 = a1;
        this.a2 = a2;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv1.setText(a1.getItem(position));
        holder.tv2.setText(String.format("%1$,.2f", a2.get(position)));
    }

    @Override
    public int getItemCount() {
        return a2.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv1, tv2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.tv1);
            tv2 = itemView.findViewById(R.id.tv2);
        }

    }
}
