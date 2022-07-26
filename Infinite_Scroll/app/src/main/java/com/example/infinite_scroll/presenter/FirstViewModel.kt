package com.example.infinite_scroll.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.infinite_scroll.data.repository.Repository
import com.example.infinite_scroll.domain.model.Picture
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirstViewModel @Inject constructor(
    private val mainRepository: Repository
) : ViewModel() {

    var getMorePictureFlag = false

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val addItem = MutableStateFlow<List<Picture>>(emptyList())

    private val _pictureList = MutableStateFlow(listOf<Picture>())
    val pictureList: StateFlow<List<Picture>> = _pictureList.asStateFlow()

    fun getPictureList(page: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            kotlin.runCatching {
                if (!getMorePictureFlag) _pictureList.value = mainRepository.getPhotoList().results
                else {
                    addItem.value = mainRepository.getPhotoList(page).results
                    _pictureList.update {
                        it.toMutableList().apply {
                            addAll(addItem.value)
                        }.toList()
                    }
                }
            }.onSuccess {
                _isLoading.value = false
            }.onFailure {
                _isLoading.value = false
            }
        }
    }

}