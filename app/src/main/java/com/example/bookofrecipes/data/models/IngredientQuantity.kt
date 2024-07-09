package com.example.bookofrecipes.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Ingredient quantity database model. Many-to-many relation between recipes and ingredients
 *
 * @property id Unique identifier
 * @property recipeId Identifier of recipe
 * @property ingredientId Identifier of ingredient
 * @property quantity Quantity of ingredient or null
 */
@Entity(
    tableName = "ingredients_quantities",
    foreignKeys = [
        ForeignKey(
            entity = Recipe::class,
            parentColumns = ["id"],
            childColumns = ["recipe_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Ingredient::class,
            parentColumns = ["id"],
            childColumns = ["ingredient_id"],
            onDelete = ForeignKey.RESTRICT
        )
    ]
)
data class IngredientQuantity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "recipe_id") val recipeId: Long,
    @ColumnInfo(name = "ingredient_id") val ingredientId: Long,
    val quantity: String? = null
)
