package com.example.cognito.repo;

import com.example.cognito.App;
import com.example.cognito.dao.RandomPoemDatabase;
import com.example.cognito.model.Favourites;
import com.example.cognito.model.FavouritesBody;
import com.example.cognito.model.PoemModel;
import com.example.cognito.model.TitleSearch;
import com.example.cognito.network.RetrofitInstance;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.example.cognito.common.Constants.ACCESS_TOKEN;

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
        return RetrofitInstance.service.putFavourite("Bearer " + ACCESS_TOKEN, body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Completable removeFromFavourites(String poemTitle) {
        FavouritesBody body = new FavouritesBody(poemTitle);
        return RetrofitInstance.service.removeFavourite("Bearer " + ACCESS_TOKEN, body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<Favourites> getFavouritesList() {
        return RetrofitInstance.service.getFavourites("Bearer " + ACCESS_TOKEN)
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
