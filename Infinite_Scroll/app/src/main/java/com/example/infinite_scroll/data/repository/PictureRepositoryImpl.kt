package com.example.infinite_scroll.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.infinite_scroll.data.paging.PicturePagingSource
import com.example.infinite_scroll.domain.model.Picture
import com.example.infinite_scroll.network.UnsplashService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PictureRepositoryImpl @Inject constructor(
    private val service: UnsplashService
) : PictureRepository {

    companion object {
        const val PAGE_SIZE = 30
    }

    override fun getPictureList(): Flow<PagingData<Picture>> =
        Pager(PagingConfig(PAGE_SIZE)) {
            PicturePagingSource(service)
        }.flow

}