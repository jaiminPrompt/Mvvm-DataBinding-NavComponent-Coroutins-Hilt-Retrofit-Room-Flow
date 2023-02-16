package com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.ui.auth.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.ui.auth.models.Users

@Dao
interface UsersDao {

    @Query("SELECT * FROM users ORDER BY id ASC")
    suspend fun getData(): List<Users>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(users: Users)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(usersList: List<Users>)

    @Query("DELETE FROM users")
    suspend fun deleteAll()

}