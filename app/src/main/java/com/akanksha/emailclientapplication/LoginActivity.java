package com.akanksha.emailclientapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText loginEmailID,loginPwd;
    Button btnLogin;
    String emailid="ak";
    String pwd="ak123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginEmailID=findViewById(R.id.loginEmailId);
        loginPwd=findViewById(R.id.loginPwd);

        btnLogin=findViewById(R.id.loginBtn);



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inputEmailId=loginEmailID.getText().toString();
                String inputPwd=loginPwd.getText().toString();
                if(inputEmailId.equals(emailid) && pwd.equals(inputPwd)){
                    Intent i=new Intent(LoginActivity.this,HomeScreen.class);
                    startActivity(i);
                }else{
                    Toast.makeText(LoginActivity.this, "Incorrect Email or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}
