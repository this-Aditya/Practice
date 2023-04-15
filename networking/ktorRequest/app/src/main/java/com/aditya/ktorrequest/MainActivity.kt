package com.aditya.ktorrequest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.aditya.ktorrequest.databinding.ActivityMainBinding
import com.aditya.ktorrequest.models.MenuCategory
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable




class MainActivity : AppCompatActivity() {
    private lateinit var client: HttpClient
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        client = HttpClient(Android) {
            install(ContentNegotiation) {
                json(contentType = ContentType("text", "plain"))
            }
        }
        binding.mainDownloadButton.setOnClickListener{
            lifecycleScope.launch {
                val menuItems = getMenu("Salads")
                menuItems.forEach{
                    Log.i("ktor",it)
                }
            }
        }
    }

    private suspend fun getMenu(category: String):List<String> {
        val response:Map<String, MenuCategory> = client
            .get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/littleLemonMenu.json")
            .body()
        return response[category]?.menu?: listOf()
    }
}