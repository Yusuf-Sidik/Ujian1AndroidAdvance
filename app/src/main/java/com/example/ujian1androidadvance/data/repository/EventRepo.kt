package com.example.ujian1androidadvance.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.android.newsapp.utils.AppExecutors
import com.example.ujian1androidadvance.data.local.entity.EventEntity
import com.example.ujian1androidadvance.data.local.room.DicodingDao
import com.example.ujian1androidadvance.data.remote.retrofit.ApiService

class EventRepo private constructor(
    private val apiService: ApiService,
    private val dicodingDao: DicodingDao,
    private val appExecutors: AppExecutors
) {
    fun getUpcomingEvents(): LiveData<Result<List<EventEntity>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getEvents(active = 1)
            val events = response.listEvents
            val eventList = events?.map { event ->
                val isFavorite = event.name?.let { dicodingDao.isEventFavorite(it) }
                val isUpcoming = true
                val isFinished = false
                EventEntity(
                    event.id,
                    event.name,
                    event.summary,
                    event.description,
                    event.imageLogo,
                    event.mediaCover,
                    event.category,
                    event.ownerName,
                    event.cityName,
                    event.quota,
                    event.registrants,
                    event.beginTime,
                    event.endTime,
                    event.link,
                    isFavorite,
                    isUpcoming,
                    isFinished,
                )
            }
//            eventDao.deleteAll()
            eventList?.let { dicodingDao.insertEvents(it) }
        } catch (e: Exception) {
            Log.d("EventRepository", "getEvents: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }
        val localData: LiveData<Result<List<EventEntity>>> =
            dicodingDao.getUpcomingEvents().map { Result.Success(it) }
        emitSource(localData)
    }

    fun getFinishedEvents(): LiveData<Result<List<EventEntity>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getEvents(active = 0)
            val events = response.listEvents
            val eventList = events?.map { event ->
                val isFavorite = event.name?.let { dicodingDao.isEventFavorite(it) }
                val isUpcoming = false
                val isFinished = true
                EventEntity(
                    event.id,
                    event.name,
                    event.summary,
                    event.description,
                    event.imageLogo,
                    event.mediaCover,
                    event.category,
                    event.ownerName,
                    event.cityName,
                    event.quota,
                    event.registrants,
                    event.beginTime,
                    event.endTime,
                    event.link,
                    isFavorite,
                    isUpcoming,
                    isFinished,
                )
            }
//            eventDao.deleteAll()
            eventList?.let { dicodingDao.insertEvents(it) }
        } catch (e: Exception) {
            Log.d("EventRepository", "getEvents: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }
        val localData: LiveData<Result<List<EventEntity>>> =
            dicodingDao.getFinishedEvents().map { Result.Success(it) }
        emitSource(localData)
    }

    fun getFavoriteEvents(): LiveData<List<EventEntity>> {
        return dicodingDao.getFavoriteEvents()
    }

    suspend fun setFavoriteEvents(events: EventEntity, favorite: Boolean) {
        events.isFavorite = favorite
        dicodingDao.updateEvents(events)

    }

    fun searchFinishedEvents(query: String): LiveData<Result<List<EventEntity>>> = liveData {
        emit(Result.Loading)
        try {
            val localData: LiveData<Result<List<EventEntity>>> =
                dicodingDao.searchFinishedEvents(query).map { Result.Success(it) }
            emitSource(localData)
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun searchFavoriteEvents(query: String): LiveData<Result<List<EventEntity>>> = liveData {
        emit(Result.Loading)
        try {
            val localData: LiveData<Result<List<EventEntity>>> =
                dicodingDao.searchFavoriteEvents(query).map { Result.Success(it) }
            emitSource(localData)
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    companion object {
        @Volatile
        private var instance: EventRepo? = null
        fun getInstance(
            apiService: ApiService,
            eventDao: DicodingDao,
            appExecutors: AppExecutors
        ): EventRepo = instance ?: synchronized(this) {
            instance ?: EventRepo(
                apiService,
                eventDao,
                appExecutors
            )
        }.also { instance = it }
    }
}