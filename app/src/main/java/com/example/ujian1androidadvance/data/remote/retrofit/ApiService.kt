package com.example.ujian1androidadvance.data.remote.retrofit

import com.example.ujian1androidadvance.data.remote.response.UpcomingResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("events?active=1")
    fun getListUpcoming(): Call<UpcomingResponse>

    @GET("events?active=0")
    fun getListFinished(): Call<UpcomingResponse>

    @GET("events")
    fun getUpcomingEvents(@Query("active") active: Int = 1): Call<UpcomingResponse>

    @GET("events")
    fun getFinishedEvents(@Query("active") active: Int = 0): Call<UpcomingResponse>
}