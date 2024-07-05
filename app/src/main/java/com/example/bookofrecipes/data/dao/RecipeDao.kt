package com.example.bookofrecipes.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.bookofrecipes.data.models.Ingredient
import com.example.bookofrecipes.data.models.Recipe

@Dao
interface RecipeDao {
    @Insert
    suspend fun insert(vararg recipe: Recipe)

    @Query("SELECT * FROM recipes WHERE recipes MATCH :query")
    suspend fun searchAll(query: String): List<Recipe>

    @Query("SELECT * FROM recipes WHERE recipes MATCH :query LIMIT :limit OFFSET :offset")
    suspend fun searchLimited(query: String, limit: Long, offset: Long = 0) : List<Recipe>

    @Query("SELECT * FROM recipes")
    suspend fun getAll(): List<Recipe>

    @Query("SELECT * FROM recipes WHERE rowid = :id")
    suspend fun getById(id: Long): Recipe?

    @Update
    suspend fun update(recipe: Recipe)

    @Delete
    suspend fun delete(recipe: Recipe)
}