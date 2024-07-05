package com.example.bookofrecipes.recipes.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.bookofrecipes.recipes.Recipe

@Dao
interface RecipeDao {
    @Insert
    suspend fun insert(vararg recipe: Recipe): Long

    @Transaction
    @Query("SELECT * FROM recipes")
    suspend fun getAll(): List<Recipe>

    @Query("SELECT * FROM recipes WHERE rowid = :id LIMIT 1")
    suspend fun getById(id: Long): Recipe?

    @Update
    suspend fun update(recipe: Recipe)

    @Delete
    suspend fun delete(recipe: Recipe)
}