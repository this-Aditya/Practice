package com.aditya.ktorrequest.models

import kotlinx.serialization.Serializable

@Serializable
data class MenuCategory(
    val menu: List<String>
)