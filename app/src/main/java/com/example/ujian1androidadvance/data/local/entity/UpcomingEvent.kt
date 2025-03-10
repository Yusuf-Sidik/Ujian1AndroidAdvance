package com.example.ujian1androidadvance.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
data class UpcomingEvent(
    @PrimaryKey val id: Int,
    val name: String,
    val summary: String,
    val mediaCover: String,
    val registrants: Int,
    val imageLogo: String,
    val link: String,
    val description: String,
    val ownerName: String,
    val cityName: String,
    val quota: Int,
    val beginTime: String,
    val endTime: String,
    val category: String
//    @field:PrimaryKey
//    @field:ColumnInfo(name = "name")
//    val name: String,
//    @field:ColumnInfo(name = "summary")
//    val summary: String,
//    @field:ColumnInfo(name = "media_cover")
//    val mediaCover: String,
//    @field:ColumnInfo(name = "image_logo")
//    val imageLogo: String,
//    @field:ColumnInfo(name = "link")
//    val link: String,
//    @field:ColumnInfo(name = "description")
//    val description: String,
//    @field:ColumnInfo(name = "favorite")
//    var isFavorite: Boolean
)