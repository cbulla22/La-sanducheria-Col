package com.example.sanducheriacol.model

import java.util.UUID

data class Sandwich(
    val id: String = UUID.randomUUID().toString(),
    val bread: String,
    val protein: String,
    val vegetables: List<String>,
    val sauces: List<String>,
    val beverage: String?,
    val price: Double
)