package com.example.bookofrecipes.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.bookofrecipes.data.models.IngredientQuantity

@Dao
interface IngredientQuantityDao {
    @Insert
    fun insert(vararg ingredientQuantity: IngredientQuantity): Array<Long>

    @Query("SELECT * FROM ingredients_quantities WHERE recipe_id = :recipeId")
    fun getAllByRecipeId(recipeId: Long): List<IngredientQuantity>

    @Query("SELECT * FROM ingredients_quantities WHERE ingredient_id = :ingredientId")
    fun getAllByIngredientId(ingredientId: Long): List<IngredientQuantity>

    @Update
    fun update(ingredientQuantity: IngredientQuantity)

    @Delete
    fun delete(ingredientQuantity: IngredientQuantity)
}