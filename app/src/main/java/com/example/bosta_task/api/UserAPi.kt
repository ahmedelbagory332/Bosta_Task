package com.example.bosta_task.api

import com.example.bosta_task.Models.AlbumsModel
import com.example.bosta_task.Models.PhotoModel
import com.example.bosta_task.Models.UserModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserAPi {
    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }

    @GET("users")
    suspend fun getUsers(): List<UserModel>

    @GET("albums")
    suspend fun getUserAlbums(@Query("userId")  userId:Int): List<AlbumsModel>

    @GET("photos")
    suspend fun getPhotos(@Query("albumId")  albumId:Int): List<PhotoModel>
}