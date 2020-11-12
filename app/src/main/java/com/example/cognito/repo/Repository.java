package com.example.cognito.repo;

import com.example.cognito.App;
import com.example.cognito.dao.PoemDao;
import com.example.cognito.dao.RandomPoemDatabase;
import com.example.cognito.model.PoemModel;
import com.example.cognito.network.RetrofitInstance;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Repository {
    public Single<PoemModel> getRandomPoemRepo() {
        return RetrofitInstance.service.getRandomPoem()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Completable addPoemToDb(PoemModel model) {
        return RandomPoemDatabase.getInstance(App.getAppContext()).randomPoemDao().insertRandomPoem(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
