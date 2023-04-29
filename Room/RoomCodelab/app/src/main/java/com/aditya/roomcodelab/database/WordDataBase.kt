package com.aditya.roomcodelab.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aditya.roomcodelab.dao.WordDao
import com.aditya.roomcodelab.entity.Word

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordDataBase : RoomDatabase() {

    abstract fun WordDao(): WordDao

    companion object {
        @Volatile
        private var INSTANCE: WordDataBase? = null

        fun getDataBase(context: Context): WordDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordDataBase::class.java,
                    "word_db"
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}