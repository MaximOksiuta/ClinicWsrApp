package com.example.clinicwsrapp.common.di

import com.example.clinicwsrapp.model.LocalData
import com.example.clinicwsrapp.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule{

    @Singleton
    @Provides
    fun provideLocalData()  = LocalData()
}