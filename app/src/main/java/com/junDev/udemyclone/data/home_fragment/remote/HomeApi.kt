package com.junDev.udemyclone.data.home_fragment.remote

import com.junDev.udemyclone.domain.home_fragment.model.CoursesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApi {

    @GET("api/v1/courses/")
    suspend fun getCourses(
        @Query("page") page: Int = 1
    ): Response<CoursesResponse>

}