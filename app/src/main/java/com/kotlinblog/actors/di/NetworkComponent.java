package com.kotlinblog.actors.di;

import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {RetrofitModule.class, PicassoModule.class})
public interface NetworkComponent {
    Picasso getPicasso();

    Retrofit getRetrofit();
}
