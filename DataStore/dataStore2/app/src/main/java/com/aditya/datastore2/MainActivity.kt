package com.aditya.datastore2

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.aditya.datastore2.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private val dataStore: DataStore<Preferences> by preferencesDataStore(name = "userdata")

    @SuppressLint("UseSwitchCompatOrMaterialCode", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var switchState: Boolean
        var key: String
        var value: Int

        val switch = binding.colorSwitch
        switch.setOnClickListener {
            if (switch.isChecked) {
                binding.clView.setBackgroundColor(Color.GRAY)
                switch.text = "Colored"
            } else {
                binding.clView.setBackgroundColor(Color.WHITE)
                switch.text = "Not Colored"
            }
        }

        binding.btnSave.setOnClickListener {
            hideKeyBoard()
            if ((binding.etKey.text.trim().isBlank()) && (binding.etValue.text.trim().isBlank())) {
                showToast("These Fields cant be empty.")
                return@setOnClickListener
            }
            key = binding.etKey.text.toString()
            value = binding.etValue.text.toString().toInt()
            switchState = binding.colorSwitch.isChecked
            lifecycleScope.launch {
                save(key, value, switchState)
            }
        }

        binding.btnLoad.setOnClickListener {
            hideKeyBoard()
        if (binding.etFind.text.isBlank()){
            showToast("These Fields cant be empty.")
            return@setOnClickListener
        }
            val key: String = binding.etFind.text.toString()
            lifecycleScope.launch {
                val foundValue = load(key)
                setText(foundValue)
            }
        }
    }

    private fun hideKeyBoard() {
        try {
            val imm: InputMethodManager =
                getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        } catch (e: Exception) {
            showToast("Can't pull keyboard down.")
        }
    }

    private fun setText(foundValue: String) {
        binding.tvValue.text = foundValue
        binding.tvValue.setTextColor(Color.CYAN)
    }

    private suspend fun load(key: String): String {
     val prefkey = stringPreferencesKey(key)
     val stateprefKey = booleanPreferencesKey("switchState")
     val preferences = dataStore.data.first()
        Log.i(TAG, "load: Key: $key, Value: ${preferences[prefkey]}, switchState: ${preferences[stateprefKey]}")
     val stateValue: Boolean = preferences[stateprefKey] ?: false
     if (stateValue){
         binding.colorSwitch.isChecked = true
         binding.clView.setBackgroundColor(Color.GRAY)
     }
        return preferences[prefkey] ?: "No Value Found."
    }

    private suspend fun save(key: String, value: Int, switchState: Boolean) {
        dataStore.edit { userdata ->
            val prefkey = stringPreferencesKey(key)
            val statekey = "switchState"
            val statePrefKey = booleanPreferencesKey(statekey)
            userdata[prefkey] = value.toString()
            userdata[statePrefKey] = switchState
            Log.i(TAG, "Key: $key, Value: $value, switchState: $switchState")
            showToast("User Data is saved locally.")
        }
    }

    private fun showToast(message: String) {
       Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }
}