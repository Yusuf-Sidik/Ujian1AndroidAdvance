package com.example.ujian1androidadvance

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ujian1androidadvance.data.local.entity.EventEntity
import com.example.ujian1androidadvance.data.repository.EventRepo
import com.example.ujian1androidadvance.data.repository.Result
import kotlinx.coroutines.launch

class MainViewModel(private val eventRepo: EventRepo) : ViewModel() {

    fun getUpcomingEvents() = eventRepo.getUpcomingEvents()
    fun getFinishedEvents() = eventRepo.getFinishedEvents()
    fun getFavoriteEvents() = eventRepo.getFavoriteEvents()

    fun searchFinishedEvents(query: String): LiveData<Result<List<EventEntity>>> =
        eventRepo.searchFinishedEvents(query)

    fun searchFavoriteEvents(query: String): LiveData<Result<List<EventEntity>>> =
        eventRepo.searchFavoriteEvents(query)

    fun saveEvents(events: EventEntity) {
        viewModelScope.launch {
            eventRepo.setFavoriteEvents(events, true)
        }
    }

    fun deleteEvents(events: EventEntity) {
        viewModelScope.launch {
            eventRepo.setFavoriteEvents(events, false)
        }
    }

}