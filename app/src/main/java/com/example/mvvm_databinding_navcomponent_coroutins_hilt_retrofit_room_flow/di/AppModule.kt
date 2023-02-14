package com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.di

import android.content.Context
import androidx.room.Room
import com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.data.local.UserDao
import com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.data.local.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): UserDatabase =
        Room.databaseBuilder(context, UserDatabase::class.java, "userDatabase")
            .allowMainThreadQueries()
            .build()

    @Provides
    fun providesUserDao(userDatabase: UserDatabase): UserDao =
        userDatabase.userDao()

}