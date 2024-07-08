package com.example.bookofrecipes.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.bookofrecipes.data.models.Ingredient

@Dao
interface IngredientDao {
    @Insert
    fun insert(vararg ingredient: Ingredient)

    @Query("SELECT * FROM ingredients JOIN ingredient_fts " +
            "ON ingredients.id = ingredient_fts.rowid WHERE ingredient_fts MATCH :query")
    fun searchAll(query: String) : List<Ingredient>

    @Query("SELECT * FROM ingredients JOIN ingredient_fts " +
            "ON ingredients.id = ingredient_fts.rowid WHERE ingredient_fts MATCH :query " +
            "LIMIT :limit OFFSET :offset")
    fun searchLimited(query: String, limit: Long, offset: Long = 0) : List<Ingredient>

    @Query("SELECT * FROM ingredients WHERE name = :name COLLATE NOCASE LIMIT 1")
    fun getByNameClosestMatch(name: String): Ingredient?

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

    @Query("SELECT * FROM ingredients WHERE rowid = :id")
    fun getById(id: Long): Ingredient?

    @Query("SELECT * FROM ingredients")
    fun getAll(): List<Ingredient>

    @Update
    fun update(ingredient: Ingredient)

    @Delete
    fun delete(ingredient: Ingredient)
}