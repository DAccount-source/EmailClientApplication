package com.akanksha.emailclientapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import static com.akanksha.emailclientapplication.HomeScreen.BODY;
import static com.akanksha.emailclientapplication.HomeScreen.FROM_ADDRESS;
import static com.akanksha.emailclientapplication.HomeScreen.SUBJECT;

public class MailAdapter extends RecyclerView.Adapter<MailAdapter.MailListViewholder> {

    private List<ItemData> itemData;
    ClickedOnItem clickedOnItem;

    MailAdapter(List<ItemData> itemData, ClickedOnItem clickedOnItem){
        this.itemData=itemData;
        this.clickedOnItem = clickedOnItem;
    }
    @NonNull
    @Override
    public MailListViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.mail_list_data_layout,viewGroup,false);
        return new MailListViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MailListViewholder mail_list_viewholder, int i) {
        final ItemData item=itemData.get(i);
        mail_list_viewholder.userName.setText("From: "+item.getUserName());
        mail_list_viewholder.subject.setText("Subject: "+item.getSubject());
        mail_list_viewholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedOnItem.onItemClicked(item);
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemData.size();
    }

    public class MailListViewholder extends RecyclerView.ViewHolder {
        TextView userName,subject;
        public MailListViewholder(@NonNull View itemView) {
            super(itemView);
            userName=itemView.findViewById(R.id.txtUserName);
            subject=itemView.findViewById(R.id.txtSubject);
        }
    }

    interface ClickedOnItem {
        void onItemClicked(ItemData itemData);
    }
}
