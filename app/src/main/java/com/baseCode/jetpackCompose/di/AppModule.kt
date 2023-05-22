package com.baseCode.jetpackCompose.di

import android.util.Log
import com.baseCode.jetpackCompose.data.remote.DemoAPi
import com.baseCode.jetpackCompose.data.repository.DemoRepoImpl
import com.baseCode.jetpackCompose.domain.repository.DemoRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @Provides
    @Singleton
    fun provideRepository(api: DemoAPi): DemoRepo {
        Log.e("AppModule", "provideRepository: ", )
        return DemoRepoImpl(api)
    }

}