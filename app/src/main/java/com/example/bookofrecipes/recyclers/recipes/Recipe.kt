package com.example.bookofrecipes.recyclers.recipes

import com.example.bookofrecipes.recyclers.steps.RecipeStep

data class Recipe(
    val id: Int,
    val name: String,
    val time: String,
    val ingredients: List<String>,
    val steps: List<RecipeStep>,
    val url: String
)