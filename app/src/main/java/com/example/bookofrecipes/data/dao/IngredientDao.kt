package com.example.bookofrecipes.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.bookofrecipes.data.models.Ingredient

/**
 * Ingredient data access object
 *
 */
@Dao
interface IngredientDao {
    /**
     * Insert ingredients into database
     *
     * @param ingredient Ingredients to insert
     * @return List of ingredients' identifiers
     */
    @Insert
    fun insert(vararg ingredient: Ingredient): Array<Long>

    /**
     * Search ingredient using full text search
     *
     * @param query Search query
     * @return List of ingredients
     */
    @Query("SELECT * FROM ingredients JOIN ingredient_fts " +
            "ON ingredients.id = ingredient_fts.rowid WHERE ingredient_fts MATCH :query")
    fun searchAll(query: String) : List<Ingredient>

    /**
     * Search ingredient using full text search
     *
     * @param query Search query
     * @param limit Maximum ingredients
     * @param offset Offset of results
     * @return List of ingredients
     */
    @Query("SELECT * FROM ingredients JOIN ingredient_fts " +
            "ON ingredients.id = ingredient_fts.rowid WHERE ingredient_fts MATCH :query " +
            "LIMIT :limit OFFSET :offset")
    fun searchLimited(query: String, limit: Long, offset: Long = 0) : List<Ingredient>

    /**
     * Get ingredient which name matches parameter
     *
     * @param name Name of ingredient
     * @return Ingredient or null
     */
    @Query("SELECT * FROM ingredients WHERE name = :name COLLATE NOCASE LIMIT 1")
    fun getByNameClosestMatch(name: String): Ingredient?

    /**
     * Get ingredient by name or create new ingredient
     *
     * @param name Name of ingredient
     * @return Ingredient
     */
    @Transaction
    fun getByName(name: String): Ingredient {
        return when(val match = getByNameClosestMatch(name)) {
            null -> {
                insert(Ingredient(name = name))
                return getByName(name)
            }
            else -> match
        }
    }

    /**
     * Get ingredient by id
     *
     * @param id Identifier of ingredient
     * @return
     */
    @Query("SELECT * FROM ingredients WHERE rowid = :id")
    fun getById(id: Long): Ingredient?

    /**
     * Get all ingredients
     *
     * @return List of all ingredients
     */
    @Query("SELECT * FROM ingredients")
    fun getAll(): List<Ingredient>

    /**
     * Update ingredient
     *
     * @param ingredient Ingredient to update
     */
    @Update
    fun update(ingredient: Ingredient)

    /**
     * Delete ingredient
     *
     * @param ingredient Ingredient to delete
     */
    @Delete
    fun delete(ingredient: Ingredient)
}