package com.emgram.greeting.data

// Location 모델 클래스
data class Location(
    val id: Int,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val timestamp: Long
)
