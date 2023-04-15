package com.aditya.room1.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aditya.room1.model.Place

@Dao
interface PlacesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPlace(place: Place)

    @Query("SELECT * FROM PLACE ORDER BY ID ASC")
    fun getAllPlaces(): LiveData<List<Place>>
}