package com.aconst.money4life.currency;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CbrRateAPI {
    @GET("XML_dynamic.asp")
    Call<CbrValcurs> getData(@Query("date_req1") String date1, @Query("date_req2") String date2,
                               @Query("VAL_NM_RQ") String currencyCode);
}
