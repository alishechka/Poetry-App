package com.example.cognito.network;

import com.example.cognito.model.PoemModel;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface PoemClient {
    @GET("random")
    Single<PoemModel> getRandomPoem();
}
