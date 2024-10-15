package com.example.sanducheriacol.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sandwiches")
data class SandwichEntity(
    @PrimaryKey val id: String,
    val bread: String,
    val protein: String,
    val vegetables: String,
    val sauces: String,
    val beverage: String?,
    val price: Double
)