package com.aditya.room1.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.aditya.room1.db.entity.Person

@Dao
interface PersonDao{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPerson(person: Person)

//    @Delete
//    suspend fun deletePerson(id: Int)

    @Query("SELECT * FROM person ORDER BY ID ASC")
    fun getAllPersons(): LiveData<List<Person>>
}