package com.example.ujian1androidadvance.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ujian1androidadvance.data.local.entity.EventEntity

@Database(entities = [EventEntity::class], version = 1, exportSchema = false)
abstract class DicodingRoomDatabases: RoomDatabase() {
    abstract fun dicodingDao(): DicodingDao
    companion object {
        @Volatile
        private var INSTANCE: DicodingRoomDatabases? = null
        @JvmStatic
        fun getInstance(context: Context): DicodingRoomDatabases{
            if (INSTANCE == null){
                synchronized(DicodingRoomDatabases::class.java){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        DicodingRoomDatabases::class.java, "dicoding.db")
                        .build()
                }
            }
            return INSTANCE as DicodingRoomDatabases
        }
    }
}