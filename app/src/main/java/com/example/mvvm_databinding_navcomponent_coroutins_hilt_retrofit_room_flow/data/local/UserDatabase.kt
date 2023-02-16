package com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.ui.auth.dao.UsersDao
import com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.ui.auth.models.Users

@Database(entities = [Users::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun usersDao(): UsersDao

}