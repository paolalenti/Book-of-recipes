package com.example.bookofrecipes.recipes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecipeStep(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "recipe_id") val recipeId: Long,
    val content: String
)