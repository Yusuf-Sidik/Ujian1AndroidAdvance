package com.example.ujian1androidadvance.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ujian1androidadvance.data.local.entity.EventEntity

@Database(entities = [EventEntity::class], version = 1, exportSchema = false)
abstract class DatabaseEvent: RoomDatabase() {
    abstract fun dicodingDao(): DicodingDao
    companion object{
        @Volatile
        private var instance: DatabaseEvent? = null
        fun getInstance(context: Context): DatabaseEvent =
            instance ?: synchronized(this){
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseEvent::class.java, "Event.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
    }
}