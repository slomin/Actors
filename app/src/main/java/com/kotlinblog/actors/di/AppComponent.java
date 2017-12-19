package com.kotlinblog.actors.di;

import com.kotlinblog.actors.view.MainViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class
})
public interface AppComponent {
    void inject(MainViewModel viewModel);
}