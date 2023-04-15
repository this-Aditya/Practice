package com.aditya.room1.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aditya.room1.db.dao.PlacesDao
import com.aditya.room1.model.Place


@Database([Place::class], version = 1, exportSchema = false)
abstract class PlacesDatabase : RoomDatabase() {
    abstract fun getPlacesDao(): PlacesDao

    companion object {
        @Volatile
        private var INSTANCE: PlacesDatabase? = null

        fun getInstance(context: Context): PlacesDatabase {
            val tempInstance = INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext, PlacesDatabase::class.java, "places_db.db"
                ).build()
            }
            INSTANCE = tempInstance
            return tempInstance
        }
    }
}