package com.example.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RestAdapter extends RecyclerView.Adapter<RestAdapter.ViewHolder> {

    Context context;
    List<Rest> items;

    public void setItems(List<Rest> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public RestAdapter(Context context) {
        this.context = context;
        items = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list_rest, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RestAdapter.ViewHolder holder, int position) {
        holder.Bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView date_rest, rest_status, date_created, dec;
        ImageView loadmore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            date_created = itemView.findViewById(R.id.date_created);
            rest_status = itemView.findViewById(R.id.rest_status);
            date_rest = itemView.findViewById(R.id.date_rest);
            dec = itemView.findViewById(R.id.dec);
            loadmore = itemView.findViewById(R.id.loadmore);


            loadmore.setOnClickListener(v -> {
                if (dec.getVisibility() == View.VISIBLE) {
                    loadmore.setRotation(0);
                    dec.setVisibility(View.GONE);
                } else {
                    loadmore.setRotation(180);
                    dec.setVisibility(View.VISIBLE);
                }
            });
        }

        public void Bind(Rest item) {
            date_created.setText(item.getDate_created());
            switch (item.getRest_status()) {
                case "1":
                    rest_status.setText("در حال بررسی");
                    break;
                case "2":
                    rest_status.setText("تایید شده");
                    break;
                case "3":
                    rest_status.setText("رد شده");
                    break;
            }
            date_rest.setText(item.getDate_rest());
            dec.setText(item.getDec());
        }


    }
}
