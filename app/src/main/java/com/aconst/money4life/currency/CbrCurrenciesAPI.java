package com.aconst.money4life.currency;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CbrCurrenciesAPI {
    @GET("XML_val.asp?d=0")
    Call<CbrValuta> getData();
}
