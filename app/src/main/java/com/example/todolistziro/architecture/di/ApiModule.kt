package com.example.todolistziro.architecture.di

import com.example.todolistziro.architecture.network.RetrofitProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideLoginApi() = RetrofitProvider().provideLoginApi()

    @Provides
    @Singleton
    fun provideIssueApi() = RetrofitProvider().provideIssueApi()

}