package com.junDev.udemyclone.data.home_fragment.repository

import com.junDev.udemyclone.common.base.BaseResult
import com.junDev.udemyclone.data.home_fragment.remote.HomeApi
import com.junDev.udemyclone.domain.home_fragment.model.CoursesResponse
import com.junDev.udemyclone.domain.home_fragment.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val homeApi: HomeApi): HomeRepository {
    override suspend fun getCourses(): Flow<BaseResult<CoursesResponse, String>> {
        return flow {
            val response = homeApi.getCourses()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let { emit(BaseResult.Success(it)) }
            }else{
                emit(BaseResult.Error(response.message()))
            }
        }
    }
}