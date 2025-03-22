package com.example.ujian1androidadvance.data.remote.retrofit

import com.example.ujian1androidadvance.data.remote.response.UpcomingResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("events")
    suspend fun getEvents(
        @Query("active") active: Int = 1, // 1 upcoming, 0 finished, -1 all
        @Query("q") query: String? = null,
        @Query("limit") limit: Int = 40,
    ): UpcomingResponse
}