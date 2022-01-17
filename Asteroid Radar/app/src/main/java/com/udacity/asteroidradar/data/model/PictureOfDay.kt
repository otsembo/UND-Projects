package com.udacity.asteroidradar.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image_of_day")
data class PictureOfDay(
    @PrimaryKey(autoGenerate = true)
    val id:Long? = null,

    @ColumnInfo(name = "media_type")
    val mediaType: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "media_url")
    val url: String)