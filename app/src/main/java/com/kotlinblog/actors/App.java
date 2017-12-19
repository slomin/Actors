package com.kotlinblog.actors;

import android.app.Application;
import android.support.annotation.NonNull;

import com.kotlinblog.actors.di.AppComponent;
import com.kotlinblog.actors.di.AppModule;
import com.kotlinblog.actors.di.DaggerAppComponent;

import timber.log.Timber;

public class App extends Application {

    private static AppComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeDagger();
        initializeTimber();
    }

    private void initializeDagger() {
        mComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    private void initializeTimber() {
        // Timber now logs line numbers by default + convenience TAG added
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree() {
                @NonNull
                @Override
                protected String createStackElementTag(@NonNull StackTraceElement element) {
                    return "Timber - " + super.createStackElementTag(element) + ":" + element.getLineNumber();
                }
            });
        }
    }

    public static AppComponent getComponent() {
        return mComponent;
    }
}
