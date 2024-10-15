package com.example.sanducheriacol.model

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SandwichDao {
    @Query("SELECT * FROM sandwiches")
    fun getAllSandwiches(): Flow<List<SandwichEntity>>

    @Query("SELECT * FROM sandwiches WHERE id = :id")
    fun getSandwich(id: String): Flow<SandwichEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSandwich(sandwich: SandwichEntity)

    @Update
    fun updateSandwich(sandwich: SandwichEntity)

    @Query("DELETE FROM sandwiches WHERE id = :id")
    fun deleteSandwich(id: String): Int
}