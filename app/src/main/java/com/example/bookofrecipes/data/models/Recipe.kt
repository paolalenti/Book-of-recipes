package com.example.bookofrecipes.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey

@Fts4(notIndexed = ["time", "image"])
@Entity(tableName = "recipes")
data class Recipe(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "rowid") val id: Long = 0,
    val name: String,
    val time: String? = null,
    val description: String? = null,
    val image: String? = null
)