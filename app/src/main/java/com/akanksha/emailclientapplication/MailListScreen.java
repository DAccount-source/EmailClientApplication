package com.akanksha.emailclientapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MailListScreen extends AppCompatActivity {
    List<ItemData> itemList;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mail_list_layout);

        final RecyclerView mailList=findViewById(R.id.mailList);
        mailList.setLayoutManager(new LinearLayoutManager(this));

        itemList=new ArrayList<>();

        for(int i=1;i<20;i++){
            ItemData itemData=new ItemData();
            itemData.setUserName("User "+i);
            itemData.setSubject("Subject "+i);
            itemList.add(itemData);
        }

        mailList.setAdapter(new mailAdapter(itemList));

    }
}
