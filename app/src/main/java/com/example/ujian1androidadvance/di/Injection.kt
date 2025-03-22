package com.example.ujian1androidadvance.di

import android.content.Context
import com.android.newsapp.utils.AppExecutors
import com.example.ujian1androidadvance.data.local.room.DatabaseEvent
import com.example.ujian1androidadvance.data.remote.retrofit.ApiConfig
import com.example.ujian1androidadvance.data.repository.EventRepo

object Injection {
    fun provideRepository(context: Context): EventRepo {
        val apiService = ApiConfig.getApiService()
        val database = DatabaseEvent.getInstance(context)
        val dao = database.dicodingDao()
        val appExecutors = AppExecutors()
        return EventRepo.getInstance(apiService,dao, appExecutors)
    }
}