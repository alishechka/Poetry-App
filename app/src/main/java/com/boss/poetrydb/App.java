package com.boss.poetrydb;

import android.app.Application;
import android.content.Context;

import timber.log.Timber;

public class App extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        App.context = getApplicationContext();
        Timber.plant(new Timber.DebugTree());
    }

    public static Context getAppContext() {
        return App.context;
    }
}
