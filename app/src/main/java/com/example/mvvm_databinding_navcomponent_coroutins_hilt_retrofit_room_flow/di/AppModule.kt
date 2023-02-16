package com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.di

import android.content.Context
import androidx.room.Room
import com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.data.local.UserDatabase
import com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.data.server.ApiServices
import com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.ui.auth.dao.UsersDao
import com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): UserDatabase =
        Room.databaseBuilder(context, UserDatabase::class.java, "userDatabase")
            .allowMainThreadQueries()
            .build()

    @Provides
    fun providesUserDao(userDatabase: UserDatabase): UsersDao =
        userDatabase.usersDao()

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun providesApiServices(retrofit: Retrofit): ApiServices {
        return retrofit.create(ApiServices::class.java)
    }

    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val requestInterceptor = Interceptor { chain ->
            val url = chain.request()
                .url
                .newBuilder()
                .build()

            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()

            return@Interceptor chain.proceed(request)
        }

        return OkHttpClient
            .Builder()
            .addInterceptor(requestInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

}

