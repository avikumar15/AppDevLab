package com.example.sqliteexcersise;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    List<User> users;

    MyRecyclerAdapter() {

    }

    MyRecyclerAdapter(List<User> users) {
        this.users = users;
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
        holder.tvEmail.setText(users.get(position).email);
        holder.tvName.setText(users.get(position).name);
        holder.tvPhone.setText(users.get(position).phone);
        holder.tvUname.setText(users.get(position).username);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvUname, tvEmail, tvPhone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvNameView);
            tvUname = itemView.findViewById(R.id.tvUnameView);
            tvPhone = itemView.findViewById(R.id.tvPhoneView);
            tvEmail = itemView.findViewById(R.id.tvEmailView);
        }

    }
}
