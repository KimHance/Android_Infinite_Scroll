package com.example.infinite_scroll.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.infinite_scroll.domain.PictureFlowUseCase
import com.example.infinite_scroll.domain.model.Picture
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SecondViewModel @Inject constructor(
    private val pictureFlowUseCase: PictureFlowUseCase
) : ViewModel() {

    private val _picList = MutableStateFlow<PagingData<Picture>>(PagingData.empty())
    val picList = _picList.asStateFlow()

    fun getPictureList() {
        viewModelScope.launch {
            pictureFlowUseCase()
                .cachedIn(viewModelScope)
                .collectLatest { picture ->
                    _picList.emit(picture)
                }
        }
    }

}