package com.aditya.roomcodelab

import android.app.Application
import com.aditya.roomcodelab.database.WordDataBase
import com.aditya.roomcodelab.repository.WordRepository

class WordApplication : Application() {

    val worddataBase by lazy {
        WordDataBase.getDataBase(this)
    }

    val wordRepository by lazy {
        WordRepository(worddataBase.WordDao())
    }


}