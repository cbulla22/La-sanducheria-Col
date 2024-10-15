package com.example.sanducheriacol.model

import kotlinx.coroutines.flow.Flow

interface SandwichRepository {
    val allSandwiches: Flow<List<Sandwich>>
    fun getSandwich(id: String): Flow<Sandwich?>
    suspend fun insertSandwich(sandwich: Sandwich)
    suspend fun updateSandwich(sandwich: Sandwich)
    suspend fun deleteSandwich(id: String)
}