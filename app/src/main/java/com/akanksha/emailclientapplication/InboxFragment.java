package com.akanksha.emailclientapplication;

import android.app.IntentService;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;

import static com.akanksha.emailclientapplication.HomeScreen.BODY;
import static com.akanksha.emailclientapplication.HomeScreen.EMAIL_ID;
import static com.akanksha.emailclientapplication.HomeScreen.FROM_ADDRESS;
import static com.akanksha.emailclientapplication.HomeScreen.PASSWORD;
import static com.akanksha.emailclientapplication.HomeScreen.SUBJECT;

public class InboxFragment extends Fragment implements MailAdapter.ClickedOnItem {

    private RecyclerView recyclerView;
    private ImageButton newMailBtn;
    private List<ItemData> itemDataList;
    private MailAdapter mailAdapter;
    private ProgressBar progressBar;
    private String emailId, pass;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getActivity().getIntent() != null) {
            emailId = getActivity().getIntent().getStringExtra(EMAIL_ID);
            pass = getActivity().getIntent().getStringExtra(PASSWORD);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mail_list_layout, container, false);
        recyclerView = view.findViewById(R.id.mailList);
        newMailBtn = view.findViewById(R.id.composeBtn);
        progressBar = view.findViewById(R.id.progress_circular);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        itemDataList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        mailAdapter = new MailAdapter(itemDataList, this);
        recyclerView.setAdapter(mailAdapter);

        new ReaderTask().execute();

        newMailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ComposeActivity.class);
                intent.putExtra(EMAIL_ID, emailId);
                intent.putExtra(PASSWORD, pass);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onItemClicked(ItemData itemData) {

        Bundle bundle = new Bundle();
        bundle.putString(SUBJECT, itemData.getSubject());
        bundle.putString(FROM_ADDRESS, itemData.getUserName());
        bundle.putString(BODY, itemData.getBodyData());


            Fragment fragment = new OpenEmailFragment();
            fragment.setArguments(bundle);

            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content_frame, fragment)
                    .addToBackStack(null)
                    .commit();
    }


    public class ReaderTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mailAdapter.notifyDataSetChanged();
            progressBar.setVisibility(View.GONE);
        }

        @Override
        protected Void doInBackground(Void... params) {

            Properties props = new Properties();
            props.setProperty("mail.store.protocol", "imaps");
            try {
                Session session = Session.getInstance(props, null);
                Store store = session.getStore();
                //if it is yahoo add :: imap.yahoo.com
                store.connect("imap.gmail.com", emailId, pass);
                Folder inbox = store.getFolder("INBOX");
                inbox.open(Folder.READ_ONLY);

                for(int i = 0; i < 10; i++ ){

                    ItemData itemData = new ItemData();

                    javax.mail.Message msg = inbox.getMessage(inbox.getMessageCount() - i);
                    javax.mail.Address[] in = msg.getFrom();
                    for (javax.mail.Address address : in) {
                        String userName = address.toString();
                        itemData.setUserName(userName);
                        System.out.println("FROM:" + address.toString());
                    }

                    Multipart mp = (Multipart) msg.getContent();
                    BodyPart bp = mp.getBodyPart(0);

                    itemData.setSubject(msg.getSubject()==null?"":msg.getSubject());
                    itemData.setBodyData(bp.getContent().toString());
                    itemData.setDateTime(""+msg.getSentDate().getTime());

                    System.out.println("SENT DATE:" + msg.getSentDate());
                    System.out.println("SUBJECT:" + msg.getSubject());
                    System.out.println("CONTENT:" + bp.getContent());

                    itemDataList.add(itemData);

                }
            } catch (Exception mex) {
                mex.printStackTrace();
            }
            return null;
        }
    }


}
