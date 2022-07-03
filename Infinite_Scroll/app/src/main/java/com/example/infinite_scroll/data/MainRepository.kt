package com.example.infinite_scroll.data

import com.example.infinite_scroll.network.UnsplashService
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject


@ViewModelScoped
class MainRepository @Inject constructor(
    private val unsplashService: UnsplashService
) {
    suspend fun getPhotoList(page: Int = 1) =
        unsplashService.getPhotoList(page)
}