package com.example.cognito.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.cognito.daoconverters.StringListConverter;
import com.example.cognito.model.PoemModel;

import static com.example.cognito.common.Constants.POEM_TABLE_NAME;

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
                            RandomPoemDatabase.class, POEM_TABLE_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
