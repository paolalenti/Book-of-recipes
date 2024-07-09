package com.example.bookofrecipes.data.models

import androidx.room.Entity
import androidx.room.Fts4

/**
 * Full text search table for recipe
 *
 * @property name Fts4 should index name of recipe
 * @property description Fts4 should index description
 */
@Fts4(contentEntity = Recipe::class)
@Entity(tableName = "recipe_fts")
data class RecipeFts(
    val name: String,
    val description: String
)
