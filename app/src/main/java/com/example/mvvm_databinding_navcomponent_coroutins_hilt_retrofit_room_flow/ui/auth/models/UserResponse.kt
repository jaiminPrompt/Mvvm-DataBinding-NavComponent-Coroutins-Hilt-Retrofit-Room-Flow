package com.example.mvvm_databinding_navcomponent_coroutins_hilt_retrofit_room_flow.ui.auth.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class UserResponse(

    @field:SerializedName("page")
    val page: Int? = null,

    @field:SerializedName("per_page")
    val perPage: Int? = null,

    @field:SerializedName("total")
    val total: Int? = null,

    @field:SerializedName("total_pages")
    val totalPages: Int? = null,

    @field:SerializedName("data")
    val data: List<Users?>? = null,

    @field:SerializedName("support")
    val support: Support? = null

)

data class Support(

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("text")
    val text: String? = null

)

@Entity(tableName = "users")
data class Users(

    @PrimaryKey(autoGenerate = false)
    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("first_name")
    val firstName: String? = null,

    @field:SerializedName("last_name")
    val lastName: String? = null,

    @field:SerializedName("avatar")
    val avatar: String? = null

)
