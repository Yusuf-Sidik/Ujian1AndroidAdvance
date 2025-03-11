package com.example.ujian1androidadvance.data.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.android.newsapp.utils.AppExecutors
import com.example.ujian1androidadvance.data.local.entity.EventEntity
import com.example.ujian1androidadvance.data.local.room.DicodingDao
import com.example.ujian1androidadvance.data.local.room.DicodingRoomDatabases
import com.example.ujian1androidadvance.data.remote.response.ListEventsItem
import com.example.ujian1androidadvance.data.remote.response.UpcomingResponse
import com.example.ujian1androidadvance.data.remote.retrofit.ApiConfig
import com.example.ujian1androidadvance.data.remote.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class EventRepo(application: Application) {
    private val mEventsDao: DicodingDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
    private val apiService = ApiConfig.getApiService()

    init {
        val db = DicodingRoomDatabases.getInstance(application)
        mEventsDao = db.dicodingDao()
    }

    fun findUpcomingEvents(): LiveData<Result<List<EventEntity>>> = mEventsDao.getAllEvents().map { Result.Success(it) }

    fun findFinishedEvents(): LiveData<Result<List<EventEntity>>> = mEventsDao.getAllEvents().map { Result.Success(it) }

    fun insert(events: EventEntity) {
        executorService.execute { mEventsDao.insert(events) }
    }

    fun delete(events: EventEntity) {
        executorService.execute { mEventsDao.delete(events) }
    }

    fun update(events: EventEntity) {
        executorService.execute { mEventsDao.update(events) }
    }

}


//    fun insert(events: EventEntity) {
//        appExecutors.diskIO.execute { mEventsDao.insert(events) }
//    }
//
//    fun delete(events: EventEntity) {
//        appExecutors.diskIO.execute { mEventsDao.delete(events) }
//    }
//
//    fun getAllEvents(): LiveData<List<EventEntity>> = mEventsDao.getAllEvents()
//
//    fun isEventFavorited(eventId: Int): LiveData<Boolean> = mEventsDao.isEventFavorited(eventId)
//
//    fun getFavoriteEvents(): LiveData<List<EventEntity>> = mEventsDao.getFavoriteEvents()
//
//    fun findFinishedEvents(eventList: MutableLiveData<List<ListEventsItem>>, isLoading: MutableLiveData<Boolean>) {
//        isLoading.value = true
//        val client = apiService.getFinishedEvents()
//        client.enqueue(object : Callback<UpcomingResponse> {
//            override fun onResponse(
//                call: Call<UpcomingResponse>,
//                response: Response<UpcomingResponse>
//            ) {
//                isLoading.value = false
//                if (response.isSuccessful) {
//                    val responseBody = response.body()
//                    if (responseBody != null) {
//                        eventList.value = responseBody.listEvents
//                    }
//                } else {
//                    Log.e("EventsRepository", "onFailure: ${response.message()}")
//                }
//            }
//
//            override fun onFailure(call: Call<UpcomingResponse>, t: Throwable) {
//                isLoading.value = false
//                Log.e("EventsRepository", "onFailure: ${t.message}")
//            }
//        })
//    }
//
//    fun findUpcomingEvents(eventList: MutableLiveData<List<ListEventsItem>>, isLoading: MutableLiveData<Boolean>) {
//        isLoading.value = true
//        val client = apiService.getUpcomingEvents()
//        client.enqueue(object : Callback<UpcomingResponse> {
//            override fun onResponse(
//                call: Call<UpcomingResponse>,
//                response: Response<UpcomingResponse>
//            ) {
//                isLoading.value = false
//                if (response.isSuccessful) {
//                    val responseBody = response.body()
//                    if (responseBody != null) {
//                        eventList.value = responseBody.listEvents
//                    }
//                } else {
//                    Log.e("EventsRepository", "onFailure: ${response.message()}")
//                }
//            }
//
//            override fun onFailure(call: Call<UpcomingResponse>, t: Throwable) {
//                isLoading.value = false
//                Log.e("EventsRepository", "onFailure: ${t.message}")
//            }
//        })
//    }