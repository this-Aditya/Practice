package com.aditya.sharedpreferences

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.aditya.sharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences:SharedPreferences
    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences("mySharedPreferences",Context.MODE_PRIVATE)

        val editor = sharedPreferences.edit()
        binding.btnSave.setOnClickListener {
            if (binding.etName.text.trim().isBlank()||binding.etSex.text.trim().isBlank()){
                Toast.makeText(this,"Fields can't be null.",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val name = binding.etName.text.toString()
            val sex = binding.etSex.text.toString()
            val isTeen = binding.isTeen.isChecked
            editor.putString("name",name)
            editor.putString("sex",sex)
            editor.putBoolean("isteen",isTeen)
            editor.apply()
        }
        binding.btnLoad.setOnClickListener {
            val name = sharedPreferences.getString("name","")
            val sex = sharedPreferences.getString("sex","")
            val isTeen = sharedPreferences.getBoolean("isteen",false)
            binding.etName.setText(name)
            binding.etSex.setText(sex)
            binding.isTeen.isChecked = isTeen
        }
    }

    override fun onResume() {
        super.onResume()
        val name = sharedPreferences.getString("name","")
        val sex = sharedPreferences.getString("sex","")
        val isTeen = sharedPreferences.getBoolean("isteen",false)
        binding.etName.setText(name)
        binding.etSex.setText(sex)
        binding.isTeen.isChecked = isTeen
    }
}