package com.example.ujian1androidadvance.ui.upcoming

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ujian1androidadvance.data.local.entity.EventEntity
import com.example.ujian1androidadvance.data.remote.response.ListEventsItem
import com.example.ujian1androidadvance.data.remote.response.UpcomingResponse
import com.example.ujian1androidadvance.data.remote.retrofit.ApiConfig
import com.example.ujian1androidadvance.data.repository.EventRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpcomingViewModel: ViewModel() {
//    private val mEventRepo: EventRepo = EventRepo(application)
//    fun insert(events: EventEntity) {
//        mEventRepo.insert(events)
//    }
//
//    fun delete(events: EventEntity) {
//        mEventRepo.delete(events)
//    }
//
//    fun update(events: EventEntity) {
//        mEventRepo.update(events)
//    }

    private val _upcomingEvent = MutableLiveData<List<ListEventsItem>>()
    val upcomingEvent: LiveData<List<ListEventsItem>> get() = _upcomingEvent

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    init {
        fetchUpcomingEvent()
    }

    fun fetchUpcomingEvent(){
//        eventRepo.findUpcomingEvents(_upcomingEvent, _isLoading)
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUpcomingEvents()
        client.enqueue(object : Callback<UpcomingResponse>{
            override fun onFailure(call: Call<UpcomingResponse>, t: Throwable) {
                _isLoading.value = false
                _errorMessage.value = t.message
            }

            override fun onResponse(
                call: Call<UpcomingResponse>,
                response: Response<UpcomingResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful){
                    _upcomingEvent.value = response.body()?.listEvents ?: emptyList()
                }else{
                    _errorMessage.value = "Error: ${response.code()}"
                }
            }

        })
    }
}