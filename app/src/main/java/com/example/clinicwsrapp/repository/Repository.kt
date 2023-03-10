package com.example.clinicwsrapp.repository

import com.example.clinicwsrapp.common.datamodels.OnBoardingData
import com.example.clinicwsrapp.model.LocalData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val localData: LocalData) {
    fun getOnBoardingData(): OnBoardingData = localData.getOnBoardingQueue()[0]

    fun nextItem(){
        localData.dropItem()
    }

    fun getOriginalSize() = localData.getAllItemsCount()

    fun getNowSize() = localData.getNowItemsCount()
}