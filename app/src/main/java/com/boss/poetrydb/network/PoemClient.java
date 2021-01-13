package com.boss.poetrydb.network;

import com.boss.poetrydb.model.Favourites;
import com.boss.poetrydb.model.FavouritesBody;
import com.boss.poetrydb.model.PoemModel;
import com.boss.poetrydb.model.TitleSearch;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface PoemClient {
    @GET("random")
    Single<PoemModel> getRandomPoem();

    @POST("favourites")
    Completable putFavourite(@Header("Authorization") String token, @Body FavouritesBody poemTitle);

    @GET("favourites")
    Single<Favourites> getFavourites(@Header("Authorization") String token);

    @PUT("favourites")
    Completable removeFavourite(@Header("Authorization") String token, @Body FavouritesBody poemTitle);

    @GET("title2")
    Single<PoemModel> getPoem(@Query("title") String title);

    @GET("titleSearch")
    Single<List<TitleSearch>> getTitleSearch(@Query("title") String title);
}
