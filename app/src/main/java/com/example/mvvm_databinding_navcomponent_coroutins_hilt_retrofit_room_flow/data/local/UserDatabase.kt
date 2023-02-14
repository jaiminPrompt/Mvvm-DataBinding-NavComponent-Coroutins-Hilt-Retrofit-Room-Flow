package com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}