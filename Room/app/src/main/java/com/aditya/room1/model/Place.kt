package com.aditya.room1.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("places")
data class Place(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val location: String,
    val ratings: Float
)