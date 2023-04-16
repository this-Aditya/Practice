package com.aditya.room1

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.aditya.room1.db.database.PersonDatabase
import com.aditya.room1.db.entity.Person
import com.aditya.room1.db.repository.PersonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonViewModel(application: Application):AndroidViewModel(application) {
    var getAllPersons: LiveData<List<Person>>
    var repository: PersonRepository

    init {
        val dao = PersonDatabase.getDataBase(application.applicationContext).getPersonDao()
         repository = PersonRepository(dao)
        getAllPersons = repository.getAllPersons
    }

    fun addPerson(person: Person){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPerson(person)
        }
    }
}