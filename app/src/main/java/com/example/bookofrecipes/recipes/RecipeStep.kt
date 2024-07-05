package com.example.bookofrecipes.recipes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Recipe::class,
            parentColumns = ["rowid"],
            childColumns = ["recipe_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class RecipeStep(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "recipe_id") val recipeId: Long,
    val content: String
)
