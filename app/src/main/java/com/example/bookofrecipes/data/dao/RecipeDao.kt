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
    fun insert(vararg recipe: Recipe)

    @Query("SELECT * FROM recipes WHERE recipes MATCH :query")
    fun searchAll(query: String): List<Recipe>

    @Query("SELECT * FROM recipes WHERE recipes MATCH :query LIMIT :limit OFFSET :offset")
    fun searchLimited(query: String, limit: Long, offset: Long = 0) : List<Recipe>

    @Query("SELECT * FROM recipes")
    fun getAll(): List<Recipe>

    @Query("SELECT * FROM recipes WHERE rowid = :id")
    fun getById(id: Long): Recipe?

    @Update
    fun update(recipe: Recipe)

    @Delete
    fun delete(recipe: Recipe)
}