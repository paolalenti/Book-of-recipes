package com.example.bookofrecipes.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.bookofrecipes.data.models.Ingredient

@Dao
interface IngredientDao {
    @Insert
    suspend fun insert(vararg ingredient: Ingredient)

    @Query("SELECT * FROM ingredients WHERE ingredients MATCH :query")
    suspend fun searchAll(query: String) : List<Ingredient>

    @Query("SELECT * FROM ingredients WHERE ingredients MATCH :query LIMIT :limit OFFSET :offset")
    suspend fun searchLimited(query: String, limit: Long, offset: Long = 0) : List<Ingredient>

    @Query("SELECT * FROM ingredients WHERE rowid = :id")
    suspend fun getById(id: Long): Ingredient?

    @Query("SELECT * FROM ingredients")
    suspend fun getAll(): List<Ingredient>

    @Update
    suspend fun update(ingredient: Ingredient)

    @Delete
    suspend fun delete(ingredient: Ingredient)
}