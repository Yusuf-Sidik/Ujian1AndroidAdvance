package com.example.ujian1androidadvance.ui.finished

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ujian1androidadvance.data.remote.response.ListEventsItem
import com.example.ujian1androidadvance.data.remote.response.UpcomingResponse
import com.example.ujian1androidadvance.data.remote.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FinishedViewModel : ViewModel() {
    private val _finishedEvent = MutableLiveData<List<ListEventsItem>>()
    val upcomingEvent: LiveData<List<ListEventsItem>> get() = _finishedEvent

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun fetchFinishedEvent(){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFinishedEvents()
        client.enqueue(object : Callback<UpcomingResponse> {
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
                    _finishedEvent.value = response.body()?.listEvents ?: emptyList()
                }else{
                    _errorMessage.value = "Error: ${response.code()}"
                }
            }

        })
    }
}