package com.boss.cognito.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.boss.cognito.common.Constants;
import com.boss.cognito.model.PoemModel;
import com.boss.cognito.daoconverters.StringListConverter;

@Database(entities = {PoemModel.class}, version = 1, exportSchema = false)
@TypeConverters({StringListConverter.class})
public abstract class RandomPoemDatabase extends RoomDatabase {

    public abstract PoemDao randomPoemDao();

    private static volatile RandomPoemDatabase INSTANCE;

    public static RandomPoemDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (RandomPoemDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RandomPoemDatabase.class, Constants.POEM_TABLE_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
