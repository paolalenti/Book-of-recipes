package com.example.bookofrecipes.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Index

@Entity(tableName = "ingredients", indices = [Index(value = ["name"], unique = true)])
data class Ingredient(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String
)