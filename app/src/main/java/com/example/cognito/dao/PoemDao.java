package com.example.cognito.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.cognito.model.PoemModel;

import io.reactivex.Completable;

import static com.example.cognito.common.Constants.POEM_TABLE_NAME;

@Dao
public interface PoemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Completable insertRandomPoem(PoemModel model);


}
