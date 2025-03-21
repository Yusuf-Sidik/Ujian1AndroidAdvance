package com.example.ujian1androidadvance.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "events")
data class EventEntity(
    @PrimaryKey val id: Int?,
    var name: String? = null,
    val summary: String?,
    val description: String?,
    val imageLogo: String?,
    val mediaCover: String?,
    val category: String?,
    val ownerName: String?,
    val cityName: String?,
    val quota: Int?,
    val registrants: Int?,
    val beginTime: String?,
    val endTime: String?,
    val link: String?,
    var isFavorite: Boolean?,
    var isUpcoming: Boolean?,
    var isFinished: Boolean?,
) : Parcelable