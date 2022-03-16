package com.example.helperfinance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ReseatActivity extends AppCompatActivity {

    private EditText email_reseat;
    private Button btn_login;
    private TextView remember_pass;
    private TextView signup_reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reseat);

        reseat_account();
    }

    private void reseat_account(){
        email_reseat=(EditText)findViewById(R.id.email_reseat);
        btn_login=(Button)findViewById(R.id.btn_login);
        remember_pass=(TextView) findViewById(R.id.remember_pass);
        signup_reg=(TextView) findViewById(R.id.signup_reg);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = email_reseat.getText().toString().trim();

                if (TextUtils.isEmpty(Email)){
                    email_reseat.setError("Введите Email");
                    return;
                }

            }
        });

    signup_reg.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(getApplicationContext(), ReqistrationActivity.class));
        }
    });

    remember_pass.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    });



    }
}