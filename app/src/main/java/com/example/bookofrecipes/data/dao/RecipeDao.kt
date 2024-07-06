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

    @Query("SELECT * FROM recipes JOIN recipe_fts ON recipes.id = recipe_fts.rowid " +
            "WHERE recipe_fts MATCH :query")
    fun searchAll(query: String): List<Recipe>

    @Query("SELECT * FROM recipes JOIN recipe_fts ON recipes.id = recipe_fts.rowid " +
            "WHERE recipe_fts MATCH :query LIMIT :limit OFFSET :offset")
    fun searchLimited(query: String, limit: Long, offset: Long = 0): List<Recipe>

    @Query("SELECT recipes.* FROM recipes, " +
            "(SELECT recipe_id, COUNT(recipe_id) as cnt FROM ingredients_quantities " +
            "WHERE ingredient_id in (:ingredientIds) GROUP BY recipe_id HAVING cnt = :count) " +
            "subquery WHERE recipes.rowid = subquery.recipe_id;")
    fun filterByIngredients(
        ingredientIds: List<Long>,
        count: Int = ingredientIds.size
    ): List<Recipe>

    @Query("SELECT recipes.* FROM recipes, " +
            "(SELECT DISTINCT recipe_id FROM ingredients_quantities " +
            "WHERE ingredient_id in (:ingredientIds)) subquery " +
            "WHERE recipes.id = subquery.recipe_id ")
    fun filterByAnyIngredients(ingredientIds: List<Long>): List<Recipe>

    @Query("SELECT * FROM recipes")
    fun getAll(): List<Recipe>

    @Query("SELECT * FROM recipes WHERE rowid = :id")
    fun getById(id: Long): Recipe?

    @Update
    fun update(recipe: Recipe)

    @Delete
    fun delete(recipe: Recipe)
}