package com.example.bookofrecipes.recipes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey

@Fts4(notIndexed = ["time"])
@Entity
data class Recipe(
    @PrimaryKey @ColumnInfo(name = "rowid") val id: Long,
    val name: String,
    val time: String?,
    val description: String?,
    val image: String?
)