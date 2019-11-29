package com.android.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.android.model.Facts;
import com.android.model.FactsRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private FactsRepository movieRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new FactsRepository(application);
    }

    public LiveData<List<Facts>> getAllBlog() {
        return movieRepository.getMutableLiveData();
    }


}
