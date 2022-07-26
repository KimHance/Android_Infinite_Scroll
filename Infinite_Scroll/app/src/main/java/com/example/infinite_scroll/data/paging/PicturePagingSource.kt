package com.example.infinite_scroll.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.infinite_scroll.domain.model.Picture
import com.example.infinite_scroll.network.UnsplashService
import kotlinx.coroutines.delay
import okio.IOException

const val STARTING_KEY = 1
private const val DELAY_MILLIS = 3_000L

class PicturePagingSource(
    private val unsplashService: UnsplashService
) : PagingSource<Int, Picture>() {

    override fun getRefreshKey(state: PagingState<Int, Picture>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Picture> {
        val position = params.key ?: STARTING_KEY
        if (position != STARTING_KEY) delay(DELAY_MILLIS)
        return try {
            val pictures = unsplashService.getPhotoList(position).results
            LoadResult.Page(
                data = pictures,
                prevKey = if (position == STARTING_KEY) null else position - 1,
                nextKey = if (pictures.isEmpty()) null else position + 1
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        }
    }

}