package com.example.ujian1androidadvance.data.remote.retrofit

import com.example.ujian1androidadvance.data.local.entity.UpcomingEvent
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("events?active=1")
    suspend fun getUpcomingEvents(): List<UpcomingEvent> // Event yang akan datang

    @GET("events?active=0")
    suspend fun getFinishedEvents(): List<UpcomingEvent> // Event yang sudah selesai

    @GET("events/{id}")
    suspend fun getEventDetail(@Path("id") id: Int): UpcomingEvent // Detail event

    @GET("events?active=-1")
    suspend fun searchEvent(@Query("q") keyword: String): List<UpcomingEvent> // Pencarian event
}