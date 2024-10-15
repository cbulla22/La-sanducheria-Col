package com.example.sanducheriacol.model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SandwichRepositoryImpl(private val sandwichDao: SandwichDao) : SandwichRepository {

    override val allSandwiches: Flow<List<Sandwich>> = sandwichDao.getAllSandwiches().map { entities ->
        entities.map { entity -> entity.toSandwich() }
    }

    override fun getSandwich(id: String): Flow<Sandwich?> {
        return sandwichDao.getSandwich(id).map { entity ->
            entity?.toSandwich()
        }
    }

    override suspend fun insertSandwich(sandwich: Sandwich) {
        sandwichDao.insertSandwich(sandwich.toEntity())
    }

    override suspend fun updateSandwich(sandwich: Sandwich) {
        sandwichDao.updateSandwich(sandwich.toEntity())
    }

    override suspend fun deleteSandwich(id: String) {
        sandwichDao.deleteSandwich(id)
    }

    private fun SandwichEntity.toSandwich() = Sandwich(
        id = id,
        bread = bread,
        protein = protein,
        vegetables = vegetables.split(","),
        sauces = sauces.split(","),
        beverage = beverage,
        price = price
    )

    private fun Sandwich.toEntity() = SandwichEntity(
        id = id,
        bread = bread,
        protein = protein,
        vegetables = vegetables.joinToString(","),
        sauces = sauces.joinToString(","),
        beverage = beverage,
        price = price
    )
}