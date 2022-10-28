package com.junDev.udemyclone.domain.home_fragment.usecase

import com.junDev.udemyclone.common.base.BaseResult
import com.junDev.udemyclone.domain.home_fragment.model.CoursesResponse
import com.junDev.udemyclone.domain.home_fragment.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCoursesUseCase @Inject constructor(private val homeRepository: HomeRepository) {
    suspend fun invoke(): Flow<BaseResult<CoursesResponse, String>> = homeRepository.getCourses()
}