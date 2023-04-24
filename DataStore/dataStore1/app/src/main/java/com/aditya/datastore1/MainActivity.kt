package com.aditya.datastore1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.aditya.datastore1.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSave.setOnClickListener {
            /**
             * Launching a new coroutineScope which is tied to the lifecycle of this activity
             */
            lifecycleScope.launch {
                val name = binding.etName.text.toString()
                val age = binding.etAge.text.toString().toInt()
                val sex: String = if (binding.rbMale.isChecked) {
                    "Male"
                } else {
                    "Female"
                }
                save("Name", name)
                save("Age", age.toString())
                save("Sex", sex)
            }
        }

        binding.btnLoad.setOnClickListener {
            // Launch a coroutine to load the data from the DataStore
            lifecycleScope.launch(Dispatchers.Main) {
                val name: String = load("Name") ?: "This value is not stored in dataBase "
                val age: Int = load("Age")?.toInt() ?: -1
                val sex = load("Sex")
                try{
                binding.etName.setText(name)
                if (sex.equals("Male")) {
                    binding.rbMale.isChecked = true
                } else {
                    binding.rbFemale.isChecked = true
                }
                    Log.i(TAG, "Age: $age, Is Int ${age is Int}")
                    binding.etAge.setText(age.toString())
                } catch (e: Exception){
                    Log.i(TAG, "Exception ${e.message}")
                }


            }
        }

    }

    /**
    * This function is used to save a key-value pair in the DataStore.
    * It takes a key and a value as arguments and saves them in the DataStore using the edit() function.
    * It also displays a Toast to indicate that the save operation was successful.
     */
    suspend fun save(key: String, value: String) {
        val prefKey = stringPreferencesKey(key)
        dataStore.edit { settings ->
            settings[prefKey] = value
            showToast()
            Log.i(TAG, "key: $prefKey, value: $value")
        }
    }

    private fun showToast() {
        Toast.makeText(this,"Save Successful",Toast.LENGTH_LONG).show()
    }

    suspend fun load(key: String): String? {
        val prefKey = stringPreferencesKey(key)
        val preferences = dataStore.data.first()
        Log.i(TAG, "load: key-> $key, value: ${preferences[prefKey]}")
        return preferences[prefKey]
    }

}