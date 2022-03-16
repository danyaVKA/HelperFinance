package com.example.helperfinance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button btn;
    private TextView forget;
    private TextView signup;


    private FirebaseAuth Auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Auth=FirebaseAuth.getInstance();

        if (Auth.getCurrentUser() !=null){
            startActivity(new Intent(getApplicationContext(), HomeActivity1.class));
        }


        login_ditails();
    }

    private void login_ditails(){
        email=(EditText)findViewById(R.id.email_login);
        password=(EditText)findViewById(R.id.password_login);
        btn=(Button)findViewById(R.id.btn_login);
        forget=(TextView)findViewById(R.id.forget_password);
        signup=(TextView)findViewById(R.id.signup_reg);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = email.getText().toString().trim();
                String Pass = password.getText().toString().trim();

                if (TextUtils.isEmpty(Email)){
                    email.setError("Введите Email");
                    return;
                }
                if (TextUtils.isEmpty(Pass)) {
                    password.setError("Введите пароль");
                    return;
                }


                Auth.signInWithEmailAndPassword(Email,Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            startActivity(new Intent(getApplicationContext(),HomeActivity1.class));

                            Toast.makeText(getApplicationContext(),"Вход завершён", Toast.LENGTH_SHORT).show();
                        }else{

                            Toast.makeText(getApplicationContext(),"Ошибка входа", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ReqistrationActivity.class));
            }
        });

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ReseatActivity.class));
            }
        });
    }
}