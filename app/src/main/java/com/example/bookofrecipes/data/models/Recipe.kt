package com.example.bookofrecipes.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class Recipe(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val time: String? = null,
    val description: String? = null,
    val image: String? = null,
    val favorite: Boolean? = null
)