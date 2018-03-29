package com.kotlinblog.actors;

import android.app.Activity;
import android.app.Application;
import android.support.annotation.NonNull;

import com.kotlinblog.actors.di.ApplicationModule;
import com.kotlinblog.actors.di.DaggerNetworkComponent;
import com.kotlinblog.actors.di.NetworkComponent;
import com.kotlinblog.actors.di.NetworkModule;
import com.kotlinblog.actors.di.PicassoModule;
import com.kotlinblog.actors.di.RetrofitModule;

import timber.log.Timber;

public class App extends Application {

    private NetworkComponent mComponent;
    private NetworkComponent mAnotherComponent;

    public static App get(Activity activity) {
        return (App) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initializeDagger();
        initializeTimber();
    }

    private void initializeDagger() {
        mComponent = DaggerNetworkComponent.builder()
                .applicationModule(new ApplicationModule(this)) // passing ApplicationContext here
                .networkModule(new NetworkModule())
                .picassoModule(new PicassoModule())
                .retrofitModule(new RetrofitModule())
                .build();

        //TODO another component for the demo purposes
        mAnotherComponent = DaggerNetworkComponent.builder()
                .applicationModule(new ApplicationModule(this))
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

    public NetworkComponent getComponent() {
        return mComponent;
    }

    public NetworkComponent getAnotherComponent() {
        return mAnotherComponent;
    }
}
