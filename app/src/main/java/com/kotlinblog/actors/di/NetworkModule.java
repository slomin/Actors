package com.kotlinblog.actors.di;

import android.content.Context;

import com.kotlinblog.actors.BuildConfig;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

@Module
//@Module(includes = {ApplicationModule.class})
public class NetworkModule {

    @Provides
    @Singleton
    public OkHttpClient okHttpClient(HttpLoggingInterceptor httpLoggingInterceptor, Cache cache) {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .cache(cache)
                .build();
    }

    @Provides
    @Singleton
    public HttpLoggingInterceptor httpLoggingInterceptor() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(message -> Timber.i(message));

        //Logging http requests in debug mode
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        return interceptor;
    }

    @Provides
    @Singleton
    public Cache cache(File cacheFile) {
        return new Cache(cacheFile, 10_000_000); // 10MB cache size
    }

    @Provides
    @Singleton
    public File cacheFile(Context context) {
        return new File(context.getCacheDir(), "okhttp_cache_file");
    }

}
