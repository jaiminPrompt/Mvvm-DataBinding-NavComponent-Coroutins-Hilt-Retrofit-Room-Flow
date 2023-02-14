package com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Query("SELECT * FROM user ORDER BY id ASC")
    fun getData(): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Query("DELETE FROM user")
    fun deleteAll()

}