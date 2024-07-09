package com.example.bookofrecipes.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.bookofrecipes.data.models.IngredientQuantity

/**
 * Ingredient quantity data access object
 *
 */
@Dao
interface IngredientQuantityDao {
    /**
     * Insert new quantity into database
     *
     * @param ingredientQuantity Quantities to insert
     * @return Array of quantity indices
     */
    @Insert
    fun insert(vararg ingredientQuantity: IngredientQuantity): Array<Long>

    /**
     * Get all quantities for recipe
     *
     * @param recipeId Recipe identifier
     * @return List of quantities
     */
    @Query("SELECT * FROM ingredients_quantities WHERE recipe_id = :recipeId")
    fun getAllByRecipeId(recipeId: Long): List<IngredientQuantity>

    /**
     * Get all quantities for ingredient
     *
     * @param ingredientId Ingredient identifier
     * @return List of quantities
     */
    @Query("SELECT * FROM ingredients_quantities WHERE ingredient_id = :ingredientId")
    fun getAllByIngredientId(ingredientId: Long): List<IngredientQuantity>

    /**
     * Update given quantity
     *
     * @param ingredientQuantity Quantity to update
     */
    @Update
    fun update(ingredientQuantity: IngredientQuantity)

    /**
     * Delete given quantity
     *
     * @param ingredientQuantity Quantity to delete
     */
    @Delete
    fun delete(ingredientQuantity: IngredientQuantity)
}