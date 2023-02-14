package com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun getString(): String {
        return "This is demo of hilt dependency injection..."
    }

}