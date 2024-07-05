package com.example.bookofrecipes.recipes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey

@Fts4
@Entity
data class Ingredient(
    @PrimaryKey @ColumnInfo(name = "rowid") val id: Long,
    @ColumnInfo(name = "recipe_id") val recipeId: Long,
    val name: String
)