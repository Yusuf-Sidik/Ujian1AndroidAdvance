package com.example.ujian1androidadvance.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "events")
data class EventEntity(
    @PrimaryKey(autoGenerate = true)
    @field:ColumnInfo(name = "id")
    var id: Int ,

    @field:ColumnInfo(name = "name")
    var name: String? = null,

    @field:ColumnInfo(name = "description")
    var description: String? = null,

    @field:ColumnInfo(name = "image")
    var image: String? = null,

    @field:ColumnInfo(name = "beginTime")
    var beginTime: String? = null,

    @field:ColumnInfo(name = "endTime")
    var endTime: String? = null,

    @field:ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean,

    @field:ColumnInfo(name = "summary")
    var summary: String? = null,

    @field:ColumnInfo(name = "ownerName")
    var ownerName: String? = null,

    @field:ColumnInfo(name = "mediaCover")
    var mediaCover: String? = null,

    @field:ColumnInfo(name = "imageLogo")
    var imageLogo: String? = null
) : Parcelable