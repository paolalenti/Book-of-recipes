package com.example.bookofrecipes.recyclers.recipes

data class Recipe(
    val id: Int,
    val name: String,
    val time: String,
    val ingredients: List<String>,
    val steps: List<String>,
    val url: String
)