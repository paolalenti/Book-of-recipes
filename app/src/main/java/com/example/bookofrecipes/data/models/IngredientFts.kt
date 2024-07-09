package com.example.bookofrecipes.data.models

import androidx.room.Entity
import androidx.room.Fts4

/**
 * Fts table for ingredients
 *
 * @property name Fts4 should index name of ingredient
 */
@Fts4(contentEntity = Ingredient::class)
@Entity(tableName = "ingredient_fts")
data class IngredientFts(
    val name: String
)
