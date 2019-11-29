package com.android.model;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.android.service.RestApiService;
import com.android.service.RetrofitInstance;
import com.android.utils.SharePreferenceUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FactsRepository {
    private ArrayList<Facts> facts = new ArrayList<>();
    private MutableLiveData<List<Facts>> mutableLiveData = new MutableLiveData<>();
    private Application application;
    public FactsRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Facts>> getMutableLiveData() {

        RestApiService apiService = RetrofitInstance.getApiService();

        Call<FactsWrapper> call = apiService.getFacts();

        call.enqueue(new Callback<FactsWrapper>() {
            @Override
            public void onResponse(Call<FactsWrapper> call, Response<FactsWrapper> response) {
                FactsWrapper mBlogWrapper = response.body();
                SharePreferenceUtils.write(SharePreferenceUtils.TITLE, mBlogWrapper.getTitle());//save string in shared preference.
                if (mBlogWrapper != null && mBlogWrapper.getRows() != null) {
                    facts = (ArrayList<Facts>) mBlogWrapper.getRows();
                    mutableLiveData.setValue(facts);
                }
            }

            @Override
            public void onFailure(Call<FactsWrapper> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}
