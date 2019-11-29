package com.android.service;

import com.android.model.FactsWrapper;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestApiService {
    @GET("facts.json")
    Call<FactsWrapper> getFacts();

}
