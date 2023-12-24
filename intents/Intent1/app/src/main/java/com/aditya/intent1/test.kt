package com.aditya.intent1

import android.content.Intent
import android.util.Log

fun main() {
val intent = "Hello World This is Aditya"
    when {
        intent.contains("Hello") -> {
            println("First when statement")
        }
        intent.contains("World") -> {
            println("Second when statement")
        }
        intent.contains("is ") -> {
            println("Third when statement")
        }
    }
}
