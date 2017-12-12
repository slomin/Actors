package com.kotlinblog.actors.di

import com.kotlinblog.actors.view.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class)])
interface AppComponent {
    fun inject(viewModel: MainViewModel)
}
