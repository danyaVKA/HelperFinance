package com.example.helperfinance.ui.chat_bot_Fragment;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/* Класс извлечения данных из "задней части"*/

public interface RetrofitAPI {

    @GET
    Call<MsqModal> getMessage(@Url String url);

}
