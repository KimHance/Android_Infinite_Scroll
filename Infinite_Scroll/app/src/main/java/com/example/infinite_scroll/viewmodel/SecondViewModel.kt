package com.example.infinite_scroll.viewmodel

import androidx.lifecycle.ViewModel
import com.example.infinite_scroll.data.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SecondViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

}