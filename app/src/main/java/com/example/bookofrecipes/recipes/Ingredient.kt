package com.example.bookofrecipes.recipes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Fts4
import androidx.room.PrimaryKey

@Fts4
@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Recipe::class,
            parentColumns = ["rowid"],
            childColumns = ["recipe_id"],
            onDelete = ForeignKey.NO_ACTION
        )
    ]
)
data class Ingredient(
    @PrimaryKey @ColumnInfo(name = "rowid") val id: Long,
    @ColumnInfo(name = "recipe_id") val recipeId: Long,
    val name: String
)