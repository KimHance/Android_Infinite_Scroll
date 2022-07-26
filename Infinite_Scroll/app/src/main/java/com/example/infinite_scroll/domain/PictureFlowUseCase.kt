package com.example.infinite_scroll.domain

import com.example.infinite_scroll.data.repository.PictureRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PictureFlowUseCase @Inject constructor(
    private val pictureRepository: PictureRepository
) {
    operator fun invoke() = pictureRepository.getPictureList()
        .flowOn(Dispatchers.Default)
}