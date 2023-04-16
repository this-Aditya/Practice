package com.aditya.room1.db.repository

import com.aditya.room1.db.dao.PersonDao
import com.aditya.room1.db.entity.Person

class PersonRepository(private val personDao: PersonDao) {

    val getAllPersons = personDao.getAllPersons()


    suspend fun addPerson(person: Person) = personDao.addPerson(person)


}