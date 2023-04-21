package com.aditya.flows2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.aditya.flows2.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

data class Person(val id:Int,val name:String,val address:String)

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            startCollector()
        }
    }

    private fun startCollector() {
        GlobalScope.launch {
            //bufffer() is used to start a new coroutine.
            getPersonInfo().buffer().collect{person->
                Log.i(TAG, "${person.id}, ${person.name}, ${person.address}")
                //here it is cold flow so producer will stop producing data
                delay(5000)
            }
            ///you might be wondering what if we don't use buffer() here.
            // In that case total delay will be 6 seconds.
            // As collect block is starting in the same coroutine.
        }
    }

    //Coroutines Returning only one value
   /** suspend fun getUsers(): List<String>{
    *    val userList = mutableListOf<String>()
    *   withContext(Dispatchers.Main){
    *   userList.add(getUser())
    *   userList.add(getUser())
    *   userList.add(getUser())}
    *   Log.i(TAG, Thread.currentThread().name)
    *   return userList
    * }
    *
    * suspend fun getUser(): String{
    *   delay(1000)
    *   i++
    *   return "User$i"
    *}
    */


fun getPersonInfo(): Flow<Person> {
       // By default flow lauches a coroutineScope
    return flow<Person> {
        val persons = getPersons()
        persons.forEach {person->
            delay(1000)
            //emit is also a suspend function
            emit(person)
        }
    }
}



    fun getPersons(): List<Person>{
       val Person= mutableListOf<Person>()
           for (i in 1..5){
           Person.add(Person(i,"Person$i","Address$i"))
        }
       return Person
    }
}