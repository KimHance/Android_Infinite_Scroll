package com.example.infinite_scroll.network

import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashService {

    @GET("search/photos")
    suspend fun getPhotoList(
        @Query("client_id") key: String = "SVLT7C5xIqBiqdqqKk-h_dzbPnRUPKh5G2DGksalVE4",
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 30
    )
}