package com.example.bookofrecipes.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.bookofrecipes.data.models.Recipe
import com.example.bookofrecipes.data.models.RecipeStep

/**
 * Recipe step data access object
 *
 */
@Dao
interface RecipeStepDao {
    /**
     * Insert steps into database
     *
     * @param step Steps to insert
     * @return Array of steps identifiers
     */
    @Insert
    fun insert(vararg step: RecipeStep): Array<Long>

    /**
     * Get nth step for recipe
     *
     * @param number Step number
     * @param recipeId Recipe identifier
     * @return RecipeStep or null
     */
    @Query("SELECT * FROM recipe_steps WHERE recipe_id = :recipeId AND number = :number LIMIT 1")
    fun getByNumberAndRecipeId(number: Int, recipeId: Long): RecipeStep?

    /**
     * Get all steps for recipe
     *
     * @param recipeId Recipe identifier
     * @return List of recipe steps
     */
    @Query("SELECT * FROM recipe_steps WHERE recipe_id = :recipeId")
    fun getAllByRecipeId(recipeId: Long): List<RecipeStep>

    /**
     * Get all steps for recipe
     * @see getAllByRecipeId
     * @param recipe Recipe to get steps from
     * @return List of recipes
     */
    fun getAllForRecipe(recipe: Recipe): List<RecipeStep> = getAllByRecipeId(recipe.id)

    /**
     * Get nth step for recipe
     * @see getByNumberAndRecipeId
     * @param number Step number
     * @param recipe Recipe
     * @return RecipeStep or null
     */
    fun getByNumberForRecipe(number: Int, recipe: Recipe): RecipeStep? =
        getByNumberAndRecipeId(number, recipe.id)

    /**
     * Get by step identifier
     *
     * @param id Identifier
     * @return RecipeStep or null
     */
    @Query("SELECT * FROM recipe_steps WHERE id = :id")
    fun getById(id: Long): RecipeStep?

    /**
     * Get list of all RecipeSteps
     *
     * @return List of RecipeSteps
     */
    @Query("SELECT * FROM recipe_steps")
    fun getAll(): List<RecipeStep>

    /**
     * Update recipe step
     *
     * @param recipeStep Step to update
     */
    @Update
    fun update(recipeStep: RecipeStep)

    /**
     * Delete recipe step
     *
     * @param recipeStep Step to delete
     */
    @Delete
    fun delete(recipeStep: RecipeStep)
}