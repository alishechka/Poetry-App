package com.example.cognito.network;

import com.example.cognito.model.FavouritesBody;
import com.example.cognito.model.PoemModel;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface PoemClient {
    @GET("random")
    Single<PoemModel> getRandomPoem();

    @POST("favourites")
    Completable putFavourite(@Header("Authorization") String token, @Body FavouritesBody poemTitle);
}
