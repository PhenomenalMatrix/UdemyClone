package com.junDev.udemyclone.domain.home_fragment.repository

import com.junDev.udemyclone.common.base.BaseResult
import com.junDev.udemyclone.domain.home_fragment.model.CoursesResponse
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    suspend fun getCourses(): Flow<BaseResult<CoursesResponse, String>>

}