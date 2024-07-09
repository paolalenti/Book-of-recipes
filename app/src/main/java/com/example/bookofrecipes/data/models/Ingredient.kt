package com.example.bookofrecipes.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Index

/**
 * Ingredients database model
 * This need to be a separate model to perform searching and filtering by ingredient
 *
 * @property id Unique index of ingredient
 * @property name Name of ingredient
 */
@Entity(tableName = "ingredients", indices = [Index(value = ["name"], unique = true)])
data class Ingredient(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String
)