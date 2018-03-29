package com.kotlinblog.actors.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private final Context mContext;

    // Providing context as a constructor arg
    public ApplicationModule(Context context) {
        this.mContext = context;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return mContext;
    }
}

