package com.example.clinicwsrapp.model

import com.example.clinicwsrapp.R
import com.example.clinicwsrapp.common.datamodels.OnBoardingData
import dagger.Provides
import javax.inject.Singleton

class LocalData {
    val onBoardingList = mutableListOf(OnBoardingData(R.drawable.onboarding1, "Анализы", "Экспресс сбор и получениие проб"),
        OnBoardingData(R.drawable.onboarding2, "Уведомления", "Вы быстро узнаете о результатах"),
        OnBoardingData(R.drawable.onboarding3, "Мониторинг", "Наши врачи всегда наблюдают за вашими показателями здоровья"))
    fun getOnBoardingQueue(): List<OnBoardingData> = onBoardingList

    fun dropItem(){
        onBoardingList.removeAt(0)
    }

    fun getAllItemsCount() = 3

    fun getNowItemsCount() = onBoardingList.size
}