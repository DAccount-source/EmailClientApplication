package com.akanksha.emailclientapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DrawerItemRecyclerViewAdapter extends RecyclerView.Adapter<DrawerItemRecyclerViewAdapter.ItemViewHolder> {

    private String[] itemData;
    private ClickListener listener;

    DrawerItemRecyclerViewAdapter(String[] data, ClickListener clickListener ) {
        this.itemData = data;
        this.listener = clickListener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recyclerview_text, viewGroup,false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, final int position) {

        itemViewHolder.textView.setText(itemData[position]);
        itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClicked(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemData.length;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{

        TextView textView;

        ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.itemName);
        }
    }

    interface ClickListener {
        void onItemClicked(int position);
    }
}