package com.example.bookofrecipes.recipes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey

@Fts4(notIndexed = ["time"])
@Entity(tableName = "recipes")
data class Recipe(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "rowid") val id: Long = 0,
    val name: String,
    val time: String?,
    val description: String?,
    val image: String?
)