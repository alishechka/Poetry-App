package com.boss.poetrydb.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.boss.poetrydb.model.PoemModel;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface PoemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertRandomPoem(PoemModel model);

    @Query("SELECT * FROM poem_model  WHERE title = :poemTitle")
    Single<PoemModel> getPoem(String poemTitle);

}
