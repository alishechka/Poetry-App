package com.example.cognito.repo;

import com.example.cognito.model.PoemModel;
import com.example.cognito.network.RetrofitInstance;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Repository {

    public Single<PoemModel> getRandomPoemRepo() {
        return RetrofitInstance.service.getRandomPoem()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
