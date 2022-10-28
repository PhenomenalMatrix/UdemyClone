package com.junDev.udemyclone.di.home_fragment

import com.junDev.udemyclone.data.home_fragment.remote.HomeApi
import com.junDev.udemyclone.data.home_fragment.repository.HomeRepositoryImpl
import com.junDev.udemyclone.di.common.NetworkModule
import com.junDev.udemyclone.domain.home_fragment.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class HomeModule {

    @Singleton
    @Provides
    fun providesFilmsApi(retrofit: Retrofit): HomeApi {
        return retrofit.create(HomeApi::class.java)
    }


    @Singleton
    @Provides
    fun providePeopleRepository(homeApi: HomeApi): HomeRepository {
        return HomeRepositoryImpl(homeApi)
    }

}