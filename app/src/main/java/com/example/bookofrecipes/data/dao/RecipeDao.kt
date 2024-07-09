package com.example.bookofrecipes.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.bookofrecipes.data.models.Recipe

/**
 * Data access object for Recipe
 * @sample dao.searchAll("search query") # will return list of matching recipes
 */
@Dao
interface RecipeDao {

    /**
     * Inserted multiple recipes into database
     *
     * @param recipe
     * @return Array of inserted objects' indices
     */
    @Insert
    fun insert(vararg recipe: Recipe): Array<Long>

    /**
     * Search using full text search
     *
     * @param query Search query
     * @return List of matching recipes
     */
    @Query("SELECT * FROM recipes JOIN recipe_fts ON recipes.id = recipe_fts.rowid " +
            "WHERE recipe_fts MATCH :query")
    fun searchAll(query: String): List<Recipe>

    /**
     * Search using full text search
     *
     * @param query Search query
     * @param limit Maximum recipes in list
     * @param offset Offset in database
     * @return List of matching recipes
     */
    @Query("SELECT * FROM recipes JOIN recipe_fts ON recipes.id = recipe_fts.rowid " +
            "WHERE recipe_fts MATCH :query LIMIT :limit OFFSET :offset")
    fun searchLimited(query: String, limit: Long, offset: Long = 0): List<Recipe>

    /**
     * Get list of all recipes marked as favorite
     *
     * @return List of recipes
     */
    @Query("SELECT * FROM recipes WHERE favorite = 1")
    fun getFavorites(): List<Recipe>

    /**
     * Get all recipes that have all of ingredients with id from ingredientIds
     *
     * @param ingredientIds Indices of required ingredients
     * @param count Size of indices list
     * @return List of recipes
     */
    @Query("SELECT recipes.* FROM recipes, " +
            "(SELECT recipe_id, COUNT(recipe_id) as cnt FROM ingredients_quantities " +
            "WHERE ingredient_id in (:ingredientIds) GROUP BY recipe_id HAVING cnt = :count) " +
            "subquery WHERE recipes.rowid = subquery.recipe_id;")
    fun filterByIngredients(
        ingredientIds: List<Long>,
        count: Int = ingredientIds.size
    ): List<Recipe>

    /**
     * Get all recipes that have any of ingredients with id from ingredientIds
     *
     * @param ingredientIds Indices of ingredients
     * @return List of recipes
     */
    @Query("SELECT recipes.* FROM recipes, " +
            "(SELECT DISTINCT recipe_id FROM ingredients_quantities " +
            "WHERE ingredient_id in (:ingredientIds)) subquery " +
            "WHERE recipes.id = subquery.recipe_id ")
    fun filterByAnyIngredients(ingredientIds: List<Long>): List<Recipe>

    /**
     * Get all recipes in database
     *
     * @return List of recipes
     */
    @Query("SELECT * FROM recipes")
    fun getAll(): List<Recipe>

    /**
     * Get one recipe by id
     *
     * @param id Recipe id
     * @return Recipe or null if not found
     */
    @Query("SELECT * FROM recipes WHERE rowid = :id")
    fun getById(id: Long): Recipe?

    /**
     * Update recipe in database
     *
     * @param recipe Updated recipe
     */
    @Update
    fun update(recipe: Recipe)

    /**
     * Delete recipe from database
     *
     * @param recipe Recipe to delete
     */
    @Delete
    fun delete(recipe: Recipe)
}