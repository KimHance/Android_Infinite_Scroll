package com.example.infinite_scroll.data.repository

import com.example.infinite_scroll.network.UnsplashService
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class Repository @Inject constructor(
    private val unsplashService: UnsplashService
) {
    suspend fun getPhotoList(page: Int = 1) =
        unsplashService.getPhotoList(page)
}