package com.boss.poetrydb.repo;

import com.boss.poetrydb.common.Constants;
import com.boss.poetrydb.model.Favourites;
import com.boss.poetrydb.model.FavouritesBody;
import com.boss.poetrydb.model.PoemModel;
import com.boss.poetrydb.model.TitleSearch;
import com.boss.poetrydb.network.RetrofitInstance;
import com.boss.poetrydb.App;
import com.boss.poetrydb.dao.RandomPoemDatabase;

import java.util.List;

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

    //for RoomDB , internal DB and not DynamoDB, remote DB
    public Completable addPoemToDb(PoemModel model) {
        return RandomPoemDatabase.getInstance(App.getAppContext()).randomPoemDao().insertRandomPoem(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<PoemModel> getPoemFromDb(String title) {
        return RandomPoemDatabase.getInstance(App.getAppContext()).randomPoemDao().getPoem(title)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Completable addToFavouritesList(String poemTitle) {
        FavouritesBody body = new FavouritesBody(poemTitle);
        return RetrofitInstance.service.putFavourite("Bearer " + Constants.ACCESS_TOKEN, body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Completable removeFromFavourites(String poemTitle) {
        FavouritesBody body = new FavouritesBody(poemTitle);
        return RetrofitInstance.service.removeFavourite("Bearer " + Constants.ACCESS_TOKEN, body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<Favourites> getFavouritesList() {
        return RetrofitInstance.service.getFavourites("Bearer " + Constants.ACCESS_TOKEN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public Single<PoemModel> getPoem(String title) {
        return RetrofitInstance.service.getPoem(title)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<List<TitleSearch>> getTitleSearch(String title) {
        return RetrofitInstance.service.getTitleSearch(title)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
