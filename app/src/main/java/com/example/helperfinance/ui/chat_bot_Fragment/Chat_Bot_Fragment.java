package com.example.helperfinance.ui.chat_bot_Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helperfinance.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Chat_Bot_Fragment extends AppCompatActivity {

    private RecyclerView chatsRV;
    private EditText userMsgEdit;
    private FloatingActionButton sendMsgFAB;
    private final  String BOT_KEY = "bot";
    private final  String USER_KEY = "user";
    private ArrayList<ChatsModal>chatsModalArrayList;
    private CharAdapter charAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_chat_bot);
        chatsRV = findViewById(R.id.idRVChars);
        userMsgEdit = findViewById(R.id.idEditMessage);
        sendMsgFAB = findViewById(R.id.idFABSend);
        chatsModalArrayList = new ArrayList<>();
        charAdapter = new CharAdapter(chatsModalArrayList, this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        chatsRV.setLayoutManager(manager);
        chatsRV.setAdapter(charAdapter);


        sendMsgFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(userMsgEdit.getText().toString().isEmpty()){
                    Toast.makeText(Chat_Bot_Fragment.this, "Пожалуйста, введите сообщение", Toast.LENGTH_SHORT).show();
                    return;
                }
                getResponse(userMsgEdit.getText().toString());
                userMsgEdit.setText("");

            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void getResponse(String message){
        chatsModalArrayList.add(new ChatsModal(message,USER_KEY));
        charAdapter.notifyDataSetChanged();
        String url = "http://api.brainshop.ai/get?bid=162901&key=PaiBiPpiHUVFFt0M&uid=[uid]&msg="+message;
        String Base_URL = "http://api.brainshop.ai/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<MsqModal> call = retrofitAPI.getMessage(url);
        call.enqueue(new Callback<MsqModal>() {
            @Override
            public void onResponse(Call<MsqModal> call, Response<MsqModal> response) {
                if(response.isSuccessful()){
                    MsqModal modal = response.body();
                    chatsModalArrayList.add(new ChatsModal(modal.getCnt(),BOT_KEY));
                    charAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MsqModal> call, Throwable t) {
                chatsModalArrayList.add(new ChatsModal("Кредит — это деньги или товары, которые банк или другая кредитная организация выдает заемщику в долг.", BOT_KEY));
                charAdapter.notifyDataSetChanged();
            }
        });
    }
}

