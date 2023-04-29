package com.aditya.roomcodelab.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.aditya.roomcodelab.entity.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {

    @Query("Select * from word_table order by word asc ")
    fun getAllWords(): Flow<List<Word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWord(word: Word)

    @Query("Delete from word_table")
    suspend fun deleteAll()
}