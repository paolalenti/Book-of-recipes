package com.example.bookofrecipes.recipes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "ingredients_quantities",
    foreignKeys = [
        ForeignKey(
            entity = Recipe::class,
            parentColumns = ["rowid"],
            childColumns = ["recipe_id"],
            onDelete = ForeignKey.NO_ACTION
        ),
        ForeignKey(
            entity = Ingredient::class,
            parentColumns = ["rowid"],
            childColumns = ["ingredient_id"],
            onDelete = ForeignKey.NO_ACTION
        )
    ]
)
data class IngredientQuanitity(
    @PrimaryKey val id: Long = 0,
    @ColumnInfo(name = "recipe_id") val recipeId: Long,
    @ColumnInfo(name = "ingredient_id") val ingredientId: Long,
    val quantity: String?
)
