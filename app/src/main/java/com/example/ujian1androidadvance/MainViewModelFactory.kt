package com.example.ujian1androidadvance

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ujian1androidadvance.data.repository.EventRepo
import com.example.ujian1androidadvance.di.Injection

class MainViewModelFactory(private val eventRepo: EventRepo) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(eventRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: MainViewModelFactory? = null
        fun getInstance(context: Context): MainViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: MainViewModelFactory(Injection.provideRepository(context))
            }.also { instance = it }
    }
}