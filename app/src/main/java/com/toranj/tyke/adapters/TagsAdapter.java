package com.toranj.tyke.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.toranj.tyke.R;
import com.toranj.tyke.models.Tag;
import com.toranj.tyke.ui.fragments.listeners.TagFragmentListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arash.jahromi on 23/8/2016.
 */
public class TagsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Tag> items;
    private Context context;
    private TagFragmentListener listener;

    public TagsAdapter(Context context, List<Tag> items, TagFragmentListener listener) {
        if(listener == null) {
            throw new NullPointerException("TagFragmentListener passed in TagsAdapter constructor cannot be null");
        }
        this.listener = listener;
        this.items = new ArrayList<>();
        this.context = context;
        if(items != null) {
            this.items = items;
        }
    }

    public void addItem(Tag item) {
        items.add(item);
        notifyDataSetChanged();
    }

    public void addItems(List<Tag> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public Tag getItemAt(int position) {
        return items.get(position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TagViewHolder tagViewHolder = (TagViewHolder)holder;
        Tag tag = items.get(position);
        if(tag != null) {
//            tagViewHolder.name.setText(tag.getName());
            tagViewHolder.bindDataWithViewHolder(tag);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View brandView = inflater.inflate(R.layout.card_view_tag, parent, false);
        viewHolder = new TagViewHolder(brandView);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class TagViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView name;
        private CardView rootView;

        public TagViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            rootView = (CardView)view.findViewById(R.id.tag_cardview);
            name = (TextView)view.findViewById(R.id.name);
        }

        @Override
        public void onClick(View v) {
            Tag tag = items.get(TagViewHolder.this.getAdapterPosition());
            if(tag != null) {
                if(!tag.isSelected()) {
                    listener.onTagSelected(tag);
                }
                else {
                 listener.onTagDeselected(tag);
                }
                tag.setSelected(!tag.isSelected());
            }
            bindDataWithViewHolder(tag);
        }

        public void bindDataWithViewHolder(Tag tag) {
            name.setText(tag.getName());
            int bgColor = ContextCompat.getColor(context, R.color.card_view_default_bg);
            int txtColor = ContextCompat.getColor(context, R.color.primary_text);
            if(tag.isSelected()) {
                bgColor = ContextCompat.getColor(context, R.color.primary_dark);
                txtColor = ContextCompat.getColor(context, R.color.drawerBackground);
            }
            rootView.setCardBackgroundColor(bgColor);
            name.setTextColor(txtColor);
        }
    }
}
