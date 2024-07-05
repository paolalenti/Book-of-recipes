package com.example.bookofrecipes.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.bookofrecipes.data.models.Recipe
import com.example.bookofrecipes.data.models.RecipeStep

@Dao
interface RecipeStepDao {
    @Insert
    fun insert(vararg step: RecipeStep)

    @Query("SELECT * FROM recipe_steps WHERE recipe_id = :recipeId AND number = :number LIMIT 1")
    fun getByNumberAndRecipeId(number: Int, recipeId: Long): RecipeStep?

    @Query("SELECT * FROM recipe_steps WHERE recipe_id = :recipeId")
    fun getAllByRecipeId(recipeId: Long): List<RecipeStep>

    fun getAllForRecipe(recipe: Recipe): List<RecipeStep> = getAllByRecipeId(recipe.id)

    fun getByNumberForRecipe(number: Int, recipe: Recipe) =
        getByNumberAndRecipeId(number, recipe.id)

    @Query("SELECT * FROM recipe_steps WHERE id = :id")
    fun getById(id: Long): RecipeStep?

    @Query("SELECT * FROM recipe_steps")
    fun getAll(id: Long): List<RecipeStep>

    @Update
    fun update(recipeStep: RecipeStep)

    @Delete
    fun delete(recipeStep: RecipeStep)
}