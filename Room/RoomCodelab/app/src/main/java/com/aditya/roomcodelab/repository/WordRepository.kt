package com.aditya.roomcodelab.repository

import androidx.annotation.WorkerThread
import com.aditya.roomcodelab.dao.WordDao
import com.aditya.roomcodelab.entity.Word
import kotlinx.coroutines.flow.Flow

class WordRepository(private val wordDao: WordDao) {

    val allWords: Flow<List<Word>> = wordDao.getAllWords()

    @WorkerThread
    suspend fun insertWord(word: Word){
        wordDao.insertWord(word)
    }

    @WorkerThread
    suspend fun deleteAllWords(){
        wordDao.deleteAll()
    }
}