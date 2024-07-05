package com.example.bookofrecipes.recipes.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.bookofrecipes.recipes.Recipe
import com.example.bookofrecipes.recipes.RecipeStep

@Dao
interface RecipeStepDao {
    @Insert
    suspend fun insert(vararg step: RecipeStep): Long

    @Query("SELECT * FROM recipe_steps WHERE recipe_id = :recipeId AND number = :number LIMIT 1")
    suspend fun getByNumberAndRecipeId(number: Int, recipeId: Long): RecipeStep?

    @Query("SELECT * FROM recipe_steps WHERE recipe_id = :recipeId")
    suspend fun getAllByRecipeId(recipeId: Long): List<RecipeStep>

    suspend fun getAllForRecipe(recipe: Recipe): List<RecipeStep> = getAllByRecipeId(recipe.id)

    suspend fun getByNumberForRecipe(number: Int, recipe: Recipe) =
        getByNumberAndRecipeId(number, recipe.id)

    @Update
    suspend fun update(recipeStep: RecipeStep)

    @Delete
    suspend fun delete(recipeStep: RecipeStep)
}