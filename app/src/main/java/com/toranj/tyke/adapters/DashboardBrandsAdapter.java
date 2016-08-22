package com.toranj.tyke.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.toranj.tyke.R;
import com.toranj.tyke.models.Brand;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arash on 8/16/16.
 */

public class DashboardBrandsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Brand> items;
    private Context context;

    public DashboardBrandsAdapter(List<Brand> items) {
        if(items == null) {
            items = new ArrayList<>();
        }
        this.items = items;
    }

    public void addItem(Brand item) {
        items.add(item);
        notifyDataSetChanged();
    }

    public void addItems(List<Brand> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_dashboard_brand, parent, false);
        context = parent.getContext();
        return new DashboardBrandViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DashboardBrandViewHolder viewHolder = (DashboardBrandViewHolder)holder;
        Brand brand = items.get(position);
        if(brand != null) {
            Glide
                    .with(context)
                    .load(brand.getImage())
                    .placeholder(R.mipmap.ic_launcher)
                    .into(viewHolder.image);
            viewHolder.name.setText(brand.getName());
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class DashboardBrandViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        public DashboardBrandViewHolder(View view) {
            super(view);
            image = (ImageView)view.findViewById(R.id.image_dashboard_brand);
            name = (TextView)view.findViewById(R.id.text_dashboard_brand_name);
        }
    }
}
