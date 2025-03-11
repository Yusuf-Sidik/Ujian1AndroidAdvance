package com.example.ujian1androidadvance.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.ujian1androidadvance.data.local.entity.EventEntity

@Dao
interface DicodingDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(events: EventEntity)

    @Delete
    fun delete(events: EventEntity)

    @Update
    fun update(events: EventEntity)

    @Query("SELECT * from events ORDER BY id ASC")
    fun getAllEvents(): LiveData<List<EventEntity>>

}

//    @Query("SELECT EXISTS(SELECT 1 FROM events WHERE id = :eventId)")
//    fun isEventFavorited(eventId: Int): LiveData<Boolean>
//
//    @Query("SELECT * FROM events WHERE isFavorite = 1")
//    fun getFavoriteEvents(): LiveData<List<EventEntity>>