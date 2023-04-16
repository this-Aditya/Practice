package com.aditya.room1.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aditya.room1.db.dao.PersonDao
import com.aditya.room1.db.entity.Person

@Database(entities = [Person::class], version = 1, exportSchema = false)
abstract class PersonDatabase: RoomDatabase() {

    abstract fun getPersonDao(): PersonDao

    companion object {
        @Volatile
        private var INSTANCE: PersonDatabase? = null

        fun getDataBase(context: Context):PersonDatabase{
            val tempInstance = INSTANCE
            if (tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                    PersonDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }}
    }
}