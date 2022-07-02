package com.example.infinite_scroll.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.infinite_scroll.data.MainRepository
import com.example.infinite_scroll.model.Picture
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirstViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _pictureList = MutableStateFlow<List<Picture>>(emptyList())
    val pictureList: StateFlow<List<Picture>> = _pictureList.asStateFlow()


    fun getPictureList(){
        viewModelScope.launch {
            _isLoading.value = true
            kotlin.runCatching {
                _pictureList.value = mainRepository.getPhotoList().results
            }.onSuccess {
                _isLoading.value = false
            }.onFailure {
                _isLoading.value = false
            }
        }
    }

}