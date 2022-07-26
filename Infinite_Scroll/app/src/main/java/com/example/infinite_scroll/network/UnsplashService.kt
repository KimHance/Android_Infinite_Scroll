package com.example.infinite_scroll.network

import com.example.infinite_scroll.domain.model.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashService {

    @GET("search/photos")
    suspend fun getPhotoList(
        @Query("page") page: Int,
        @Query("query") query: String = "android",
        @Query("client_id") key: String = "SVLT7C5xIqBiqdqqKk-h_dzbPnRUPKh5G2DGksalVE4",
        @Query("per_page") perPage: Int = 30
    ): Response
}