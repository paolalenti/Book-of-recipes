package com.example.bookofrecipes.data.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.bookofrecipes.data.models.Ingredient

@Dao
interface IngredientDao {
    @Insert
    suspend fun insert(vararg ingredient: Ingredient)
}