package com.akanksha.emailclientapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import static com.akanksha.emailclientapplication.HomeScreen.BODY;
import static com.akanksha.emailclientapplication.HomeScreen.EMAIL_ID;
import static com.akanksha.emailclientapplication.HomeScreen.FROM_ADDRESS;
import static com.akanksha.emailclientapplication.HomeScreen.PASSWORD;
import static com.akanksha.emailclientapplication.HomeScreen.SUBJECT;

public class OpenEmailFragment extends Fragment {

    private TextView subject, fromAddress, emailBody;
    private String mSubject, mFromAddress, mEmailBody;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            mSubject = getArguments().getString(SUBJECT);
            mFromAddress = getArguments().getString(FROM_ADDRESS);
            mEmailBody = getArguments().getString(BODY);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.opened_mail_layout, container, false);

        subject = view.findViewById(R.id.email_subject);
        fromAddress = view.findViewById(R.id.emailFrom);
        emailBody = view.findViewById(R.id.body);
        emailBody.setMovementMethod(new ScrollingMovementMethod());

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(mSubject != null)
            subject.setText("Subject: "+mSubject);
        if(mFromAddress != null)
            fromAddress.setText("From: "+mFromAddress);
        if(mEmailBody != null)
            emailBody.setText("Content Body: "+mEmailBody);
    }

}
