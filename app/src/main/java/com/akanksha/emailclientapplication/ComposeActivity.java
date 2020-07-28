package com.akanksha.emailclientapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import static com.akanksha.emailclientapplication.HomeScreen.EMAIL_ID;
import static com.akanksha.emailclientapplication.HomeScreen.PASSWORD;

public class ComposeActivity extends AppCompatActivity {

    EditText toEdit, subjectEdit, bodyEdit;
    Button sendBtn;
    private String senderEmailId, password;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_acticity);

        toEdit = findViewById(R.id.toId);
        subjectEdit = findViewById(R.id.subjectET);
        bodyEdit = findViewById(R.id.bodyET);
        sendBtn = findViewById(R.id.sendBtn);
        progressBar = findViewById(R.id.progress_send);

        if(getIntent() != null) {
            senderEmailId = getIntent().getStringExtra(EMAIL_ID);
            password = getIntent().getStringExtra(PASSWORD);
        }

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String toAddress = toEdit.getText().toString();
                String subject = subjectEdit.getText().toString();
                String body = bodyEdit.getText().toString();

                if(!toAddress.isEmpty() && !subject.isEmpty()&& !body.isEmpty()) {
                    new SendEmailTask().execute(subject, body, toAddress);
                } else
                    Toast.makeText(ComposeActivity.this, "Please enter mandatory fields", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private class SendEmailTask extends AsyncTask<String, Void, String> {



        @Override
        protected String doInBackground(String... strings) {

            try {

                MailService sender = new MailService(senderEmailId,password);

                sender.sendMail(strings[0], strings[1], senderEmailId, strings[2]);

            } catch (Exception e) {
                Toast.makeText(ComposeActivity.this, "Error while sending", Toast.LENGTH_SHORT).show();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);

        }

        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);
            finished();
        }
    }

    private void finished(){
        Toast.makeText(this, "Mail sent...", Toast.LENGTH_SHORT).show();
        finish();
    }

}
