package com.junDev.udemyclone.domain.home_fragment.model

data class CoursesResponse(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<CoursesItem>
)

data class CoursesItem (
    val id: Int,
    val level: String,
    val title: String
        )