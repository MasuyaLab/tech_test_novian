package com.example.technical_test_novian.di

import com.example.technical_test_novian.data.dataSrc.ApiService
import com.example.technical_test_novian.data.repo_impl.UserRepositoryImpl
import com.example.technical_test_novian.domain.repository.UserRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideUserRepository(apiService: ApiService, gson: Gson): UserRepository {
        return UserRepositoryImpl(apiService, gson)
    }

}