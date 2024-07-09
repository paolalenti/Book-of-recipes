package com.example.bookofrecipes.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Recipe database model
 *
 * @property id Unique identifier of recipe
 * @property name Name of recipe
 * @property time Time to cook
 * @property description Description of recipe
 * @property image Base64 encoded image or url
 * @property favorite Is recipe favorite
 */
@Entity(tableName = "recipes")
data class Recipe(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val time: String? = null,
    val description: String? = null,
    val image: String? = null,
    var favorite: Boolean? = null
)