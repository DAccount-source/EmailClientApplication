package com.akanksha.emailclientapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class mailAdapter extends RecyclerView.Adapter<mailAdapter.mailListViewholder> {

    private List<ItemData> itemData;

    public mailAdapter(List<ItemData> itemData){
        this.itemData=itemData;
    }
    @NonNull
    @Override
    public mailListViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.mail_list_data_layout,viewGroup,false);
        return new mailListViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull mailListViewholder mail_list_viewholder, int i) {
        ItemData item=itemData.get(i);
        mail_list_viewholder.userName.setText(item.getUserName());
        mail_list_viewholder.subject.setText(item.getSubject());

    }

    @Override
    public int getItemCount() {
        return itemData.size();
    }

    public class mailListViewholder extends RecyclerView.ViewHolder {
        TextView userName,subject;
        public mailListViewholder(@NonNull View itemView) {
            super(itemView);
            userName=itemView.findViewById(R.id.txtUserName);
            subject=itemView.findViewById(R.id.txtSubject);
        }
    }
}
