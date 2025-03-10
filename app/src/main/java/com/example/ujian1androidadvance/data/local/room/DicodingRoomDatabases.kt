package com.example.ujian1androidadvance.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ujian1androidadvance.data.local.entity.UpcomingEvent

@Database(entities = [UpcomingEvent::class], version = 1, exportSchema = false)
abstract class DicodingRoomDatabases: RoomDatabase() {
    abstract fun dicodingDao(): DicodingDao

    companion object{
        @Volatile
        private var instance: DicodingRoomDatabases? = null
        fun getInstance(context: Context): DicodingRoomDatabases =
            instance ?: synchronized(this){
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    DicodingRoomDatabases::class.java, "Event.db"
                ).build()
            }
    }
}