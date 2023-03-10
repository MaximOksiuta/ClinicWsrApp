package com.example.clinicwsrapp.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clinicwsrapp.common.datamodels.OnBoardingData
import com.example.clinicwsrapp.model.LocalData
import com.example.clinicwsrapp.repository.Repository
import kotlinx.coroutines.launch
import javax.inject.Inject

class OnBoardingViewModel  @Inject constructor(private val repository: Repository): ViewModel() {
    private val _onBoardingData: MutableLiveData<OnBoardingData> = MutableLiveData()
    val onBoardingData: LiveData<OnBoardingData>
        get() = _onBoardingData

    private val _originalSize: MutableLiveData<Int> = MutableLiveData()
    val originalSize: LiveData<Int>
        get() = _originalSize
    private val _nowSize: MutableLiveData<Int> = MutableLiveData()
    val nowSize: LiveData<Int>
        get() = _nowSize

    fun updateOnBoardingData(){
        _onBoardingData.value = repository.getOnBoardingData()
    }

    fun dropItem() {
        repository.nextItem()
    }

    fun updateOriginalSize(){
        _originalSize.value = repository.getOriginalSize()
    }

    fun updateNowSize(){
        _nowSize.value = repository.getNowSize()
    }
}