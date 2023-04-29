package com.aditya.roomcodelab

import android.app.Application
import com.aditya.roomcodelab.database.WordDataBase

class WordApplication: Application() {

    val worddataBase by lazy {
        WordDataBase.getDataBase(this)
    }
    val wordDao by lazy {
        worddataBase.WordDao()
    }


}