package com.example.bookofrecipes.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="ingredients")
data class Ingredient(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String
)