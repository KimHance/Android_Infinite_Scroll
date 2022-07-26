package com.example.infinite_scroll.data.repository

import androidx.paging.PagingData
import com.example.infinite_scroll.domain.model.Picture
import kotlinx.coroutines.flow.Flow


interface PictureRepository {

    fun getPictureList(): Flow<PagingData<Picture>>

}