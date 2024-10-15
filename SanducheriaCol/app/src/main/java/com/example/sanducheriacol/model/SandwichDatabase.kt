package com.example.sanducheriacol.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SandwichEntity::class], version = 1, exportSchema = false)
abstract class SandwichDatabase : RoomDatabase() {
    abstract fun sandwichDao(): SandwichDao

    companion object {
        @Volatile
        private var INSTANCE: SandwichDatabase? = null

        fun getDatabase(context: Context): SandwichDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SandwichDatabase::class.java,
                    "sandwich_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

// Add this function to create a repository
fun SandwichDatabase.createRepository(): SandwichRepository {
    return SandwichRepositoryImpl(sandwichDao())
}