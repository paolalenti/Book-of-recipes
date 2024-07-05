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
    suspend fun insert(vararg ingredientQuantity: IngredientQuantity)

    @Query("SELECT * FROM ingredients_quantities WHERE recipe_id = :recipeId")
    suspend fun getAllByRecipeId(recipeId: Long): List<IngredientQuantity>

    @Query("SELECT * FROM ingredients_quantities WHERE recipe_id = :recipeId")
    suspend fun getAllByIngredientId(recipeId: Long): List<IngredientQuantity>

    @Query("SELECT * FROM ingredients_quantities WHERE recipe_id in (:ingredientIds)")
    suspend fun getAllByAnyIngredientId(ingredientIds: List<Long>): List<IngredientQuantity>

    @Query("SELECT * FROM ingredients_quantities, " +
            "(SELECT recipe_id, COUNT(recipe_id) as cnt FROM ingredients_quantities " +
            "WHERE ingredient_id in (:ingredientIds) GROUP BY recipe_id HAVING cnt == :count) subquery " +
            "WHERE recipe_id = subquery.recipe_id")
    suspend fun getAllByAllIngredientId(ingredientIds: List<Long>, count: Int = ingredientIds.size): List<IngredientQuantity>

    @Update
    suspend fun update(ingredientQuantity: IngredientQuantity)

    @Delete
    suspend fun delete(ingredientQuantity: IngredientQuantity)
}