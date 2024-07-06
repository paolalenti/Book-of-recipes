package com.example.bookofrecipes.data.models

import androidx.room.Entity
import androidx.room.Fts4

@Fts4(contentEntity = Recipe::class)
@Entity(tableName = "recipe_fts")
data class RecipeFts(
    val name: String,
    val description: String
)
