package com.toranj.tyke.adapters;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.toranj.tyke.R;
import com.toranj.tyke.models.Lottery;
import com.toranj.tyke.models.Spending;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arash on 7/28/16.
 */
public class DashboardLotteriesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Lottery> lotteryList;
    private List<Object> items;
    private double expiringSpending;

    private final int LOTTERY_VIEW_TYPE = 1;
    private final int SPENDING_VIEW_TYPE = 2;

    public DashboardLotteriesAdapter(List<Object> items) {
        if(items == null) {
            items = new ArrayList<>();
        }
        this.items = items;
    }

    public void addItems(List<Object> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void addItem(Object item) {
        items.add(item);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = null;
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch(viewType) {
            case LOTTERY_VIEW_TYPE:
                View lotteryView = inflater.inflate(R.layout.card_view_lottery, parent, false);
                viewHolder = new LotteryViewHolder(lotteryView);
                break;
            case SPENDING_VIEW_TYPE:
                View spendingView = inflater.inflate(R.layout.card_view_expiring_spending, parent, false);
                viewHolder = new SpendingViewHolder(spendingView);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case LOTTERY_VIEW_TYPE:
                LotteryViewHolder lotteryViewHolder = (LotteryViewHolder)viewHolder;
                configureViewHolder(lotteryViewHolder, position);
                break;
            case SPENDING_VIEW_TYPE:
                SpendingViewHolder spendingViewHolder = (SpendingViewHolder)viewHolder;
                configureViewHolder(spendingViewHolder, position);
                break;
        }
    }

    @Override
    public int getItemCount() {

        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(items != null) {
            Object item = items.get(position);
            if(item != null) {
                if(item instanceof Lottery) {
                    return LOTTERY_VIEW_TYPE;
                }
                else if(item instanceof Spending) {
                    return SPENDING_VIEW_TYPE;
                }
            }
        }
        return -1;
    }

    private void configureViewHolder(LotteryViewHolder viewHolder, int position) {
        Lottery lottery = (Lottery)items.get(position);
        if(lottery != null) {
            viewHolder.name.setText(lottery.getName());
        }
    }

    private void configureViewHolder(SpendingViewHolder viewHolder, int position) {
        Spending spending = (Spending)items.get(position);
        if(spending != null) {
            viewHolder.amount.setText(String.valueOf(spending.getAmount()));
        }
    }

    public class LotteryViewHolder extends RecyclerView.ViewHolder {
        private TextView name;

        public LotteryViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
        }
    }

    public class SpendingViewHolder extends RecyclerView.ViewHolder {
        public TextView amount;

        public SpendingViewHolder(View view) {
            super(view);
            amount = (TextView) view.findViewById(R.id.amount);
        }
    }
}
