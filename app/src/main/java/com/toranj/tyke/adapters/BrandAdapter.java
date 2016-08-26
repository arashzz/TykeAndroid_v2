package com.toranj.tyke.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.toranj.tyke.R;
import com.toranj.tyke.models.Brand;
import com.toranj.tyke.models.Lottery;
import com.toranj.tyke.models.Spending;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arash on 8/13/16.
 */
public class BrandAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Brand> items;

    public BrandAdapter(List<Brand> items) {
        this.items = new ArrayList<>();
        if(items != null) {
            this.items = items;
        }
    }

    public void addItems(List<Brand> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void addItem(Brand item) {
        items.add(item);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View brandView = inflater.inflate(R.layout.card_view_brand, parent, false);
        viewHolder = new BrandViewHolder(brandView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        BrandViewHolder brandViewHolder = (BrandViewHolder)viewHolder;
        Brand brand = (Brand)items.get(position);
        if(brand != null) {
            brandViewHolder.name.setText(brand.getName());
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class BrandViewHolder extends RecyclerView.ViewHolder {
        private TextView name;

        public BrandViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
        }
    }
}
